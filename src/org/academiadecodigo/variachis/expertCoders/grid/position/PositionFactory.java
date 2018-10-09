package org.academiadecodigo.variachis.expertCoders.grid.position;

import org.academiadecodigo.variachis.expertCoders.grid.Grid;

/**
 * A factory to create different positions for different items.
 */

public class PositionFactory {

    private Grid grid;


    //method to instantiate the positions
    public static Position getPlayerPosition(Grid grid) {

        return new Position(grid.getCols() / 2, grid.getRows(), grid);

    }

    //I THINK ITS THIS WAY BECAUSE THE PLAYER DOESNT NEED TO KNOW THE GRID AGAIN
    public Position playerPosition(){
        return new Position(50, 50, this.grid);
    }



    public static Position getItemPosition(Grid grid){
        int randomCol = (int)(Math.random()* grid.getCols());
        return new Position(randomCol,0,grid);
    }

    public static Position getLevelPosition(Grid grid){
        return new Position(grid.getCols(), grid.getRows(), grid);
    }



    public void setGrid(Grid grid) {
        this.grid = grid;
    }


    public int getRandomCol() {
        int cols = this.grid.getCols();
        return (int) (Math.random() * cols);

    }

    public int getRandomRow(){
        int rows = this.grid.getRows();
        return (int) (Math.random() * rows);
    }




}
