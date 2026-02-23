package jortoo.hugeboxesBot.commands.minecraft;

import jortoo.hugeboxesBot.BotCreation;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.event.HoverEvent;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class DiscordCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String s, @NotNull String @NotNull [] args) {

        if (!(sender instanceof Player player)) return false;

        String link = (String) BotCreation.getConfigValue("bot.discord-link");

        if (link == null || link.isEmpty()) {
            player.sendMessage("§cDiscord link not set in config!");
            return true;
        }

        Component message = MiniMessage.miniMessage().deserialize(
                "<blue><bold>DISCORD</bold></blue><dark_gray> ▪ <gray>Join our discord: </gray>"
        );

        Component clickPart = MiniMessage.miniMessage().deserialize("<blue><underlined>[CLICK!]</underlined>")
                .hoverEvent(HoverEvent.showText(MiniMessage.miniMessage().deserialize("<green>Click to join our Discord!</green>")))
                .clickEvent(ClickEvent.openUrl(link));

        Component finalMessage = message.append(clickPart);

        player.sendMessage(finalMessage);
        player.playSound(player, Sound.BLOCK_CHEST_OPEN, 1, 1.5f);
        return true;

    }
}
