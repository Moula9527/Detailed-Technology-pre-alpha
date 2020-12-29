package detailedTechnology.blockEntity.currentdone;

import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.group.Machines;
import detailedTechnology.group.currentdone.Materials;
import detailedTechnology.gui.currentdone.CombustionChamberScreenHandler;
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

public class FirebrickCombustionChamberEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable {


    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public static final float charcoalUnitTemperature = 550;
    public static final float cokeUnitTemperature = 1000;
    public static final int charcoalUnitTime = 3600;
    public static final int cokeUnitTime = 2000;

    private final float burningRate=1.75f;
    private int burningTime;
    private String fuelName;
    public float temperature;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            switch (index){
                case 0: return burningTime;
                case 1: return (int)temperature;
                case 2: if(fuelName.equals(Items.CHARCOAL.getName().getString())){
                    return (int)(burningRate*charcoalUnitTemperature);
                } else if(fuelName.equals(Materials.coke.getName().getString())){
                    return (int)(burningRate*cokeUnitTemperature);
                } else{
                    return 20;
                }
                default: return 0;
            }
        }
        @Override
        public void set(int index, int value) {
            burningTime = value;
        }

        //this is supposed to return the amount of integers you have in your delegate, in our example only one
        @Override
        public int size() {
            return 3;
        }
    };

    public FirebrickCombustionChamberEntity(){
        super(Machines.firebrickCombustionChamberEntity);
        burningTime=0;
        fuelName=Items.AIR.getName().getString();
        temperature=20;
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
        burningTime = tag.getInt("burning time");
        fuelName = tag.getString("fuel name");
        temperature = tag.getFloat("temperature");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        tag.putInt("burning time",burningTime);
        tag.putString("fuel name",fuelName);
        tag.putFloat("temperature", temperature);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CombustionChamberScreenHandler(syncId, playerInventory, this,propertyDelegate);
    }

    private void burnNext(Inventory inventory){
        fuelName = inventory.getStack(0).getName().getString();
        if(fuelName.equals(Items.CHARCOAL.getName().getString())){
            this.burningTime=(int)(charcoalUnitTime/burningRate);
            if(inventory.getStack(0).getCount()==1){
                inventory.setStack(0,Items.AIR.getDefaultStack());
            }else{
                inventory.getStack(0).setCount(inventory.getStack(0).getCount()-1);
            }
        }else if(fuelName.equals(Materials.coke.getName().getString())){
            this.burningTime=(int)(cokeUnitTime/burningRate);
            if(inventory.getStack(0).getCount()==1){
                inventory.setStack(0,Items.AIR.getDefaultStack());
            }else{
                inventory.getStack(0).setCount(inventory.getStack(0).getCount()-1);
            }
        }
    }

    private void getNextTemp(){
        if(fuelName.equals(Items.CHARCOAL.getName().getString())){
            temperature+=(charcoalUnitTemperature*burningRate-temperature)/100.0;
        }else if(fuelName.equals(Materials.coke.getName().getString())){
            temperature+=(cokeUnitTemperature*burningRate-temperature)/100.0;
        }
        burningTime--;
    }

    private void checkBreak() {
        if (this.temperature > Materials.FIREBRICK.getMeltingPoint()) {
            assert world != null;
            world.breakBlock(pos, false);
        }
    }

    @Override
    public void tick() {
        if(burningTime==0){
            burnNext((Inventory)this);
        }
        if(burningTime>0){
            getNextTemp();
        }
        temperature-=(temperature-20)/5000.0;
        checkBreak();
    }
}
