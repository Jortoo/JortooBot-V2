package jortoo.hugeboxesBot;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.awt.*;
import java.util.UUID;

public class MinecraftChat implements Listener {

    @EventHandler
    public void onChat(AsyncChatEvent event) {

        if (event.isCancelled()) {
            return;
        }

        MiniMessage mm = MiniMessage.miniMessage();
        String message = mm.serialize(event.message());

        String ingameChannel = (String) BotCreation.getConfigValue("bot.ingame-channel");

        if (ingameChannel == null) {
            HugeboxesBot.plugin.getLogger().warning(BotCreation.getConfigValue("console-prefix") + "No ingame channel has been found!");
            return;
        }

        if (message.charAt(0) == '!') return;

        if (message.contains("@")) message.replaceAll("@", "");

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        JDA jda = BotCreation.jda;
        EmbedBuilder eb = new EmbedBuilder();

        String color = (String) BotCreation.getConfigValue("bot.color");

        eb.setColor(Color.decode(color));
        eb.setAuthor(player.getName() + " ‣ "+ message, null, "http://cravatar.eu/avatar/" + uuid);

        jda.getTextChannelById(ingameChannel).sendMessageEmbeds(eb.build()).queue();

    }

}
