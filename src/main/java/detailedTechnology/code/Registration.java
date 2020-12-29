package detailedTechnology.code;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Registration {

    public static String MOD_ID = "dt";

    public static String getIdName(String name){
        return name.toLowerCase().replaceAll(" ","_");
    }

    public static void item(String name, Item item){
        String idName = getIdName(name);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID,idName),item);
        System.out.println("\"item."+MOD_ID+"."+idName+"\":\""+name+"\",");
    }

    public static void block(String name, Block block){
        String idName = getIdName(name);
        Registry.register(Registry.BLOCK,new Identifier(MOD_ID,idName),block);
        System.out.println("\"block."+MOD_ID+"."+idName+"\":\""+name+"\",");
    }

    public static void blockWithItem(String name, Block block, ItemGroup itemGroup){
        String idName = getIdName(name);
        Registry.register(Registry.BLOCK,new Identifier(MOD_ID,idName),block);
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, idName),
                new BlockItem(block, new FabricItemSettings().group(itemGroup)));
        System.out.println("\"block."+MOD_ID+"."+idName+"\":\""+name+"\",");
    }

}
