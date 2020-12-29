package detailedTechnology.blocks.currentdone;

import detailedTechnology.blockEntity.currentdone.FireStarterBlockEntity;
import detailedTechnology.group.Machines;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.tools.FabricToolTags;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.Items;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Properties;
import net.minecraft.text.MutableText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class FireStarterBlock extends HorizontalFacingBlock implements BlockEntityProvider {
    public FireStarterBlock()
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
    public BlockEntity createBlockEntity(BlockView blockView) {
        return new FireStarterBlockEntity();
    }

    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return (BlockState)this.getDefaultState().with(FACING, ctx.getPlayerFacing());
    }

    @Override
    public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult hit) {
        if (!world.isClient) {
            if(player.isSneaking()) {
                Inventory inventory = (Inventory)world.getBlockEntity(pos);
                if(inventory!=null&&inventory.getStack(0).getName().equals(Items.CHARCOAL.getName())) {
                    int charcoalCount = inventory.getStack(0).getCount();
                    if(charcoalCount==1)
                    {
                        inventory.setStack(0,Items.AIR.getDefaultStack());
                    }else{
                        inventory.getStack(0).setCount(charcoalCount-1);
                    }
                    Direction direction = state.get(FACING);
                    BlockPos backPos = pos.east();
                    switch (direction) {
                        case NORTH: backPos=pos.north();
                            break;
                        case SOUTH: backPos=pos.south();
                            break;
                        case WEST: backPos=pos.west();
                    }
                    MutableText blockName = world.getBlockState(backPos).getBlock().getName();
                    if(blockName.equals(Blocks.STRIPPED_ACACIA_LOG.getName())||
                            blockName.equals(Blocks.STRIPPED_BIRCH_LOG.getName())||
                            blockName.equals(Blocks.STRIPPED_DARK_OAK_LOG.getName())||
                            blockName.equals(Blocks.STRIPPED_OAK_LOG.getName())||
                            blockName.equals(Blocks.STRIPPED_JUNGLE_LOG.getName())||
                            blockName.equals(Blocks.STRIPPED_SPRUCE_LOG.getName())) {
                        world.setBlockState(backPos, Machines.burningCharcoalHeap.getDefaultState());
                    }
                }
            } else {
                NamedScreenHandlerFactory screenHandlerFactory = state.createScreenHandlerFactory(world, pos);
                if (screenHandlerFactory != null) {
                    player.openHandledScreen(screenHandlerFactory);
                }
            }
        }
        return ActionResult.SUCCESS;
    }

    //drop inside items when break
    @Override
    public void onStateReplaced(BlockState state, World world, BlockPos pos, BlockState newState, boolean moved) {
        if (state.getBlock() != newState.getBlock()) {
            BlockEntity blockEntity = world.getBlockEntity(pos);
            if (blockEntity instanceof FireStarterBlockEntity) {
                ItemScatterer.spawn(world, pos, (FireStarterBlockEntity)blockEntity);
                // update comparators
                world.updateComparators(pos,this);
            }
            super.onStateReplaced(state, world, pos, newState, moved);
        }
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