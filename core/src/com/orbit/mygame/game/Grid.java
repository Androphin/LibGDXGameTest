package com.orbit.mygame.game;

import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector3;

public final class Grid {

    final int TILES_X;
    final int TILES_Y;
    final int TILE_WIDTH;
    final int TILE_HEIGHT;
    final float TILE_WIDTH_DIAGONALE;
    final float TILE_HEIGHT_DIAGONALE;

    final int AREA_WIDTH;
    final int AREA_HEIGHT;
    final float AREA_WIDTH_DIAGONALE;
    final float AREA_HEIGHT_DIAGONALE;

    final float obliqueProjectionAmplifier;

    final Matrix4 areaRotation = new Matrix4();
    final float areaOffset;

    /** Creates the oblique grid */
    public Grid(){
        //the ratio to size the height (4:3 for oblique)
        obliqueProjectionAmplifier = 1.33f; //1.4f

        //TILE setup
        TILES_X = 20;
        TILES_Y = 20;
        TILE_WIDTH  = 64;
        TILE_HEIGHT = 64;

        //AREA setup
        AREA_WIDTH = TILES_X*TILE_WIDTH;
        AREA_HEIGHT = TILES_Y*TILE_HEIGHT;
        AREA_WIDTH_DIAGONALE = (float)Math.sqrt( (AREA_WIDTH*AREA_WIDTH)+(AREA_HEIGHT*AREA_HEIGHT) );
        AREA_HEIGHT_DIAGONALE = AREA_WIDTH_DIAGONALE/obliqueProjectionAmplifier;
        //calculate angle for oblique
        float angle = 0;
        angle = (float)Math.atan( (AREA_HEIGHT_DIAGONALE/2)/(AREA_WIDTH_DIAGONALE/2) );
        angle = (float)Math.toDegrees(angle);
        angle = (90-angle)*-1;
        angle = 41.26f*-1;

        //rotation for the area background
        areaRotation.setToRotation(new Vector3(1,0,0), angle);
        //calculate position Y offset, that happens through rotation
        areaOffset = (AREA_HEIGHT_DIAGONALE*(obliqueProjectionAmplifier/2))-AREA_HEIGHT_DIAGONALE;

        TILE_WIDTH_DIAGONALE = AREA_WIDTH_DIAGONALE/TILES_X;
        TILE_HEIGHT_DIAGONALE = AREA_HEIGHT_DIAGONALE/TILES_Y;
    }

    /** Converts a simple x,y position on the grid to the position in the game world
     * @param x The x-position on the grid
     * @param y The y-position on the grid
     * @return array holding the two world coordinates */
    public float[] convertToWorldCoordinates(int x, int y){
        float[] coordinates = new float[2];
        float gridX = 0f;
        float gridY = 0f;
        float offsetY = AREA_HEIGHT_DIAGONALE/2;
        //check out of coords
        if( x > TILES_X || y > TILES_Y ){
            //error
        }else {
            gridX = x * TILE_WIDTH_DIAGONALE / 2 + y * TILE_WIDTH_DIAGONALE / 2;
            gridY = offsetY - (y * TILE_HEIGHT_DIAGONALE / 2) + (x * TILE_HEIGHT_DIAGONALE / 2);
        }
        coordinates[0] = gridX;
        coordinates[1] = gridY;
        return coordinates;
    }

    public float getAreaWidthDiagonale(){
        return this.AREA_WIDTH_DIAGONALE;
    }
    public float getAreaHeightDiagonale(){
        return this.AREA_HEIGHT_DIAGONALE;
    }
}
