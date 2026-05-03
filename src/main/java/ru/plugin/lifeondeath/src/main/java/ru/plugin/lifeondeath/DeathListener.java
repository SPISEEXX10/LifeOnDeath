package ru.plugin.lifeondeath;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeInstance;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathListener implements Listener {

    private final LifeOnDeath plugin;

    public DeathListener(LifeOnDeath plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();

        double hpToRemove = plugin.getConfig().getDouble("hp-remove-on-death", 2.0);
        double minHp = plugin.getConfig().getDouble("min-hp", 2.0);

        AttributeInstance maxHealthAttr = player.getAttribute(Attribute.GENERIC_MAX_HEALTH);
        if (maxHealthAttr == null) return;

        double currentMax = maxHealthAttr.getBaseValue();
        double newMax = Math.max(minHp, currentMax - hpToRemove);

        maxHealthAttr.setBaseValue(newMax);

        int hearts = (int)(newMax / 2);
        player.sendMessage("§c❤ У тебя осталось сердец: " + hearts);

        if (newMax <= minHp) {
            player.sendMessage("§4⚠ Это твоё последнее сердце! Будь осторожен!");
        }
    }
}
