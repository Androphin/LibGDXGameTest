package com.orbit.mygame.game;

public class Factory extends Building implements Production {
    int productionRate;
    int energyCostPerCycle;

    @Override
    public void setProductionRate(int rate) {
        this.productionRate = rate;
    }

    @Override
    public int getProductionRate() {
        return this.productionRate;
    }

    @Override
    public void setEnergyConsumption(int rate) {
        this.energyCostPerCycle = rate;
    }

    @Override
    public int getEnergyConsumption() {
        return this.energyCostPerCycle;
    }
}
