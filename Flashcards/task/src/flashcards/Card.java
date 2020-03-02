package flashcards;

public class Card {

    private String term;
    private String definition;
    private int mistakes;

    public Card(String term, String definition) {
        this.term = term;
        this.definition = definition;
        mistakes = 0;
    }

    public Card(String term, String definition, int mistakes) {
        this.term = term;
        this.definition = definition;
        this.mistakes = mistakes;
    }

    public String getTerm() {
        return term;
    }

    public String getDefinition() {
        return definition;
    }

    public int getMistakes() {
        return mistakes;
    }

    public void addMistake() {
        mistakes++;
    }

    public void resetMistakes() {
        mistakes = 0;
    }
}
