package backrer.rconqqshop.rconqqshop.Gui;

import backrer.rconqqshop.rconqqshop.data.FileEvent;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class ItemGui extends GiftGui {
    //初始化ItemGui
    public static void StartItemGui(Player player,String name){
        if (Gift_getter_String("Gift."+name+".name") == null){
            player.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.GiftItemGuiError"));
        }else {
            List<String> ListYaml = File_Getter_Gift("Gift."+name+".Item");
            Inventory inventory1 = Bukkit.createInventory(player,54,player.getDisplayName()+config.getConfig().getString("message.GiftItemTile"));
            for (int a = 0; a < ListYaml.size(); a++){
                //inventory1.setItem(a,new ItemStack(Material.DIAMOND));
                inventory1.setItem(a,ItemGuiStack(
                        Gift_getter_String("Gift."+name+".Item."+ListYaml.get(a)+".mate"),
                        Gift_getter_String("Gift."+name+".Item."+ListYaml.get(a)+".displayname"),
                        Gift_getter_List("Gift."+name+".Item."+ListYaml.get(a)+".lore")
                ));
            }
            player.openInventory(inventory1);
        }
    }
    //初始化玩家物品gui
    public static void ItemPlayer(Player player){
        Inventory inventory1 = Bukkit.createInventory(player,54,config.getConfig().getString("message.ItemTitle"));
        List<String> Reload_Item = FileEvent.Event_getter_PlayerList(player.getPlayer().getName(),"Item");
        for (int a = 0;a < Reload_Item.size();a++){
            inventory1.setItem(a,new ItemStack(Material.getMaterial(Reload_Item.get(a))));
        }
        player.openInventory(inventory1);
    }
    //物品线程
    private static ItemStack ItemGuiStack(String _Material, String name, List<String> lore){
        ItemStack itemStack = new ItemStack(Material.getMaterial(_Material));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
}
