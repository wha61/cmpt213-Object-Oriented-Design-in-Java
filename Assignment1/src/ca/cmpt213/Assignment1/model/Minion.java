package ca.cmpt213.Assignment1.model;

/**
 * Minion class models the information about a little minion. Data includes minion's name,
 * minion's height, and number of minion's evil deeds. It provides the elements for the
 * Arraylist "Minions" in the MinionManager class.
 * @author Wenxiang He
 */
public class Minion {
    private String name;
    private double height;
    private int numOfEvilDeedsCompleted;

    public Minion(String name, double height, int numOfEvilDeedsCompleted) {
        this.name = name;
        this.height = height;
        this.numOfEvilDeedsCompleted = numOfEvilDeedsCompleted;
    }

    public String getName() {
        return name;
    }

    public double getHeight() {
        return height;
    }

    public int getNumOfEvilDeedsCompleted() {
        return numOfEvilDeedsCompleted;
    }

    public void setNumOfEvilDeedsCompleted(int numOfEvilDeedsCompleted) {
        this.numOfEvilDeedsCompleted = numOfEvilDeedsCompleted;
    }

    @Override
    public String toString() {
        return "ca.cmpt213.Assigment1.model.Minion[Name: " + name +
                ", Evil Deeds:" + numOfEvilDeedsCompleted +
                ", height=" + height +
                "]";
    }
}
