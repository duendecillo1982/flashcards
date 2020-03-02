package flashcards;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CardCollection {
    private ArrayList<Card> cards;

    public CardCollection() {
        this.cards = new ArrayList<>();
    }

    public ArrayList<Card> getCards() {
        return cards;
    }

    public boolean termExists(String term) {
        boolean found = false;
        for (Card card: cards) {
            if (card.getTerm().equals(term)) {
                found = true;
            }
        }
        return found;
    }

    public boolean definitionExists(String definition) {
        boolean found = false;
        for (Card card: cards) {
            if (card.getDefinition().equals(definition)) {
                found = true;
            }
        }
        return found;
    }

    public void addCard(String term, String definition) {
        Card card = new Card(term, definition);
        cards.add(card);
    }

    public void addCard(String term, String definition, int mistakes) {
        Card card = new Card(term, definition, mistakes);
        cards.add(card);
    }

    public void addCard(Card card) {
        cards.add(card);
    }

    public void removeCard(String term) {
        CardCollection cardsToRemove = new CardCollection();
        for (Card card: cards) {
            if (card.getTerm().equals(term)) {
                cardsToRemove.addCard(card);
            }
        }
        cards.removeAll(cardsToRemove.getCards());
    }

    public ArrayList<String> getHardestCards() {
        int highestMistakes = 0;
        for (Card card: cards) {
            if (card.getMistakes() > highestMistakes) {
                highestMistakes = card.getMistakes();
            }
        }
        ArrayList<String> hardestCards = new ArrayList<>();

        for (Card card: cards) {
            if (card.getMistakes() == highestMistakes & card.getMistakes() != 0) {
                hardestCards.add(card.getTerm());
            }
        }
        return hardestCards;
    }

    public int getMistakesForTerm(String term) {
        int mistakes = -1;
        for (Card card: cards) {
            if (card.getTerm().equals(term)) {
                mistakes = card.getMistakes();
            }
        }
        return mistakes;
    }

    public void resetMistakes() {
        for (Card card: cards) {
            card.resetMistakes();
        }
    }
}
