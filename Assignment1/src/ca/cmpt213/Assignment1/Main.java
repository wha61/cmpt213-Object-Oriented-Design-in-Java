package ca.cmpt213.Assignment1;

import ca.cmpt213.Assignment1.model.MinionManger;
import ca.cmpt213.Assignment1.ui.TextMenu;

/**
 * Main class uses Minion class, MinionManger class and TextMenu class. It is a class for the main
 * application. Data includes manager, ui.
 * @author Wenxiang He
 */
public class Main {
    public static final void main(String[] args){
        //create Minions Arraylist
        MinionManger manger = new MinionManger();

        //create user interface
        TextMenu ui = new TextMenu(manger);

        //print welcome
        System.out.println("***************************************\n" +
                "Welcome to the Evil Minion Tracker (tm)\n" +
                "by Wenxiang He(Bruce)\n" +
                "***************************************\n");

        //Launch
        ui.show();
    }
}

