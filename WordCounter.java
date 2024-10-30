import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCounter {

    public WordCounter() {
        return;
    }
    public String processText(StringBuffer text, String stopword) {
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
        if(words.contains(stopword)) {
            return "" + (words.indexOf(stopword) + 1);
        }
        //if its null
        else if (stopword == null){
            return "" + words.size();
        }
        //not found in buffer, have to raise stopwordexception
        System.out.println("");
        
        throw new InvalidStopwordException("Stopword is invalid");
    }   

    public String processFile(String path) {
        StringBuffer buffer = new StringBuffer(path);
        //if file cannot be opened, prompt user to reenter filename until they enter a filename that can be opened
        //if file empty, raise EmptyFileException
        throw new EmptyFileException();
        //return stringBuffer;
    }
    public static void main(String[] args) {
        StringBuffer newBuffer = new StringBuffer("This sentence is long enough to yellow pass this test. But, it could be -- even -- longer...");
        WordCounter newCounter = new WordCounter();
        newCounter.processText(newBuffer, null);
    }

}
