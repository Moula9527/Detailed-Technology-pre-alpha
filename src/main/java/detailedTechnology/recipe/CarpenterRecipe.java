package detailedTechnology.recipe;

import detailedTechnology.group.Machines;
import detailedTechnology.group.currentdone.Armors;
import detailedTechnology.group.currentdone.Materials;
import detailedTechnology.group.currentdone.Ores;
import detailedTechnology.group.currentdone.Tools;
import detailedTechnology.recipe.general.ShapelessRecipe;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class CarpenterRecipe {

    public static final ArrayList<ShapelessRecipe> CONTENTS = new ArrayList<>();
    public static final ArrayList<String> TOOL_TYPE = new ArrayList<>();
    public static final ArrayList<Integer> TOOL_LEVEL = new ArrayList<>();

    public void addRecipe(String toolName,int toolLevel,Item[] ingredients, int[] ingredientsNum, Item result, int resultNum) {
        TOOL_TYPE.add(toolName);
        TOOL_LEVEL.add(toolLevel);
        CONTENTS.add(new ShapelessRecipe(ingredients,ingredientsNum,result,resultNum));
    }

    public static boolean[] newBooleanArrayWithFalse(int length)
    {
        boolean[] result = new boolean[length];
        for(int i=0;i<length;i++) result[i]=false;
        return result;
    }

    public static int getRecipeId(Inventory inventory){
        int stackCount = 0;
        String[] itemNames = new String[9];
        int[] itemNums = new int[9];
        ShapelessRecipe content;
        boolean[] included1,included2;
        int checkNum;
        String resultItemName = inventory.getStack(9).getName().getString();
        int resultItemNum = inventory.getStack(9).getCount();

        for(int i=0;i<9;i++) {
            if(!inventory.getStack(i).getName().equals(Blocks.AIR.getName())){
                itemNames[stackCount] = inventory.getStack(i).getName().getString();
                itemNums[stackCount] = inventory.getStack(i).getCount();
                stackCount++;
            }
        }

        for (int i=0;i<CONTENTS.size();i++) {
            content = CONTENTS.get(i);
            if (content.ingredients.length == stackCount &&
                    (resultItemName.equals(content.result.getName().getString())||resultItemName.equals(Items.AIR.getName().getString())) &&
                    content.resultNum + resultItemNum <= content.result.getMaxCount()) {
                included1 = newBooleanArrayWithFalse(9);
                included2 = newBooleanArrayWithFalse(9);
                checkNum = 0;
                for (int j = 0; j < stackCount; j++) {
                    for(int k = 0; k < stackCount; k++) {
                        if(!included1[j]&&!included2[k]&&
                                itemNames[j].equals(content.ingredients[k].getName().getString())&&
                                itemNums[j]>=content.ingredientsNum[k]) {
                            included1[j]=true;
                            included2[k]=true;
                            checkNum++;
                        }
                    }
                }
                if(checkNum==stackCount) return i;
            }
        }
        return -1;
    }

    public static boolean tryCraft(Inventory inventory) {
        int stackCount = 0;
        String[] itemNames = new String[9];
        int[] itemNums = new int[9];
        ShapelessRecipe content;
        boolean[] included1,included2;
        int checkNum;
        String resultItemName = inventory.getStack(9).getName().getString();
        int resultItemNum = inventory.getStack(9).getCount();

        for(int i=0;i<9;i++) {
            if(!inventory.getStack(i).getName().equals(Blocks.AIR.getName())){
                itemNames[stackCount] = inventory.getStack(i).getName().getString();
                itemNums[stackCount] = inventory.getStack(i).getCount();
                stackCount++;
            }
        }

        for (int i=0;i<CONTENTS.size();i++) {
            content = CONTENTS.get(i);
            if (content.ingredients.length == stackCount &&
                    (resultItemName.equals(content.result.getName().getString())||resultItemName.equals(Items.AIR.getName().getString())) &&
                    content.resultNum + resultItemNum <= content.result.getMaxCount()) {
                included1 = newBooleanArrayWithFalse(9);
                included2 = newBooleanArrayWithFalse(9);
                checkNum = 0;
                for (int j = 0; j < stackCount; j++) {
                    for(int k = 0; k < stackCount; k++) {
                        if(!included1[j]&&!included2[k]&&
                                itemNames[j].equals(content.ingredients[k].getName().getString())&&
                                itemNums[j]>=content.ingredientsNum[k]) {
                            included1[j]=true;
                            included2[k]=true;
                            checkNum++;
                        }
                    }
                }
                if(checkNum==stackCount) {
                    included1 = newBooleanArrayWithFalse(9);
                    included2 = newBooleanArrayWithFalse(9);
                    for (int j = 0; j < stackCount; j++) {
                        for(int k = 0; k < stackCount; k++) {
                            if(!included1[j]&&!included2[k]&&
                                    itemNames[j].equals(content.ingredients[k].getName().getString())&&
                                    itemNums[j]>=content.ingredientsNum[k]) {
                                included1[k]=true;
                                included2[k]=true;
                                itemNums[j]-=content.ingredientsNum[k];
                            }
                        }
                    }
                    stackCount=0;
                    for(int j=0;j<9;j++) {
                        if(!inventory.getStack(j).getName().equals(Blocks.AIR.getName())){
                            if(itemNums[stackCount]==0) {
                                inventory.setStack(j, Items.AIR.getDefaultStack());
                            }else{
                                inventory.getStack(j).setCount(itemNums[stackCount]);
                            }
                            stackCount++;
                        }
                    }
                    if(inventory.getStack(9).getName().equals(Blocks.AIR.getName())) {
                        inventory.setStack(9,content.result.getDefaultStack());
                        inventory.getStack(9).setCount(content.resultNum);
                    }else{
                        inventory.getStack(9).setCount(inventory.getStack(9).getCount()+content.resultNum);
                    }
                    return true;
                }
            }
        }
        return false;
    }

    public CarpenterRecipe() {
        addRecipe("saw",1,new Item[]{Items.STRIPPED_ACACIA_LOG},
                new int[]{1}, Items.ACACIA_PLANKS,4);
        addRecipe("saw",1,new Item[]{Items.STRIPPED_BIRCH_LOG},
                new int[]{1}, Items.BIRCH_PLANKS,4);
        addRecipe("saw",1,new Item[]{Items.STRIPPED_JUNGLE_LOG},
                new int[]{1}, Items.JUNGLE_PLANKS,4);
        addRecipe("saw",1,new Item[]{Items.STRIPPED_OAK_LOG},
                new int[]{1}, Items.OAK_PLANKS,4);
        addRecipe("saw",1,new Item[]{Items.STRIPPED_DARK_OAK_LOG},
                new int[]{1}, Items.DARK_OAK_PLANKS,4);
        addRecipe("saw",1,new Item[]{Items.STRIPPED_SPRUCE_LOG},
                new int[]{1}, Items.SPRUCE_PLANKS,4);

        addRecipe("saw",1,new Item[]{Items.ACACIA_PLANKS},
                new int[]{1}, Items.STICK,4);
        addRecipe("saw",1,new Item[]{Items.BIRCH_PLANKS},
                new int[]{1}, Items.STICK,4);
        addRecipe("saw",1,new Item[]{Items.JUNGLE_PLANKS},
                new int[]{1}, Items.STICK,4);
        addRecipe("saw",1,new Item[]{Items.OAK_PLANKS},
                new int[]{1}, Items.STICK,4);
        addRecipe("saw",1,new Item[]{Items.DARK_OAK_PLANKS},
                new int[]{1}, Items.STICK,4);
        addRecipe("saw",1,new Item[]{Items.SPRUCE_PLANKS},
                new int[]{1}, Items.STICK,4);

        addRecipe("knife",0,new Item[]{Items.ACACIA_LOG},
                new int[]{1}, Items.STRIPPED_ACACIA_LOG,1);
        addRecipe("knife",0,new Item[]{Items.BIRCH_LOG},
                new int[]{1}, Items.STRIPPED_BIRCH_LOG,1);
        addRecipe("knife",0,new Item[]{Items.JUNGLE_LOG},
                new int[]{1}, Items.STRIPPED_JUNGLE_LOG,1);
        addRecipe("knife",0,new Item[]{Items.OAK_LOG},
                new int[]{1}, Items.STRIPPED_OAK_LOG,1);
        addRecipe("knife",0,new Item[]{Items.DARK_OAK_LOG},
                new int[]{1}, Items.STRIPPED_DARK_OAK_LOG,1);
        addRecipe("knife",0,new Item[]{Items.SPRUCE_LOG},
                new int[]{1}, Items.STRIPPED_SPRUCE_LOG,1);

    }
}