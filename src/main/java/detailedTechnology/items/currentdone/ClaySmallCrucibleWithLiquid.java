package detailedTechnology.items.currentdone;

import detailedTechnology.group.currentdone.Ores;
import net.minecraft.block.Blocks;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.util.ActionResult;

public class ClaySmallCrucibleWithLiquid extends Item {
    private final String liquidType;

    public ClaySmallCrucibleWithLiquid(String liquidType,Settings settings) {
        super(settings.maxCount(1));
        this.liquidType = liquidType;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().getBlockState(context.getBlockPos().up()).getBlock().getName().equals(Blocks.WATER.getName())
                && context.getPlayer()!=null)
        {
            Inventory inventory = context.getPlayer().inventory;
            int slot = context.getPlayer().inventory.selectedSlot;
            int number = inventory.getStack(slot).getCount();
            switch (liquidType)
            {
                case "copper":
                    inventory.setStack(slot,new ItemStack(Ores.copperIngot));
                    break;
                case "tin":
                    inventory.setStack(slot,new ItemStack(Ores.tinIngot));
                    break;
                case "bronze":
                    inventory.setStack(slot,new ItemStack(Ores.bronzeIngot));
                    break;
            }
            inventory.getStack(slot).setCount(number);
        }
        return ActionResult.PASS;
    }
}