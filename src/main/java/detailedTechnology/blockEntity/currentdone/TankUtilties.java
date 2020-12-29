package detailedTechnology.blockEntity.currentdone;

import detailedTechnology.group.Pipes;
import detailedTechnology.group.currentdone.Tools;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import org.lwjgl.system.CallbackI;

public class TankUtilties {
    public int MaximumCapacitance;
    public int liquidAmount;
    public String liquidName;

    public TankUtilties(int maximumCapacitance, int liquidAmount, String liquidName) {
        MaximumCapacitance = maximumCapacitance;
        this.liquidAmount = liquidAmount;
        this.liquidName = liquidName;
    }

    public boolean receiveBucketLiquid(String liquidName) {
        if (liquidAmount <= MaximumCapacitance - 1000 && this.liquidName.equals(liquidName)) {
            liquidAmount += 1000;
            return true;
        } else if (liquidAmount == 0) {
            this.liquidName = liquidName;
            liquidAmount = 1000;
            return true;
        }
        return false;
    }

    public boolean recieveLiquie(String liquidName,int liquidAmount){
        if (liquidAmount <= MaximumCapacitance - liquidAmount && this.liquidName.equals(liquidName)) {
            this.liquidAmount += liquidAmount;
            return true;
        } else if (this.liquidAmount == 0&&liquidAmount<MaximumCapacitance) {
            this.liquidName = liquidName;
            this.liquidAmount = liquidAmount;
            return true;
        }
        return false;
    }

    public String giveLiquidBucket() {
        if (liquidAmount < 1000) {
            return "air";
        } else {
            String result = liquidName;
            if (liquidAmount == 1000) {
                liquidName = "air";
                liquidAmount = 0;
            } else {
                liquidAmount -= 1000;
            }
            return result;
        }
    }

    public boolean inputOnlyInventoryManipulate(Inventory inventory) {
        String name0 = inventory.getStack(0).getName().getString();
        String name1 = inventory.getStack(1).getName().getString();
        int num0 = inventory.getStack(0).getCount();
        int num1 = inventory.getStack(1).getCount();

        if (inventory.getStack(0).getCount() == 1) {
            if (name0.equals(Items.WATER_BUCKET.getName().getString())) {
                if (name1.equals(Items.BUCKET.getName().getString()) && num1 < 16) {
                    if (receiveBucketLiquid("water")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.getStack(1).setCount(num1 + 1);
                        return true;
                    }
                } else if (name1.equals(Items.AIR.getName().getString())) {
                    if (receiveBucketLiquid("water")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.setStack(1, Items.BUCKET.getDefaultStack());
                        return true;
                    }
                }
            } else if (name0.equals(Items.LAVA_BUCKET.getName().getString())) {
                if (name1.equals(Items.BUCKET.getName().getString()) && num1 < 16) {
                    if (receiveBucketLiquid("lava")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.getStack(1).setCount(num1 + 1);
                        return true;
                    }
                } else if (name1.equals(Items.AIR.getName().getString())) {
                    if (receiveBucketLiquid("lava")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.setStack(1, Items.BUCKET.getDefaultStack());
                        return true;
                    }
                }
            } else if (name0.equals(Pipes.copperWaterBucket.getName().getString())) {
                if (name1.equals(Tools.copperBucket.getName().getString()) && num1 < 16) {
                    if (receiveBucketLiquid("water")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.getStack(1).setCount(num1 + 1);
                        return true;
                    }
                } else if (name1.equals(Items.AIR.getName().getString())) {
                    if (receiveBucketLiquid("water")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.setStack(1, Tools.copperBucket.getDefaultStack());
                        return true;
                    }
                }
            } else if (name0.equals(Pipes.copperLavaBucket.getName().getString())) {
                if (name1.equals(Tools.copperBucket.getName().getString()) && num1 < 16) {
                    if (receiveBucketLiquid("lava")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.getStack(1).setCount(num1 + 1);
                        return true;
                    }
                } else if (name1.equals(Items.AIR.getName().getString())) {
                    if (receiveBucketLiquid("lava")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.setStack(1, Tools.copperBucket.getDefaultStack());
                        return true;
                    }
                }
            } else if (name0.equals(Pipes.clayWaterBucket.getName().getString())) {
                if (name1.equals(Tools.clayBucket.getName().getString()) && num1 < 16) {
                    if (receiveBucketLiquid("water")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.getStack(1).setCount(num1 + 1);
                        return true;
                    }
                } else if (name1.equals(Items.AIR.getName().getString())) {
                    if (receiveBucketLiquid("water")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.setStack(1, Tools.clayBucket.getDefaultStack());
                        return true;
                    }
                }
            } else if (name0.equals(Pipes.clayLavaBucket.getName().getString())) {
                if (name1.equals(Tools.clayBucket.getName().getString()) && num1 < 16) {
                    if (receiveBucketLiquid("lava")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.getStack(1).setCount(num1 + 1);
                        return true;
                    }
                } else if (name1.equals(Items.AIR.getName().getString())) {
                    if (receiveBucketLiquid("lava")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.setStack(1, Tools.clayBucket.getDefaultStack());
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public void InventoryManipulate(Inventory inventory) {
        String name0 = inventory.getStack(0).getName().getString();
        String name1 = inventory.getStack(1).getName().getString();
        int num0 = inventory.getStack(0).getCount();
        int num1 = inventory.getStack(1).getCount();
        if (inventory.getStack(0).getCount() == 1) {
            if(inputOnlyInventoryManipulate(inventory)) return;
            if (num1 == 0) {
                String liquidName;
                if (name0.equals(Items.BUCKET.getName().getString())) {
                    liquidName = giveLiquidBucket();
                    if (liquidName.equals("water")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.setStack(1, Items.WATER_BUCKET.getDefaultStack());
                    } else if (liquidName.equals("lava")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.setStack(1, Items.LAVA_BUCKET.getDefaultStack());
                    }
                } else if (name0.equals(Tools.copperBucket.getName().getString())) {
                    liquidName = giveLiquidBucket();
                    if (liquidName.equals("water")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.setStack(1, Pipes.copperWaterBucket.getDefaultStack());
                    } else if (liquidName.equals("lava")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.setStack(1, Pipes.copperLavaBucket.getDefaultStack());
                    }
                } else if (name0.equals(Tools.clayBucket.getName().getString())) {
                    liquidName = giveLiquidBucket();
                    if (liquidName.equals("water")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.setStack(1, Pipes.clayWaterBucket.getDefaultStack());
                    } else if (liquidName.equals("lava")) {
                        inventory.setStack(0, Items.AIR.getDefaultStack());
                        inventory.setStack(1, Pipes.clayLavaBucket.getDefaultStack());
                    }
                }
            }
        } else if (num1 == 0) {
            String liquidName;
            if (name0.equals(Items.BUCKET.getName().getString())) {
                liquidName = giveLiquidBucket();
                if (liquidName.equals("water")) {
                    inventory.getStack(0).setCount(num0 - 1);
                    inventory.setStack(1, Items.WATER_BUCKET.getDefaultStack());
                } else if (liquidName.equals("lava")) {
                    inventory.getStack(0).setCount(num0 - 1);
                    inventory.setStack(1, Items.LAVA_BUCKET.getDefaultStack());
                }
            } else if (name0.equals(Tools.copperBucket.getName().getString())) {
                liquidName = giveLiquidBucket();
                if (liquidName.equals("water")) {
                    inventory.getStack(0).setCount(num0 - 1);
                    inventory.setStack(1, Pipes.copperWaterBucket.getDefaultStack());
                } else if (liquidName.equals("lava")) {
                    inventory.getStack(0).setCount(num0 - 1);
                    inventory.setStack(1, Pipes.copperLavaBucket.getDefaultStack());
                }
            } else if (name0.equals(Tools.clayBucket.getName().getString())) {
                liquidName = giveLiquidBucket();
                if (liquidName.equals("water")) {
                    inventory.getStack(0).setCount(num0 - 1);
                    inventory.setStack(1, Pipes.clayWaterBucket.getDefaultStack());
                } else if (liquidName.equals("lava")) {
                    inventory.getStack(0).setCount(num0 - 1);
                    inventory.setStack(1, Pipes.clayLavaBucket.getDefaultStack());
                }
            }
        }
    }
}
