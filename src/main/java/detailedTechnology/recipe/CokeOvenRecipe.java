package detailedTechnology.recipe;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.group.currentdone.Materials;
import detailedTechnology.group.currentdone.Ores;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class CokeOvenRecipe {
    public static final ArrayList<Item> ingredients = new ArrayList<>();
    public static final ArrayList<Integer> ingredientNums = new ArrayList<>();
    public static final ArrayList<Item> products = new ArrayList<>();
    public static final ArrayList<Integer> productNums = new ArrayList<>();
    public static final ArrayList<Integer> creosoteAmounts = new ArrayList<>();
    public static final ArrayList<Integer> timeRequires = new ArrayList<>();

    public static void addRecipe(Item ingredient,int ingredientNum,Item product, int productNum,
                                 int creosoteAmount,int timeRequire){
        ingredients.add(ingredient);
        ingredientNums.add(ingredientNum);
        productNums.add(productNum);
        products.add(product);
        creosoteAmounts.add(creosoteAmount);
        timeRequires.add(timeRequire);
    }

    public static int tryCraft(Inventory inventory){
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
                    return creosoteAmounts.get(i);
                }else{
                    outStack.setCount(outStack.getCount()+productNums.get(i));
                    return creosoteAmounts.get(i);
                }
            }
        }
        return 0;
    }


    public CokeOvenRecipe(){
        addRecipe(Items.STRIPPED_BIRCH_LOG,1,Items.CHARCOAL,2,50,400);
        addRecipe(Items.STRIPPED_ACACIA_LOG,1,Items.CHARCOAL,2,50,400);
        addRecipe(Items.STRIPPED_DARK_OAK_LOG,1,Items.CHARCOAL,2,50,400);
        addRecipe(Items.STRIPPED_JUNGLE_LOG,1,Items.CHARCOAL,2,50,400);
        addRecipe(Items.STRIPPED_OAK_LOG,1,Items.CHARCOAL,2,50,400);
        addRecipe(Items.STRIPPED_SPRUCE_LOG,1,Items.CHARCOAL,2,50,400);

        addRecipe(Items.COAL,1,Materials.coke,1,25,600);
        addRecipe(Items.COAL_BLOCK,1, DetailedTechnology.cokeBlock.asItem(),1,225,5000);
    }

}
