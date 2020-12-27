package detailedTechnology.blockEntity;

import detailedTechnology.DetailedTechnology;
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

public class BrickCrucibleEntity extends BlockEntity implements ImplementedInventory, NamedScreenHandlerFactory, Tickable{

    private final DefaultedList<ItemStack> inventory = DefaultedList.ofSize(1, ItemStack.EMPTY);

    public static final float OriginalHeatCapacitance = 15;

    public String liquidName = "air";
    public int liquidAmount = 0;
    public float temperature = 20;
    public float heatCapacitance = OriginalHeatCapacitance;

    private final PropertyDelegate propertyDelegate = new PropertyDelegate() {

        @Override
        public int get(int index) {
            if(index==0){
                return (int)temperature;
            }
            if(index==1){
                return (int)heatCapacitance;
            }
            if(index==2){
                for (int i = 0; i < Materials.MATERIAL_STATUSES.size(); i++) {
                    if(liquidName.equals(Materials.MATERIAL_STATUSES.get(i).getName())) return i;
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
            return 4;
        }
    };

    public BrickCrucibleEntity(){
        super(Machines.brickCrucibleEntity);
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

    private void updateHeatCapacitance(Inventory inventory){
        if(inventory.getStack(0).getName().getString().equals(Ores.copperDust.getName().getString())){
            heatCapacitance=OriginalHeatCapacitance+Materials.COPPER.getSpecificHeatCapacityOfVolume()/9;
        }else if(inventory.getStack(0).getName().getString().equals(Ores.tinDust.getName().getString())){
            heatCapacitance=OriginalHeatCapacitance+Materials.TIN.getSpecificHeatCapacityOfVolume()/9;
        }else if(inventory.getStack(0).getName().getString().equals(Ores.bronzeDust.getName().getString())){
            heatCapacitance=OriginalHeatCapacitance+Materials.BRONZE.getSpecificHeatCapacityOfVolume()/9;
        }else{
            heatCapacitance=OriginalHeatCapacitance;
        }

        if(liquidName.equals("copper")){//144*9=1296
            heatCapacitance+=((float)liquidAmount/1296)*Materials.COPPER.getSpecificHeatCapacityOfVolume();
        }else if(liquidName.equals("tin")){
            heatCapacitance+=((float)liquidAmount/1296)*Materials.TIN.getSpecificHeatCapacityOfVolume();
        }else if(liquidName.equals("bronze")){
            heatCapacitance+=((float)liquidAmount/1296)*Materials.BRONZE.getSpecificHeatCapacityOfVolume();
        }
    }

    private void updateTemperature(){
        float temperature = 20;
        float specificHeatRatio = 0.1f;
        assert this.world != null;
        if (this.world.getBlockState(pos.down()).getBlock().getName().getString().equals(
                Machines.bronzeCombustionChamber.getName().getString())){
            temperature = ((BronzeCombustionChamberEntity) Objects.requireNonNull(
                    this.world.getBlockEntity(this.pos.down()))).temperature;
            specificHeatRatio = Materials.BRONZE.getSpecificHeatCapacityOfVolume()/9/heatCapacitance;
            ((BronzeCombustionChamberEntity) Objects.requireNonNull(this.world.getBlockEntity(this.pos.down())))
                    .temperature-= (temperature-this.temperature)/50.0/specificHeatRatio;
        }else if (this.world.getBlockState(pos.down()).getBlock().getName().getString().equals(
                Machines.firebrickCombustionChamber.getName().getString())){
            temperature = ((FirebrickCombustionChamberEntity) Objects.requireNonNull(
                    this.world.getBlockEntity(this.pos.down()))).temperature;
            specificHeatRatio = Materials.FIREBRICK.getSpecificHeatCapacityOfVolume()/9/heatCapacitance;
            ((FirebrickCombustionChamberEntity) Objects.requireNonNull(this.world.getBlockEntity(this.pos.down())))
                    .temperature-= (temperature-this.temperature)/50.0/specificHeatRatio;
        }
        this.temperature+=(temperature-this.temperature)/50.0*specificHeatRatio;
    }

    private void updateLiquid(Inventory inventory){
        int itemCount = inventory.getStack(0).getCount();
        if(inventory.getStack(0).getName().getString().equals(Ores.copperDust.getName().getString())){
            if(temperature>Materials.COPPER.getMeltingPoint()){
                if(this.liquidName.equals("air")||(this.liquidName.equals("copper")&&liquidAmount+144<=576)){//can melt
                    if(itemCount==1){
                        inventory.setStack(0,Items.AIR.getDefaultStack());
                    }else{
                        inventory.getStack(0).setCount(itemCount-1);
                    }
                    this.liquidAmount+=144;
                    if(this.liquidName.equals("air")){
                        liquidName = "copper";
                    }
                }
            }
        }else if(inventory.getStack(0).getName().getString().equals(Ores.tinDust.getName().getString())){
            if(temperature>Materials.TIN.getMeltingPoint()){
                if(this.liquidName.equals("air")||(this.liquidName.equals("tin")&&liquidAmount+144<=576)){//can melt
                    if(itemCount==1){
                        inventory.setStack(0,Items.AIR.getDefaultStack());
                    }else{
                        inventory.getStack(0).setCount(itemCount-1);
                    }
                    this.liquidAmount+=144;
                    if(this.liquidName.equals("air")){
                        liquidName = "tin";
                    }
                }
            }
        }else if(inventory.getStack(0).getName().getString().equals(Ores.bronzeDust.getName().getString())){
            if(temperature>Materials.BRONZE.getMeltingPoint()){
                if(this.liquidName.equals("air")||(this.liquidName.equals("bronze")&&liquidAmount+144<=576)){//can melt
                    if(itemCount==1){
                        inventory.setStack(0,Items.AIR.getDefaultStack());
                    }else{
                        inventory.getStack(0).setCount(itemCount-1);
                    }
                    this.liquidAmount+=144;
                    if(this.liquidName.equals("air")){
                        liquidName = "bronze";
                    }
                }
            }
        }
    }

    public String getUnitLiquid(){
        if(liquidAmount<144){
            return "air";
        }else{
            String result = liquidName;
            if(liquidAmount==144){
                liquidName = "air";
            }
            liquidAmount-=144;
            return result;
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
        Inventory inventory = (Inventory)this;
        updateHeatCapacitance(inventory);
        updateTemperature();
        updateLiquid(inventory);
        checkBreak();
    }
}
