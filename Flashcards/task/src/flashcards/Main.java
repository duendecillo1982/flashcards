package flashcards;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CardCollection cards = new CardCollection();

        UserInterface userInterface = new UserInterface(scanner, cards);
        userInterface.start();

    }

}
