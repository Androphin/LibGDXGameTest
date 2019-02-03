package com.orbit.mygame.game;

public class House extends Building {
    private int residents = 0;

    public void setResidents(int num){
        this.residents = num;
    }
    public int getResidents(){
        return this.residents;
    }
}
