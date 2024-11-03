import java.io.IOException;

public class EmptyFileException extends IOException {
    //An exception of this type should be raised when the contents of the file to be parsed are empty. 
    //You should pass in a string to its constructor, 
    //which passes that string to the parent’s constructor. 
    public EmptyFileException(String message) {
        super(message);
    }
}

