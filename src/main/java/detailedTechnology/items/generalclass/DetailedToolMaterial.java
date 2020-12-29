package detailedTechnology.items.generalclass;

import detailedTechnology.group.currentdone.Ores;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;
import net.minecraft.util.Lazy;

import java.util.function.Supplier;

public enum DetailedToolMaterial implements ToolMaterial {

	COPPER("copper", 100, 5,1,2,9, () -> {
		      return Ingredient.ofItems(Ores.copperIngot);
	   }),

	BRONZE("bronze", 128, 6,2,2,12, () -> {
		return Ingredient.ofItems(Ores.bronzeIngot);
	});

	public final String name;
	public final int durability;
	public final float miningSpeedMultiplier;
	public final float attackDamage;
	public final int miningLevel;
	public final int enchantability;
	private final Lazy<Ingredient> repairIngredientSupplier;

	private DetailedToolMaterial(String name, int durability, float miningSpeedMultiplier,float attackDamage,int miningLevel, int enchantability,  Supplier<Ingredient> repairIngredientSupplier) {
		this.name = name;
		this.durability = durability;
		this.miningSpeedMultiplier = miningSpeedMultiplier;
		this.attackDamage = attackDamage;
		this.miningLevel = miningLevel;
		this.enchantability = enchantability;
		this.repairIngredientSupplier = new Lazy(repairIngredientSupplier);
	}

	@Override
	public int getDurability() {
		return durability;
	}

	@Override
	public float getMiningSpeedMultiplier() {
		return miningSpeedMultiplier;
	}

	@Override
	public float getAttackDamage() {
		return attackDamage;
	}

	@Override
	public int getMiningLevel() {
		return miningLevel;
	}

	@Override
	public int getEnchantability() {
		return enchantability;
	}

	@Override
	public Ingredient getRepairIngredient() {
		return (Ingredient)this.repairIngredientSupplier.get();
	}
}
