package detailedTechnology.recipe.general;

import net.minecraft.item.Item;

public class ShapelessRecipe {
    public Item[] ingredients;
    public int[] ingredientsNum;
    public Item result;
    public int resultNum;

    public ShapelessRecipe(Item[] ingredients, int[] ingredientsNum, Item result, int resultNum) {
        this.ingredients = ingredients;
        this.ingredientsNum = ingredientsNum;
        this.result = result;
        this.resultNum = resultNum;
    }
}
