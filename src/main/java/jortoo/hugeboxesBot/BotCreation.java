package jortoo.hugeboxesBot;

import jortoo.hugeboxesBot.commands.discord.status;
import jortoo.hugeboxesBot.events.discord.DiscordChat;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.bukkit.configuration.file.FileConfiguration;

import javax.security.auth.login.LoginException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class BotCreation {

    public static JDA jda;
    public static Guild guild;
    public static String guildId;

    private static Map<String, Object> configValues = new HashMap<>();

    public static Object getConfigValue(String path) { return configValues.get(path); }

    public static void initBot() throws LoginException {

        HugeboxesBot plugin = HugeboxesBot.plugin;
        FileConfiguration config = plugin.getConfig();
        String token = config.getString("bot.token");
        String prefix = config.getString("bot.console-prefix");

        Logger logger = plugin.getLogger();

        if (token == null || token.isEmpty()) {

            logger.warning(prefix + "No bot token has been found");
            return;

        }

        if (jda != null) {

            logger.warning(prefix + "Bot is already running");
            return;

        }

        configValues = config.getValues(true);

        try {

            JDABuilder jdaBuilder = JDABuilder.createDefault(token)
                    .setStatus(OnlineStatus.DO_NOT_DISTURB)
                    .setActivity(Activity.watching("Hugeboxes.minekeep.gg"))
                    .addEventListeners(new DiscordChat(), new status());

            for (GatewayIntent value : GatewayIntent.values())
                jdaBuilder.enableIntents(value);

            jda = jdaBuilder
                    .build()
                    .awaitReady();

            guild = jda.getGuildById(config.getString("bot.guild-id"));
            HugeboxesBot.loadCommands();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

}
