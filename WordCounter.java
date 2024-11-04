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
        if (words.size() < 5) {
            throw new TooSmallText("Only found " + words.size() + " words.");
        }
        if (words.contains(stopword)) {
            return "" + (words.indexOf(stopword) + 1);
        }
        // if its null
        else if (stopword == null) {
            System.out.println("Stopword is null!");
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
        
        try {
            LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream(path)));
        } catch (FileNotFoundException e) {
            // TODO: handle exception
            //reenter the file name
            BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
            String answer = in.readLine();
            LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream(answer)));
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
            //means there is a stopword
            String stopword = null;
            if(args.length == 2) {
                stopword = args[1];
            }
            try {
                processFile(args[0]);
            } catch (EmptyFileException e) {
                // TODO: handle exception
                System.out.println(e);
            }
            try {
                processFile(args[0]);
            } catch (FileNotFoundException e) {
                // TODO: handle exception
                System.out.println(e);
            }
            StringBuffer file = processFile(args[0]);
            
            try {
                processText(file, stopword);
            } catch (TooSmallText e) {
                // TODO: handle exception
                System.out.println(e);
            }

            String numWords = processText(file, stopword);
            System.out.println("Found " + numWords + " words.");
        }
        else if (answer.equals("2")) {

        }
        //reprompt
        else {

        }

 
    }

}
