package ca.cmpt213.Assignment1.ui;

import ca.cmpt213.Assignment1.model.MinionManger;

import java.util.Scanner;

/**
 * TextMenu class use the methods that are implemented in the MinionManger class. Data includes manager.
 * It has the Scanner to receive the input that users enter, and use the switch method to return the
 * corresponding output and handle the error exceptions. It also uses the while loop to keep program
 * running till users choose to exit.
 * @author Wenxiang He
 */
public class TextMenu {
    private MinionManger manger;

    public TextMenu(MinionManger manger) {
        this.manger = manger;
    }

    public void show(){
        boolean exit = false;
        while(!exit){
            System.out.print("\n"+
                    "*************\n" +
                    "* Main Menu *\n" +
                    "*************\n" +
                    "1. List minions\n" +
                    "2. Add a new minion\n" +
                    "3. Remove a minion\n" +
                    "4. Attribute evil deed to a minion\n" +
                    "5. DEBUG: Dump objects (toString)\n" +
                    "6. Exit\n" +
                    "> ");

            Scanner in = new Scanner(System.in);
            int choice = in.nextInt();
            while (choice > 6 || choice < 1){
                System.out.println("Error: Please enter a selection between 1 and 6");
                System.out.print("> ");
                choice = in.nextInt();
            }

            switch (choice){
                case 1:
                    manger.list();
                    break;
                case 2:
                    in.nextLine();
                    System.out.println("Enter minion's name: ");
                    String name = in.nextLine();
                    System.out.println("Enter minion's height: ");
                    double height = in.nextDouble();
                    manger.add(name, height);
                    break;
                case 3:
                    manger.list();
                    System.out.println("(Enter 0 to cancel)");
                    int removeIndex = in.nextInt();
                    manger.remove(removeIndex);
                    break;
                case 4:
                    manger.list();
                    System.out.println("(Enter 0 to cancel)");
                    int attributeIndex = in.nextInt();
                    manger.Attribute(attributeIndex);
                    break;
                case 5:
                    manger.dump();
                    break;
                case 6:
                    exit = true;
                    break;
            }
        }
    }
}
