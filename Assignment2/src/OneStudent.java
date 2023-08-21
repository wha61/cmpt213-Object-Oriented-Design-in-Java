/**
 *  OneStudentPlus class models the information about one student in the group. Data includes student's name,
 *  student's mail, student's score and comment on other students. It provides the field for the
 *  OneStudentPlus class.
 *  @author Wenxiang He
 */
public class OneStudent {
    private String name;
    private String mail;
    private Double score;
    private String comment;

    public OneStudent(String name, String mail, Double score, String comment) {
        this.name = name;
        this.mail = mail;
        this.score = score;
        this.comment = comment;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public String toString() {
        return "OneStudent{" +
                "name='" + name + '\'' +
                ", mail='" + mail + '\'' +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                "}\n";
    }
}