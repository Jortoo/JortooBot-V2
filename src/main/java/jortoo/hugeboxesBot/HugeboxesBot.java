package jortoo.hugeboxesBot;

import jortoo.hugeboxesBot.commands.DonationCommand;
import org.bukkit.plugin.java.JavaPlugin;

import javax.security.auth.login.LoginException;
import java.io.IOException;

public final class HugeboxesBot extends JavaPlugin {

    public static HugeboxesBot plugin;

    @Override
    public void onEnable() {

        plugin = this;

        saveDefaultConfig();

        plugin.getCommand("donation").setExecutor(new DonationCommand());

        getServer().getPluginManager().registerEvents(new MinecraftChat(), this);

        try {

            BotCreation.initBot();
            plugin.getLogger().info(BotCreation.getConfigValue("bot.console-prefix") + "Bot Initialized");

        } catch (LoginException e) { e.printStackTrace(); }

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
