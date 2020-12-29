package detailedTechnology.blockEntity.currentdone;

import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.group.Machines;
import detailedTechnology.gui.currentdone.CokeOvenScreenHandler;
import detailedTechnology.recipe.CokeOvenRecipe;
import detailedTechnology.structure.CokeOvenStructure;
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

import static net.minecraft.block.HorizontalFacingBlock.FACING;

public class CokeOvenEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable{

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);
    int checkTime = 0;
    boolean needInitialise = true;
    boolean validness;
    private int workingTime;
    private int currentCraftingId;
    public CokeOvenStructure cokeOvenStructure;
    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            if(index==0){
                return  workingTime;
            }else if(index==1){
                return  currentCraftingId;
            }else{
                return validness?0:1;
            }
        }

        @Override
        public void set(int index, int value) {
            workingTime = value;
        }

        //this is supposed to return the amount of integers you have in your delegate, in our example only one
        @Override
        public int size() {
            return 3 ;
        }
    };

    public CokeOvenEntity(){
        super(Machines.cokeOvenEntity);
        workingTime=0;
        currentCraftingId=-1;
        validness = false;
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
        validness = tag.getBoolean("validness");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        tag.putInt("working time",workingTime);
        tag.putBoolean("validness",validness);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CokeOvenScreenHandler(syncId, playerInventory, this,propertyDelegate);
    }

    public void getCurrentCraftingId(Inventory inventory){
        if(inventory.getStack(0).getName().getString().equals(Items.AIR.getName().getString())){
            currentCraftingId = -1;
        }else for (int i = 0; i < CokeOvenRecipe.products.size(); i++) {
            if(inventory.getStack(0).getName().getString().equals(CokeOvenRecipe.ingredients.get(i).getName().getString())){
                currentCraftingId = i;
                break;
            }
        }
    }

    @Override
    public void tick() {
        int tempCid = currentCraftingId;
        getCurrentCraftingId((Inventory) this);
        if(tempCid!=currentCraftingId){
            workingTime=0;
        }
        else if(currentCraftingId!=-1&&validness){
            if(workingTime>=CokeOvenRecipe.timeRequires.get(currentCraftingId)){
                int liquidAmount = CokeOvenRecipe.tryCraft((Inventory)this);
                if(liquidAmount!=0){
                    workingTime=0;
                    assert world != null;
                    FirebrickBarrelEntity firebrickBarrelEntity = ((FirebrickBarrelEntity) Objects.requireNonNull(
                            world.getBlockEntity(cokeOvenStructure.firebrickBarrelPos)));
                    TankUtilties tank = firebrickBarrelEntity.tankUtilties;
                    tank.recieveLiquie("creosote",liquidAmount);
                    System.out.println(tank.liquidName);
                }
            }else{
                workingTime++;
            }
        }
        if(checkTime==0){
            if(needInitialise){
                assert world != null;
                cokeOvenStructure = new CokeOvenStructure(world,pos,world.getBlockState(pos).get(FACING));
                needInitialise = false;
            }
            validness = cokeOvenStructure.isValid();
        }
        checkTime=(checkTime+1)%100;
    }
}
