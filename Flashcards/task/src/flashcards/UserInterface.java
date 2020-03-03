package flashcards;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class UserInterface {

    private Scanner scanner;
    private CardCollection cards;
    private List<String> log;

    public UserInterface(Scanner scanner, CardCollection cards) {
        this.scanner = scanner;
        this.cards = cards;
        this.log = new ArrayList<>();
    }

    private void exit() {
        log.add("exit");
        System.out.println("Bye bye!");
        log.add("Bye bye!");
    }

    private void add() {
        log.add("add");
        System.out.println("The card:");
        log.add("The card:");
        String term = scanner.nextLine();
        log.add(term);
        if (cards.termExists(term)) {
            System.out.println("The card \"" + term + "\" already exists.");
            log.add("The card \\\"\" + term + \"\\\" already exists.");
            return;
        }
        System.out.println("The definition of the card:");
        log.add("The definition of the card:");
        String definition = scanner.nextLine();
        if (cards.definitionExists(definition)) {
            System.out.println("The definition \"" + definition + "\" already exists.");
            log.add("The definition \"" + definition + "\" already exists.");
            return;
        }
        cards.addCard(term, definition);
        System.out.println("The card (\"" + term + "\":\"" + definition + "\") has been added.");
        log.add("The card (\"" + term + "\":\"" + definition + "\") has been added.");
    }

    private void remove() {
        log.add("remove");
        System.out.println("The card:");
        log.add("The card:");
        String term = scanner.nextLine();
        log.add(term);
        if (!cards.termExists(term)) {
            System.out.println("Can't remove \"" + term + "\": there is no such card.");
            log.add("Can't remove \"" + term + "\": there is no such card.");
        }
        cards.removeCard(term);
        System.out.println("The card has been removed.");
        log.add("The card has been removed.");
    }

    private void importCards() {
        log.add("import");
        System.out.println("File name:");
        log.add("File name:");
        String fileName = scanner.nextLine();
        log.add(fileName);
        File file = new File(fileName);
        int count = 0;
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String term = fileScanner.nextLine();
                String definition = fileScanner.nextLine();
                int mistakes = Integer.valueOf(fileScanner.nextLine());
                cards.addCard(term, definition, mistakes);
                count++;
            }
            System.out.println(count + " cards have been loaded.");
            log.add(count + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            log.add("File not found.");
        }
    }

    private void exportCards () {
        log.add("export");
        System.out.println("File name:");
        log.add("File name:");
        String fileName = scanner.nextLine();
        log.add(fileName);
        int count = 0;
        try (PrintWriter printer = new PrintWriter(fileName)) {
            ArrayList<Card> exportCards = cards.getCards();
            for (Card card: exportCards) {
                String term = card.getTerm();
                String definition = card.getDefinition();
                int mistakes = card.getMistakes();
                printer.println(term);
                printer.println(definition);
                printer.println(mistakes);
                count++;
            }
            System.out.println(count + " cards have been saved.");
            log.add(count + " cards have been saved.");
        } catch (IOException e) {
            System.out.println("Something went wrong");
            log.add("Something went wrong");
        }
    }

    private void ask() {
        log.add("ask");
        System.out.println("How many times to ask?");
        log.add("How many times to ask?");
        int numCards = scanner.nextInt();
        log.add(Integer.toString(numCards));
        scanner.nextLine();
        ArrayList<Card> askCards = cards.getCards();
        if (askCards.isEmpty()) {
            return;
        }
        for (int i = 0; i < numCards; i++) {

            Random random = new Random();
            int rand = random.nextInt(askCards.size());
            Card cardToAsk = askCards.get(rand);
            String term = cardToAsk.getTerm();
            String definition = cardToAsk.getDefinition();

            System.out.println("Print the definition of \"" + term + "\":");
            log.add("Print the definition of \"" + term + "\":");
            String answer = scanner.nextLine();
            log.add(answer);
            if (answer.equals(definition)) {
                System.out.println("Correct answer");
                log.add("Correct answer");
            } else if (cards.definitionExists(answer)) {
                cardToAsk.addMistake();
                String s = "";
                for (Card card : askCards) {
                    if (card.getDefinition().equals(answer)) {
                        s = card.getTerm();
                    }
                }
                System.out.println("Wrong answer. The correct one is \"" + definition + "\", " +
                        "you've just written the definition of \"" + s + "\".");
                log.add("Wrong answer. The correct one is \"" + definition + "\", " +
                        "you've just written the definition of \"" + s + "\".");
            } else {
                cardToAsk.addMistake();
                System.out.println("Wrong answer. The correct one is \"" + definition + "\".");
                log.add("Wrong answer. The correct one is \"" + definition + "\".");
            }
        }
    }

    private void log() {
        log.add("log");
        System.out.println("File name:");
        log.add("File name:");
        String fileName = scanner.nextLine();
        log.add(fileName);
        int count = 0;
        try (PrintWriter printer = new PrintWriter(fileName)) {
            for (String s: log) {
                printer.println(s);
            }
            System.out.println("The log has been saved.");
            log.add("The log has been saved.");
        } catch (IOException e) {
            System.out.println("Something went wrong");
            log.add("Something went wrong");
        }
    }

    private void hardestCard() {
        log.add("hardest card");
        ArrayList<String> hardestCards = cards.getHardestCards();
        if (hardestCards.size() == 0) {
            System.out.println("There are no cards with errors.");
            log.add("There are no cards with errors.");
        } else if (hardestCards.size() == 1) {
            String firstPart = "The hardest card is \"" + hardestCards.get(0) + "\".";
            String secondPart = "You have " + cards.getMistakesForTerm(hardestCards.get(0)) + " errors answering it.";
            System.out.println(firstPart + " " + secondPart);
        } else {
            String firstPart = "The hardest cards are ";
            String secondPart = "";
            for (String s: hardestCards) {
                secondPart += ("\"" + s + "\", ");
            }
            secondPart = secondPart.substring(0, secondPart.length() - 2);
            secondPart += ".";
            String thirdPart = " You have " + cards.getMistakesForTerm(hardestCards.get(0)) + " errors answering them.";
            System.out.println(firstPart + secondPart + thirdPart);
        }
    }

    private void resetStats() {
        cards.resetMistakes();
        System.out.println("Card statistics has been reset.");
        log.add("Card statistics has been reset.");
    }

    public void start() {
        String action = "";

        while (!action.equals("exit")) {
            System.out.println("");
            System.out.println("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            log.add("Input the action (add, remove, import, export, ask, exit, log, hardest card, reset stats):");
            action = scanner.nextLine();
            if (action.equals("exit")) {
                exit();
            } else if (action.equals("add")) {
               add();
            } else if (action.equals("remove")) {
                remove();
            } else if (action.equals("import")) {
                importCards();;
            } else if (action.equals("export")) {
                exportCards();
            } else if (action.equals("ask")) {
                ask();
            } else if (action.equals("log")) {
                log();
            } else if (action.equals("hardest card")) {
                hardestCard();
            } else if (action.equals("reset stats")) {
                resetStats();
            }
        }
    }

    public void prepareImport(String fileName) {
        File file = new File(fileName);
        int count = 0;
        try {
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNext()) {
                String term = fileScanner.nextLine();
                String definition = fileScanner.nextLine();
                int mistakes = Integer.valueOf(fileScanner.nextLine());
                cards.addCard(term, definition, mistakes);
                count++;
            }
            System.out.println(count + " cards have been loaded.");
            log.add(count + " cards have been loaded.");
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            log.add("File not found.");
        }
    }

    public void finalExport(String fileName) {
        int count = 0;
        try (PrintWriter printer = new PrintWriter(fileName)) {
            ArrayList<Card> exportCards = cards.getCards();
            for (Card card: exportCards) {
                String term = card.getTerm();
                String definition = card.getDefinition();
                int mistakes = card.getMistakes();
                printer.println(term);
                printer.println(definition);
                printer.println(mistakes);
                count++;
            }
            System.out.println(count + " cards have been saved.");
            log.add(count + " cards have been saved.");
        } catch (IOException e) {
            System.out.println("Something went wrong");
            log.add("Something went wrong");
        }
    }
}
