/**
 *  Mark class models the information from OneStudent class and stores it in different structure
 *  for better out put in the .csv file. Data includes targetStudent, sourceStudent, score and comment.
 *  It provides the field for the Marks class and provides elements for the Arraylist in main.
 *  @author Wenxiang He
 */
public class Mark {
    private String targetStudent;
    private String sourceStudent;
    private Double score;
    private String comment;

    public Mark(String targetStudent, String sourceStudent, Double score, String comment) {
        this.targetStudent = targetStudent;
        this.sourceStudent = sourceStudent;
        this.score = score;
        this.comment = comment;
    }

    public Mark() {

    }

    public String getTargetStudent() {
        return targetStudent;
    }

    public void setTargetStudent(String targetStudent) {
        this.targetStudent = targetStudent;
    }

    public String getSourceStudent() {
        return sourceStudent;
    }

    public void setSourceStudent(String sourceStudent) {
        this.sourceStudent = sourceStudent;
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
        return "Mark{" +
                "targetStudent='" + targetStudent + '\'' +
                ", sourceStudent='" + sourceStudent + '\'' +
                ", score=" + score +
                ", comment='" + comment + '\'' +
                "}\n";
    }
}
