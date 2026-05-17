package me.anngtv.medievalrpg;

import me.anngtv.medievalrpg.commands.RPGCommand;
import me.anngtv.medievalrpg.listeners.RPGListener;
import me.anngtv.medievalrpg.managers.GUIManager;
import me.anngtv.medievalrpg.managers.LocaleManager;
import me.anngtv.medievalrpg.managers.PlayerManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MedievalRPG extends JavaPlugin {

    private static MedievalRPG instance;
    private PlayerManager playerManager;
    private LocaleManager localeManager;
    private GUIManager guiManager;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();

        this.localeManager = new LocaleManager();
        this.localeManager.loadLanguages();

        this.guiManager = new GUIManager();

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

    public LocaleManager getLocaleManager() {
        return localeManager;
    }

    public GUIManager getGuiManager() {
        return guiManager;
    }
}
