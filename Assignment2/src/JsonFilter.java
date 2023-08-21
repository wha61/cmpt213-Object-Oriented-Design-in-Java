/**
 * JsonFilter class is a full external class, it is used for creating filter for methods
 * gsonReadInputGroup(File input) and gsonReadInputConfidentialComments(File input) to
 * filter the files end in .json
 * @author Wenxiang He
 */
import java.io.File;
import java.io.FileFilter;

public class JsonFilter implements FileFilter {
    @Override
    public boolean accept(File file){
            return file.getName().toLowerCase().endsWith(".json");
        }

    }

