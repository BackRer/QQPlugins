package backrer.rconqqshop.rconqqshop.Gui;

import backrer.rconqqshop.rconqqshop.data.FileGift;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.List;


public class GiftGui extends FileGift implements Listener{
    static Inventory inventory = null;
    //初始化Gui
    private static Inventory GiftGuiStart(Player player){
        return inventory = Bukkit.createInventory(player,54,config.getConfig().getString("message.GiftTitle"));
    }
    //物品线程
    public static ItemStack ITem(String name,List lore){
        ItemStack itemStack = new ItemStack(Material.getMaterial(config.getConfig().getString("Item.GiftMate")));
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(name);
        itemMeta.setLore(lore);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    //生成Gui
    public static void StartGiftGui(Player player){
        List<String> YamlReload = File_Getter_Gift("Gift");
        Inventory inventory1 = GiftGuiStart(player);
        //player.sendMessage(String.valueOf(YamlReload));
        for (int a = 0; a < YamlReload.size(); a++){
            inventory1.setItem(a,ITem(Gift_getter_String("Gift."+YamlReload.get(a)+".name"),Gift_getter_List("Gift."+YamlReload.get(a)+".lore")));
        }
        player.openInventory(inventory1);
    }

}
