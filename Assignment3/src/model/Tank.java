package model;

import java.util.ArrayList;
import java.util.List;
/**
 *  Tank class models the information of a tank among many tanks. It has its own Units.
 *  Data includes tankUnitsNum, tankDamage and tankUnitList.
 *  It provides the element for the Tanks class and Battlefield class.
 *  We can calculate damage per tanks based on its Unit num.
 *  @author Wenxiang He & Bowei Pan
 */
public class Tank {
    private final int INIT_UNITS_NUM= 5;

    private final int INIT_DAMAGE = 20;
    private int tankUnitsNum;
    private int tankDamage;
    private final List<Unit> tankUnitList;

    // initial state of a tank
    public Tank() {
        this.tankUnitsNum = INIT_UNITS_NUM;
        this.tankDamage = INIT_DAMAGE;
        this.tankUnitList = new ArrayList<>();
        Unit tankUnit = new Unit();
        for(int i = 0; i < INIT_UNITS_NUM; i++){
            this.tankUnitList.add(i, tankUnit);
        }
    }


    public int getTankCellsNum() {
        return tankUnitsNum;
    }


    public int getTankDamage() {
        return tankDamage;
    }

    public void setTankDamage(int tankDamage) {
        this.tankDamage = tankDamage;
    }

    public List<Unit> getTankUnitList() {
        return tankUnitList;
    }


    // get tank damage based on tank's cells
    public int tankDamageDone(){
        int damage = 0;
        if(this.tankUnitsNum == this.INIT_UNITS_NUM){
            damage = this.INIT_DAMAGE;
        }
        int FOUR_UNITS_NUM = 4;
        if(this.tankUnitsNum == FOUR_UNITS_NUM){
            damage = 20;
        }
        int THREE_UNITS_NUM = 3;
        if(this.tankUnitsNum == THREE_UNITS_NUM){
            damage = 5;
        }
        int TWO_UNITS_NUM = 2;
        if(this.tankUnitsNum == TWO_UNITS_NUM){
            damage = 2;
        }
        int ONE_UNITS_NUM = 1;
        if(this.tankUnitsNum == ONE_UNITS_NUM){
            damage = 1;
        }
        return damage;
    }

    public void tankUnitsDecrement() {
        this.tankUnitsNum--;
    }
}
