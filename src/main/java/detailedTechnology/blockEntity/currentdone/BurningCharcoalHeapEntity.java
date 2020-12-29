package detailedTechnology.blockEntity.currentdone;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.group.Machines;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.text.MutableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BurningCharcoalHeapEntity extends BlockEntity implements Tickable {

    private int time = 0;

    public BurningCharcoalHeapEntity(){ super(Machines.burningCharcoalHeapEntity); }

    private boolean isStripedLog(MutableText blockName){
        return blockName.equals(Blocks.STRIPPED_ACACIA_LOG.getName())||blockName.equals(Blocks.STRIPPED_BIRCH_LOG.getName())||
                blockName.equals(Blocks.STRIPPED_DARK_OAK_LOG.getName())||blockName.equals(Blocks.STRIPPED_OAK_LOG.getName())||
                blockName.equals(Blocks.STRIPPED_JUNGLE_LOG.getName())||blockName.equals(Blocks.STRIPPED_SPRUCE_LOG.getName());
    }

    private MutableText getBlockName(World world, BlockPos pos) {
        return world.getBlockState(pos).getBlock().getName();
    }

    private void burnOtherStripedLog(World world,BlockPos pos) {
        if(isStripedLog(getBlockName(world,pos.up()))) {
            world.setBlockState(pos.up(), Machines.burningCharcoalHeap.getDefaultState());
        }if(isStripedLog(getBlockName(world,pos.down()))) {
            world.setBlockState(pos.down(), Machines.burningCharcoalHeap.getDefaultState());
        }if(isStripedLog(getBlockName(world,pos.west()))) {
            world.setBlockState(pos.west(), Machines.burningCharcoalHeap.getDefaultState());
        }if(isStripedLog(getBlockName(world,pos.east()))) {
            world.setBlockState(pos.east(), Machines.burningCharcoalHeap.getDefaultState());
        }if(isStripedLog(getBlockName(world,pos.north()))) {
            world.setBlockState(pos.north(), Machines.burningCharcoalHeap.getDefaultState());
        }if(isStripedLog(getBlockName(world,pos.south()))) {
            world.setBlockState(pos.south(), Machines.burningCharcoalHeap.getDefaultState());
        }
    }

    @Override
    public void tick()
    {
        time++;
        if(time==100)
        {
            burnOtherStripedLog(world,pos);
            if(getBlockName(world,pos.up()).equals(Blocks.AIR.getName())||
                    getBlockName(world,pos.down()).equals(Blocks.AIR.getName())||
                    getBlockName(world,pos.east()).equals(Blocks.AIR.getName())||
                    getBlockName(world,pos.west()).equals(Blocks.AIR.getName())||
                    getBlockName(world,pos.north()).equals(Blocks.AIR.getName())||
                    getBlockName(world,pos.south()).equals(Blocks.AIR.getName())) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState());
            }else{
                world.setBlockState(pos, DetailedTechnology.charcoalHeap.getDefaultState());
            }
        }
    }

}
