package me.anngtv.medievalrpg.listeners;

import me.anngtv.medievalrpg.MedievalRPG;
import me.anngtv.medievalrpg.models.PlayerData;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDeathEvent;

public class RPGListener implements Listener {

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            PlayerData data = MedievalRPG.getInstance().getPlayerManager().getPlayerData(player.getUniqueId());
            
            // Strength increases damage
            double bonusDamage = data.getStrength() * 0.2;
            event.setDamage(event.getDamage() + bonusDamage);
        }

        if (event.getEntity() instanceof Player) {
            Player player = (Player) event.getEntity();
            PlayerData data = MedievalRPG.getInstance().getPlayerManager().getPlayerData(player.getUniqueId());

            // Constitution reduces damage
            double reduction = data.getConstitution() * 0.1;
            double finalDamage = Math.max(0.5, event.getDamage() - reduction);
            event.setDamage(finalDamage);
        }
    }

    @EventHandler
    public void onDeath(EntityDeathEvent event) {
        if (event.getEntity().getKiller() != null) {
            Player killer = event.getEntity().getKiller();
            PlayerData data = MedievalRPG.getInstance().getPlayerManager().getPlayerData(killer.getUniqueId());
            
            // Gain XP on kill
            data.addXp(20);
        }
    }
}
