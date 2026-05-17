package me.anngtv.medievalrpg.commands;

import me.anngtv.medievalrpg.MedievalRPG;
import me.anngtv.medievalrpg.models.PlayerData;
import me.anngtv.medievalrpg.models.RPGClass;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;

public class RPGCommand implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("Only players can use this command!");
            return true;
        }

        Player player = (Player) sender;
        PlayerData data = MedievalRPG.getInstance().getPlayerManager().getPlayerData(player.getUniqueId());

        if (args.length == 0 || args[0].equalsIgnoreCase("help")) {
            sendHelp(player);
            return true;
        }

        if (args[0].equalsIgnoreCase("stats")) {
            sendStats(player, data);
            return true;
        }

        if (args[0].equalsIgnoreCase("class")) {
            if (args.length < 2) {
                player.sendMessage(ChatColor.YELLOW + "Usage: /rpg class <warrior|mage|paladin|thief>");
                return true;
            }
            try {
                RPGClass chosen = RPGClass.valueOf(args[1].toUpperCase());
                data.setRpgClass(chosen);
                player.sendMessage(ChatColor.GREEN + "You are now a " + ChatColor.GOLD + chosen.getDisplayName() + "!");
            } catch (IllegalArgumentException e) {
                player.sendMessage(ChatColor.RED + "Invalid class! Choices: Warrior, Mage, Paladin, Thief.");
            }
            return true;
        }

        return false;
    }

    private void sendHelp(Player player) {
        player.sendMessage(ChatColor.GOLD + "=== MedievalRPG Help ===");
        player.sendMessage(ChatColor.YELLOW + "/rpg stats " + ChatColor.WHITE + "- View your RPG stats.");
        player.sendMessage(ChatColor.YELLOW + "/rpg class <type> " + ChatColor.WHITE + "- Choose your class.");
    }

    private void sendStats(Player player, PlayerData data) {
        player.sendMessage(ChatColor.GOLD + "=== Your RPG Stats ===");
        player.sendMessage(ChatColor.YELLOW + "Class: " + ChatColor.WHITE + data.getRpgClass().getDisplayName());
        player.sendMessage(ChatColor.YELLOW + "Level: " + ChatColor.WHITE + data.getLevel());
        player.sendMessage(ChatColor.YELLOW + "XP: " + ChatColor.WHITE + data.getXp() + "/" + (data.getLevel() * 100));
        player.sendMessage(ChatColor.YELLOW + "Strength: " + ChatColor.WHITE + data.getStrength());
        player.sendMessage(ChatColor.YELLOW + "Dexterity: " + ChatColor.WHITE + data.getDexterity());
        player.sendMessage(ChatColor.YELLOW + "Intelligence: " + ChatColor.WHITE + data.getIntelligence());
        player.sendMessage(ChatColor.YELLOW + "Constitution: " + ChatColor.WHITE + data.getConstitution());
    }
}
