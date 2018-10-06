package org.academiadecodigo.variachis.expertCoders;

import java.util.LinkedList;

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
    private PositionFactory positionFactory = new PositionFactory();
    private boolean gameOver;


    public void addItemsToList() {
        int items = 0;
        while (items != 20) {
            allItems.add(itemFactory.getItem());
            items++;
        }
    }


    public void gameInit() {
        this.grid = new Grid(200, 200);
        this.player = new Player(PositionFactory.getPosition(grid.getCols() / 2, grid.getRows(), grid));


        //adding items to the list and giving them a position
        addItemsToList();
        for (int i = 0; i < 6; i++) {
            activeItems.add(allItems.remove(i));
        }

        for (Item item : activeItems) {
            item.setPosition(PositionFactory.getItemPosition(grid));
        }

        for (Item item : allItems) {
            item.setPosition(PositionFactory.getItemPosition(grid));
        }


        player.draw();
        grid.draw();
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

        for (Item item : activeItems) {

        }

        while (!gameOver) {
            //TODO: IF PLAYER PRESS KEY PLAYER.MOVE()


            for (Item item : activeItems) {

                item.move();
                item.draw();
            }

            for (Item item : activeItems) {

                if (player.checkCollision(item.getPosition())) {
                    System.out.println("COLIDEDDDDD with player");
                    item.setColided(true);
                    gameOver = true;
                    //TODO: PLAYER INTERACTION WITH ITEM TYPE
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

        }

    }

/*
    //recycle items
    for (int i = 0; i < 6; i++) {
        activeItems.add(allItems.remove(i));
    }

    for (int i = 0; i < 6; i++) {
        allItems.add(activeItems.remove(i));
    }
*/


}



