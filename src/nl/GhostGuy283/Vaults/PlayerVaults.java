package nl.GhostGuy283.Vaults;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import net.md_5.bungee.api.ChatColor;
import net.minecraft.server.v1_8_R3.InventoryCrafting;

public class PlayerVaults implements CommandExecutor{

	//if(args[2].equalsIgnoreCase("EnabledMessage")){
	//						StringBuilder sb = new StringBuilder();
	//						for (int i = 0; i < args.length; i++){
	//						sb.append(args[i]).append(" ");
	//						}
	//						String allArgs = sb.toString().trim();
	//						plugin.new1.set("EnabledMessage", allArgs);
	//						p.sendMessage(ChatColor.GREEN+"Set EnabledMessages to: "+plugin.new1.get("EnabledMessage").toString());
	//					}

	Main plugin;
	 
	public PlayerVaults(Main instance) {
	plugin = instance;
	}
	 File newp = null;
	 FileConfiguration new2 = null;
 
	@SuppressWarnings("unused")
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(label.equalsIgnoreCase("PlayerVaults") || label.equalsIgnoreCase("pv")){
		String prefix = plugin.new1.get("Prefix").toString();
		if(sender instanceof Player){
			Player p = (Player) sender;
			if(args.length==0){
				sender.sendMessage(prefix+ChatColor.RED+"The command is: /playervaults <command>!");
				p.sendMessage(ChatColor.GREEN+"/PlayerVaults help");
			}
			if(args==null){
				sender.sendMessage(prefix+ChatColor.RED+"The command is: /playervaults <command>!");
				p.sendMessage(ChatColor.GREEN+"/PlayerVaults help");
			}
			if(args.length>=1){
				if(args[0].equalsIgnoreCase("config")){
					if(p.hasPermission("PlayerVaults.Config")){
					if(args.length==1){
						p.sendMessage(prefix+ChatColor.GREEN+" Command list:");
						p.sendMessage(ChatColor.GREEN+"/PlayerVaults config Language ");
						p.sendMessage(ChatColor.GREEN+"/PlayerVaults config VaultSize (to set the Size of the vault, WIP Do not use!!)");
					}
					if(args.length>=2){
						if(args[1].equalsIgnoreCase("Language")){
							if(args.length==2){
								p.sendMessage(prefix+ChatColor.GREEN+" Translation list:");
								p.sendMessage(ChatColor.RED+"EnabledMessage Prefix");	
							}
							if(args.length>=3){
							if(args[2].equalsIgnoreCase("EnabledMessage")){
								if(args.length==3){
									p.sendMessage(ChatColor.RED+"/PlayerVaults config language EnabledMessage <Enable Message>");	
								}
								if(args.length>=4){
														StringBuilder sb = new StringBuilder();
														for (int i = 3; i < args.length; i++){
														sb.append(args[i]).append(" ");
													}
														String allArgs = sb.toString().trim();
														allArgs = ChatColor.translateAlternateColorCodes('&', allArgs);
														plugin.new1.set("EnabledMessage", allArgs);
														p.sendMessage(ChatColor.GREEN+"Set EnabledMessages to: "+ChatColor.RESET+plugin.new1.get("EnabledMessage").toString());
														try {
															plugin.new1.save(plugin.newF);
														} catch (IOException e) {
															e.printStackTrace();
														}
								}
								}else if(args[2].equalsIgnoreCase("Prefix")){
									if(args.length>=4){
										StringBuilder sb2 = new StringBuilder();
										for (int i1 = 3; i1 < args.length; i1++){
										sb2.append(args[i1]).append(" ");
									}
										String allArgs1 = sb2.toString().trim();
										allArgs1 = ChatColor.translateAlternateColorCodes('&', allArgs1);
										plugin.new1.set("Prefix", allArgs1);
										p.sendMessage(ChatColor.GREEN+"Set Prefix to: "+ChatColor.RESET+plugin.new1.get("Prefix").toString());
										try {
											plugin.new1.save(plugin.newF);
										} catch (IOException e) {
											e.printStackTrace();
										}
				}
								}
							}
						}else if(args[1].equalsIgnoreCase("VaultSize")){
						if(args.length==3){
							File newC = new File(plugin.getDataFolder(), "Config_pv.yml");
							   FileConfiguration new2 = YamlConfiguration.loadConfiguration(newC);
							   if(newC.exists()){
								   try {
									   new2.set("Vault.size", args[2]);
									new2.save(newC);
									p.sendMessage(prefix+ChatColor.GREEN+" The Vaultsize is now: "+args[2]);
									p.sendMessage(ChatColor.RED+"All vaults are reseted!");
									File datafolder = new File(plugin.getDataFolder()+"//Players");
									if(datafolder.exists()){
										delete(datafolder);
									}
								} catch (IOException e) {
									e.printStackTrace();
								}
							   }else{
								   p.sendMessage(prefix+ChatColor.RED+" The config file cannot be found!");
							   }
						}else{
							p.sendMessage(prefix+ChatColor.GOLD+" Do /playervaults config vaultsize <Size>");
							p.sendMessage(ChatColor.BOLD+""+ChatColor.RED+"the number must be divisible by nine!");
						}
						}
					}
				}else{
					p.sendMessage(ChatColor.RED+"You don't have permissions!");
				}
				}
				else if(args[0].equalsIgnoreCase("help")){
					if(p.hasPermission("PlayerVaults.help")){
					p.sendMessage(prefix+ChatColor.GOLD+" Commands:");
					p.sendMessage(ChatColor.GREEN+"/PlayerVaults config");
					p.sendMessage(ChatColor.GREEN+"/PlayerVaults create");
					p.sendMessage(ChatColor.GREEN+"/PlayerVaults open");
					}else{
						p.sendMessage(ChatColor.RED+"You don't have permissions!");
					}
				}
				else if(args[0].equalsIgnoreCase("create")){
					if(p.hasPermission("PlayerVault.create")){
					//Creating the vault file
					File newB = new File(plugin.getDataFolder(), "Config_pv.yml");
					   FileConfiguration new3 = YamlConfiguration.loadConfiguration(newB);
					File Player = new File(plugin.getDataFolder()+"//Players");
					Player.mkdirs();
					this.newp = new File(plugin.getDataFolder()+"//Players", p.getUniqueId().toString());
					this.new2 = YamlConfiguration.loadConfiguration(newp);
					if(!newp.exists()){
						try {
							newp.createNewFile();
							p.sendMessage(prefix+ChatColor.GREEN+" Vault Created!");
							Inventory inv = Bukkit.createInventory(null, new3.getInt("Vault.size"), ChatColor.BLACK+"["+ChatColor.DARK_PURPLE+"Vault"+ChatColor.BLACK+"]");
							p.openInventory(inv);
						} catch (IOException e) {
							e.printStackTrace();
						}
						}else{
							p.sendMessage(prefix+ChatColor.RED+" You already have a vault!");
							p.sendMessage(ChatColor.GOLD+"Use /playervaults Open to open your Vault");
					}
					}else{
						p.sendMessage(ChatColor.RED+"You don't have permissions!");
					}
				}
				else if(args[0].equalsIgnoreCase("open")){
					if(p.hasPermission("PlayerVaults.open")){
					File newB = new File(plugin.getDataFolder(), "Config_pv.yml");
					   FileConfiguration new3 = YamlConfiguration.loadConfiguration(newB);
					this.newp = new File(plugin.getDataFolder()+"//Players", p.getUniqueId().toString());
					if(newp.exists()){
					p.sendMessage(ChatColor.GREEN+"Opening Vault!");
					Inventory inv = Bukkit.createInventory(null, new3.getInt("Vault.size"), ChatColor.BLACK+"["+ChatColor.DARK_PURPLE+"Vault"+ChatColor.BLACK+"]");
					try {
						restoreInventory(p.getPlayer(), inv);
					} catch (IOException e) {
						e.printStackTrace();
					}
					}else{
						p.sendMessage(prefix+ChatColor.RED+" You don't have a Vault! ");
						p.sendMessage(ChatColor.GREEN+"Create one first with /playervaults create");
					}
					
				}else{
					p.sendMessage(ChatColor.RED+"You don't have permissions!");
				}
				}
			}
		}else{
			sender.sendMessage(ChatColor.RED+"You need to be a player!");
		}
		}
		return false;
}
	@SuppressWarnings("unchecked")
    public void restoreInventory(Player p, Inventory i) throws IOException {
		this.newp = new File(plugin.getDataFolder()+"//Players", p.getUniqueId().toString());
        FileConfiguration c = YamlConfiguration.loadConfiguration(newp);
        ItemStack[] content = ((List<ItemStack>) c.get("Vault1.inventory.content")).toArray(new ItemStack[0]);        
        i.setContents(content);
        p.openInventory(i);
    }
	  public static void delete(File file)
		    	throws IOException{
		 
		    	if(file.isDirectory()){
		 
		    		//directory is empty, then delete it
		    		if(file.list().length==0){
		    			
		    		   file.delete();
		    		   System.out.println("Directory is deleted : " 
		                                                 + file.getAbsolutePath());
		    			
		    		}else{
		    			
		    		   //list all the directory contents
		        	   String files[] = file.list();
		     
		        	   for (String temp : files) {
		        	      //construct the file structure
		        	      File fileDelete = new File(file, temp);
		        		 
		        	      //recursive delete
		        	     delete(fileDelete);
		        	   }
		        		
		        	   //check the directory again, if empty then delete it
		        	   if(file.list().length==0){
		           	     file.delete();
		        	     System.out.println("Directory is deleted : " 
		                                                  + file.getAbsolutePath());
		        	   }
		    		}
		    		
		    	}else{
		    		//if file, then delete it
		    		file.delete();
		    		System.out.println("File is deleted : " + file.getAbsolutePath());
		    	}
		    }
}
