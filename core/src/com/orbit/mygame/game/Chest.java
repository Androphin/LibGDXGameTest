package com.orbit.mygame.game;

public class Chest extends Item implements Storage {

    int slots;
    int maxStorage;

    @Override
    public void setInventarSlots(int num) {
        this.slots = num;
    }

    @Override
    public int getInventarSlots() {
        return this.slots;
    }

    @Override
    public void setStorageAmount(int num) {
        this.maxStorage = num;
    }

    @Override
    public int getStorageAmount() {
        return this.maxStorage;
    }
}
