package ca.cmpt213.Assignment1.model;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * MinionManger class models a sets of little minions using the ArrayList. Data includes minions.
 * It provides five methods to modify the minions set, include list all minions in the set, add specific
 * minion with read in name and height, remove specific minion, attribute specific minion with an evil
 * deed, and debug dump of minion details.
 * @author Wenxiang He
 */
public class MinionManger implements Iterable<Minion>{
    private ArrayList<Minion> minions = new ArrayList<>();
    private int minionIndex;

    public void list(){
        System.out.println("\n" + "List of Minions:\n" + "****************");
        if(minions.size() == 0){
            System.out.println("No minions found.");
        } else{
            minionIndex = 1;
            for (Minion minion:minions) {
                System.out.println(minionIndex + ". " + minion.getName() + ", " + minion.getHeight() + "m, " +
                    minion.getNumOfEvilDeedsCompleted() + " evil deed(s)");
                minionIndex++;
            }
        }
    }

    public void add(String name, double height){
        if(height < 0){
            System.out.println("ERROR: Height must be >= 0.");
        } else {
            Minion minion = new Minion(name, height, 0);
            minions.add(minion);
        }
    }

    public void remove(int number){
        int size = minions.size();
        if(number == 0){
            return;
        } else if(number > 0 && number <= size){
            minionIndex = number - 1;
            minions.remove(minionIndex);
        } else{
            System.out.println("Error: Please enter a selection between 0 and 0");
        }
    }

    public void Attribute(int number){
        int size = minions.size();
        if(number == 0){
            return;
        } else if(number > 0 && number <= size){
            minionIndex = number - 1;
            Minion minion = minions.get(minionIndex);
            String name = minion.getName();
            int evilMinionSeedBefore = minion.getNumOfEvilDeedsCompleted();
            int evilMinionSeedAfter = evilMinionSeedBefore + 1;
            minion.setNumOfEvilDeedsCompleted(evilMinionSeedAfter);
            System.out.println(name + " has now down " + evilMinionSeedAfter + " evil deed(s)!");
        } else{
            System.out.println("Error: Please enter a selection between 0 and 0");
        }
    }

    public void dump(){
        System.out.println("All minion objects:");
        if(minions.size() == 0){
            System.out.println("No minions found.");
        } else{
            minionIndex = 1;
            for (Minion minion:minions) {
                System.out.println(minionIndex + ". " + minion);
                minionIndex++;
            }
        }
    }

    @Override
    public Iterator<Minion> iterator() {
        return minions.iterator();
    }
}
