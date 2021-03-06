package detailedTechnology.items.generalclass;

import detailedTechnology.group.Pipes;
import detailedTechnology.group.currentdone.Tools;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.*;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class DetailedBucketItem extends BucketItem {
    public final String material;
    private final Fluid fluid;

    public DetailedBucketItem(Settings settings,String material, Fluid fluid) {
        super(fluid,settings);
        this.fluid = fluid;
        this.material=material;
    }

    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack itemStack = user.getStackInHand(hand);
        BlockHitResult hitResult = raycast(world, user, this.fluid == Fluids.EMPTY ? RaycastContext.FluidHandling.SOURCE_ONLY : RaycastContext.FluidHandling.NONE);
        if (hitResult.getType() == HitResult.Type.MISS) {
            return TypedActionResult.pass(itemStack);
        } else if (hitResult.getType() != HitResult.Type.BLOCK) {
            return TypedActionResult.pass(itemStack);
        } else {
            BlockPos blockPos = hitResult.getBlockPos();
            Direction direction = hitResult.getSide();
            BlockPos blockPos2 = blockPos.offset(direction);
            if (world.canPlayerModifyAt(user, blockPos) && user.canPlaceOn(blockPos2, direction, itemStack)) {
                BlockState blockState;
                if (this.fluid == Fluids.EMPTY) {
                    blockState = world.getBlockState(blockPos);
                    if (blockState.getBlock() instanceof FluidDrainable) {
                        Fluid fluid = ((FluidDrainable)blockState.getBlock()).tryDrainFluid(world, blockPos, blockState);
                        if (fluid != Fluids.EMPTY) {
                            user.incrementStat(Stats.USED.getOrCreateStat(this));
                            user.playSound(fluid.isIn(FluidTags.LAVA) ? SoundEvents.ITEM_BUCKET_FILL_LAVA : SoundEvents.ITEM_BUCKET_FILL, 1.0F, 1.0F);
                            Item fullBucket = fluid.equals(Fluids.LAVA) ?
                                    (material.equals("clay") ? Pipes.clayLavaBucket : Pipes.copperLavaBucket) :
                                    (material.equals("clay") ? Pipes.clayWaterBucket : Pipes.copperWaterBucket);
                            ItemStack itemStack2 = ItemUsage.method_30012(itemStack, user, new ItemStack(fullBucket));
                            if (!world.isClient) {
                                Criteria.FILLED_BUCKET.trigger((ServerPlayerEntity)user, new ItemStack(fullBucket));
                            }

                            return TypedActionResult.success(itemStack2, world.isClient());
                        }
                    }
                    return TypedActionResult.fail(itemStack);
                } else {
                    blockState = world.getBlockState(blockPos);
                    BlockPos blockPos3 = blockState.getBlock() instanceof FluidFillable && this.fluid == Fluids.WATER ? blockPos : blockPos2;
                    if (this.placeFluid(user, world, blockPos3, hitResult)) {
                        this.onEmptied(world, itemStack, blockPos3);
                        if (user instanceof ServerPlayerEntity) {
                            Criteria.PLACED_BLOCK.trigger((ServerPlayerEntity)user, blockPos3, itemStack);
                        }

                        user.incrementStat(Stats.USED.getOrCreateStat(this));
                        return TypedActionResult.success(this.getEmptiedStack(itemStack, user), world.isClient());
                    } else {
                        return TypedActionResult.fail(itemStack);
                    }
                }
            } else {
                return TypedActionResult.fail(itemStack);
            }
        }
    }

    protected ItemStack getEmptiedStack(ItemStack stack, PlayerEntity player) {
        if(player.abilities.creativeMode) return stack;
        else if(this.material.equals("clay")){
            if(this.fluid.equals(Fluids.LAVA)){
                return Items.AIR.getDefaultStack();
            }
            return Tools.clayBucket.getDefaultStack();
        } else{
            return Tools.copperBucket.getDefaultStack();
        }
    }

    public boolean placeFluid(@Nullable PlayerEntity player, World world, BlockPos pos,
                              @Nullable BlockHitResult blockHitResult) {
        if (!(this.fluid instanceof FlowableFluid)) {
            return false;
        } else {
            BlockState blockState = world.getBlockState(pos);
            Block block = blockState.getBlock();
            Material material = blockState.getMaterial();
            boolean bl = blockState.canBucketPlace(this.fluid);
            boolean bl2 = blockState.isAir() || bl || block instanceof FluidFillable &&
                    ((FluidFillable)block).canFillWithFluid(world, pos, blockState, this.fluid);
            if (!bl2) {//can't place
                return blockHitResult != null && this.placeFluid(player, world,
                        blockHitResult.getBlockPos().offset(blockHitResult.getSide()), (BlockHitResult)null);
            } else if (world.getDimension().isUltrawarm() && this.fluid.isIn(FluidTags.WATER)) {//PUT WATER IN HELL
                int i = pos.getX();
                int j = pos.getY();
                int k = pos.getZ();
                world.playSound(player, pos, SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.5F,
                        2.6F + (world.random.nextFloat() - world.random.nextFloat()) * 0.8F);
                for(int l = 0; l < 8; ++l) {
                    world.addParticle(ParticleTypes.LARGE_SMOKE, (double)i + Math.random(), (double)j + Math.random(),
                            (double)k + Math.random(), 0.0D, 0.0D, 0.0D);
                }
                return true;
            } else if (block instanceof FluidFillable && this.fluid == Fluids.WATER) {//put water in fillable block
                ((FluidFillable)block).tryFillWithFluid(world, pos, blockState, ((FlowableFluid)this.fluid).getStill(
                        false));
                this.playEmptyingSound(player, world, pos);
                return true;
            } else {//put other
                if (!world.isClient && bl && !material.isLiquid()) {//break block like sugar canes
                    world.breakBlock(pos, true);
                }//place water
                if (!world.setBlockState(pos, this.fluid.getDefaultState().getBlockState(), 11) && !blockState.getFluidState().isStill()) {
                    return false;
                } else {
                    this.playEmptyingSound(player, world, pos);
                    return true;
                }
            }
        }
    }
}
