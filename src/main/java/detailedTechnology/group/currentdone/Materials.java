package detailedTechnology.group.currentdone;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.code.Registration;
import detailedTechnology.items.currentdone.ClaySmallCrucibleWithLiquid;
import detailedTechnology.items.generalclass.DetailedMaterialStatus;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import java.util.ArrayList;

public class Materials {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("dt", "material"),() -> new ItemStack(Items.SUGAR));
    public static final String MOD_ID = DetailedTechnology.MOD_ID;

    public static final Item tinder = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item wetClaySmallCrucible = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item claySmallCrucible = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item claySmallCrucibleWithCopper = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item claySmallCrucibleWithTin = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item claySmallCrucibleWithBronze = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final Item copperRodFrame = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item bronzeRodFrame = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item brickDust = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item rawFirebrickMixture = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item fireBrick = new Item(new FabricItemSettings().group(ITEM_GROUP));
    public static final Item coke = new Item(new FabricItemSettings().group(ITEM_GROUP));

    public static final ClaySmallCrucibleWithLiquid claySmallCrucibleWithMeltingCopper
            = new ClaySmallCrucibleWithLiquid("copper",new FabricItemSettings().group(ITEM_GROUP));
    public static final ClaySmallCrucibleWithLiquid claySmallCrucibleWithMeltingTin
            = new ClaySmallCrucibleWithLiquid("tin",new FabricItemSettings().group(ITEM_GROUP));
    public static final ClaySmallCrucibleWithLiquid claySmallCrucibleWithMeltingBronze
            = new ClaySmallCrucibleWithLiquid("bronze",new FabricItemSettings().group(ITEM_GROUP));

    public static final DetailedMaterialStatus COPPER = new DetailedMaterialStatus(
            "copper",8.96f,385,1085,2567);
    public static final DetailedMaterialStatus TIN = new DetailedMaterialStatus(
            "tin",5.77f,230,232,2602);
    public static final DetailedMaterialStatus BRONZE = new DetailedMaterialStatus(
            "bronze",8.16f,347,800,2567);
    public static final DetailedMaterialStatus BRICK = new DetailedMaterialStatus(
            "brick",1.7f,750,1280,3000);
    public static final DetailedMaterialStatus FIREBRICK = new DetailedMaterialStatus(
            "firebrick",2f,858,1580,3000);
    public static final DetailedMaterialStatus WATER = new DetailedMaterialStatus(
            "water",1f,4200,0,100);

    public static final float R = 8.31446261815324f;


    public static final ArrayList<String> LiquidList = new ArrayList<>();

    public static final ArrayList<DetailedMaterialStatus> MATERIAL_STATUSES = new ArrayList<>();

    static {
        Registration.item("Tinder",tinder);

        Registration.item("Wet Clay Small Crucible", wetClaySmallCrucible);
        Registration.item("Clay Small Crucible", claySmallCrucible);
        Registration.item("Clay Crucible With Copper", claySmallCrucibleWithCopper);
        Registration.item("Clay Crucible With Tin", claySmallCrucibleWithTin);
        Registration.item("Clay Crucible With Bronze", claySmallCrucibleWithBronze);
        Registration.item("Clay Crucible With Melting Copper", claySmallCrucibleWithMeltingCopper);
        Registration.item("Clay Crucible With Melting Tin", claySmallCrucibleWithMeltingTin);
        Registration.item("Clay Crucible With Melting Bronze", claySmallCrucibleWithMeltingBronze);

        Registration.item("Copper Rod Frame", copperRodFrame);
        Registration.item("Bronze Rod Frame", bronzeRodFrame);
        Registration.item("Brick Dust",brickDust);
        Registration.item("Raw Firebrick Mixture", rawFirebrickMixture);
        Registration.item("Firebrick",fireBrick);
        Registration.item("Coke",coke);

        MATERIAL_STATUSES.add(COPPER);
        MATERIAL_STATUSES.add(TIN);
        MATERIAL_STATUSES.add(BRONZE);
        MATERIAL_STATUSES.add(BRICK);
        MATERIAL_STATUSES.add(FIREBRICK);


        LiquidList.add("water");
        LiquidList.add("creosote");
        LiquidList.add("steam");
        LiquidList.add("lava");
    }

}
