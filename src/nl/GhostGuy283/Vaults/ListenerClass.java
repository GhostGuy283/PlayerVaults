package nl.GhostGuy283.Vaults;

import java.io.File;
import java.io.IOException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.HumanEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import net.md_5.bungee.api.ChatColor;

public class ListenerClass implements Listener {
	Main plugin;
	 
	public ListenerClass(Main instance) {
		plugin = instance;
	}
	File newp = null;
	 FileConfiguration new2 = null;
	@EventHandler
	public void onInventoryClose(InventoryCloseEvent e) throws IOException{
		if(e.getInventory().getHolder()==null){
			if(e.getInventory().getName().equals(ChatColor.BLACK+"["+ChatColor.DARK_PURPLE+"Vault"+ChatColor.BLACK+"]")){
			Player p = (Player) e.getPlayer();
			p.sendMessage(ChatColor.GREEN+"Saved Vault!");
		saveInventory(e.getPlayer(), e.getInventory());
			}
		}
	}
	public void saveInventory(HumanEntity p, Inventory i) throws IOException {
		 this.newp = new File(plugin.getDataFolder()+"//Players", p.getUniqueId().toString());
	        FileConfiguration c = YamlConfiguration.loadConfiguration(newp);
	        c.set("Vault1.inventory.content", i.getContents());
	        c.save(newp);
	    }
}
