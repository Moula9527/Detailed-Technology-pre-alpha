package detailedTechnology.blockEntity.currentdone;

import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.blocks.currentdone.StoneMile;
import detailedTechnology.group.Machines;
import detailedTechnology.gui.currentdone.StoneMileScreenHandler;
import detailedTechnology.recipe.StoneMileRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

import java.util.Objects;

public class StoneMileEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable{

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
    private int workingTime;
    private int currentCraftingId;
    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            return index==0?workingTime:currentCraftingId;
        }

        @Override
        public void set(int index, int value) {
            workingTime = value;
        }

        //this is supposed to return the amount of integers you have in your delegate, in our example only one
        @Override
        public int size() {
            return 2 ;
        }
    };

    public StoneMileEntity(){
        super(Machines.stoneMileEntity);
        workingTime=0;
        currentCraftingId=-1;
    }

    @Override
    public DefaultedList<ItemStack> getItems() {
        return inventory;
    }

    @Override
    public Text getDisplayName() {
        return new TranslatableText(getCachedState().getBlock().getTranslationKey());
    }

    @Override
    public void fromTag(BlockState state,CompoundTag tag) {
        super.fromTag(state,tag);
        Inventories.fromTag(tag,inventory);
        workingTime = tag.getInt("working time");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        tag.putInt("working time",workingTime);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new StoneMileScreenHandler(syncId, playerInventory, this,propertyDelegate);
    }

    public void getCurrentCraftingId(Inventory inventory){
        if(inventory.getStack(0).getName().getString().equals(Items.AIR.getName().getString())){
            currentCraftingId = -1;
        }else for (int i = 0; i < StoneMileRecipe.products.size(); i++) {
            if(inventory.getStack(0).getName().getString().equals(StoneMileRecipe.ingredients.get(i).getName().getString())){
                currentCraftingId = i;
                break;
            }
        }
    }

    public void getWorkingTime(){
        this.workingTime = ((StoneMile) Objects.requireNonNull(this.getWorld()).getBlockState(
                this.getPos()).getBlock()).workingTime;
    }

    @Override
    public void tick() {
        getWorkingTime();
        getCurrentCraftingId((Inventory) this);
    }
}
