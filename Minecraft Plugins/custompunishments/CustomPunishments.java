package me.riskings.custompunishments;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;


/**
 * Created by Joel on 7/29/2015.
 */
public class CustomPunishments extends JavaPlugin implements CommandExecutor {
    public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
        Player player = (Player) sender;

        //WARN

        if(cmd.getName().equalsIgnoreCase("warn")){
            if(args.length == 0 ){
                player.sendMessage(ChatColor.RED + "Error: Please use the correct format! /warn <player> <reason>");
                return true;
            }

            Player target = Bukkit.getServer().getPlayer(args[0]);
            if(target == null){
                player.sendMessage(ChatColor.RED + "Error: Player, " + target.getName() + ", could not be found!");
                return true;
            }

            StringBuilder str = new StringBuilder();
            for(int i = 1; i < args.length; i++){
                str.append(args[i] + " ");
            }

            String warnMessage = str.toString();
            if(args.length == 0){
                player.sendMessage(ChatColor.RED + "Error: Please specify a reason!");
                return true;
            }
            if(args.length > 1){
                target.sendMessage(ChatColor.GOLD + "" + ChatColor.BOLD + "" + ChatColor.UNDERLINE + "You have been warned by: " + sender.getName()
                        + ChatColor.RESET +
                        ChatColor.RED + "\n" + ChatColor.BOLD + warnMessage);
            }


        }

        // KICK
        if(cmd.getName().equalsIgnoreCase("kick")) {
            if (args.length == 0) {
                player.sendMessage(ChatColor.RED + "/kick <name> <reason>");
                return true;
            }
            Player target = Bukkit.getServer().getPlayer(args[0]);
            if (target == null) {
                player.sendMessage(ChatColor.RED + args[0] + " cannot be found!");
                return true;
            }
            StringBuilder str = new StringBuilder();
            for (int i = 1; i < args.length; i++) {
                str.append(args[i] + " ");
            }

            String kickMessage = str.toString();
            if (args.length == 1) {
                player.sendMessage(ChatColor.RED + "Please specify a reason");
                return true;
            }
            if (args.length >= 2) {
                target.kickPlayer(ChatColor.RED + "You have been kicked by: " + ChatColor.AQUA + player.getName() + ChatColor.GOLD + "\n" + kickMessage);
                Bukkit.broadcastMessage(
                        ChatColor.AQUA + "" + ChatColor.BOLD + player.getDisplayName() + ChatColor.RESET +
                                ChatColor.GOLD + ">>" + " Kicked " + ">>" +
                                ChatColor.AQUA + target.getName() + ChatColor.GOLD +
                                ">>" + ChatColor.AQUA + kickMessage
                );
            }
        }
        return true;
    }
}
