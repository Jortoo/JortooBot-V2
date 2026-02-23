package jortoo.hugeboxesBot.events.minecraft;

import jortoo.hugeboxesBot.BotCreation;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.awt.*;
import java.util.UUID;

public class DeathEvent implements Listener {

    @EventHandler
    public void onKill(PlayerDeathEvent event) {

        Player attacker = event.getPlayer().getKiller();
        Player victim = event.getPlayer();

        UUID uuid = attacker.getUniqueId();

        JDA jda = BotCreation.jda;
        EmbedBuilder eb = new EmbedBuilder();

        eb.setColor(Color.decode((String) BotCreation.getConfigValue("bot.color")));
        eb.setAuthor(attacker.getName() + " has killed: " + victim.getName(), null, "http://cravatar.eu/avatar/" + uuid);

        String killLogChannel = (String) BotCreation.getConfigValue("bot.kill-log-channel");
        jda.getTextChannelById(killLogChannel).sendMessageEmbeds(eb.build()).queue();


    }

}
