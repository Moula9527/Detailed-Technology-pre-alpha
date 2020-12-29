package detailedTechnology.blocks.currentdone;

import detailedTechnology.blockEntity.currentdone.BrickCrucibleEntity;
import detailedTechnology.blockEntity.currentdone.ClayGearModelEntity;
import detailedTechnology.blockEntity.currentdone.ClayPlateModelEntity;
import detailedTechnology.group.Machines;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

public class ClayGearModel extends HorizontalFacingBlock implements BlockEntityProvider{
    private static final VoxelShape SHAPE = Block.createCuboidShape(0, 0, 0, 16, 4, 16);
    boolean useTime=false;
    public ClayGearModel()
    {
        super(FabricBlockSettings.of(Material.STONE, MaterialColor.STONE)
                .strength(2f,3f)
                .requiresTool().breakByTool(FabricToolTags.PICKAXES, 2));
        setDefaultState(this.stateManager.getDefaultState().with(Properties.HORIZONTAL_FACING, Direction.NORTH));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> stateManager) {
        stateManager.add(Properties.HORIZONTAL_FACING);
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        Direction dir = state.get(FACING);
        return SHAPE;
    }

    @Override
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new ClayGearModelEntity();
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if (player.isSneaking()){
                Direction direction = state.get(FACING);
                BlockPos backPos = pos.east();
                switch (direction) {
                    case NORTH:
                        backPos = pos.north();
                        break;
                    case SOUTH:
                        backPos = pos.south();
                        break;
                    case WEST:
                        backPos = pos.west();
                }if (world.getBlockState(backPos).getBlock().getName().getString().equals(
                        Machines.brickCrucible.getName().getString())&&
                        ((ClayGearModelEntity) Objects.requireNonNull(world.getBlockEntity(pos))).liquidName.equals("air")) {
                    if(useTime){
                        ((ClayGearModelEntity) Objects.requireNonNull(world.getBlockEntity(pos))).receiveUnitLiquid(
                                ((BrickCrucibleEntity) Objects.requireNonNull(world.getBlockEntity(backPos))).getUnitLiquid(),
                                ((BrickCrucibleEntity) Objects.requireNonNull(world.getBlockEntity(backPos))).temperature);
                    }
                    useTime=!useTime;
                }
            } else{
                NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
                if (screenHandlerFactory != null) {
                    player.openHandledScreen(screenHandlerFactory);
                }
            }
        }
        return ActionResult.PASS;
    }

    //drop inside items when break
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof ClayGearModelEntity) {
                ItemScatterer.spawn(world, pos, (ClayGearModelEntity)blockEntity);
                // update comparators
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override
    public boolean hasComparatorOutput(BlockState state) {
        return true;
    }

    @Override
    public int getComparatorOutput(BlockState state, World world, BlockPos pos) {
        return ScreenHandler.calculateComparatorOutput(world.getBlockEntity(pos));
    }

    @Nullable
    public NamedScreenHandlerFactory createScreenHandlerFactory(BlockState state, World world, BlockPos pos) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        return blockEntity instanceof NamedScreenHandlerFactory ? (NamedScreenHandlerFactory)blockEntity : null;
    }
}
