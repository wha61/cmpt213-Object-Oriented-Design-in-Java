package UI;

import com.google.gson.Gson;
import java.util.Scanner;
import model.Battlefield;
import model.Fortress;
import model.Tanks;
/**
 *  This class is for user interface.
 *  It uses battlefield, fortress, and tanks classes to create a game state, with initial fortress health2400, default tank number 5.
 *  The program main function is here.
 *  Players can play the game by using command Java -jar path arg1(tank number) arg2(cheatModeOn/OFF)
 *  Sometimes the game may not run properly because when generating tank on the field, there are situation that not able to
 *  put all tank on a finite space. This usually happens when there is too many tanks.
 *  In this situation, the game might stuck.
 *  You only need to stop the terminal and reenter the same command.
 * @author Bowei Pan, Wnexiang He.
 */
public class UI {
    public static void main(String[] args) {
        // GSON
        Gson gson = new Gson();
        int tankNum = 5;
        if(args.length > 0){
            tankNum = Integer.parseInt(args[0]);
        }

        UI ui = new UI();
        Fortress fortress = new Fortress();
        Battlefield battlefield = new Battlefield();
        battlefield.setInitialBattlefield();
        battlefield.setInitialTanks(tankNum);
        Tanks tanks = battlefield.getTanks();
        battlefield.setTanksPosition(tanks);

        String cheatMode = "--cheat";
        if(args.length == 2 && args[1].equals(cheatMode)){
            tankNum = Integer.parseInt(args[0]);
            battlefield.setCheatBattlefield();
            battlefield.setTanksPositionCheated(tanks);
            ui.displayVisibleField(battlefield, fortress);
        }

        battlefield.quitCheatBattlefield();
        battlefield.setTanksPositionCheatedBack(tanks);

        UI.showGameStartTanks(tankNum);
        UI.showGameStart();

        ui.displayInvisibleField(battlefield, fortress);
        String input = ui.inputShootCoordinates();
        StringBuilder sbFirst = new StringBuilder();
        if (input.length() == 2) {
            sbFirst.append(input.charAt(0));
            sbFirst.append(input.charAt(1));
        }
        if (input.length() == 3) {
            sbFirst.append(input.charAt(0));
            sbFirst.append(input.charAt(1));
            sbFirst.append(input.charAt(2));
        }
        while (!ui.checkValidInput(input)) {
            String change = ui.inputShootCoordinates();
            if (change.length() == 2) {
                sbFirst.append(change.charAt(0));
                sbFirst.append(change.charAt(1));
                input = sbFirst.toString();
            } else if (change.length() == 3) {
                sbFirst.append(change.charAt(0));
                sbFirst.append(change.charAt(1));
                sbFirst.append(change.charAt(2));
                input = sbFirst.toString();
            }
        }
        int firstNum = tanks.getCurrentUnitNum();
        battlefield.hitOnTank(input, tanks);
        fortress.setHealth(tanks);
        ui.displayInvisibleFieldDamage(battlefield, firstNum, fortress);

        for (int i = 0; i < 100; i++) {
            ui.displayInvisibleField(battlefield, fortress);
            input = ui.inputShootCoordinates();
            StringBuilder sb = new StringBuilder();
            if (input.length() == 2) {
                sb.append(input.charAt(0));
                sb.append(input.charAt(1));
            }
            if (input.length() == 3) {
                sb.append(input.charAt(0));
                sb.append(input.charAt(1));
                sb.append(input.charAt(2));
            }
            while (!ui.checkValidInput(input)) {
                String change = ui.inputShootCoordinates();
                if (change.length() == 2) {
                    sb.append(change.charAt(0));
                    sb.append(change.charAt(1));
                    input = sb.toString();
                } else if (change.length() == 3) {
                    sb.append(change.charAt(0));
                    sb.append(change.charAt(1));
                    sb.append(change.charAt(2));
                    input = sb.toString();
                }
            }
            int num = tanks.getCurrentUnitNum();
            battlefield.hitOnTank(input, tanks);
            fortress.setHealth(tanks);
            ui.displayInvisibleFieldDamage(battlefield, num, fortress);
            if(battlefield.getTanks().getCurrentUnitNum() < 1){
                System.out.println("Congratulations! You won!");
                battlefield.setCheatBattlefield();
                battlefield.setTanksPositionCheated(tanks);
                ui.displayVisibleField(battlefield, fortress);
                break;
            }
            if(fortress.getCurrentHealth() < 1){
                System.out.println("I'm sorry, your fortress has been smashed!");
                battlefield.setCheatBattlefield();
                battlefield.setTanksPositionCheated(tanks);
                ui.displayVisibleField(battlefield, fortress);
                break;
            }
        }
    }
    public static void showGameStart () {
        System.out.println("----------------------------\n" +
                "Welcome to Fortress defence!\n" +
                "By Bowei Pan and Wenxiang He\n" +
                "----------------------------");
    }
    public static void showGameStartTanks (int tankNum) {
        System.out.println("\nStarting game with " + tankNum + " tanks.");
    }
    public String inputShootCoordinates () {
        System.out.print("Enter your move: ");
        Scanner scanner = new Scanner(System.in);
        String coordinates = scanner.next();
        StringBuilder sb = new StringBuilder();
        boolean check = false;
        boolean ifValid = false;
        char firstArgument = coordinates.charAt(0);
        char secondArgument = coordinates.charAt(1);

        if (secondArgument >= '1' && secondArgument <='9' && coordinates.length() == 2) {
            if (firstArgument >= 'A' && firstArgument <= 'J') {
                sb.append(firstArgument);
                sb.append(secondArgument);
                ifValid = true;
            }
            if (firstArgument >= 'a' && firstArgument <= 'j') {
                sb.append(Character.toUpperCase(firstArgument));
                sb.append(secondArgument);
                ifValid = true;
            }
        }
        else if (secondArgument == '1' && coordinates.charAt(2) == '0' && coordinates.length() == 3) {
            if (firstArgument >= 'A' && firstArgument <= 'J') {
                sb.append(firstArgument);
                sb.append(secondArgument);
                sb.append(coordinates.charAt(2));
                ifValid = true;
            }
            if (firstArgument >= 'a' && firstArgument <= 'j') {
                sb.append(Character.toUpperCase(firstArgument));
                sb.append(secondArgument);
                sb.append(coordinates.charAt(2));
                ifValid = true;
            }
        }
        while (!check) {
            if (coordinates.length() > 3 || !ifValid) {
                System.out.println("Invalid target. Please enter a coordinate such as D10.");
                break;
            } else {
                check = true;
            }
        }
        return sb.toString();
    }

    public void displayInvisibleField (Battlefield battlefield, Fortress fortress) {
        System.out.println("\nGame Board:\n" +
                "     1  2  3  4  5  6  7  8  9  10");
        int start = 'A';
        for (int i = 0; i < 10; i++) {
            System.out.print((char) (start + i));
            System.out.print("    ");
            for (int j = 0; j < 10; j++) {
                System.out.print(battlefield.showPositionStateByCoordinates(i, j));
                System.out.print("  ");
            }
            System.out.println();
        }
        System.out.println("Fortress Structure Left: " +
                fortress.getCurrentHealth());
    }

    public void displayInvisibleFieldDamage(Battlefield battlefield, int num, Fortress fortress) {
        if(battlefield.getTanks().getCurrentUnitNum() == (num -1)){
            System.out.println("HIT!");
        }else {
            System.out.println("Miss.");
        }
        for (int i = 1; i <= battlefield.getTanks().getTankList().size(); i++) {
            System.out.println("Alive tank #" +
                    i + " of " + battlefield.getTanks().getTankList().size() + " shot you for " +
                    battlefield.getTanks().getTankList().get(i - 1).getTankDamage() + "!");
        }
    }

    public void displayVisibleField (Battlefield battlefield, Fortress fortress) {
        System.out.println("\nGame Board:\n" +
                "     1  2  3  4  5  6  7  8  9  10");
        int start = 'A';
        for (int i = 0; i < 10; i++) {
            System.out.print((char) (start + i));
            System.out.print("    ");
            for (int j = 0; j < 10; j++) {
                System.out.print(battlefield.showPositionStateByCoordinates(i, j));
                System.out.print("  ");
            }
            System.out.println();
        }
        if(fortress.getCurrentHealth() < 0){
            System.out.println("Fortress Structure Left: " + 0);
        }
        else {
            System.out.println("Fortress Structure Left: " +
                    fortress.getCurrentHealth());
        }
        System.out.println("(Lower case tank letters are where you shot.)");
    }

    public boolean checkValidInput(String coordinates) {
        boolean check = false;
        if (coordinates.length() == 2) {
            char firstArgument = coordinates.charAt(0);
            char secondArgument = coordinates.charAt(1);
            if (secondArgument >= '1' && secondArgument <= '9') {
                if (firstArgument >= 'A' && firstArgument <= 'J') {
                    check = true;
                }
            }
        }
        else if (coordinates.length() == 3) {
            char firstArgument = coordinates.charAt(0);
            char secondArgument = coordinates.charAt(1);
            char thirdArgument = coordinates.charAt(2);
            if (secondArgument == '1' && thirdArgument == '0') {
                if (firstArgument >= 'A' && firstArgument <= 'J') {
                    check = true;
                }
            }
        }
        return check;
    }

}
