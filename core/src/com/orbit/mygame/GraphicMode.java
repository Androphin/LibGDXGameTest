package com.orbit.mygame;

public class GraphicMode {
    //default 0 = 2D
    //1 = 3D
    private int mode = 0;

    public void setMode(int mode){
        this.mode = mode;
    }
    public int getMode(){
        return this.mode;
    }
}
