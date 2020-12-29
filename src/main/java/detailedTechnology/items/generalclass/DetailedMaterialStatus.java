package detailedTechnology.items.generalclass;

public class DetailedMaterialStatus {
    private final String name;
    private final float specificHeatCapacity;
    private final float density;
    private final float meltingPoint;
    private final float boilingPoint;

    public DetailedMaterialStatus(String name, float density, float specificHeatCapacity, float meltingPoint, float boilingPoint) {
        this.name = name;
        this.density = density;
        this.specificHeatCapacity = specificHeatCapacity;
        this.meltingPoint = meltingPoint;
        this.boilingPoint = boilingPoint;
    }

    public String getName() {
        return name;
    }

    public float getSpecificHeatCapacity() {
        return specificHeatCapacity;
    }

    public float getDensity() {
        return density;
    }

    public float getMeltingPoint() {
        return meltingPoint;
    }

    public float getBoilingPoint() {
        return boilingPoint;
    }

    public float getSpecificHeatCapacityOfVolume(){
        return specificHeatCapacity*density;
    }
}
