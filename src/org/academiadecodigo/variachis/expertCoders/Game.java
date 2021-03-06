package org.academiadecodigo.variachis.expertCoders;

import java.util.LinkedList;


import org.academiadecodigo.simplegraphics.graphics.Text;
import org.academiadecodigo.simplegraphics.keyboard.Keyboard;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEvent;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardEventType;
import org.academiadecodigo.simplegraphics.keyboard.KeyboardHandler;
import org.academiadecodigo.variachis.expertCoders.grid.Grid;
import org.academiadecodigo.variachis.expertCoders.grid.position.Position;
import org.academiadecodigo.variachis.expertCoders.grid.position.PositionFactory;
import org.academiadecodigo.variachis.expertCoders.item.Item;
import org.academiadecodigo.variachis.expertCoders.item.ItemFactory;
import org.academiadecodigo.variachis.expertCoders.player.Player;

public class Game implements KeyboardHandler {


    private LinkedList<Item> allItems = new LinkedList<>();
    private LinkedList<Item> activeItems = new LinkedList<>();
    private Player player;
    private Grid grid;
    private boolean gameOver;
    private GameLevel levelOne = new GameLevel();
    private boolean gameStart = false;


    private void addItemsToList() {

        for (int i = 0; i < 25; i++) {
            allItems.add(ItemFactory.getItem(grid));
        }

        for (int i = 0; i < 7; i++) {
            activeItems.add(allItems.remove(i));
        }
    }


    public boolean isGameStart() {
        return gameStart;
    }


    private void itemRecycle(Item item) {
        allItems.add(item);
    }


    public void gameInit() {
        this.grid = new Grid(80, 60);
        levelOne.setActualLevel(GameLevel.Level.ZERO);
        levelOne.draw();
        this.player = new Player(PositionFactory.getPlayerPosition(grid));
        grid.draw();
        player.draw();
        addItemsToList();
        keyboardInit();

    }


    public void gameStart() {

        while (levelOne.getActualLevel() == GameLevel.Level.ZERO || levelOne.getActualLevel() == GameLevel.Level.HELP) {
            System.out.println();
        }


        Text knowlege = new Text(12, 12, "Player Knowledge: " + player.getKnowledge());
        knowlege.grow(2, 2);
        knowlege.draw();
        Text fun = new Text(12, 28, "Player fun: " + player.getFun());
        fun.grow(2, 2);
        fun.draw();

        while (!gameOver) {
            knowlege.setText("Player Knowledge: " + player.getKnowledge());
            fun.setText("Player fun : " + player.getFun());

            try {
                Thread.sleep(16);

            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for (Item item : activeItems) {

                item.move(Position.Direction.DOWN);
                item.draw();


                if (player.checkCollision(item.getPosition())) {
                    item.setColided(true);
                    player.beingHit(item);
                }

                if (grid.checkCollision(item.getPosition())) {
                    item.setColided(true);

                }

                if (item.isColided()) {
                    item.recycle();
                    itemRecycle(item);
                }
            }

            if (player.nextlevel()) {
                levelOne.setActualLevel(GameLevel.Level.TWO);
                levelOne.draw();
            }

            if (player.gameOver()) {
                levelOne.setActualLevel(GameLevel.Level.OVER);
                levelOne.draw();
                for (Item item1 : activeItems) {
                    item1.getPicture().delete();
                }
                Sound sound = new Sound("/resources/DoomOST.wav"); // open stream

                try {

                    sound.play(true); // play sound from start
                    //Thread.sleep(10000);
                    //sound.stop(); // pause sound
                    Thread.sleep(5000);
                    sound.play(false); // continue playing
                    Thread.sleep(10000);

                } catch (InterruptedException e) {
                    e.printStackTrace();

                } finally {
                    sound.close(); // close stream
                }

                sound.loopIndef(); // play forever
                gameOver = true;


            }

        }

    }

    public void keyboardInit() {
        Keyboard keyboard = new Keyboard(this);

        int[] keys = new int[]{
                KeyboardEvent.KEY_LEFT,
                KeyboardEvent.KEY_RIGHT,
                KeyboardEvent.KEY_1,
                KeyboardEvent.KEY_2,
                KeyboardEvent.KEY_H,
                KeyboardEvent.KEY_P,
                KeyboardEvent.KEY_B
        };

        for (int key : keys) {
            KeyboardEvent event = new KeyboardEvent();
            event.setKeyboardEventType(KeyboardEventType.KEY_PRESSED);
            event.setKey(key);
            keyboard.addEventListener(event);
        }

    }

    @Override
    public void keyPressed(KeyboardEvent keyboardEvent) {
        switch (keyboardEvent.getKey()) {

            case KeyboardEvent.KEY_RIGHT:
                if (player.isLeftOf(grid.getCols())) {
                    player.move(Position.Direction.RIGHT);
                }
                break;
            case KeyboardEvent.KEY_LEFT:
                if (player.isRightOf(0)) {
                    player.move(Position.Direction.LEFT);
                }
                break;
            case KeyboardEvent.KEY_1:
                levelOne.setActualLevel(GameLevel.Level.TWO);
                levelOne.draw();
                break;
            case KeyboardEvent.KEY_2:
                levelOne.setActualLevel(GameLevel.Level.ZERO);
                levelOne.draw();
                break;
            case KeyboardEvent.KEY_P:
                levelOne.setActualLevel(GameLevel.Level.ONE);
                levelOne.draw();
                gameStart = true;
                break;
            case KeyboardEvent.KEY_H:
                levelOne.setActualLevel(GameLevel.Level.HELP);
                levelOne.draw();
                break;
            case KeyboardEvent.KEY_B:
                levelOne.setActualLevel(GameLevel.Level.ZERO);
                levelOne.draw();
                break;
        }
    }

    @Override
    public void keyReleased(KeyboardEvent keyboardEvent) {

    }
}



