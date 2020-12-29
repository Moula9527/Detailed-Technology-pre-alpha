package detailedTechnology.blockEntity.currentdone;

import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.group.Machines;
import detailedTechnology.gui.currentdone.BronzeAnvilScreenHandler;
import detailedTechnology.recipe.AnvilRecipe;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.screen.NamedScreenHandlerFactory;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

import java.util.Objects;

public class BronzeAnvilEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(10, ItemStack.EMPTY);
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

    public BronzeAnvilEntity(){
        super(Machines.bronzeAnvilEntity);
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
        return new BronzeAnvilScreenHandler(syncId, playerInventory, this,propertyDelegate);
    }

    public boolean addWorkingTime(String tool,int level,PlayerEntity playerEntity){
        boolean result = false;
        if(currentCraftingId!=-1&&
        tool.equals(AnvilRecipe.TOOL_TYPE.get(currentCraftingId))&&level>=AnvilRecipe.TOOL_LEVEL.get(currentCraftingId)){
            result = workingTime==7;
            workingTime++;
        }
        if(result)Objects.requireNonNull(playerEntity).playSound(SoundEvents.BLOCK_ANVIL_PLACE,0.5f,1.0f);
        return result;
    }

    @Override
    public void tick() {
        int temp = AnvilRecipe.getRecipeId((Inventory) this);
        if(temp!=currentCraftingId){
            workingTime=0;
        }
        currentCraftingId = temp;
        if(workingTime==8){
            AnvilRecipe.tryCraft((Inventory)this);
            workingTime=0;
        }
    }
}
