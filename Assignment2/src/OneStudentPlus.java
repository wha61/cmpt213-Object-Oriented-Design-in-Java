/**
 *  OneStudentPlus class also models the information about one student in the group, but it includes
 *  ID of the target student for regrouping the group. Data includes oneStudent from class OneStudent,
 *  and student's ID. It provides elements for the Arraylist in main.
 *  @author Wenxiang He
 */
public class OneStudentPlus {
    OneStudent oneStudent;
    String ID;

    public OneStudentPlus(OneStudent oneStudent, String ID) {
        this.oneStudent = oneStudent;
        this.ID = ID;
    }

    public OneStudent getOneStudent() {
        return oneStudent;
    }

    public String getID() {
        return ID;
    }


    public void setName(String ID) {
        this.ID = ID;
    }

    @Override
    public String toString() {
        return "OneStudentPlus{" +
                oneStudent +
                ", ID='" + ID + '\'' +
                "}\n";
    }
}
