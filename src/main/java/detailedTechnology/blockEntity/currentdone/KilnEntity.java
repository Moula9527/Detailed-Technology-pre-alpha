package detailedTechnology.blockEntity.currentdone;

import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.group.Machines;
import detailedTechnology.gui.currentdone.KilnScreenHandler;
import detailedTechnology.recipe.KilnRecipe;
import detailedTechnology.recipe.general.ShapedRecipe;
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

public class KilnEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(11, ItemStack.EMPTY);

    private int currentCraftingId;
    private int workingTime;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            return index==0?workingTime: currentCraftingId;
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

    public KilnEntity(){
        super(Machines.kilnEntity);
        currentCraftingId =-1;
        workingTime = 0;
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
        currentCraftingId = tag.getInt("craft id");
        workingTime = tag.getInt("working time");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        tag.putInt("craft id",currentCraftingId);
        tag.putInt("working time",workingTime);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new KilnScreenHandler(syncId, playerInventory, this,propertyDelegate);
    }

    public void tryCraft(Inventory inventory) {
        int stackCount = 0;
        String[] itemNames = new String[9];
        int[] itemNums = new int[9];
        ShapedRecipe content;
        String fuelName = inventory.getStack(9).getName().getString();
        int fuelCount = inventory.getStack(9).getCount();
        String resultItemName = inventory.getStack(10).getName().getString();
        int resultItemNum = inventory.getStack(10).getCount();
        if(currentCraftingId ==-1) {
            for(int i = 0; i< KilnRecipe.CONTENTS.size(); i++) {
                content = KilnRecipe.CONTENTS.get(i);
                for (int j = 0; j < 9; j++) {
                    if(!inventory.getStack(j).getName().getString().equals(content.ingredients[j].getName().getString())||
                            inventory.getStack(j).getCount()<content.ingredientsNum[j]) {
                        break;
                    }if(j==8&&((resultItemName.equals(content.result.getName().getString())&&
                            resultItemNum+content.resultNum<=64)||
                            resultItemName.equals(Items.AIR.getName().getString()))){
                        if(workingTime==0){
                            if(fuelName.equals(Items.CHARCOAL.getName().getString())&&
                                    fuelCount>=KilnRecipe.charcoalCosts.get(i)){
                                if(fuelCount==KilnRecipe.charcoalCosts.get(i)){
                                    inventory.setStack(9,Items.AIR.getDefaultStack());
                                }else{
                                    inventory.getStack(9).setCount(fuelCount-KilnRecipe.charcoalCosts.get(i));
                                }
                                currentCraftingId = i;
                                for (int k = 0; k < 9; k++) {
                                    if(content.ingredientsNum[k]==inventory.getStack(k).getCount()){
                                        inventory.setStack(k,Items.AIR.getDefaultStack());
                                    }else{
                                        inventory.getStack(k).setCount(inventory.getStack(k).getCount()-content.ingredientsNum[k]);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        else if(workingTime<400){
                workingTime++;
            }else if(resultItemName.equals(Items.AIR.getName().getString())){
                inventory.setStack(10,KilnRecipe.CONTENTS.get(currentCraftingId).result.getDefaultStack());
                inventory.getStack(10).setCount(KilnRecipe.CONTENTS.get(currentCraftingId).resultNum);
                workingTime=0;
                currentCraftingId =-1;
            }else if(resultItemName.equals(KilnRecipe.CONTENTS.get(currentCraftingId).result.getName().getString())&&
                resultItemNum+KilnRecipe.CONTENTS.get(currentCraftingId).resultNum<=64){
                inventory.getStack(10).setCount(resultItemNum+KilnRecipe.CONTENTS.get(currentCraftingId).resultNum);
                workingTime=0;
                currentCraftingId =-1;
            }
    }

    @Override
    public void tick()
    {
        tryCraft((Inventory) this);
    }

}
