package detailedTechnology.structure;

import detailedTechnology.DetailedTechnology;
import detailedTechnology.group.Machines;
import detailedTechnology.group.Pipes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class CokeOvenStructure {

    public World world;
    public BlockPos controllerPos;
    public BlockPos fireStarterBlockPos;
    public BlockPos firebrickBarrelPos;
    public BlockPos[] firebrickBlocksPos = new BlockPos[24];

    private void initialise(Direction direction){
        switch (direction){
            case NORTH:
                fireStarterBlockPos = controllerPos.north();
                firebrickBlocksPos[17] = fireStarterBlockPos.north();
                firebrickBlocksPos[18] = firebrickBlocksPos[17].west();
                firebrickBlocksPos[19] = firebrickBlocksPos[17].east();
                firebrickBlocksPos[20] = controllerPos.west();
                firebrickBlocksPos[21] = controllerPos.east();
                firebrickBlocksPos[22] = fireStarterBlockPos.west();
                firebrickBlocksPos[23] = fireStarterBlockPos.east();
                break;
            case SOUTH:
                fireStarterBlockPos = controllerPos.south();
                firebrickBlocksPos[17] = fireStarterBlockPos.south();
                firebrickBlocksPos[18] = firebrickBlocksPos[17].west();
                firebrickBlocksPos[19] = firebrickBlocksPos[17].east();
                firebrickBlocksPos[20] = controllerPos.west();
                firebrickBlocksPos[21] = controllerPos.east();
                firebrickBlocksPos[22] = fireStarterBlockPos.west();
                firebrickBlocksPos[23] = fireStarterBlockPos.east();
                break;
            case WEST:
                fireStarterBlockPos = controllerPos.west();
                firebrickBlocksPos[17] = fireStarterBlockPos.west();
                firebrickBlocksPos[18] = firebrickBlocksPos[17].north();
                firebrickBlocksPos[19] = firebrickBlocksPos[17].south();
                firebrickBlocksPos[20] = controllerPos.north();
                firebrickBlocksPos[21] = controllerPos.south();
                firebrickBlocksPos[22] = fireStarterBlockPos.north();
                firebrickBlocksPos[23] = fireStarterBlockPos.south();
                break;
            case EAST:
                fireStarterBlockPos = controllerPos.east();
                firebrickBlocksPos[17] = fireStarterBlockPos.east();
                firebrickBlocksPos[18] = firebrickBlocksPos[17].north();
                firebrickBlocksPos[19] = firebrickBlocksPos[17].south();
                firebrickBlocksPos[20] = controllerPos.north();
                firebrickBlocksPos[21] = controllerPos.south();
                firebrickBlocksPos[22] = fireStarterBlockPos.north();
                firebrickBlocksPos[23] = fireStarterBlockPos.south();
                break;
        }
        firebrickBarrelPos = fireStarterBlockPos.down();
        firebrickBlocksPos[0] = fireStarterBlockPos.up();
        firebrickBlocksPos[1] = firebrickBlocksPos[0].north();
        firebrickBlocksPos[2] = firebrickBlocksPos[0].south();
        firebrickBlocksPos[3] = firebrickBlocksPos[0].west();
        firebrickBlocksPos[4] = firebrickBlocksPos[0].east();
        firebrickBlocksPos[5] = firebrickBlocksPos[1].west();
        firebrickBlocksPos[6] = firebrickBlocksPos[1].east();
        firebrickBlocksPos[7] = firebrickBlocksPos[2].west();
        firebrickBlocksPos[8] = firebrickBlocksPos[2].east();
        firebrickBlocksPos[9] = firebrickBarrelPos.north();
        firebrickBlocksPos[10] = firebrickBarrelPos.south();
        firebrickBlocksPos[11] = firebrickBarrelPos.west();
        firebrickBlocksPos[12] = firebrickBarrelPos.east();
        firebrickBlocksPos[13] = firebrickBlocksPos[9].west();
        firebrickBlocksPos[14] = firebrickBlocksPos[9].east();
        firebrickBlocksPos[15] = firebrickBlocksPos[10].west();
        firebrickBlocksPos[16] = firebrickBlocksPos[10].east();
    }

    private static String getBlockName(World world,BlockPos pos){
        return world.getBlockState(pos).getBlock().getName().getString();
    }

    public boolean isValid(){
        if(world==null) return false;
        if(!getBlockName(world,controllerPos).equals(Machines.cokeOven.getName().getString())) {
            return false;
        }
        if(!getBlockName(world,fireStarterBlockPos).equals(Machines.fireStarterBlock.getName().getString())) {
            return false;
        }
        if(!getBlockName(world,firebrickBarrelPos).equals(Pipes.firebrickBarrel.getName().getString())) {
            return false;
        }
        for(int i=0;i<24;i++){
            if(!getBlockName(world,firebrickBlocksPos[i]).equals(DetailedTechnology.firebrickBlock.getName().getString())) {
                return false;
            }
        }
        return true;
    }

    public CokeOvenStructure(World world,BlockPos controllerPos,Direction facing){
        this.world=world;
        this.controllerPos=controllerPos;
        initialise(facing);
    }

}
