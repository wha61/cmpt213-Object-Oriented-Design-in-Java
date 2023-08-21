/**
 *  Marks class also models the information about all students. It includes one student's information
 *  and the group that student belongs to. It provides elements for the Arraylist in main.
 *  @author Wenxiang He
 */

public class Marks {
    private Mark m;
    private int groupIndex;

    public Marks(Mark m, int groupIndex) {
        this.m = m;
        this.groupIndex = groupIndex;
    }

    public Mark getM() {
        return m;
    }

    public int getGroupIndex() {
        return groupIndex;
    }


    @Override
    public String toString() {
        return "Marks{" +
                "m=" + m +
                ", groupIndex=" + groupIndex +
                '}';
    }
}
