package model;

import java.util.ArrayList;
import java.util.List;
/**
 *  Tanks class models the information of all tanks on the Battlefield. It has its own Unit.
 *  Data includes tankNum, and tankList.
 *  We use this class for better calculating the damage sum.
 *  @author Wenxiang He & Bowei Pan
 */
public class Tanks {
    private List<Tank> tankList;
    private int tankNum;

    public Tanks() {
        this.tankNum = 5;
        this.tankList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Tank tank = new Tank();
            tankList.add(i, tank);
        }
    }

    public Tanks(int tankNum) {
        this.tankNum = tankNum;
        this.tankList = new ArrayList<>();
        for (int i = 0; i < tankNum; i++) {
            Tank tank = new Tank();
            tankList.add(i, tank);
        }
    }


    public List<Tank> getTankList() {
        return tankList;
    }

    public int getTankNum() {
        return tankNum;
    }

    public int getCurrentUnitNum() {
        int num = 0;
        for (Tank tank : this.tankList) {
            num += tank.getTankCellsNum();
        }
        return num;
    }

    // get the damage sum of all tanks on the battlefield
    public int damageTanksDone(){
        int damage = 0;
        for(Tank tank: this.tankList){
            damage += tank.getTankDamage();
        }
        return damage;
    }
}
