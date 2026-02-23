package jortoo.hugeboxesBot.events.minecraft;

import jortoo.hugeboxesBot.BotCreation;
import jortoo.hugeboxesBot.HugeboxesBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Activity;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.awt.*;
import java.util.UUID;

public class PlayerLeave implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event) {

        System.out.println("Left ----------------------");

        JDA jda = BotCreation.jda;

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        String channelId = (String) BotCreation.getConfigValue("bot.ingame-channel");

        if (channelId == null) {
            HugeboxesBot.plugin.getLogger().warning(BotCreation.getConfigValue("bot.prefix") + "No ingame-channel found");
            return;
        }

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.red);
        eb.setAuthor(player.getName() + " Has left", null, "http://cravatar.eu/avatar/" + uuid);

        jda.getTextChannelById(channelId).sendMessageEmbeds(eb.build()).queue();

        jda.getPresence().setActivity(Activity.playing("Online players: (" + (Bukkit.getOnlinePlayers().size() - 1) + "/" + Bukkit.getMaxPlayers() + ")"));

    }

}
