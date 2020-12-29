package detailedTechnology.blocks.currentdone;

import detailedTechnology.blockEntity.currentdone.BurningCharcoalHeapEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class BurningCharcoalHeap extends Block implements BlockEntityProvider{
    public BurningCharcoalHeap()
    {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.STONE)
                .strength(2f,3f)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new BurningCharcoalHeapEntity();
    }

}