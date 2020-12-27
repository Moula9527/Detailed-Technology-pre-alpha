package detailedTechnology.blockEntity;

import detailedTechnology.Interfaces.ImplementedInventory;
import detailedTechnology.group.Machines;
import detailedTechnology.group.Materials;
import detailedTechnology.group.Ores;
import detailedTechnology.gui.CrucibleScreenHandler;
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
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Tickable;
import net.minecraft.util.collection.DefaultedList;

import java.util.Objects;

public class ClayIngotModelEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable{

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public String liquidName = "air";
    public float temperature = 20;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            if(index==0){
                return (int)temperature;
            }

                for (int i = 0; i < Materials.MATERIAL_STATUSES.size(); i++) {
                    if(liquidName.equals(Materials.MATERIAL_STATUSES.get(i).getName())) return i;
                }
                return -1;
        }
        @Override
        public void set(int index, int value) {
            temperature = value;
        }

        //this is supposed to return the amount of integers you have in your delegate, in our example only one
        @Override
        public int size() {
            return 2;
        }
    };

    public ClayIngotModelEntity(){
        super(Machines.clayIngotModelEntity);
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
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new CrucibleScreenHandler(syncId, playerInventory, this,propertyDelegate);
    }

    private void updateTemperature(){
        float temperature = 20;
        float specificHeatRatio = 0.1f;
        this.temperature+=(temperature-this.temperature)/50.0*specificHeatRatio;
    }

    private void updateLiquid(Inventory inventory){
        float solidifyTemp=0;
        for (int i = 0; i < Materials.MATERIAL_STATUSES.size(); i++) {
            if(Materials.MATERIAL_STATUSES.get(i).getName().equals(liquidName)){
                solidifyTemp = Materials.MATERIAL_STATUSES.get(i).getBoilingPoint();
            }
        }
        if(temperature<=solidifyTemp){
            int itemCount = inventory.getStack(0).getCount();
            switch (liquidName) {
                case "copper":
                    if (itemCount == 0) {
                        inventory.setStack(0, Ores.copperIngot.getDefaultStack());
                        liquidName = "air";
                    } else if (itemCount < 64) {
                        inventory.getStack(0).setCount(itemCount + 1);
                        liquidName = "air";
                    }
                    break;
                case "tin":
                    if (itemCount == 0) {
                        inventory.setStack(0, Ores.tinIngot.getDefaultStack());
                        liquidName = "air";
                    } else if (itemCount < 64) {
                        inventory.getStack(0).setCount(itemCount + 1);
                        liquidName = "air";
                    }
                    break;
                case "bronze":
                    if (itemCount == 0) {
                        inventory.setStack(0, Ores.bronzeIngot.getDefaultStack());
                        liquidName = "air";
                    } else if (itemCount < 64) {
                        inventory.getStack(0).setCount(itemCount + 1);
                        liquidName = "air";
                    }
                    break;
            }

        }

    }

    private void checkBreak() {
        if (this.temperature > Materials.BRICK.getMeltingPoint()) {
            assert world != null;
            world.breakBlock(pos, true);
        }
    }

    @Override
    public void tick() {
        updateTemperature();
        updateLiquid((Inventory)this);
        checkBreak();
    }
}
