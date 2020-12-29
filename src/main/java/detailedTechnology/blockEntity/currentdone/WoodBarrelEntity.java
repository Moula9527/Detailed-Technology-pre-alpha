package detailedTechnology.blockEntity.currentdone;

import detailedTechnology.blockEntity.currentdone.TankUtilties;
import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.group.Machines;
import detailedTechnology.group.Pipes;
import detailedTechnology.group.currentdone.Materials;
import detailedTechnology.group.currentdone.Ores;
import detailedTechnology.group.currentdone.Tools;
import detailedTechnology.gui.currentdone.BarrelScreenHandler;
import detailedTechnology.gui.currentdone.CrucibleScreenHandler;
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


public class WoodBarrelEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable{

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(2, ItemStack.EMPTY);

    public static final int MaximumCapacitance = 16000;

    public String liquidName;
    public int liquidAmount;
    public TankUtilties tankUtilties;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            if(index==0){
                for (int i = 0; i < Materials.LiquidList.size(); i++) {
                    if(liquidName.equals(Materials.LiquidList.get(i))) return i;
                }
                return -1;
            } else{
                return liquidAmount;
            }
        }
        @Override
        public void set(int index, int value) {
            liquidAmount = value;
        }

        //this is supposed to return the amount of integers you have in your delegate, in our example only one
        @Override
        public int size() {
            return 2;
        }
    };

    public WoodBarrelEntity(){
        super(Pipes.woodBarrelEntity);
        liquidName="air";
        liquidAmount=0;
        tankUtilties = new TankUtilties(MaximumCapacitance,liquidAmount,liquidName);
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
        liquidName = tag.getString("liquid name");
        liquidAmount = tag.getInt("liquid amount");
        tankUtilties = new TankUtilties(MaximumCapacitance,liquidAmount,liquidName);
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        Inventories.fromTag(tag,inventory);
        tag.putString("liquid name",liquidName);
        tag.putInt("liquid amount",liquidAmount);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new BarrelScreenHandler(syncId, playerInventory, this,propertyDelegate);
    }


    private void checkBreak() {
        if (this.liquidName.equals("lava") ) {
            assert world != null;
            world.breakBlock(pos, false);
        }
    }

    public void loadTank(TankUtilties tankUtilties){
        this.tankUtilties = tankUtilties;
        this.liquidName = tankUtilties.liquidName;
        this.liquidAmount = tankUtilties.liquidAmount;
    }

    @Override
    public void tick() {
        tankUtilties.InventoryManipulate((Inventory)this);
        this.liquidName = tankUtilties.liquidName;
        this.liquidAmount = tankUtilties.liquidAmount;
        checkBreak();
    }
}
