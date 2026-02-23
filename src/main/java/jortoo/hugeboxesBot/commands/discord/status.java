package jortoo.hugeboxesBot.commands.discord;

import jortoo.hugeboxesBot.BotCreation;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.events.interaction.command.SlashCommandInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class status extends ListenerAdapter {

    @Override
    public void onSlashCommandInteraction(@NotNull SlashCommandInteractionEvent event) {

        String command = event.getName();
        JDA jda = BotCreation.jda;

        switch (command) {

            case "status" -> {

                Runtime runtime = Runtime.getRuntime();

                int playerCount = Bukkit.getOnlinePlayers().size();
                int maxPlayers = Bukkit.getMaxPlayers();

                double[] tps = Bukkit.getTPS();
                int cores = runtime.availableProcessors();

                long maxRam = runtime.maxMemory();
                long totalRam = runtime.totalMemory();
                long freeRam = runtime.freeMemory();
                long usedRam = (totalRam - freeRam);

                EmbedBuilder eb = new EmbedBuilder();
                eb.setColor(Color.decode((String) BotCreation.getConfigValue("bot.color")));
                eb.setTitle("Server Status");
                eb.addField("Ram Usage:", "> 🔝 Max Ram: "+ (maxRam / 1024 / 1024) + " MB\n> 💯 Total Ram: " + (totalRam / 1024 / 1024) + " MB\n" + "> 🆓 Free Ram: "+ (freeRam / 1024 / 1024) + " MB\n> 🗑️ Used Ram: " + (usedRam / 1024 / 1024) + " MB", false);
                eb.addField("TPS:",
                        "> 📈 1m: " + String.format("%.2f", tps[0]) +
                                " | 5m: " + String.format("%.2f", tps[1]) +
                                " | 15m: " + String.format("%.2f", tps[2]),
                        false
                );
                eb.addField("Players:", "> 👥 Online players: (" + playerCount + "/" + maxPlayers + ")\n> 🆕 Unique Players: " + Bukkit.getOfflinePlayers().length, false);

                event.replyEmbeds(eb.build()).queue();


            }

        }

    }
}
