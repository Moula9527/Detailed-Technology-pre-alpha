package detailedTechnology.group.currentdone;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.code.Registration;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;

public class Ores {

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("dt", "ore"),() -> new ItemStack(Items.FURNACE));
    public static final String MOD_ID = DetailedTechnology.MOD_ID;

    public static final Block copperOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
    public static final Block copperBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,5f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block tinOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
    public static final Block tinBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block leadOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
    public static final Block leadBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,5f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block nickelOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
    public static final Block nickelBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block silverOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block silverBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,5f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 3));
    public static final Block platinumOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
    public static final Block platinumBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 3));
    public static final Block uraniumOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 3));
    public static final Block uraniumBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,5f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 3));
    public static final Block wolframiteOreBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 3));
    public static final Block wolframiteBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(2f,8f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 3));
    public static final Block bronzeBlock = new Block(FabricBlockSettings
            .of(Material.METAL, MaterialColor.STONE)
            .strength(3f,6f)
            .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));

    public static final Item copperIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperDrop = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperPlate = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperRod = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item copperGear = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item tinIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinPlate = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinRod = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item tinGear = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item bronzeIngot = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeNugget = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzePlate = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeRod = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeGear = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item ironSharp = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item ironDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item ironGear = new Item(new FabricItemSettings().group(ITEM_GROUP));

    private static final ConfiguredFeature<?, ?> oreCopperOverWorld = Feature.ORE.configure(new OreFeatureConfig(
            OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,copperOreBlock.getDefaultState(),16))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(40,40,60)))
            .spreadHorizontally().repeat(2);
    private static final ConfiguredFeature<?, ?> oreTinOverWorld = Feature.ORE.configure(new OreFeatureConfig(
            OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,tinOreBlock.getDefaultState(),8))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(30,30,55)))
            .spreadHorizontally().repeat(2);

    private static final ConfiguredFeature<?, ?> oreLeadOverWorld = Feature.ORE.configure(new OreFeatureConfig(
            OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,leadOreBlock.getDefaultState(),8))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(40,40,70)))
            .spreadHorizontally().repeat(2);
    private static final ConfiguredFeature<?, ?> oreNickelOverWorld = Feature.ORE.configure(new OreFeatureConfig(
            OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,nickelOreBlock.getDefaultState(),8))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(30,30,60)))
            .spreadHorizontally().repeat(2);
    private static final ConfiguredFeature<?, ?> oresilverOverWorld = Feature.ORE.configure(new OreFeatureConfig(
            OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,silverOreBlock.getDefaultState(),4))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(10,10,30)))
            .spreadHorizontally().repeat(2);
    private static final ConfiguredFeature<?, ?> orePlatinumOverWorld = Feature.ORE.configure(new OreFeatureConfig(
            OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,platinumOreBlock.getDefaultState(),3))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(5,5,20)))
            .spreadHorizontally().repeat(2);
    private static final ConfiguredFeature<?, ?> oreUraniumOverWorld = Feature.ORE.configure(new OreFeatureConfig(
            OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,uraniumOreBlock.getDefaultState(),2))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(1,1,15)))
            .spreadHorizontally().repeat(2);
    private static final ConfiguredFeature<?, ?> oreWolframiteOverWorld = Feature.ORE.configure(new OreFeatureConfig(
            OreFeatureConfig.Rules.BASE_STONE_OVERWORLD,wolframiteOreBlock.getDefaultState(),2))
            .decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(1,1,10)))
            .spreadHorizontally().repeat(2);

    static
    {
        Registration.blockWithItem("Copper Ore",copperOreBlock, ITEM_GROUP);
        Registration.blockWithItem("Copper Block",copperBlock, ITEM_GROUP);
        Registration.blockWithItem("Tin Ore",tinOreBlock, ITEM_GROUP);
        Registration.blockWithItem("Tin Block",tinBlock, ITEM_GROUP);
        Registration.blockWithItem("Nickel Ore",nickelOreBlock, ITEM_GROUP);
        Registration.blockWithItem("Nickel Block",nickelBlock, ITEM_GROUP);
        Registration.blockWithItem("Lead Ore",leadOreBlock, ITEM_GROUP);
        Registration.blockWithItem("Lead Block",leadBlock, ITEM_GROUP);
        Registration.blockWithItem("Silver Ore",silverOreBlock, ITEM_GROUP);
        Registration.blockWithItem("Silver Block",silverBlock, ITEM_GROUP);
        Registration.blockWithItem("Platinum Ore",platinumOreBlock, ITEM_GROUP);
        Registration.blockWithItem("Platinum Block",platinumBlock, ITEM_GROUP);
        Registration.blockWithItem("Uranium Ore",uraniumOreBlock, ITEM_GROUP);
        Registration.blockWithItem("Uranium Block",uraniumBlock, ITEM_GROUP);
        Registration.blockWithItem("Wolframite Ore",wolframiteOreBlock, ITEM_GROUP);
        Registration.blockWithItem("Wolframite Block",wolframiteBlock, ITEM_GROUP);
        Registration.blockWithItem("Bronze Block",bronzeBlock, ITEM_GROUP);

        Registration.item("Copper Ingot", copperIngot);
        Registration.item("Copper Drop", copperDrop);
        Registration.item("Copper Nugget", copperNugget);
        Registration.item("Copper Sharp", copperSharp);
        Registration.item("Copper Dust", copperDust);
        Registration.item("Copper Plate", copperPlate);
        Registration.item("Copper Rod", copperRod);
        Registration.item("Copper Gear",copperGear);

        Registration.item("Tin Ingot", tinIngot);
        Registration.item("Tin Nugget", tinNugget);
        Registration.item("Tin Sharp", tinSharp);
        Registration.item("Tin Dust", tinDust);
        Registration.item("Tin Plate", tinPlate);
        Registration.item("Tin Rod", tinRod);
        Registration.item("Tin Gear",tinGear);

        Registration.item("Bronze Ingot", bronzeIngot);
        Registration.item("Bronze Nugget", bronzeNugget);
        Registration.item("Bronze Sharp", bronzeSharp);
        Registration.item("Bronze Dust", bronzeDust);
        Registration.item("Bronze Plate", bronzePlate);
        Registration.item("Bronze Rod", bronzeRod);
        Registration.item("Bronze Gear",bronzeGear);

        Registration.item("Iron Sharp", ironSharp);
        Registration.item("Iron Dust", ironDust);
        Registration.item("Iron Gear",ironGear);

        RegistryKey<ConfiguredFeature<?, ?>> oreCopperOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_copper_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreCopperOverworld.getValue(), oreCopperOverWorld);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreCopperOverworld);

        RegistryKey<ConfiguredFeature<?, ?>> oreTinOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_tin_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreTinOverworld.getValue(), oreTinOverWorld);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreTinOverworld);

        RegistryKey<ConfiguredFeature<?, ?>> oreLeadOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_lead_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreLeadOverworld.getValue(), oreLeadOverWorld);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreLeadOverworld);

        RegistryKey<ConfiguredFeature<?, ?>> oreNickelOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_nickel_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreNickelOverworld.getValue(), oreNickelOverWorld);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreNickelOverworld);

        RegistryKey<ConfiguredFeature<?, ?>> oresilverOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_silver_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oresilverOverworld.getValue(), oresilverOverWorld);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oresilverOverworld);

        RegistryKey<ConfiguredFeature<?, ?>> orePlatinumOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_platnium_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, orePlatinumOverworld.getValue(), orePlatinumOverWorld);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, orePlatinumOverworld);
        RegistryKey<ConfiguredFeature<?, ?>> oreUraniumOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_uranium_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreUraniumOverworld.getValue(), oreUraniumOverWorld);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreUraniumOverworld);
        RegistryKey<ConfiguredFeature<?, ?>> oreWolframiteOverworld = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN,
                new Identifier(MOD_ID, "ore_wolframite_overworld"));
        Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, oreWolframiteOverworld.getValue(), oreWolframiteOverWorld);
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, oreWolframiteOverworld);
    }
}
