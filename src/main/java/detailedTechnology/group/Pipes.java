package detailedTechnology.group;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.blockEntity.currentdone.BronzeBarrelEntity;
import detailedTechnology.blockEntity.currentdone.FirebrickBarrelEntity;
import detailedTechnology.blockEntity.currentdone.WoodBarrelEntity;
import detailedTechnology.blocks.currentdone.Barrel;
import detailedTechnology.code.Registration;
import detailedTechnology.gui.currentdone.BarrelScreenHandler;
import detailedTechnology.items.generalclass.DetailedBucketItem;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.screenhandler.v1.ScreenHandlerRegistry;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class Pipes {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("dt", "tank"), () -> new ItemStack(Items.WATER_BUCKET));
    public static final String MOD_ID = DetailedTechnology.MOD_ID;

    public static final Item copperWaterBucket = new DetailedBucketItem(new FabricItemSettings().group(ITEM_GROUP)
            .maxCount(1), "copper", Fluids.WATER);
    public static final Item copperLavaBucket = new DetailedBucketItem(new FabricItemSettings().group(ITEM_GROUP)
            .maxCount(1), "copper", Fluids.LAVA);
    public static final Item clayWaterBucket = new DetailedBucketItem(new FabricItemSettings().group(ITEM_GROUP)
            .maxCount(1), "clay", Fluids.WATER);
    public static final Item clayLavaBucket = new DetailedBucketItem(new FabricItemSettings().group(ITEM_GROUP)
            .maxCount(1), "clay", Fluids.LAVA);


    public static ScreenHandlerType<BarrelScreenHandler> barrelScreenHandler;

    public static BlockEntityType<WoodBarrelEntity> woodBarrelEntity;
    public static BlockEntityType<BronzeBarrelEntity> bronzeBarrelEntity;
    public static BlockEntityType<FirebrickBarrelEntity> firebrickBarrelEntity;


    public static final Barrel woodBarrel = new Barrel("wood");
    public static final Barrel bronzeBarrel = new Barrel("bronze");
    public static final Barrel firebrickBarrel = new Barrel("firebrick");

    static {
        Registration.item("Clay Water Bucket", clayWaterBucket);
        Registration.item("Clay Lava Bucket", clayLavaBucket);
        Registration.item("Copper Water Bucket", copperWaterBucket);
        Registration.item("Copper Lava Bucket", copperLavaBucket);


        woodBarrelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":wood_barrel",
                BlockEntityType.Builder.create(WoodBarrelEntity::new, woodBarrel).build(null));
        bronzeBarrelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":bronze_barrel",
                BlockEntityType.Builder.create(BronzeBarrelEntity::new, bronzeBarrel).build(null));
        firebrickBarrelEntity = Registry.register(Registry.BLOCK_ENTITY_TYPE, MOD_ID + ":firebrick_barrel",
                BlockEntityType.Builder.create(FirebrickBarrelEntity::new, firebrickBarrel).build(null));

        Registration.blockWithItem("Wood Barrel", woodBarrel, ITEM_GROUP);
        Registration.blockWithItem("Bronze Barrel", bronzeBarrel, ITEM_GROUP);
        Registration.blockWithItem("Firebrick Barrel", firebrickBarrel, ITEM_GROUP);

        barrelScreenHandler = ScreenHandlerRegistry.registerSimple(
                new Identifier(MOD_ID, "barrel"), BarrelScreenHandler::new);
    }

}
