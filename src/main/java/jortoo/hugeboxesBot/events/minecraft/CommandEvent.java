package jortoo.hugeboxesBot.events.minecraft;

import jortoo.hugeboxesBot.BotCreation;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerCommandSendEvent;

import java.awt.*;
import java.util.UUID;

public class CommandEvent implements Listener {

    @EventHandler
    public void onCommand(PlayerCommandPreprocessEvent event) {

        Player sender = event.getPlayer();
        String command = event.getMessage();

        UUID uuid = sender.getUniqueId();

        JDA jda = BotCreation.jda;

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.decode((String) BotCreation.getConfigValue("bot.color")));
        eb.setAuthor(sender.getName() + " Ran the command: " + command, null,  "http://cravatar.eu/avatar/" + uuid);

        String commandLogChannel = (String) BotCreation.getConfigValue("bot.command-log-channel");
        jda.getTextChannelById(commandLogChannel).sendMessageEmbeds(eb.build()).queue();

    }

}
