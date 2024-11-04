import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    public static String processText(StringBuffer text, String stopword) throws TooSmallText, InvalidStopwordException {
        // if the stopword is not found in stringBuffer, raise invalidstopwordexception
        // if it is found, count the number of words through that stopword
        // if stopword == null, method counts all words in the file
        // COUNTS THE STOPWORD AS A WORD
        // if it doesn't have at least 1 character in the string, don't include it

        String buffer = text.toString();
        Pattern regex = Pattern.compile("[a-zA-Z]+");
        Matcher regexMatcher = regex.matcher(buffer);

        ArrayList<String> words = new ArrayList<String>();
        while (regexMatcher.find()) {
            // This works
            words.add(regexMatcher.group());
        }
        // if stopword exists
        if (words.size() < 5) {
            throw new TooSmallText("Only found " + words.size() + " words.");
        }
        if (words.contains(stopword)) {
            return "" + (words.indexOf(stopword) + 1);
        }
        // if its null
        else if (stopword == null) {
            return "" + words.size();
        }

        // not found in buffer, have to raise stopwordexception

        throw new InvalidStopwordException("Couldn't find stopword: " + stopword);

    }

    public static StringBuffer processFile(String path) throws EmptyFileException, FileNotFoundException, IOException {
        // if file cannot be opened, prompt user to reenter filename until they enter a
        // filename that can be opened
        // if file empty, raise EmptyFileException
        if (path == "" || path == null) {
            throw new EmptyFileException(path + " was empty");
        }
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream(path)));
        String line = reader.readLine();
        if (line == null || line == "") {
            reader.close();
            throw new EmptyFileException(path + " was empty");
        }
        StringBuffer retVal = new StringBuffer();
        while (line != null) {
            retVal.append(line);
            line = reader.readLine();
        }

        // return the stringbuffer, have to close the reader to avoid leaks

        reader.close();
        return retVal;
    }

    public static void main(String[] args) throws IOException, InvalidStopwordException, TooSmallText {
        // will prompt user to enter an option 1 or option 2
        // System.out.println("Please input option 1 or 2: ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String answer = in.readLine();
        // System.out.println("Answer: " + answer);

        // ARGS[] is the command line input!
        if (answer.equals("1")) {
            // System.out.println("reaches here");
            try {
                processFile(args[0]);
            } catch (EmptyFileException e) {
                // TODO: handle exception
                System.out.println(e);
            }
            StringBuffer file = processFile(args[0]);
            // stopword cannot be invalid here because I am manually inputting ""
            try {
                processText(file, null);
            } catch (TooSmallText e) {
                // TODO: handle exception
                System.out.println(e);
            }

            String numWords = processText(file, null);
            System.out.println("Found " + numWords + " words.");
        }
        // else if (answer == "2") {
        // //if there is a second command line input (stopword)
        // if(args.length == 2) {
        // String stopword = args[1];
        // }

        // }
        // INVALID ANSWER
        // else {

        // }
        // if 1, process a file.
        // if (answer != null && answer != "") {
        // StringBuffer file = processFile(answer);
        // String stopword = in.readLine();
        // System.out.println("Stopword: " + answer);
        // try {
        // processText(file, stopword);
        // } catch (InvalidStopwordException e) {
        // // should recurse, ask user to input again
        // System.out.println(e);
        // System.out.println("Please a new stopword: ");
        // BufferedReader inNew = new BufferedReader(new InputStreamReader(System.in));
        // String newStopword = inNew.readLine();
        // try {
        // processText(file, newStopword);
        // } catch (InvalidStopwordException j) {
        // System.out.println(j);
        // System.out.println("Failed to compute");
        // }
        // String returnVal = processText(file, newStopword);
        // System.out.println("Found " + returnVal + " words.");
        // }

        // String returnVal = processText(file, stopword);
        // System.out.println("Found " + returnVal + " words.");
        // }
        // // reprompt
        // else {
        // //reprompt/recurse on main???
        // main(args);
        // }
    }

}
