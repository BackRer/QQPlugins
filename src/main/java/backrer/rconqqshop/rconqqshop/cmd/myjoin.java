package backrer.rconqqshop.rconqqshop.cmd;

import backrer.rconqqshop.rconqqshop.Gui.GiftGui;
import backrer.rconqqshop.rconqqshop.data.FileEvent;
import backrer.rconqqshop.rconqqshop.data.FileGift;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.List;

import static backrer.rconqqshop.rconqqshop.Gui.GiftGui.ITem;
import static backrer.rconqqshop.rconqqshop.Gui.ItemGui.ItemPlayer;
import static backrer.rconqqshop.rconqqshop.data.FileGift.Gift_getter_List;
import static backrer.rconqqshop.rconqqshop.data.FileGift.Gift_getter_String;

public class myjoin extends FileEvent implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player){
            if (sender.hasPermission("rconqqshop.use")){
                if (args.length > 0){
                    if (args[0].equalsIgnoreCase("help")){
                        for (String ll : config.getConfig().getStringList("message.command.user")){
                            sender.sendMessage(config.getConfig().getString("message.prefix")+ll);
                        }
                    }else if (args[0].equalsIgnoreCase("gift")){
                        if (Event_getter_PlayerList(((Player) sender).getPlayer().getName(),"Gift").size() > 0){
                            StartGiftGui(((Player) sender).getPlayer());
                            PlayerClearGiftAndItem(((Player) sender).getPlayer(),"Gift");
                        }else {
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.GiftOpenError"));
                        }
                    }else if (args[0].equalsIgnoreCase("item")){
                        if (Event_getter_PlayerList(((Player) sender).getPlayer().getName(),"Item").size() > 0){
                            ItemPlayer(((Player) sender).getPlayer());
                            PlayerClearGiftAndItem(((Player) sender).getPlayer(),"Item");
                        }else {
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.ItemOpenError"));
                        }
                    }else {
                        sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.erroruser"));
                    }
                }else {
                    sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.erroruser"));
                }
            }else {
                sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.Permission"));
            }
        }else {
            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.senderError"));
        }

        return false;
    }
    public static void StartGiftGui(Player player){
        List<String> YamlReload = Event_getter_PlayerList(player.getName(),"Gift");
        Inventory inventory1 = Bukkit.createInventory(player,54,config.getConfig().getString("message.GiftTitle"));;
        //player.sendMessage(String.valueOf(YamlReload));
        for (int a = 0; a < YamlReload.size(); a++){
            inventory1.setItem(a,ITem(Gift_getter_String("Gift."+YamlReload.get(a)+".name"),Gift_getter_List("Gift."+YamlReload.get(a)+".lore")));
        }
        player.openInventory(inventory1);
    }
}
