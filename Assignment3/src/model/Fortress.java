package model;
/**
 *  Unit class models the information of Fortress on the battlefield.
 *  Data includes currentHealth.
 *  We can change its currentHealth through minus tanksDamage.
 *  @author Wenxiang He & Bowei Pan
 */
public class Fortress {
    private int currentHealth;

    public Fortress() {
        this.currentHealth = 2400;
    }


    public int getCurrentHealth() {
        return currentHealth;
    }


    // set fortress's health, decrease due to tanks' damage per round
    public void setHealth(Tanks tanks){
        int tanksDamage = tanks.damageTanksDone();
        currentHealth =  currentHealth - tanksDamage;
    }
}
