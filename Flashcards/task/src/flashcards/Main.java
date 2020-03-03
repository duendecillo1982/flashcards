package flashcards;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        CardCollection cards = new CardCollection();

        UserInterface userInterface = new UserInterface(scanner, cards);

        String fileNameImport = "";
        String fileNameExport = "";

        for (int i = 0; i < args.length; i++) {
            if (args[i].equals("-import")) {
                fileNameImport = args[i + 1];
            } else if (args[i].equals("-export")) {
                fileNameExport = args[i + 1];
            }
        }

        if (!fileNameImport.equals("")) {
            userInterface.prepareImport(fileNameImport);
        }

        userInterface.start();

        if (!fileNameExport.equals("")) {
            userInterface.finalExport(fileNameExport);
        }


    }

}
