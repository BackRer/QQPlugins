package backrer.rconqqshop.rconqqshop.data;

import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileEvent extends FilePlayer{
    //玩家数据Reload
    public static String Event_getter_PlayerString(String player,String prefix){
        return getter_PlayerDataString("Player."+player+"."+prefix);
    }
    public static List Event_getter_PlayerList(String player, String prefix){
        return getter_PlayerDataList("Player."+player+"."+prefix);
    }
    //玩家数据编写
    public static void Event_Setter_PlayerString(String player,String prefix,String suffix){
        WritPlayerStringData("Player."+player+"."+prefix,suffix);
        //reloadYml();
        try {
            GetFileFilc().save(GetPlayerData());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void Event_Setter_PlayerList(String player,String prefix,List suffix){
        WritPlayerListData("Player."+player+"."+prefix,suffix);
        Event_Setter_PlayerString(player,"On","true");
        //reloadYml();
        try {
            GetFileFilc().save(GetPlayerData());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void Event_Clear_PlayerList(String player,String prefix,List suffix){
        WritPlayerListData("Player."+player+"."+prefix,suffix);
        Event_Setter_PlayerString(player,"On","false");
        try {
            GetFileFilc().save(GetPlayerData());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //创建玩家基础数据
    public static void PlayerCreate(Player player){
        if (getter_PlayerDataString("Player."+player.getName()+".OnJoin") == null){
            WritPlayerStringData("Player."+player.getName()+".OnJoin","false");
            WritPlayerListData("Player."+player.getName()+".Item",new ArrayList<>());
            WritPlayerListData("Player."+player.getName()+".Gift",new ArrayList<>());
            WritPlayerStringData("Player."+player.getName()+".On","false");
            try {
                GetFileFilc().save(GetPlayerData());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
    //玩家上线时候处理的事件
    public static void PlayerJoin(Player player){
        reloadYml();
        List<String> ReloadItem = Event_getter_PlayerList(player.getName(),"Item");
        List<String> RelaodGift = Event_getter_PlayerList(player.getName(),"Gift");
        if (ReloadItem.size() > 0 || RelaodGift.size() > 0){
            List<String> Reloadmessage = config.getConfig().getStringList("message.command.JoinMessage");
            for (String ll : Reloadmessage){
                ll = ll.replaceAll("/item_list_size/", String.valueOf(ReloadItem.size()));
                ll = ll.replaceAll("/gift_list_size/",String.valueOf(RelaodGift.size()));
                player.sendMessage(ll);
            }
        }
    }
    public static void PlayerClearGiftAndItem(Player player,String or){
        if (or.equalsIgnoreCase("Gift")){
            Event_Setter_PlayerList(player.getName(),"Gift",new ArrayList<>());
        }else if (or.equalsIgnoreCase("Item")){
            Event_Setter_PlayerList(player.getName(),"Item",new ArrayList<>());
        }
    }
}
