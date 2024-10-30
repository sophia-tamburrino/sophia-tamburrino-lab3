import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.swing.plaf.BorderUIResource.EmptyBorderUIResource;

public class EmptyFileException extends IOException {
    //An exception of this type should be raised when the contents of the file to be parsed are empty. 
    //You should pass in a string to its constructor, 
    //which passes that string to the parent’s constructor. 
    public EmptyFileException() {
    }

    LineNumberReader reader = new LineNumberReader(new InputStreamReader(new FileInputStream("data.txt")));
    String line = reader.readLine();
    while( line != null ) {
    Scanner sc = new Scanner(line);
        
    // read your tokens for whatever you need!
    // ...
        
    line = reader.readLine();
    System.out.println("You're on line " + reader.getLineNumber());      
}

