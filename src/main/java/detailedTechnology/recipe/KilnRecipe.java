package detailedTechnology.recipe;

import detailedTechnology.group.Machines;
import detailedTechnology.group.Pipes;
import detailedTechnology.group.currentdone.Materials;
import detailedTechnology.group.currentdone.Ores;
import detailedTechnology.group.currentdone.Tools;
import detailedTechnology.recipe.general.ShapedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.Items;

import java.util.ArrayList;

public class KilnRecipe {

    public static final ArrayList<ShapedRecipe> CONTENTS = new ArrayList<>();
    public static final ArrayList<Integer> charcoalCosts = new ArrayList<>();

    public void addRecipe(int charcoalCost,Item[] ingredients,int[] ingredientsNum, Item product, int resultNum){
        charcoalCosts.add(charcoalCost);
        CONTENTS.add(new ShapedRecipe(ingredients,ingredientsNum,product,resultNum));
    }

    public KilnRecipe() {
        Item clayBall = Items.CLAY_BALL;
        Item air = Items.AIR;
        Item brick = Items.BRICK;
        Item rawFirebrickMixture = Materials.rawFirebrickMixture;
        Item fireBrick = Materials.fireBrick;

        addRecipe(1, new Item[]{clayBall,air,clayBall,clayBall,air,clayBall,clayBall,clayBall,clayBall},
                new int[]{1,0,1,1,0,1,1,1,1}, Materials.claySmallCrucible,8);

        addRecipe(1, new Item[]{clayBall,clayBall,clayBall,clayBall,clayBall,clayBall,air,air,air},
                new int[]{1,1,1,1,1,1,0,0,0},brick,8);
        addRecipe(1, new Item[]{air,air,air,clayBall,clayBall,clayBall,clayBall,clayBall,clayBall},
                new int[]{0,0,0,1,1,1,1,1,1},brick,8);

        addRecipe(1, new Item[]{clayBall,air,clayBall,air,clayBall,air,air,air,air},
                new int[]{1,0,1,0,1,0,0,0,0}, Tools.clayBucket,1);
        addRecipe(1, new Item[]{air,air,air,clayBall,air,clayBall,air,clayBall,air},
                new int[]{0,0,0,1,0,1,0,1,0}, Tools.clayBucket,1);

        addRecipe(4, new Item[]{air,air,air,brick,clayBall,brick,brick,brick,brick},
                new int[]{0,0,0,4,4,4,4,4,4},Machines.brickCrucible.asItem(),1);
        addRecipe(4, new Item[]{brick,clayBall,brick,brick,brick,brick,air,air,air},
                new int[]{4,4,4,4,4,4,0,0,0}, Machines.brickCrucible.asItem(),1);

        addRecipe(2, new Item[]{rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,air,air,air},
                new int[]{1,1,1,1,1,1,0,0,0},fireBrick,8);
        addRecipe(2, new Item[]{air,air,air,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture,rawFirebrickMixture},
                new int[]{0,0,0,1,1,1,1,1,1},fireBrick,8);

        addRecipe(1,new Item[]{fireBrick,air,fireBrick,fireBrick,air,fireBrick,fireBrick,fireBrick,fireBrick},
                new int[]{1,0,1,1,0,1,1,1,1}, Pipes.firebrickBarrel.asItem(),1);

        addRecipe(1,new Item[]{clayBall,brick,clayBall,clayBall,clayBall,clayBall,air,air,air},
                new int[]{1,0,1,1,1,1,0,0,0},Machines.clayIngotModel.asItem(),1);
        addRecipe(1,new Item[]{air,air,air,clayBall,brick,clayBall,clayBall,clayBall,clayBall},
                new int[]{0,0,0,1,0,1,1,1,1},Machines.clayIngotModel.asItem(),1);

        addRecipe(1,new Item[]{clayBall,Ores.copperPlate,clayBall,clayBall,clayBall,clayBall,air,air,air},
                new int[]{1,0,1,1,1,1,0,0,0},Machines.clayPlateModel.asItem(),1);
        addRecipe(1,new Item[]{air,air,air,clayBall,Ores.copperPlate,clayBall,clayBall,clayBall,clayBall},
                new int[]{0,0,0,1,0,1,1,1,1},Machines.clayPlateModel.asItem(),1);

        addRecipe(1,new Item[]{Ores.copperRod,clayBall,Ores.copperRod,clayBall,clayBall,clayBall,air,air,air},
                new int[]{0,1,0,1,1,1,0,0,0},Machines.clayRodModel.asItem(),1);
        addRecipe(1,new Item[]{air,air,air,Ores.copperRod,clayBall,Ores.copperRod,clayBall,clayBall,clayBall},
                new int[]{0,0,0,0,1,0,1,1,1},Machines.clayRodModel.asItem(),1);

        addRecipe(1,new Item[]{clayBall,Ores.copperGear,clayBall,clayBall,clayBall,clayBall,air,air,air},
                new int[]{1,0,1,1,1,1,0,0,0},Machines.clayGearModel.asItem(),1);
        addRecipe(1,new Item[]{air,air,air,clayBall,Ores.copperGear,clayBall,clayBall,clayBall,clayBall},
                new int[]{0,0,0,1,0,1,1,1,1},Machines.clayGearModel.asItem(),1);
    }
}