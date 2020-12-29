package detailedTechnology.group.currentdone;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.code.Registration;
import detailedTechnology.items.FlintKnife;
import detailedTechnology.items.SawItem;
import detailedTechnology.items.currentdone.FireStarter;
import detailedTechnology.items.currentdone.HammerItem;
import detailedTechnology.items.currentdone.RasperItem;
import detailedTechnology.items.generalclass.*;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

public class Tools {
    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("dt", "tool"),() -> new ItemStack(Items.DIAMOND_PICKAXE));
    public static final String MOD_ID = DetailedTechnology.MOD_ID;

    public static final SawItem copperSaw = new SawItem(DetailedToolMaterial.COPPER,
            new FabricItemSettings().group(ITEM_GROUP));
    public static final SawItem bronzeSaw = new SawItem(DetailedToolMaterial.BRONZE,
            new FabricItemSettings().group(ITEM_GROUP));

    public static final HammerItem bronzeHammer = new HammerItem(DetailedToolMaterial.BRONZE,
            new FabricItemSettings().group(ITEM_GROUP));

    public static final RasperItem bronzeRasper = new RasperItem(DetailedToolMaterial.BRONZE,
            new FabricItemSettings().group(ITEM_GROUP));

    public static final DetailedPickaxeItem copperPickaxe = new DetailedPickaxeItem(DetailedToolMaterial.COPPER,
            2,1.5f, new FabricItemSettings().group(ITEM_GROUP));
    public static final DetailedAxeItem copperAxe = new DetailedAxeItem(DetailedToolMaterial.COPPER,
            5,0.5f, new FabricItemSettings().group(ITEM_GROUP));
    public static final DetailedShovelItem copperShovel = new DetailedShovelItem(DetailedToolMaterial.COPPER,
            2,1.5f, new FabricItemSettings().group(ITEM_GROUP));
    public static final DetailedHoeItem copperHoe = new DetailedHoeItem(DetailedToolMaterial.COPPER,
            1,1.5f, new FabricItemSettings().group(ITEM_GROUP));
    public static final DetailedSwordItem copperSword = new DetailedSwordItem(DetailedToolMaterial.COPPER,
            4,2.5f, new FabricItemSettings().group(ITEM_GROUP));
    public static final DetailedPickaxeItem bronzePickaxe = new DetailedPickaxeItem(DetailedToolMaterial.BRONZE,
            2,1.5f, new FabricItemSettings().group(ITEM_GROUP));
    public static final DetailedAxeItem bronzeAxe = new DetailedAxeItem(DetailedToolMaterial.BRONZE,
            5,0.5f, new FabricItemSettings().group(ITEM_GROUP));
    public static final DetailedShovelItem bronzeShovel = new DetailedShovelItem(DetailedToolMaterial.BRONZE,
            2,1.5f, new FabricItemSettings().group(ITEM_GROUP));
    public static final DetailedHoeItem bronzeHoe = new DetailedHoeItem(DetailedToolMaterial.BRONZE,
            1,1.5f, new FabricItemSettings().group(ITEM_GROUP));
    public static final DetailedSwordItem bronzeSword = new DetailedSwordItem(DetailedToolMaterial.BRONZE,
            4,2.5f, new FabricItemSettings().group(ITEM_GROUP));

    public static final Item copperBucket = new DetailedBucketItem(new FabricItemSettings().group(ITEM_GROUP)
            .maxCount(16), "copper", Fluids.EMPTY);
    public static final Item clayBucket = new DetailedBucketItem(new FabricItemSettings().group(ITEM_GROUP)
            .maxCount(16),"clay", Fluids.EMPTY);

    public static final Item beginnerFireStarter =
            new FireStarter(new FabricItemSettings().group(ITEM_GROUP),1);
    public static final Item basicFireStarter =
            new FireStarter(new FabricItemSettings().group(ITEM_GROUP),2);
    public static final Item advancedFireStarter =
            new FireStarter(new FabricItemSettings().group(ITEM_GROUP),3);

    public static final Item flintKnife = new FlintKnife(new FabricItemSettings().group(ITEM_GROUP));

    static {
        Registration.item("Flint Knife",flintKnife);
        Registration.item("Beginner Fire Starter",beginnerFireStarter);
        Registration.item("Basic Fire Starter",basicFireStarter);
        Registration.item("Advanced Fire Starter",advancedFireStarter);

        Registration.item("Copper Saw",copperSaw);
        Registration.item("Bronze Saw",bronzeSaw);

        Registration.item("Bronze Hammer",bronzeHammer);
        Registration.item("Bronze Rasper",bronzeRasper);

        Registration.item("Copper Pickaxe",copperPickaxe);
        Registration.item("Copper Axe",copperAxe);
        Registration.item("Copper Sword",copperSword);
        Registration.item("Copper Shovel",copperShovel);
        Registration.item("Copper Hoe",copperHoe);

        Registration.item("Bronze Pickaxe",bronzePickaxe);
        Registration.item("Bronze Axe",bronzeAxe);
        Registration.item("Bronze Sword",bronzeSword);
        Registration.item("Bronze Shovel",bronzeShovel);
        Registration.item("Bronze Hoe",bronzeHoe);

        Registration.item("Clay Bucket",clayBucket);

        Registration.item("Copper Bucket",copperBucket);

    }

}
