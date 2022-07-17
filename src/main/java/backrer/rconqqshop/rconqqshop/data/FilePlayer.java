package backrer.rconqqshop.rconqqshop.data;

import backrer.rconqqshop.rconqqshop.RconQQShop;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FilePlayer {
    public static Plugin config = backrer.rconqqshop.rconqqshop.RconQQShop.getPlugin(backrer.rconqqshop.rconqqshop.RconQQShop.class);
    private static File FileData = new File(RconQQShop.getPlugin(RconQQShop.class).getDataFolder()+"//data");
    private static File PlayerData = new File(GetFileData().getAbsolutePath()+"\\date.yml");
    private static File GiftData = new File(RconQQShop.getPlugin(RconQQShop.class).getDataFolder().getAbsolutePath()+"\\gift.yml");
    private static FileConfiguration filc = YamlConfiguration.loadConfiguration(GetPlayerData());
    public static FileConfiguration GiftFilc = YamlConfiguration.loadConfiguration(GiftData);
    public static File GetFileGift(){
        return GiftData;
    }
    public static FileConfiguration GetGiftFilc(){
        return GiftFilc;
    }
    public static File GetFileData(){
        return FileData;
    }
    public static File GetPlayerData(){
        return PlayerData;
    }
    public static FileConfiguration GetFileFilc(){
        return filc;
    }
    //创建data文件夹
    public static void ReadFileData(){
        if(!GetFileData().exists()){
            GetFileData().mkdirs();
        }
    }
    //写配置文件函数
    public static void WritPlayerStringData(String prefix, String suffix){
        GetFileFilc().set(prefix,suffix);
    }
    public static void WritPlayerListData(String prefix, List suffix){
        GetFileFilc().set(prefix,suffix);
    }
    //读配置文件函数
    public static String getter_PlayerDataString(String prefix){
        return GetFileFilc().getString(prefix);
    }
    public static boolean getter_PlayerDatabool(String prefix){
        return GetFileFilc().getBoolean(prefix);
    }
    public static List getter_PlayerDataList(String prefix){
        return GetFileFilc().getStringList(prefix);
    }
    //重置文件
    public static void reloadYml(){

        try {
            FileReader fr = new FileReader(PlayerData);
            BufferedReader br = new BufferedReader(fr);
            br.readLine();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
