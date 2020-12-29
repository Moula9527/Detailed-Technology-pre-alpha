package detailedTechnology.recipe.general;

import net.minecraft.item.Item;

public class ShapedRecipe {
    public Item[] ingredients;
    public int[] ingredientsNum;
    public Item result;
    public int resultNum;

    public ShapedRecipe(Item[] ingredients, int[] ingredientsNum, Item result, int resultNum) {
        this.ingredients = ingredients;
        this.ingredientsNum = ingredientsNum;
        this.result = result;
        this.resultNum = resultNum;
    }
}
