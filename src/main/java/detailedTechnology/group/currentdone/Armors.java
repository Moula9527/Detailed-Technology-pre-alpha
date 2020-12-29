package detailedTechnology.group.currentdone;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.code.Registration;
import detailedTechnology.items.generalclass.DetailedArmorMaterial;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;

public class Armors {

    public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
            new Identifier("dt", "armor"),() -> new ItemStack(Items.DIAMOND_HELMET));
    public static final String MOD_ID = DetailedTechnology.MOD_ID;
    public static final Item copperHelmet = new ArmorItem(DetailedArmorMaterial.COPPER,
            EquipmentSlot.HEAD, new Item.Settings().group(ITEM_GROUP));
    public static final Item copperChest = new ArmorItem(DetailedArmorMaterial.COPPER,
            EquipmentSlot.CHEST, new Item.Settings().group(ITEM_GROUP));
    public static final Item copperLeg = new ArmorItem(DetailedArmorMaterial.COPPER,
            EquipmentSlot.LEGS, new Item.Settings().group(ITEM_GROUP));
    public static final Item copperBoots = new ArmorItem(DetailedArmorMaterial.COPPER,
            EquipmentSlot.FEET, new Item.Settings().group(ITEM_GROUP));

    public static final Item bronzeHelmet = new ArmorItem(DetailedArmorMaterial.BRONZE,
            EquipmentSlot.HEAD, new Item.Settings().group(ITEM_GROUP));
    public static final Item bronzeChest = new ArmorItem(DetailedArmorMaterial.BRONZE,
            EquipmentSlot.CHEST, new Item.Settings().group(ITEM_GROUP));
    public static final Item bronzeLeg = new ArmorItem(DetailedArmorMaterial.BRONZE,
            EquipmentSlot.LEGS, new Item.Settings().group(ITEM_GROUP));
    public static final Item bronzeBoots = new ArmorItem(DetailedArmorMaterial.BRONZE,
            EquipmentSlot.FEET, new Item.Settings().group(ITEM_GROUP));

    static{
        Registration.item("Copper Helmet", copperHelmet);
        Registration.item("Copper Chest", copperChest);
        Registration.item("Copper Legs", copperLeg);
        Registration.item("Copper Boots", copperBoots);

        Registration.item("Bronze Helmet", bronzeHelmet);
        Registration.item("Bronze Chest", bronzeChest);
        Registration.item("Bronze Legs", bronzeLeg);
        Registration.item("Bronze Boots", bronzeBoots);
    }
}
