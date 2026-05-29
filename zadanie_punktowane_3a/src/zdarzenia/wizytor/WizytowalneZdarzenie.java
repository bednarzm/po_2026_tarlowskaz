package zdarzenia.wizytor;

public interface WizytowalneZdarzenie {

    <T> T akceptuj(WizytorZdarzenia<T> wizytor);
}