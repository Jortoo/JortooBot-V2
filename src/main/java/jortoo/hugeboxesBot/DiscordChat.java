package jortoo.hugeboxesBot;


import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Bukkit;

public class DiscordChat extends ListenerAdapter {

    @Override
    public void onMessageReceived(MessageReceivedEvent event) {

        if (event.getAuthor().isBot()) return;

        String ingameChannel = (String) BotCreation.getConfigValue("bot.ingame-channel");

        if (ingameChannel == null) {
            HugeboxesBot.plugin.getLogger().warning(BotCreation.getConfigValue("bot.console-prefix") + "No ingame channel has been found!");
            return;
        }

        if (!event.getMessage().getChannelId().equals(ingameChannel)) return;

        String message = event.getMessage().getContentRaw();
        String user = event.getAuthor().getName();

        MiniMessage mm = MiniMessage.miniMessage();
        Component mcMessage = mm.deserialize("<#5493F4><bold>DISCORD</bold> <white>" + user + " <dark_gray>▪ <gray>7" + message);

        Bukkit.getScheduler().runTask(HugeboxesBot.plugin, () -> { Bukkit.broadcast(mcMessage); });

    }


}
