package ru.plugin.lifeondeath;

import org.bukkit.plugin.java.JavaPlugin;

public class LifeOnDeath extends JavaPlugin {

    private static LifeOnDeath instance;

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        getServer().getPluginManager().registerEvents(new DeathListener(this), this);
        getLogger().info("LifeOnDeath включён! При смерти будет отниматься жизнь.");
    }

    @Override
    public void onDisable() {
        getLogger().info("LifeOnDeath выключён.");
    }

    public static LifeOnDeath getInstance() {
        return instance;
    }
}
