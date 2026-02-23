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
import org.bukkit.event.player.PlayerJoinEvent;

import java.awt.*;
import java.util.UUID;

public class PlayerJoin implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {

        JDA jda = BotCreation.jda;

        Player player = event.getPlayer();
        UUID uuid = player.getUniqueId();

        String channelId = (String) BotCreation.getConfigValue("bot.ingame-channel");

        if (channelId == null) {
            HugeboxesBot.plugin.getLogger().warning((String) BotCreation.getConfigValue("bot.prefix") + "No ingame-channel found");
            return;
        }

        EmbedBuilder eb = new EmbedBuilder();
        eb.setColor(Color.GREEN);
        eb.setAuthor(player.getName() + " Has joined!", null, "http://cravatar.eu/avatar/" + uuid);

        jda.getTextChannelById(channelId).sendMessageEmbeds(eb.build()).queue();

        jda.getPresence().setActivity(Activity.playing("Online players: (" + (Bukkit.getOnlinePlayers().size()) + "/" + Bukkit.getMaxPlayers() + ")"));

    }

}
