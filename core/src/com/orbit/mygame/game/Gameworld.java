package com.orbit.mygame.game;

public class Gameworld {
    final float WORLD_WIDTH;
    final float WORLD_HEIGHT;

    final Grid grid;

    public Gameworld(){
        grid = new Grid();

        //WORLD size (PADDING for VIEWPORT is missing!)
        WORLD_WIDTH = grid.getAreaWidthDiagonale();
        WORLD_HEIGHT = grid.getAreaHeightDiagonale();

    }

    public float getWorldWidth() {
        return WORLD_WIDTH;
    }

    public float getWorldHeight() {
        return WORLD_HEIGHT;
    }
}
