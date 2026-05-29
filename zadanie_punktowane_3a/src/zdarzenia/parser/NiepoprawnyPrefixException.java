package zdarzenia.parser;

public class NiepoprawnyPrefixException extends RuntimeException {

    public NiepoprawnyPrefixException(String komunikat) {
        super(komunikat);
    }
}