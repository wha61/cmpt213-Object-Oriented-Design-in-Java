package model;


import java.util.Random;
/**
 *  Battlefield class models the information of the battlefield. It includes unitMatrix(the field), tanks and fortress.
 *  It is the place where generate the Tanks on, generate and change the Battlefield, and enable the cheat mode.
 *  @author Wenxiang He & Bowei Pan
 */
public class Battlefield {
    private Unit[][] unitMatrix;
    private Tanks tanks;
    private Fortress fortress;

    public Battlefield() {
        this.fortress = new Fortress();
        this.tanks = new Tanks();
        unitMatrix = new Unit[10][10];
    }

    public void setInitialBattlefield(){
        for(int i = 0; i <= 9; i++){
            for(int j = 0; j <= 9; j++){
                Unit unit = new Unit();
                unit.setxCoordinates(i);
                unit.setyCoordinates(j);
                unit.setCheckFog(true);
                unit.setCheckTank(false);
                unit.setCheckCheat(false);
                unit.setCurrentState();
                unitMatrix[i][j] = unit;
            }
        }
    }
    public void setCheatBattlefield(){
        for(int i = 0; i <= 9; i++){
            for(int j = 0; j <= 9; j++){
                this.unitMatrix[i][j].setCheckCheat(true);
            }
        }
    }

    public void quitCheatBattlefield(){
        for(int i = 0; i <= 9; i++){
            for(int j = 0; j <= 9; j++){
                this.unitMatrix[i][j].setCheckCheat(false);
            }
        }
    }

    public void setInitialTanks(int tankNum){
        tanks = new Tanks(tankNum);
    }


    public char showPositionStateByCoordinates (int xCoordinate, int yCoordinate) {
        return unitMatrix[xCoordinate][yCoordinate].getState();
    }

    public void hitOnTank (String coordinate, Tanks tanks) {
        int xCoordinate = coordinate.charAt(0) - 65;
        int yCoordinate = coordinate.charAt(1) - 49;
        if (coordinate.length() == 3 && coordinate.charAt(1) == '1' && coordinate.charAt(2) == '0') {
            yCoordinate = 9;
        }
        unitMatrix[xCoordinate][yCoordinate].setCheckFog(false);
        unitMatrix[xCoordinate][yCoordinate].setCurrentState();
        if(unitMatrix[xCoordinate][yCoordinate].getState() == 'X'){
            for(Tank tank: tanks.getTankList()){
                for(Unit unit: tank.getTankUnitList()){
                    if(unit.getxCoordinates() == xCoordinate && unit.getyCoordinates() == yCoordinate){
                        tank.tankUnitsDecrement();
                        int newDamage = tank.tankDamageDone();
                        tank.setTankDamage(newDamage);
                    }
                }
            }
        }

    }


    public int getRandomInt(int min,int max)
    {
        Random rand = new Random();
        return rand.nextInt(max - min + 1) + min;
    }

    public int getRandomIntInBound(int min,int max, int minBound, int maxBound)
    {
        int n = getRandomInt(min, max);
        if(n > maxBound){
            n = getRandomIntInBound(min, max, minBound, maxBound);
        }
        if(n < minBound){
            n = getRandomIntInBound(min, max, minBound, maxBound);
        }
        return n;
    }

    public Unit generateFirstTankUnitCoordinate(){
        int firstX = getRandomInt(0, 9);
        int firstY = getRandomInt(0, 9);

        Unit firstUnit = unitMatrix[firstX][firstY];
        if(firstUnit.isCheckTank()){
            generateFirstTankUnitCoordinate();
        }
        firstUnit.setCheckTank(true);
        firstUnit.setCurrentState();

        return firstUnit;
    }
    public Unit generateNextTankUnitCoordinate(Unit parentUnit){
        // get parentUnit's coordinates
        int parentX = parentUnit.getxCoordinates();
        int parentY = parentUnit.getyCoordinates();
        int xDif = 0;
        int yDif = 0;
        // get random candidate coordinates for next Unit
        int randomCandidateX = getRandomIntInBound(parentX-1, parentX+1, 0, 9);
        int randomCandidateY = getRandomIntInBound(parentY-1, parentY+1, 0, 9);
        Unit nextUnit = unitMatrix[randomCandidateX][randomCandidateY];
        if (parentX > randomCandidateX) {
            xDif = parentX - randomCandidateX;
        }
        else {
            xDif = randomCandidateX - parentX;
        }
        if (parentY > randomCandidateY) {
            yDif = parentY - randomCandidateY;
        }
        else {
            yDif = randomCandidateY - parentY;
        }
        while (nextUnit.isCheckTank() || randomCandidateY > 9 || (xDif + yDif) > 1){
            randomCandidateX = getRandomIntInBound(parentX-1, parentX+1,0,9);
            randomCandidateY = getRandomIntInBound(parentY-1, parentY+1,0,9);
            nextUnit = unitMatrix[randomCandidateX][randomCandidateY];
            if (parentX > randomCandidateX) {
                xDif = parentX - randomCandidateX;
            }
            else {
                xDif = randomCandidateX - parentX;
            }
            if (parentY > randomCandidateY) {
                yDif = parentY - randomCandidateY;
            }
            else {
                yDif = randomCandidateY - parentY;
            }
        }
        nextUnit.setCheckTank(true);
        nextUnit.setCurrentState();
        return nextUnit;
    }

    public void setTanksPosition(Tanks tanks) {
        int tanksNum = tanks.getTankNum();
        // set tank with num of tanks
        for(int i = 0; i<tanksNum; i++){
            // set one tank's first unit
            Unit firstTankUnit = generateFirstTankUnitCoordinate();
            tanks.getTankList().get(i).getTankUnitList().add(0, firstTankUnit);
            Unit parentUnit = firstTankUnit;
            // set tanks next unit one by one, one tank, except first unit, left four units
            for(int j = 1; j <= 4; j++){
                Unit nextUnit = generateNextTankUnitCoordinate(parentUnit);
                parentUnit = nextUnit;
                tanks.getTankList().get(i).getTankUnitList().add(j, nextUnit);
            }
        }

    }

    public void setTanksPositionCheated(Tanks tanks) {
        int tanksNum = tanks.getTankNum();
        char[] c = {'A','B','C','D','E','F','G','H','I','J','K','L','M','N'};
        // set tank with num of tanks
        for(int i = 0; i<tanksNum; i++){
            char tankCheatedWord = c[i];
            tanks.getTankList().get(i).getTankUnitList().get(0).setState(tankCheatedWord);
            // set tanks next unit one by one, one tank, except first unit, left four units
            for(int j = 1; j <= 4; j++){
                tanks.getTankList().get(i).getTankUnitList().get(j).setState(tankCheatedWord);
            }
        }

    }

    public void setTanksPositionCheatedBack(Tanks tanks) {
        int tanksNum = tanks.getTankNum();
        // set tank with num of tanks
        for(int i = 0; i<tanksNum; i++){
            tanks.getTankList().get(i).getTankUnitList().get(0).setCurrentState();
            // set tanks next unit one by one, one tank, except first unit, left four units
            for(int j = 1; j <= 4; j++){
                tanks.getTankList().get(i).getTankUnitList().get(j).setCurrentState();
            }
        }

    }

    public Tanks getTanks() {
        return tanks;
    }

}
