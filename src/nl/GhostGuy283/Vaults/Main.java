package nl.GhostGuy283.Vaults;

import java.io.File;
import java.io.IOException;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class Main extends JavaPlugin{
	//Language Config:
	   File newF = null;
	   FileConfiguration new1 = null;
	String prefix =  ""+ChatColor.RED+"["+ChatColor.DARK_PURPLE+"PlayerVaults"+ChatColor.RED+"]";
	String EnabledMessage = "[PlayerVaults] Enabled Succesfull!";
	String WrongCommand = "[PlayerVaults] Wrong command! use /PlayerVaults help";
	String HelpMessage = "Use /PlayerVaults help";
	
	public void onEnable() {
	   // Vars
	   PluginManager pm = this.getServer().getPluginManager();
	   pm.addPermission(new Permission("PlayerVaults.config"));
	   pm.addPermission(new Permission("PlayerVaults.help"));
	   pm.addPermission(new Permission("PlayerVaults.create"));
	   pm.addPermission(new Permission("PlayerVaults.open"));
	   //Events
	   pm.registerEvents(new ListenerClass(this), this);
	   //Commands
	   getCommand("PlayerVaults").setExecutor(new PlayerVaults(this));
	   getCommand("pv").setExecutor(new PlayerVaults(this));
	   //Setting config
	   File newC = new File(this.getDataFolder(), "Config_pv.yml");
	   FileConfiguration new2 = YamlConfiguration.loadConfiguration(newC);
	   if(!newC.exists()) {
		   try {
			newC.createNewFile();
			new2.set("Vault.size", 36);
			new2.save(newC);
		} catch (IOException e) {
			e.printStackTrace();
		}
	   }
	   //Language.yml
	   this.newF = new File(this.getDataFolder(), "language.yml");
	   this.new1 = YamlConfiguration.loadConfiguration(newF);
	   //setting Language.yml
	   if(!newF.exists()){
	   new1.set("Prefix", prefix);
	   new1.set("EnabledMessage", EnabledMessage);
	   new1.set("WrongCommand", WrongCommand);
	   new1.set("HelpMessage", HelpMessage);
	   }
	   //saving
	   try {
		new1.save(newF);
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   //Enabled Succesfully
	   System.out.println(new1.get("EnabledMessage"));
   }
   @Override
public void onDisable() {
	   this.getLogger().info("Saving the config!");
	   try {
			new1.save(newF);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   this.getLogger().info("Disabled!");
}
}