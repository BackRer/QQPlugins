package backrer.rconqqshop.rconqqshop.data;

import org.bukkit.entity.Player;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileGift extends FilePlayer {
    //加载配置所有子节点
    public static List<String> File_Getter_Gift(String name){
        return new ArrayList<>(GetGiftFilc().getConfigurationSection(name).getKeys(false));
    }
    public static void GiftCreate(){
        if (Gift_getter_String("Gift.Text.name") == null){
            List<String> lore = new ArrayList<>();
            Gift_setter_String("Gift.Text.name","Demo");
            Gift_setter_List("Gift.Text.lore",lore);
            Gift_setter_String("Gift.Text.Item.Item1.mate","BOW");
            Gift_setter_String("Gift.Text.Item.Item1.displayname","This A BOW!");
            Gift_setter_Int("Gift.Text.Item.Item1.size",1);
            lore.add("lore1");
            lore.add("lore2");
            Gift_setter_List("Gift.Text.Item.Item1.lore",lore);
            Gift_setter_String("Gift.Text.Item.Item2.mate","BOW");
            Gift_setter_String("Gift.Text.Item.Item2.displayname","This A BOW Two!");
            Gift_setter_Int("Gift.Text.Item.Item2.size",1);
            lore.add("lore1");
            lore.add("lore2");
            Gift_setter_List("Gift.Text.Item.Item2.lore",lore);
        }
    }
    //礼包属性修改函数
    public static void Gift_setter_String(String prefix,String suffix){
        GetGiftFilc().set(prefix,suffix);
        try {
            GetGiftFilc().save(GetFileGift());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void Gift_setter_List(String prefix, List suffix){
        GetGiftFilc().set(prefix,suffix);
        try {
            GetGiftFilc().save(GetFileGift());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void Gift_setter_Int(String prefix,int suffix){
        GetGiftFilc().set(prefix,suffix);
        try {
            GetGiftFilc().save(GetFileGift());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //礼包属性读取函数
    public static String Gift_getter_String(String prefix){
        return GetGiftFilc().getString(prefix);
    }
    public static List<String> Gift_getter_List(String prefix){return GetGiftFilc().getStringList(prefix);}
    public static int Gift_getter_int(String prefix){return GetGiftFilc().getInt(prefix);}
}
