package jortoo.hugeboxesBot;

import jortoo.hugeboxesBot.commands.DiscordCommand;
import jortoo.hugeboxesBot.commands.DonationCommand;
import jortoo.hugeboxesBot.events.minecraft.MinecraftChat;
import jortoo.hugeboxesBot.events.minecraft.PlayerJoin;
import jortoo.hugeboxesBot.events.minecraft.PlayerLeave;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;

public final class HugeboxesBot extends JavaPlugin {

    public static HugeboxesBot plugin;

    @Override
    public void onEnable() {

        plugin = this;

        saveDefaultConfig();

        try {

            BotCreation.initBot();
            plugin.getLogger().info(BotCreation.getConfigValue("bot.console-prefix") + "Bot Initialized");

        } catch (LoginException e) { e.printStackTrace(); }

        plugin.getCommand("donation").setExecutor(new DonationCommand());
        plugin.getCommand("discord").setExecutor(new DiscordCommand());

        getServer().getPluginManager().registerEvents(new MinecraftChat(), this);
        getServer().getPluginManager().registerEvents(new PlayerLeave(), this);
        getServer().getPluginManager().registerEvents(new PlayerJoin(), this);

    }

    @Override
    public void onDisable() {

        BotCreation.jda.shutdown();

        //try {
        //    Linking.linkedYml.save(Linking.linkFile);
        //} catch (IOException e) {
        //    throw new RuntimeException();
        //}

    }
}
