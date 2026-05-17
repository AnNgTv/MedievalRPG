package me.anngtv.medievalrpg;

import me.anngtv.medievalrpg.commands.RPGCommand;
import me.anngtv.medievalrpg.listeners.RPGListener;
import me.anngtv.medievalrpg.managers.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MedievalRPG extends JavaPlugin {

    private static MedievalRPG instance;
    private PlayerManager playerManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        this.playerManager = new PlayerManager();
        this.playerManager.loadAll();

        getCommand("rpg").setExecutor(new RPGCommand());
        getServer().getPluginManager().registerEvents(new RPGListener(), this);

        getLogger().info("MedievalRPG by AnNgTv enabled! Supporting 1.8 - 1.21.");
    }

    @Override
    public void onDisable() {
        if (playerManager != null) {
            playerManager.saveAll();
        }
    }

    public static MedievalRPG getInstance() {
        return instance;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }
}
