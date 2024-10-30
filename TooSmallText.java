public class TooSmallText extends Exception {
    //that is raised when the length of the text is less than five words.
    public TooSmallText(String message) {
        super(message);
    }

}
