package detailedTechnology.blocks.currentdone;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class StoneMileRunner extends HorizontalFacingBlock {
    private static final VoxelShape SHAPE =
            Block.createCuboidShape(1, 0, 1, 15, 4, 15);

    public StoneMileRunner()
    {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.STONE)
                .strength(2f,3f)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES, 1));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPE;
    }


    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override///
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            Direction currentDirection = state.get(Properties.HORIZONTAL_FACING);
            if(currentDirection.equals(Direction.NORTH)) {
                world.setBlockState(pos,state.with(Properties.HORIZONTAL_FACING,Direction.EAST));
            } else if(currentDirection.equals(Direction.EAST)) {
                world.setBlockState(pos,state.with(Properties.HORIZONTAL_FACING,Direction.SOUTH));
            } else if(currentDirection.equals(Direction.SOUTH)) {
                world.setBlockState(pos,state.with(Properties.HORIZONTAL_FACING,Direction.WEST));
            } else if(currentDirection.equals(Direction.WEST)) {
                world.setBlockState(pos, state.with(Properties.HORIZONTAL_FACING, Direction.NORTH));
            }
        }
        return ActionResult.SUCCESS;
    }


}