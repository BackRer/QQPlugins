package backrer.rconqqshop.rconqqshop.cmd;

import backrer.rconqqshop.rconqqshop.Gui.GiftGui;
import backrer.rconqqshop.rconqqshop.Gui.ItemGui;
import backrer.rconqqshop.rconqqshop.data.FileEvent;
import backrer.rconqqshop.rconqqshop.data.FileGift;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;

public class rqs extends FileEvent implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length > 0){
            if (sender.hasPermission("RconQQShop.admin")){
                switch (args[0]){
                    case "help":
                        for (String ll : config.getConfig().getStringList("message.command.help")){
                            sender.sendMessage(config.getConfig().getString("message.prefix")+ll);
                        }
                        for (String ll : config.getConfig().getStringList("message.command.user")){
                            sender.sendMessage(config.getConfig().getString("message.prefix")+ll);
                        }
                        break;
                    case "addItem":
                        if (args.length == 3){
                            if (Material.getMaterial(args[2]) instanceof Material){
                                List<String> Reload_Item = Event_getter_PlayerList(args[1],"Item");
                                Reload_Item.add(args[2]);
                                Event_Setter_PlayerList(args[1],"Item",Reload_Item);
                                sender.sendMessage(config.getConfig().getString("message.command.AddItem"));
                            }else {
                                sender.sendMessage(config.getConfig().getString("message.command.AddItemWron"));
                            }
                        }else {
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.AddItemError"));
                        }
                        break;
                    case "listItem":
                        if (args.length == 2){
                            List<String> Reload_Item = Event_getter_PlayerList(args[1],"Item");
                            int a = 0;
                            if (Reload_Item.size() <= 0){
                                sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.ListItem"));
                            }else {
                                for (String ll : Reload_Item){
                                    a++;
                                    sender.sendMessage(config.getConfig().getString("message.prefix")+a+".物品:"+ll);
                                }
                                a = 0;
                            }
                        }else {
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.ListItemError"));
                        }
                        break;
                    case "removeItem":
                        if (args.length == 3){
                            List<String> Reload_Item = Event_getter_PlayerList(args[1],"Item");
                            Reload_Item.remove(args[2]);
                            Event_Setter_PlayerList(args[1],"Item",Reload_Item);
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.RemoveItem"));
                        }else {
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.RemoveItemError"));
                        }
                        break;
                    case "clearItem":
                        if (args.length == 2){
                            Event_Setter_PlayerList(args[1],"Item",new ArrayList<>());
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.ClearItem"));
                        }else {
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.ClearItemError"));
                        }
                        break;
                    case "gift":
                        if (sender instanceof Player){
                            Player player = ((Player) sender).getPlayer();
                            if (args.length == 2){
                                ItemGui.StartItemGui(player.getPlayer(),args[1]);
                            }else {
                                GiftGui.StartGiftGui(player.getPlayer());
                            }
                        }else {
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.senderError"));
                        }
                        break;
                    case "getItem":
                        if (sender instanceof Player){
                            Player player = ((Player) sender).getPlayer();
                            player.sendMessage(String.valueOf(player.getPlayer().getInventory().getItemInMainHand().getType()));
                        }else {
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.senderError"));
                        }
                        break;
                    case "giftAdd":
                        if (args.length == 3){
                            if (FileGift.File_Getter_Gift("Gift").contains(args[2])){
                                List<String> Reload_Gift = Event_getter_PlayerList(args[1],"Gift");
                                Reload_Gift.add(args[2]);
                                Event_Setter_PlayerList(args[1],"Gift",Reload_Gift);
                                sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.GiftAdd"));
                            }else {
                                sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.GiftNot"));
                            }
                        }else {
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.GiftAddError"));
                        }
                        break;
                    case "giftdelete":
                        if(args.length == 3){
                            List<String> Reload_Gift = Event_getter_PlayerList(args[1],"Gift");
                            Reload_Gift.remove(args[2]);
                            Event_Setter_PlayerList(args[1],"Gift",Reload_Gift);
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.Giftdelete"));
                        }else {
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.GiftdeleteError"));
                        }
                        break;
                    case "giftclear":
                        if (args.length == 2){
                            Event_Setter_PlayerList(args[1],"Gift",new ArrayList<>());
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.Clearclear"));
                        }else {
                            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.GiftclearError"));
                        }
                        break;
                    case "giftlist":
                            if (args.length == 2){
                                List<String> Reload_Gift = Event_getter_PlayerList(args[1],"Gift");
                                int a = 0;
                                if (Reload_Gift.size() <= 0){
                                    sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.Giftlist"));
                                }else {
                                    for (String ll : Reload_Gift){
                                        a++;
                                        sender.sendMessage(config.getConfig().getString("message.prefix")+a+".礼包:"+ll);
                                    }
                                    a = 0;
                                }
                            }else {
                                sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.GiftlistError"));
                            }
                        break;
                    default:
                        sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.error"));
                        break;
                }
            }else {
                sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.Permission"));
            }
        }else {
            sender.sendMessage(config.getConfig().getString("message.prefix")+config.getConfig().getString("message.command.error"));
        }
        return false;
    }
}
