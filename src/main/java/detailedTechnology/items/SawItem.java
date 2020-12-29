package detailedTechnology.items;

import detailedTechnology.blockEntity.currentdone.BronzeAnvilEntity;
import detailedTechnology.blockEntity.currentdone.CarpenterWorkbenchEntity;
import detailedTechnology.group.Machines;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

import java.util.Objects;


public class SawItem extends ToolItem {
    private final int sawLevel;
    public SawItem(ToolMaterial material, Settings settings){
        super(material, settings);
        sawLevel = material.getMiningLevel()-1;
    }

    public int getSawLevel() {
        return sawLevel;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().getBlockState(context.getBlockPos()).getBlock().getName().getString()
                .equals(Machines.carpenterWorkbench.getName().getString())) {
            if(!((CarpenterWorkbenchEntity) Objects.requireNonNull(context.getWorld().getBlockEntity(context.getBlockPos())))
                    .addWorkingTime("saw",sawLevel, context.getPlayer())) {
                Objects.requireNonNull(context.getPlayer()).playSound(SoundEvents.BLOCK_WOOD_BREAK,0.5f,1.0f);
            }
            Objects.requireNonNull(context.getPlayer()).getStackInHand(context.getHand()).damage(1,
                    context.getPlayer(),(playerEntity -> {
                        playerEntity.sendToolBreakStatus(context.getHand());
                    }));
            return ActionResult.SUCCESS;
        }else {
            return super.useOnBlock(context);
        }
    }

}
