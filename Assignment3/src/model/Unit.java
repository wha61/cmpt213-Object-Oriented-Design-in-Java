package model;
/**
 *  Unit class models the information of a coordinate on the battlefield. It can be a tank unit or a fog unit.
 *  Data includes xCoordinates, yCoordinates, checkTank checkFog and state.
 *  It provides the Unit for the Tank class and Battlefield class.
 *  @author Wenxiang He & Bowei Pan
 */
public class Unit {
    private int xCoordinates;
    private int yCoordinates;
    private boolean checkTank;
    private boolean checkFog;
    private boolean checkCheat;
    private char state;

    public Unit () {
        xCoordinates = 0;
        yCoordinates = 0;
        checkTank = false;
        checkFog = true;
        checkCheat = false;
        state = '~';
    }

    public Unit(int xCoordinates, int yCoordinates, boolean checkTank, boolean checkFog, boolean checkCheat, char state) {
        this.xCoordinates = xCoordinates;
        this.yCoordinates = yCoordinates;
        this.checkTank = checkTank;
        this.checkFog = checkFog;
        this.checkCheat = checkCheat;
        this.state = state;
    }

    public void setCheckCheat(boolean checkCheat) {
        this.checkCheat = checkCheat;
    }

    public int getxCoordinates() {
        return xCoordinates;
    }

    public void setxCoordinates(int xCoordinates) {
        this.xCoordinates = xCoordinates;
    }

    public int getyCoordinates() {
        return yCoordinates;
    }

    public void setyCoordinates(int yCoordinates) {
        this.yCoordinates = yCoordinates;
    }

    public boolean isCheckTank() {
        return checkTank;
    }

    public void setCheckTank(boolean checkTank) {
        this.checkTank = checkTank;
    }

    public void setCheckFog(boolean checkFog) {
        this.checkFog = checkFog;
    }

    public char getState() {
        return state;
    }

    public void setState(char state) {
        this.state = state;
    }

    public void setCurrentState(){
        if(this.checkCheat && !this.checkTank){
            this.state = '.';
        }
        if(this.checkFog && !this.checkCheat){
            this.state = '~';
        }
        if(!this.checkFog && !checkTank){
            this.state = ' ';
        }
        if(!this.checkFog && checkTank){
            this.state = 'X';
        }
    }
}
