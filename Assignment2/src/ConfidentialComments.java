/**
 * ConfidentialComments class models the information about private comments. Data includes student's name,
 * student's private comments.
 * @author Wenxiang He
 */
public class ConfidentialComments {
    String name;
    String confidential_comments;

    public ConfidentialComments() {
    }

    public ConfidentialComments(String name, String confidential_comments) {
        this.name = name;
        this.confidential_comments = confidential_comments;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConfidential_comments() {
        return confidential_comments;
    }

    public void setConfidential_comments(String confidential_comments) {
        this.confidential_comments = confidential_comments;
    }

    @Override
    public String toString() {
        return "ConfidentialComments{" +
                "name='" + name + '\'' +
                ", confidential_comments='" + confidential_comments + '\'' +
                "}\n";
    }
}
