package detailedTechnology.items.currentdone;

import detailedTechnology.blockEntity.currentdone.BronzeAnvilEntity;
import detailedTechnology.group.Machines;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.item.ToolItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;

import java.util.Objects;


public class HammerItem extends ToolItem {
    private final int hammerLevel;
    public HammerItem(ToolMaterial material, Settings settings){
        super(material, settings);
        hammerLevel = material.getMiningLevel()-1;
    }

    public int getHammerLevel() {
        return hammerLevel;
    }

    @Override
    public ActionResult useOnBlock(ItemUsageContext context) {
        if(context.getWorld().getBlockState(context.getBlockPos()).getBlock().getName().getString()
                .equals(Machines.bronzeAnvil.getName().getString())) {
            if(!((BronzeAnvilEntity) Objects.requireNonNull(context.getWorld().getBlockEntity(context.getBlockPos())))
                    .addWorkingTime("hammer",hammerLevel, context.getPlayer())) {
                Objects.requireNonNull(context.getPlayer()).playSound(SoundEvents.BLOCK_ANVIL_PLACE,0.5f,1.0f);
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
