package backrer.rconqqshop.rconqqshop;

import backrer.rconqqshop.rconqqshop.Gui.GiftGui;
import backrer.rconqqshop.rconqqshop.Gui.ItemGui;
import backrer.rconqqshop.rconqqshop.cmd.myjoin;
import backrer.rconqqshop.rconqqshop.cmd.rqs;
import backrer.rconqqshop.rconqqshop.data.FileEvent;
import backrer.rconqqshop.rconqqshop.data.FileGift;
import backrer.rconqqshop.rconqqshop.data.FilePlayer;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;

import static backrer.rconqqshop.rconqqshop.data.FilePlayer.config;

public final class RconQQShop extends JavaPlugin implements Listener{
    //Plugin config = backrer.rconqqshop.rconqqshop.RconQQShop.getPlugin(backrer.rconqqshop.rconqqshop.RconQQShop.class);
    @Override
    public void onEnable() {
        saveDefaultConfig();
        reloadConfig();
        //构建文件夹
        FilePlayer.ReadFileData();
        FileGift.GiftCreate();
        reloadConfig();
        //注册事件
        getServer().getPluginManager().registerEvents(this,this);
        getServer().getPluginManager().registerEvents(new GiftGui(),this);
        getServer().getPluginManager().registerEvents(new ItemGui(),this);
        //注册指令
        getCommand("rqs").setExecutor(new rqs());
        getCommand("myjoin").setExecutor(new myjoin());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
    //进入游戏事件
    @EventHandler
    public void onJoin(PlayerJoinEvent e){
        FileEvent.PlayerCreate(e.getPlayer());
        FileEvent.PlayerJoin(e.getPlayer());
    }
    //Gui监听事件
    @EventHandler
    public void OnGuiClick(InventoryClickEvent event){
        if (event.getView().getTitle().equalsIgnoreCase(config.getConfig().getString("message.GiftTitle"))){
            event.setCancelled(true);
            List<String> ReloadYaml = GiftGui.File_Getter_Gift("Gift");
            Player player = (Player) event.getWhoClicked();
            try {
                if (event.getCurrentItem().getType() == Material.getMaterial(config.getConfig().getString("Item.GiftMate"))){
                    String level = ReloadYaml.get(event.getRawSlot());
                    player.closeInventory();
                    ItemGui.StartItemGui(player,level);
                }
            }catch (NullPointerException ignored){

            }
        }
    }
}
