package jortoo.hugeboxesBot.commands;

import jortoo.hugeboxesBot.BotCreation;
import jortoo.hugeboxesBot.HugeboxesBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.JDA;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

import java.awt.*;
import java.util.UUID;
import java.util.logging.Logger;

public class DonationCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        Logger logger = HugeboxesBot.plugin.getLogger();

        Object consolePrefix = BotCreation.getConfigValue("console-prefix");
        if (args.length < 3) {
            logger.warning(consolePrefix + "Command failure, not enough arguments!");
            return false;
        }

        String packageName = args[1];
        String player = args[0];
        String amount = args[2];

        String donationChannel = (String) BotCreation.getConfigValue("bot.donation-channel");

        if (donationChannel == null) {
            logger.warning(consolePrefix + "No donation channel found!");
            return false;
        }

        JDA jda = BotCreation.jda;
        EmbedBuilder eb = new EmbedBuilder();

        String color = (String) BotCreation.getConfigValue("bot.color");

        UUID uuid = Bukkit.getPlayer(player).getUniqueId();

        eb.setColor(Color.decode(color));
        eb.setAuthor(" >> Store Purchase! << ");
        eb.addField("Thanks for your purchase!" ,"\n > **"+ player +"** Bought: `" + packageName + "`! \n > **Amount: **$ " + amount + "\n", false);
        eb.setThumbnail("http://cravatar.eu/avatar/" + uuid);
        eb.setFooter("Buying a package on the store helps keeping the server alive!");

        jda.getTextChannelById(donationChannel).sendMessageEmbeds(eb.build()).queue();

        return true;
    }

}
