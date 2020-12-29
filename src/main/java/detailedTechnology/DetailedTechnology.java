package detailedTechnology;

import detailedTechnology.code.Registration;
import detailedTechnology.group.currentdone.Recipes;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

public class DetailedTechnology implements ModInitializer {

	public static final String MOD_ID = "dt";

	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, "general"), () -> new ItemStack(Blocks.COBBLESTONE));

	public static final Block charcoalHeap = new Block(FabricBlockSettings.of(Material.SOIL, MaterialColor.STONE)
			.strength(1f,1f).breakByTool(FabricToolTags.SHOVELS, 0));
	public static final Block firebrickBlock = new Block(FabricBlockSettings.of(Material.STONE, MaterialColor.STONE)
			.strength(4f,8f).breakByTool(FabricToolTags.PICKAXES, 0));
	public static final Block cokeBlock = new Block(FabricBlockSettings.of(Material.STONE, MaterialColor.STONE)
			.strength(4f,8f).breakByTool(FabricToolTags.PICKAXES, 0));

	@Override
	public void onInitialize() {

		Registration.blockWithItem("Charcoal Heap",charcoalHeap,ITEM_GROUP);
		Registration.blockWithItem("Firebrick Block", firebrickBlock,ITEM_GROUP);
		Registration.blockWithItem("Coke Block", cokeBlock, ITEM_GROUP);

		new Recipes();

		System.out.println("Hello! Detailed world!");

	}
}
