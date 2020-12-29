package detailedTechnology.recipe;

import detailedTechnology.group.currentdone.Materials;
import detailedTechnology.group.currentdone.Ores;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class StoneMileRecipe {
    public static final ArrayList<Item> ingredients = new ArrayList<>();
    public static final ArrayList<Integer> ingredientNums = new ArrayList<>();
    public static final ArrayList<Item> products = new ArrayList<>();
    public static final ArrayList<Integer> productNums = new ArrayList<>();

    public static void addRecipe(Item ingredient,int ingredientNum,Item product, int productNum){
        ingredients.add(ingredient);
        ingredientNums.add(ingredientNum);
        productNums.add(productNum);
        products.add(product);
    }

    public static void tryMile(Inventory inventory){
        ItemStack inStack = inventory.getStack(0);
        ItemStack outStack = inventory.getStack(1);
        for (int i = 0; i < ingredients.size(); i++) {
            if(inStack.getName().getString().equals(ingredients.get(i).getName().getString())&&
                    inStack.getCount()>=ingredientNums.get(i)&&
                    (outStack.getName().getString().equals(Items.AIR.getName().getString())||
                    (outStack.getName().getString().equals(products.get(i).getName().getString())&&
                            outStack.getCount()+productNums.get(i)<=64))){
                if(inStack.getCount()==ingredientNums.get(i)){
                    inventory.setStack(0,Items.AIR.getDefaultStack());
                }else {
                    inStack.setCount(inStack.getCount()-ingredientNums.get(i));
                }
                if(outStack.getName().getString().equals(Items.AIR.getName().getString())){
                    inventory.setStack(1,products.get(i).getDefaultStack());
                    inventory.getStack(1).setCount(productNums.get(i));
                }else{
                    outStack.setCount(outStack.getCount()+productNums.get(i));
                }
                return;
            }
        }
    }


    public StoneMileRecipe(){
        addRecipe(Ores.tinOreBlock.asItem(),1,Ores.tinSharp,2);
        addRecipe(Ores.copperOreBlock.asItem(),1,Ores.copperSharp,2);
        addRecipe(Items.IRON_ORE,1,Ores.ironSharp,2);

        addRecipe(Ores.tinIngot,1,Ores.tinSharp,1);
        addRecipe(Ores.copperIngot,1,Ores.copperSharp,1);
        addRecipe(Items.IRON_INGOT,1,Ores.ironSharp,1);
        addRecipe(Ores.bronzeIngot,1,Ores.bronzeSharp,1);

        addRecipe(Ores.tinSharp,1,Ores.tinDust,1);
        addRecipe(Ores.copperSharp,1,Ores.copperDust,1);
        addRecipe(Ores.ironSharp,1,Ores.ironDust,1);
        addRecipe(Ores.bronzeSharp,1,Ores.bronzeDust,1);

        addRecipe(Items.BONE,1,Items.BONE_MEAL,6);
        addRecipe(Items.SUGAR_CANE,1,Items.SUGAR,2);
        addRecipe(Items.BRICK,1, Materials.brickDust,1);
    }

}
