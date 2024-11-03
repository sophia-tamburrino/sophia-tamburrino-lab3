import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {
    public static String processText(StringBuffer text, String stopword) throws TooSmallText, InvalidStopwordException{
        //if the stopword is not found in stringBuffer, raise invalidstopwordexception
        //if it is found, count the number of words through that stopword
        //if stopword == null, method counts all words in the file
        //COUNTS THE STOPWORD AS A WORD
        //if it doesn't have at least 1 character in the string, don't include it
        
        String buffer = text.toString();
        Pattern regex = Pattern.compile("[a-zA-Z]+");
        Matcher regexMatcher = regex.matcher(buffer);

        ArrayList<String> words = new ArrayList<String>();
        while (regexMatcher.find()) {
            //This works
            words.add(regexMatcher.group());
        } 
        //if stopword exists
        if(words.size() < 5) {
            throw new TooSmallText("TooSmallText: Only found "+ words.size() + " words.");
        }
        if(words.contains(stopword)) {
            return "" + (words.indexOf(stopword) + 1);
        }
        //if its null
        else if (stopword == null){
            return "" + words.size();
        }

        //not found in buffer, have to raise stopwordexception
        
        throw new InvalidStopwordException("InvalidStopwordException: Couldn't find stopword: " + stopword);
        
    }   

    public static StringBuffer processFile(String path) throws EmptyFileException{
        //if file cannot be opened, prompt user to reenter filename until they enter a filename that can be opened
        //if file empty, raise EmptyFileException
        if(path == "" || path == null) {
            throw new EmptyFileException("EmptyFileException: " + path + " was empty");
        }
        LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream(path)));
        String line = reader.readLine();
        StringBuffer retVal = new StringBuffer();
        while( line != null ) {
            line = reader.readLine();
            retVal.append(line);
        }

        //return the stringbuffer
        return retVal;
    }
    public static void main(String[] args) {
        StringBuffer newBuffer = new StringBuffer("This sentence is long enough to yellow pass this test. But, it could be -- even -- longer...");
        try {
            processText(newBuffer, "lol");
        } catch (InvalidStopwordException e) {
            System.out.println(e);
        }
        
        StringBuffer bufferTwo = new StringBuffer("Only Three Words");
        try {
            processText(bufferTwo, "");
        } catch (TooSmallText e) {
            System.out.println(e);
        }

    }

}
