package org.academiadecodigo.variachis.expertCoders;

import java.util.LinkedList;
import java.util.logging.Level;

import org.academiadecodigo.simplegraphics.pictures.Picture;
import org.academiadecodigo.variachis.expertCoders.grid.Grid;
import org.academiadecodigo.variachis.expertCoders.grid.position.Position;
import org.academiadecodigo.variachis.expertCoders.grid.position.PositionFactory;
import org.academiadecodigo.variachis.expertCoders.item.Item;
import org.academiadecodigo.variachis.expertCoders.item.ItemFactory;
import org.academiadecodigo.variachis.expertCoders.player.Player;

public class Game {
    private Item item;
    private LinkedList<Item> allItems = new LinkedList<>();
    private LinkedList<Item> activeItems = new LinkedList<>();
    private Player player;
    private Grid grid;
    private ItemFactory itemFactory = new ItemFactory();
    private boolean gameOver;
    private GameLevel gameLevel = new GameLevel();
    //private Picture picture = new Picture();

    public void addItemsToList() {
        int items = 0;
        while (items != 50) {
            allItems.add(itemFactory.getItem());
            items++;
        }
    }


    public void gameInit() {
        this.grid = new Grid(60, 80);
        grid.draw();

        this.player = new Player(PositionFactory.getPosition(grid.getCols() / 2, grid.getRows(), grid));

        //loading level 1
        gameLevel.setGrid(grid);
        gameLevel.setActualLevel("levelOne"); //sets current level at level one
        gameLevel.draw();

        //add player to game
        player.setGrid(grid);
        player.draw();

        //adding items to the list and giving them a position
        addItemsToList();
        for (int i = 0; i < 10; i++) {
            activeItems.add(allItems.remove(i));
        }


        for (Item item : activeItems) {
            item.setPosition(PositionFactory.getItemPosition(grid));
            switch (item.getType()){
                case CAP:
                    item.draw();
                    break;
                case MAC:
                    item.draw();
                    break;
                case PEN:
                    item.draw();
                    break;
                case COCK:
                    item.draw();
                    break;
                case POLY:
                    item.draw();
                    break;

            }
        }


        for (Item item : allItems) {
            item.setPosition(PositionFactory.getItemPosition(grid));
        }

    }


    public Item itemRecycle(Item item) {
        item.setColided(false);
        item.getPosition().setRowZero();
        item.getPosition().setCol();
        allItems.add(item);
        Item itemNew = allItems.poll();
        return itemNew;
    }


    public void gameStart() {


        //CHECK LEVEL METHOD -> ALLWAYS LEVEL ONE INIT VALUE TODO:CHANGE THE VAL OF CURRENTLEVEL WHEN CONDITION TO NEXT LVL MEETS

        while (!gameOver) {

            //TODO: IF PLAYER PRESS KEY PLAYER.MOVE()


            for (Item item : activeItems) {

                //draw the items and move them down
                item.draw();

                item.move();

                if (player.checkCollision(item.getPosition())) {
                    System.out.println("COLIDEDDDDD with player");
                    item.setColided(true);
                    switch (item.getType()) {
                        case CAP:
                            player.setKnowledge(player.getKnowledge() - 2);
                            break;
                        case MAC:
                            player.setKnowledge(player.getKnowledge() + 2);
                            break;
                        case PEN:
                            player.setFun(player.getFun() - 2);
                            break;
                        case COCK:
                            player.setFun(player.getFun() + 2);
                            break;
                        case POLY:
                            //TODO change image
                            System.out.println("fun: " + player.getFun() + " knowlege: " + player.getKnowledge());
                            gameOver = true;

                    }
                }

                if (grid.checkCollision(item.getPosition())) {
                    System.out.println("colided with grid");
                    item.setColided(true);
                    //gameOver = true;
                }
            }

            for (Item item : activeItems) {
                if (item.isColided()) {
                    itemRecycle(item);
                }
            }

            if (player.getFun() <= 0 || player.getKnowledge() <= 0) {
                gameOver = true;
            }

        }

    }

}



