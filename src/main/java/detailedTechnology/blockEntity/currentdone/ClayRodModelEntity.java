package detailedTechnology.blockEntity.currentdone;

import detailedTechnology.code.ImplementedInventory;
import detailedTechnology.group.Machines;
import detailedTechnology.group.currentdone.Materials;
import detailedTechnology.group.currentdone.Ores;
import detailedTechnology.gui.currentdone.ClayModelScreenHandler;
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

public class ClayRodModelEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable {

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public String liquidName;
    public float temperature;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            if (index == 0) {
                return (int) temperature;
            }
            for (int i = 0; i < Materials.MATERIAL_STATUSES.size(); i++) {
                if (liquidName.equals(Materials.MATERIAL_STATUSES.get(i).getName())) return i;
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

    public ClayRodModelEntity() {
        super(Machines.clayRodModelEntity);
        liquidName = "air";
        temperature = 20;
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
    public void fromTag(BlockState state, CompoundTag tag) {
        super.fromTag(state, tag);
        Inventories.fromTag(tag, inventory);
        liquidName = tag.getString("liquid name");
        temperature = tag.getFloat("temperature");
    }

    @Override
    public CompoundTag toTag(CompoundTag tag) {
        super.toTag(tag);
        Inventories.toTag(tag, this.inventory);
        tag.putString("liquid name", liquidName);
        tag.putFloat("temperature", temperature);
        return tag;
    }

    @Override
    public ScreenHandler createMenu(int syncId, PlayerInventory playerInventory, PlayerEntity player) {
        return new ClayModelScreenHandler(syncId, playerInventory, this, propertyDelegate);
    }

    public void receiveUnitLiquid(String liquidName, float temperature) {
        float heatCapacitance = 15;
        for (int i = 0; i < Materials.MATERIAL_STATUSES.size(); i++) {
            if (liquidName.equals(Materials.MATERIAL_STATUSES.get(i).getName())) {
                heatCapacitance = Materials.MATERIAL_STATUSES.get(i).getSpecificHeatCapacityOfVolume() / 9;
            }
        }
        this.temperature = (this.temperature * 15 + temperature * heatCapacitance) / (15 + heatCapacitance);
        this.liquidName = liquidName;
    }

    private void updateTemperature() {
        float temperature = 20;
        this.temperature += (temperature - this.temperature) / 100.0;
    }

    private void updateLiquid(Inventory inventory) {
        float solidifyTemp = 0;
        for (int i = 0; i < Materials.MATERIAL_STATUSES.size(); i++) {
            if (Materials.MATERIAL_STATUSES.get(i).getName().equals(liquidName)) {
                solidifyTemp = Materials.MATERIAL_STATUSES.get(i).getMeltingPoint();
                break;
            }
        }
        if (temperature <= solidifyTemp) {
            int itemCount = inventory.getStack(0).getCount();
            switch (liquidName) {
                case "copper":
                    if (itemCount == 0) {
                        inventory.setStack(0, Ores.copperRod.getDefaultStack());
                        inventory.getStack(0).setCount(2);
                        liquidName = "air";
                    } else if (itemCount < 63&&
                            inventory.getStack(0).getName().getString().equals(Ores.copperRod.getName().getString())) {
                        inventory.getStack(0).setCount(itemCount + 2);
                        liquidName = "air";
                    }
                    break;
                case "tin":
                    if (itemCount == 0) {
                        inventory.setStack(0, Ores.tinRod.getDefaultStack());
                        inventory.getStack(0).setCount(2);
                        liquidName = "air";
                    } else if (itemCount < 63&&
                            inventory.getStack(0).getName().getString().equals(Ores.tinRod.getName().getString())) {
                        inventory.getStack(0).setCount(itemCount + 2);
                        liquidName = "air";
                    }
                    break;
                case "bronze":
                    if (itemCount == 0) {
                        inventory.setStack(0, Ores.bronzeRod.getDefaultStack());
                        inventory.getStack(0).setCount(2);
                        liquidName = "air";
                    } else if (itemCount < 63&&
                            inventory.getStack(0).getName().getString().equals(Ores.bronzeRod.getName().getString())) {
                        inventory.getStack(0).setCount(itemCount + 2);
                        liquidName = "air";
                    }
                    break;
            }

        }

    }

    private void checkBreak() {
        if (this.temperature > Materials.BRICK.getMeltingPoint()) {
            assert world != null;
            world.breakBlock(pos, false);
        }
    }

    @Override
    public void tick() {
        updateTemperature();
        updateLiquid((Inventory) this);
        checkBreak();
    }
}
