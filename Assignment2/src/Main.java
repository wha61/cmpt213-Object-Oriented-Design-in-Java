import com.google.gson.*;

import java.io.*;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {

        // Get exactly two arguments
        checkArguments(args);

        // Print input and output path
        System.out.println("Input Path: " + args[0]);
        System.out.println("Output Path: " + args[1]);

        // Read files
        File input = new File(args[0]);
        File output = new File(args[1]);
        readFiles(input,output);

        // Filter only .json files and store student information
        List<OneStudentPlus> students = new ArrayList<>();
        List<OneStudentPlus> studentFiltered = folderFilter(students, input);

        // Error handing, return error and exit with -1
        checkScore(studentFiltered);
        checkFiles(studentFiltered);
        checkID(studentFiltered);
        checkRequiredFields(studentFiltered);

        // Filter only .json files and store student private comment
        List<ConfidentialComments> privateComments = new ArrayList<>();
        List<ConfidentialComments> privateCommentsFiltered = folderFilterForConfidentialComments(privateComments,input);

        // Put data in OneStudentPlus list to Mark list
        List<Mark> marks = new ArrayList<>();
        OneStudentPlusToMark(marks, studentFiltered);

        // Sort through Alphabet order
        sortStudents(marks);

        // Put data in Mark list to Marks list, create new group
        List<Marks> regroupMarks = new ArrayList<>();
        MarkToMarks(regroupMarks, marks);


        // Create output String for .csv file
        String out = outputString( regroupMarks, privateCommentsFiltered);

        // Check output string format
        System.out.println(out);

        //use BufferedWriter and FileWriter to put string in the output .csv file
        BufferedWriter bw = new BufferedWriter(new FileWriter(output.getAbsolutePath()+"/group_feedback.csv"));
        bw.write(out);
        bw.close();
    }

    // Error handing, return error and exit with -1
    private static void checkArguments(String[] args){
        if(args.length != 2){
            System.out.println("Accepted number of command line arguments is only two, please reenter exactly two command line arguments.");
            final int FAILURE = -1;
            System.exit(FAILURE);
        }
    }

    // Error handing, return error and exit with -1
    private static void readFiles(File input, File output){
        boolean inputCheck = input.exists();
        boolean outputCheck = output.exists();
        if(inputCheck == false || outputCheck == false){
            System.err.println("Input folder (for .json files) or the output folder (for the generated .csv file) do not exist, please reenter correct path.");
            final int FAILURE = -1;
            System.exit(FAILURE);
        }
    }

    // Error handing, return error and exit with -1
    private static void checkScore(List<OneStudentPlus> studentFiltered){
        double sumOfScore = 0;
        for(OneStudentPlus one : studentFiltered){
            double score = one.getOneStudent().getScore();
            if(score < 0){
                System.err.println("Score smaller than 0.");
                final int FAILURE = -1;
                System.exit(FAILURE);
            }
            sumOfScore += score;
        }
        int numOfGroupMember = (studentFiltered.size()) * 20;
        final double TOLERANCE = 0.1;
        if(Math.abs(sumOfScore - numOfGroupMember) > TOLERANCE){
            System.err.println("Sum of scores in the file is not (20 * number of group members), with a tolerance of 0.1");
            final int FAILURE = -1;
            System.exit(FAILURE);
        }
    }

    // Error handing, return error and exit with -1
    private static void checkFiles(List<OneStudentPlus> studentFiltered){
        if(studentFiltered.size() == 0){
            System.err.println("No JSON files are found.");
            final int FAILURE = -1;
            System.exit(FAILURE);
        }
    }

    // Error handing, return error and exit with -1
    private static void checkID(List<OneStudentPlus> studentFiltered){
        final int TOTAL = 9;
        for(OneStudentPlus o : studentFiltered){
            if(o.getOneStudent().getMail().length() != TOTAL){
                System.err.println("ID Type has problem.");
                final int FAILURE = -1;
                System.exit(FAILURE);
            }
        }
    }

    // Error handing, return error and exit with -1
    private static void checkRequiredFields(List<OneStudentPlus> studentFiltered){
        for(OneStudentPlus o : studentFiltered){
            if(o.getOneStudent() == null){
                System.err.println("Missing required fields");
                final int FAILURE = -1;
                System.exit(FAILURE);
            }
        }
    }

    private static List<OneStudentPlus> gsonReadInputGroup(File input) {
        List<OneStudentPlus> group = new ArrayList<>();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            //process all group
            JsonArray jsonArrayOfGroup = fileObject.get("group").getAsJsonArray();
            //precess comment student name first
            String sourceStudent = jsonArrayOfGroup.get(0).getAsJsonObject().get("sfu_email").getAsString();
            for (JsonElement groupElement : jsonArrayOfGroup) {
                //Get the JsonObjects:
                JsonObject groupJsonObject = groupElement.getAsJsonObject();

                //Extract some data;
                String name = groupJsonObject.get("name").getAsString();
                String mail = groupJsonObject.get("sfu_email").getAsString();

                //Extract contribution
                JsonObject jsonOfContribution = groupJsonObject.get("contribution").getAsJsonObject();
                //Contribution contribution = null;

                Double score = jsonOfContribution.get("score").getAsDouble();
                String comment = jsonOfContribution.get("comment").getAsString();

                //contribution = new Contribution(score, comment);

                OneStudent oneStudent = new OneStudent(name, mail, score, comment);
                OneStudentPlus oneStudentPlus = new OneStudentPlus(oneStudent, sourceStudent);

                group.add(oneStudentPlus);
            }
        }catch (FileNotFoundException e){
            System.err.println("No JSON files are found.!");
            e.printStackTrace();
            final int FAILURE = -1;
            System.exit(FAILURE);
        } catch(NullPointerException e){
            System.err.println("Missing required field.");
            e.printStackTrace();
            final int FAILURE = -1;
            System.exit(FAILURE);
        }
        return group;
    }

    private static ConfidentialComments gsonReadInputConfidentialComments(File input) {
        ConfidentialComments comment = new ConfidentialComments();
        try {
            JsonElement fileElement = JsonParser.parseReader(new FileReader(input));
            JsonObject fileObject = fileElement.getAsJsonObject();

            //process all group
            JsonArray jsonArrayOfGroup = fileObject.get("group").getAsJsonArray();
            //precess comment student name first
            String id = jsonArrayOfGroup.get(0).getAsJsonObject().get("sfu_email").getAsString();
            //Process confidentialComments
            String confidentialComments = fileObject.get("confidential_comments").getAsString();

            comment = new ConfidentialComments(id, confidentialComments);
        }catch (FileNotFoundException e){
            System.err.println("No JSON files are found.!");
            e.printStackTrace();
            final int FAILURE = -1;
            System.exit(FAILURE);
        } catch(NullPointerException e){
            System.err.println("Missing required field.");
            e.printStackTrace();
            final int FAILURE = -1;
            System.exit(FAILURE);
        }
        return comment;
    }

    //recursively read in information of private comments from .json
    private static List<ConfidentialComments> folderFilterForConfidentialComments(List<ConfidentialComments> merge, File file){
        File[] files = file.listFiles();
        //fileFilter(file);
        FileFilter filter = new JsonFilter();
        File[] jsonFile = file.listFiles(filter);
        for (File subFile : jsonFile) {
            //System.out.println("  file: " + subFile.getAbsolutePath());
            ConfidentialComments one = gsonReadInputConfidentialComments(subFile);
            merge.add(one);
        }
        for(File a:files){
            if(a.isDirectory()){
                folderFilterForConfidentialComments(merge, a);
            }
        }
        return merge;
    }

    //recursively read in information of students from .json
    private static List<OneStudentPlus> folderFilter(List<OneStudentPlus> merge, File file){
        File[] files = file.listFiles();
        //fileFilter(file);
        FileFilter filter = new JsonFilter();
        File[] jsonFile = file.listFiles(filter);
        for (File subFile : jsonFile) {
            //System.out.println("  file: " + subFile.getAbsolutePath());
            merge.addAll(gsonReadInputGroup(subFile));
        }
        for(File a:files){
            if(a.isDirectory()){
                folderFilter(merge,a);
            }
        }
        return merge;
    }

    // Put data in OneStudentPlus list to Mark list
    private static List<Mark> OneStudentPlusToMark(List<Mark> marks, List<OneStudentPlus> studentFiltered){
        for(int i = 0; i < studentFiltered.size(); i++){
            String sourceStudent = studentFiltered.get(i).getID();
            String targetStudent = studentFiltered.get(i).getOneStudent().getMail();
            Double score = studentFiltered.get(i).getOneStudent().getScore();
            String comment = studentFiltered.get(i).getOneStudent().getComment();
            comment = deleteCharString(comment, '\n');
            comment = replaceCharString(comment, '\"', '\'');
            comment = replaceCharString(comment, ',', '.');

            Mark mark = new Mark(targetStudent,sourceStudent,score,comment);
            marks.add(mark);
        }
        return  marks;
    }
    // Put data in Mark list to Marks list
    private static List<Marks> MarkToMarks(List<Marks> regroupMarks, List<Mark> marks){
        int index = 0;
        int groupIndex = 1;
        int size = marks.size();
        int sortIndex = 0;
        while(index < size ){
            String targetStudent = marks.get(index).getTargetStudent();
            while(sortIndex < size && (Objects.equals(marks.get(sortIndex).getTargetStudent(), targetStudent) )){
                Marks m = new Marks(marks.get(sortIndex), groupIndex);
                regroupMarks.add(m);
                sortIndex++;
                index = sortIndex;
            }
            groupIndex++;
        }
        return regroupMarks;
    }

    // Create output String for .csv file
    private static String outputString(List<Marks> regroupMarks, List<ConfidentialComments> privateCommentsFiltered){
        String out = "Group#,Source Student,Target Student,Score,Comment,,Private" + "\n";
        int i = 0;
        double s = 0;
        double numS = 0;
        while(i < regroupMarks.size()){
            int regroupIndex = regroupMarks.get(i).getGroupIndex();
            String oneGroup = "Group" + regroupIndex + ",,,," + "\n";
            out = out + oneGroup;
            Mark mark = new Mark();
            String con = null;
            while(i < regroupMarks.size() && (Objects.equals(regroupMarks.get(i).getGroupIndex(),regroupIndex))){
                mark = regroupMarks.get(i).getM();
                int k = 0;

                while(k < privateCommentsFiltered.size()){
                    if((Objects.equals(privateCommentsFiltered.get(k).getName(),mark.getTargetStudent()))){
                        con = privateCommentsFiltered.get(k).getConfidential_comments();
                        con = deleteCharString(con, '\n');
                        con = replaceCharString(con, '\"', '\'');
                        con = replaceCharString(con, ',', '.');
                    }
                    k++;
                }
                s += mark.getScore();
                if(Objects.equals(mark.getTargetStudent(),mark.getSourceStudent())){
                    mark.setSourceStudent("-->");
                }
                String oneRow = "," + mark.getSourceStudent() + "," + mark.getTargetStudent()
                        + "," + mark.getScore() + "," + mark.getComment() + "\n";
                out = out + oneRow;
                i++;
                numS = i;
            }
            double avg = s/numS;
            avg=Math.round(avg*10)/10.0;
            String confidential = ",-->," + mark.getTargetStudent()
                    + ",avg " + avg +  ",,," + con + "\n";
            out = out + confidential;
        }
        return out;
    }

    // For formatting the data to output in .csv file
    private static String deleteCharString(String sourceString, char replace) {
        String newString = "";
        for (int i = 0; i < sourceString.length(); i++) {
            if (sourceString.charAt(i) != replace) {
                newString += sourceString.charAt(i);
            }
        }
        return newString;
    }

    // For formatting the data to output in .csv file
    private static String replaceCharString(String sourceString, char replace, char replaced) {
        String newString = "";
        for (int i = 0; i < sourceString.length(); i++) {
            if (sourceString.charAt(i) != replace) {
                newString += sourceString.charAt(i);
            }
            else{
                newString += replaced;
            }
        }
        return newString;
    }

    //Sort the mark through student's mail in Alphabet order
    private static void sortStudents(List<Mark> marks) {
        Comparator<Mark> makeSorter = new Comparator<Mark>() {
            @Override
            public int compare(Mark mark1, Mark mark2) {
                return mark1.getTargetStudent().compareTo(mark2.getTargetStudent());
            }
        };
        java.util.Collections.sort(marks, makeSorter);
    }

}
