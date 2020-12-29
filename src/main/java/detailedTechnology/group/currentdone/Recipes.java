package detailedTechnology.group.currentdone;

import detailedTechnology.recipe.*;

public class Recipes {
    public static StoneMileRecipe stoneMileRecipe;
    public static AnvilRecipe anvilRecipe;
    public static KilnRecipe kilnRecipe;
    public static CokeOvenRecipe cokeOvenRecipe;
    public static CarpenterRecipe carpenterRecipe;
    public Recipes(){
        stoneMileRecipe = new StoneMileRecipe();
        anvilRecipe = new AnvilRecipe();
        kilnRecipe = new KilnRecipe();
        cokeOvenRecipe = new CokeOvenRecipe();
        carpenterRecipe = new CarpenterRecipe();
    }
}
