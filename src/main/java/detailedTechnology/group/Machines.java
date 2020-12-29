package detailedTechnology.group;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.blocks.BronzeBoiler;
import detailedTechnology.blocks.CokeOven;
import detailedTechnology.code.Registration;
import detailedTechnology.blockEntity.currentdone.*;
import detailedTechnology.blocks.currentdone.*;
import detailedTechnology.gui.currentdone.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Machines {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("dt", "machine"),() -> new ItemStack(Items.PISTON));
    public static final String MOD_ID = DetailedTechnology.MOD_ID;

    public static ScreenHandlerType<CrucibleScreenHandler> crucibleScreenHandler;
    public static ScreenHandlerType<CarpenterWorkbenchScreenHandler> carpenterWorkbenchScreenHandler;
    public static ScreenHandlerType<StoneMileScreenHandler> stoneMileScreenHandler;
    public static ScreenHandlerType<FireStarterBlockScreenHandler> fireStarterBlockScreenHandler;
    public static ScreenHandlerType<BronzeAnvilScreenHandler> bronzeAnvilScreenHandler;
    public static ScreenHandlerType<KilnScreenHandler> kilnScreenHandler;
    public static ScreenHandlerType<CombustionChamberScreenHandler> combustionChamberScreenHandler;
    public static ScreenHandlerType<ClayModelScreenHandler> clayModelScreenHandler;
    public static ScreenHandlerType<CokeOvenScreenHandler> cokeOvenScreenHandler;
    public static ScreenHandlerType<BronzeBoilerScreenHandler> bronzeBoilerScreenHandler;

    public static BlockEntityType<BrickCrucibleEntity> brickCrucibleEntity;
    public static BlockEntityType<CarpenterWorkbenchEntity> carpenterWorkbenchEntity;
    public static BlockEntityType<StoneMileEntity> stoneMileEntity;
    public static BlockEntityType<BurningCharcoalHeapEntity> burningCharcoalHeapEntity;
    public static BlockEntityType<FireStarterBlockEntity> fireStarterBlockEntity;
    public static BlockEntityType<BronzeAnvilEntity> bronzeAnvilEntity;
    public static BlockEntityType<KilnEntity> kilnEntity;
    public static BlockEntityType<FirebrickCombustionChamberEntity> firebrickCombustionChamberEntity;
    public static BlockEntityType<BronzeCombustionChamberEntity> bronzeCombustionChamberEntity;
    public static BlockEntityType<ClayIngotModelEntity> clayIngotModelEntity;
    public static BlockEntityType<ClayPlateModelEntity> clayPlateModelEntity;
    public static BlockEntityType<ClayGearModelEntity> clayGearModelEntity;
    public static BlockEntityType<ClayRodModelEntity> clayRodModelEntity;
    public static BlockEntityType<CokeOvenEntity> cokeOvenEntity;
    public static BlockEntityType<BronzeBoilerEntity> bronzeBoilerEntity;

    public static final CarpenterWorkbench carpenterWorkbench = new CarpenterWorkbench();
    public static final ClayIngotModel clayIngotModel = new ClayIngotModel();
    public static final ClayPlateModel clayPlateModel = new ClayPlateModel();
    public static final ClayRodModel clayRodModel = new ClayRodModel();
    public static final ClayGearModel clayGearModel = new ClayGearModel();
    public static final StoneMileRunner stoneMileRunner = new StoneMileRunner();
    public static final BurningCharcoalHeap burningCharcoalHeap = new BurningCharcoalHeap();
    public static final BrickCrucible brickCrucible = new BrickCrucible();
    public static final StoneMile stoneMile = new StoneMile();
    public static final FireStarterBlock fireStarterBlock = new FireStarterBlock();
    public static final BronzeAnvil bronzeAnvil = new BronzeAnvil();
    public static final Kiln kiln = new Kiln();
    public static final CombustionChamber bronzeCombustionChamber = new CombustionChamber("bronze");
    public static final CombustionChamber firebrickCombustionChamber = new CombustionChamber("firebrick");
    public static final CokeOven cokeOven = new CokeOven();
    public static final BronzeBoiler bronzeBoiler = new BronzeBoiler();

    static{
        brickCrucibleEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":brick_crucible",
                BlockEntityType.Builder.create(BrickCrucibleEntity::new,brickCrucible).build(null));
        carpenterWorkbenchEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":carpenter_workbench",
                BlockEntityType.Builder.create(CarpenterWorkbenchEntity::new,carpenterWorkbench).build(null));
        stoneMileEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":stone_mile",
                BlockEntityType.Builder.create(StoneMileEntity::new,stoneMile).build(null));
        burningCharcoalHeapEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":burning_charcoal_heap",
                BlockEntityType.Builder.create(BurningCharcoalHeapEntity::new,burningCharcoalHeap).build(null));
        fireStarterBlockEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":fire_starter_block",
                BlockEntityType.Builder.create(FireStarterBlockEntity::new,fireStarterBlock).build(null));
        bronzeAnvilEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":bronze_anvil",
                BlockEntityType.Builder.create(BronzeAnvilEntity::new,bronzeAnvil).build(null));
        kilnEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":kiln",
                BlockEntityType.Builder.create(KilnEntity::new,kiln).build(null));
        firebrickCombustionChamberEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":firebrick_combustion_chamber",
                BlockEntityType.Builder.create(FirebrickCombustionChamberEntity::new,firebrickCombustionChamber).build(null));
        bronzeCombustionChamberEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":bronze_combustion_chamber",
                BlockEntityType.Builder.create(BronzeCombustionChamberEntity::new,bronzeCombustionChamber).build(null));
        clayIngotModelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":clay_ingot_model",
                BlockEntityType.Builder.create(ClayIngotModelEntity::new,clayIngotModel).build(null));
        clayPlateModelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":clay_plate_model",
                BlockEntityType.Builder.create(ClayPlateModelEntity::new,clayPlateModel).build(null));
        clayGearModelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":clay_gear_model",
                BlockEntityType.Builder.create(ClayGearModelEntity::new,clayGearModel).build(null));
        clayRodModelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":clay_rod_model",
                BlockEntityType.Builder.create(ClayRodModelEntity::new,clayRodModel).build(null));
        cokeOvenEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":coke_oven",
                BlockEntityType.Builder.create(CokeOvenEntity::new,cokeOven).build(null));
        bronzeBoilerEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE,MOD_ID+":bronze_boiler",
                BlockEntityType.Builder.create(BronzeBoilerEntity::new,bronzeBoiler).build(null));

        Registration.blockWithItem("Brick Crucible",brickCrucible,ITEM_GROUP);
        Registration.blockWithItem("Carpenter Workbench",carpenterWorkbench,ITEM_GROUP);
        Registration.blockWithItem("Stone Mile",stoneMile,ITEM_GROUP);
        Registration.blockWithItem("Stone Mile Runner",stoneMileRunner,ITEM_GROUP);
        Registration.block("Burning Charcoal Heap",burningCharcoalHeap);
        Registration.blockWithItem("Fire Starter Block",fireStarterBlock,ITEM_GROUP);
        Registration.blockWithItem("Clay Ingot Model", clayIngotModel,ITEM_GROUP);
        Registration.blockWithItem("Clay Plate Model", clayPlateModel,ITEM_GROUP);
        Registration.blockWithItem("Clay Rod Model", clayRodModel,ITEM_GROUP);
        Registration.blockWithItem("Clay Gear Model", clayGearModel,ITEM_GROUP);
        Registration.blockWithItem("Bronze Anvil",bronzeAnvil,ITEM_GROUP);
        Registration.blockWithItem("Kiln",kiln,ITEM_GROUP);
        Registration.blockWithItem("Bronze Combustion Chamber",bronzeCombustionChamber,ITEM_GROUP);
        Registration.blockWithItem("Firebrick Combustion Chamber",firebrickCombustionChamber, ITEM_GROUP);
        Registration.blockWithItem("Coke Oven",cokeOven,ITEM_GROUP);
        Registration.blockWithItem("Bronze Boiler",bronzeBoiler,ITEM_GROUP);

        crucibleScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "brick_crucible"),CrucibleScreenHandler::new);
        carpenterWorkbenchScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "carpenter_workbench"),CarpenterWorkbenchScreenHandler::new);
        stoneMileScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "stone_mile"),StoneMileScreenHandler::new);
        fireStarterBlockScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "fire_starter_block"), FireStarterBlockScreenHandler::new);
        bronzeAnvilScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "bronze_anvil"), BronzeAnvilScreenHandler::new);
        kilnScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "kiln"), KilnScreenHandler::new);
        combustionChamberScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "combustion_chamber"), CombustionChamberScreenHandler::new);
        clayModelScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "clay_model"), ClayModelScreenHandler::new);
        cokeOvenScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "coke_oven"), CokeOvenScreenHandler::new);
        bronzeBoilerScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "bronze_boiler"), BronzeBoilerScreenHandler::new);
    }
}
