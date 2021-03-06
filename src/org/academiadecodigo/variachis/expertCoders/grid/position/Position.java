package org.academiadecodigo.variachis.expertCoders.grid.position;

import org.academiadecodigo.variachis.expertCoders.grid.Grid;


public class Position {

    private int row;
    private int col;
    private Grid grid;


    public Position(int col, int row, Grid grid) {

        this.col = col;
        this.row = row;
        this.grid = grid;
    }


    public enum Direction {
        DOWN,
        RIGHT,
        LEFT;
    }

    public void setRowZero() {
        this.row = 0;
    }

    public void setCol() {
        this.col = ((int) (Math.random() * grid.getCols()));
    }


    public void move(Direction direction) {
        switch (direction) {
            case LEFT:
                moveLeft();
                break;
            case RIGHT:
                moveRight();
                break;
            case DOWN:
                moveDown();
                break;
        }
    }


    private void moveRight() {
        this.col++;
    }

    private void moveLeft() {
        this.col--;
    }

    private void moveDown() {

        this.row++;
    }



    public boolean equals(Position position) {

        //positions plus five because item collides with grid at row 56/55
        if(this.row == position.row + 5 && this.col == position.col){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col +1){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col +2){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col + 3){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col +4){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col +5){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col +6){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col +7){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col +8){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col -1){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col -2){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col -3){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col -4){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col -5){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col -6){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col -7){
            return true;
        }
        if(this.row == position.row + 5 && this.col == position.col -8){
            return true;
        }
        return false;

    }


    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }



    @Override
    public String toString() {
        return "Position{" +
                "row=" + row +
                ", col=" + col +
                '}';
    }
}