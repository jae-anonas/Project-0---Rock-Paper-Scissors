package com.company;

import java.io.*;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {

    static List<String> userChoiceHistory = new ArrayList<>();
    static List<String> computerChoiceHistory = new ArrayList<>();
    static List<String> resultHistory = new ArrayList<>();
    static List<String> gameTimeHistory = new ArrayList<>();

    static int wins;
    static int losses;
    static int ties;

    static String[] getGestureInASCII(String choice) {
        String[] gesture = new String[6];
        switch (choice) {
            case "rock":
                gesture[0] = "     ______   ";
                gesture[1] = "---'   ____)  ";
                gesture[2] = "      (_____) ";
                gesture[3] = "      (_____) ";
                gesture[4] = "      (____)  ";
                gesture[5] = "---.__(___)   ";
                break;
            case "paper":
                gesture[0] = "    _______        ";
                gesture[1] = "---'   ____)____   ";
                gesture[2] = "          ______)  ";
                gesture[3] = "          _______) ";
                gesture[4] = "         _______)  ";
                gesture[5] = "---.__________)    ";
                break;
            case "scissors":
                gesture[0] = "    _______        ";
                gesture[1] = "---'   ____)____   ";
                gesture[2] = "          ______)  ";
                gesture[3] = "       __________) ";
                gesture[4] = "      (____)       ";
                gesture[5] = "---.__(___)        ";
                break;

        }

        return gesture;

    }

    static String[] reverseStringArray(String[] strArray) {
        String[] str = new String[6];
        for (int i = 0; i < strArray.length; i++) {
            //reverse string
            str[i] = " ";
            for (int j = strArray[i].length() - 1; j >= 0; j--) {
                if (strArray[i].charAt(j) == ')')
                    str[i] += "(";
                else if (strArray[i].charAt(j) == '(')
                    str[i] += ")";
                else
                    str[i] += strArray[i].charAt(j);
            }
        }
        return str;
    }

    static void printGestures(String choice1, String choice2) {
        String[] gesture1 = getGestureInASCII(choice1);
        String[] gesture2 = reverseStringArray(getGestureInASCII(choice2));

        for (int i = 0; i < gesture1.length; i++) {
            if (i == 3)
                System.out.println(gesture1[i] + "  VS " + gesture2[i]);
            else
                System.out.println(gesture1[i] + "     " + gesture2[i]);
        }
    }

    static String getUserChoiceMenu() {
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine().toString().toLowerCase();
        switch(choice){
            case "p":
                return "play";
            case "h":
                return "history";
            case "q":
                return "quit";
            default:
                return "Impossible";
        }
    }
    static String getUserChoiceGame() {
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine().toString().toLowerCase();
        switch(choice){
            case "p":
                return "paper";
            case "r":
                return "rock";
            case "s":
                return "scissors";
            case "q":
                return "quit";
            default:
                return "Impossible";
        }
    }

    static boolean choiceIsValid(String choice) {
        choice = choice.toLowerCase();
        boolean isValid = choice.equals("rock") || choice.equals("paper") || choice.equals("scissors") || choice.equals("quit");
        if (!isValid)
            System.out.println("Invalid choice.");
        return isValid;
    }

    static String getComputerChoice() {
        Random r = new SecureRandom();
        int randomNum = r.nextInt();
        int numChoice = randomNum % 3;

        switch (numChoice) {
            case 0:
                return "rock";
            case 1:
                return "paper";
            default:
                return "scissors";
        }
    }

    static void printChoice(String choice, String player) {
        System.out.println("\n" + player + " picks: " + choice);

    }

    static void playGame() {

        System.out.println("\n\nChoose a move('q' to quit):  ");
        System.out.println("    \'r\' - rock" );
        System.out.println("    \'p\' - paper" );
        System.out.println("    \'s\' - scissors\n" );

        System.out.print("Enter choice: ");
        String choice = getUserChoiceGame();

        if (!choiceIsValid(choice) || choice.equals("quit")) {
            return;
        }

        printChoice(choice, "User");
        String computerChoice = getComputerChoice();
        printGestures(choice, computerChoice);
        printChoice(computerChoice, "Computer");

        String result = getResult(choice, computerChoice);

        saveToHistory(choice, computerChoice, result);

        System.out.println("\n" + result);

    }

    static void saveToHistory(String userChoice, String computerChoice, String result) {
        String timeStamp = new SimpleDateFormat("EEE, MMM d 'at' h:mm a").format(Calendar.getInstance().getTime());
        userChoiceHistory.add(userChoice);
        computerChoiceHistory.add(computerChoice);
        resultHistory.add(result);
        gameTimeHistory.add(timeStamp);
    }

    static void printHistoryHeading() {

        String uLCorner = String.format("%c", getAscii(217));
        String uRCorner = String.format("%c", getAscii(190));
        String lLCorner = String.format("%c", getAscii(191));
        String lRCorner = String.format("%c", getAscii(216));
        String hLCorner = String.format("%c", getAscii(194));
        String hRCorner = String.format("%c", getAscii(179));

        char side = String.format("%c", getAscii(178)).charAt(0);
        char perpendicular = String.format("%c", getAscii(193)).charAt(0);
        char cross = String.format("%c", getAscii(196)).charAt(0);

        System.out.println("\n\n");
        System.out.println("                    " + uLCorner + getLengthySide(31, false) + uRCorner);
        System.out.println("                    " + side + "         Game History          " + side);
        System.out.println("                    " + lLCorner + getLengthySide(31, false) + lRCorner + "\n");
        StringBuilder bTop= new StringBuilder(uLCorner + getLengthySide(82, false) + uRCorner);
        bTop.setCharAt(30, perpendicular);
        bTop.setCharAt(47, perpendicular);
        bTop.setCharAt(69, perpendicular);
        System.out.println(bTop);
        StringBuilder s = new StringBuilder(side + "            Time             " + side + "   User choice  " + side + "   Computer choice   " + side + "   Result    " + side);
        s.setCharAt(30, side);
        s.setCharAt(47, side);
        s.setCharAt(69, side);
        System.out.println(s);

        StringBuilder bTopB= new StringBuilder(hLCorner + getLengthySide(82, false) + hRCorner);
        bTopB.setCharAt(30, cross);
        bTopB.setCharAt(47, cross);
        bTopB.setCharAt(69, cross);
        System.out.println(bTopB);

//        System.out.println(bTop);

    }

    static void printTBBorder(){
        String lLCorner = String.format("%c", getAscii(191));
        String lRCorner = String.format("%c", getAscii(216));
        char perpendicular = String.format("%c", getAscii(192)).charAt(0);

        StringBuilder s = new StringBuilder(lLCorner + getLengthySide(82, false) + lRCorner);
        s.setCharAt(30, perpendicular);
        s.setCharAt(47, perpendicular);
        s.setCharAt(69, perpendicular);
        System.out.println(s);

        System.out.println(" Wins: " + wins + "  Losses: " + losses + "  Draws: " + ties);
    }

    static void printHistory() {
        char side = String.format("%c", getAscii(178)).charAt(0);
        wins = 0;
        losses = 0;
        ties = 0;
        printHistoryHeading();

        for (int i = 0; i < gameTimeHistory.size(); i++) {
            StringBuilder line = new StringBuilder(String.format("%25s%19s%18s%20s", gameTimeHistory.get(i), userChoiceHistory.get(i), computerChoiceHistory.get(i), resultHistory.get(i)) + "  ");
            if (line.toString().contains("win"))
                wins++;
            else if (line.toString().contains("lose"))
                losses++;
            else
                ties++;
            line.setCharAt(30, side);
            line.setCharAt(47, side);
            line.setCharAt(69, side);
            line.setCharAt(0, side);
            line.setCharAt(line.length() - 1, side);
            System.out.println(line);
        }
        printTBBorder();
        System.out.println("\n\n");
    }

    static void saveHistoryToFile() {
        for (int i = 0; i < gameTimeHistory.size(); i++) {
            String line = String.format("%25s%19s%18s%20s", gameTimeHistory.get(i), userChoiceHistory.get(i), computerChoiceHistory.get(i), resultHistory.get(i));

            try (Writer output = new BufferedWriter(new FileWriter("history.txt", true))) {
                output.append(line + "\n");
                output.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


    }

    static void printAllHistory() {
        char side = String.format("%c", getAscii(178)).charAt(0);
        try (BufferedReader br = new BufferedReader(new FileReader("history.txt"))) {
            wins = 0;
            losses = 0;
            ties = 0;
            printHistoryHeading();
            String line;
            while ((line = br.readLine()) != null) {
                StringBuilder s = new StringBuilder(line + "  ");
                if (s.toString().contains("win"))
                    wins++;
                else if (s.toString().contains("lose"))
                    losses++;
                else
                    ties++;
                s.setCharAt(30, side);
                s.setCharAt(47, side);
                s.setCharAt(69, side);
                s.setCharAt(0, side);
                s.setCharAt(s.length() - 1, side);
                System.out.println(s);
            }
            printTBBorder();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    static void askWhichHistory() {
        String choice;
        Scanner sc = new Scanner(System.in);
        boolean validChoice;
        do {
            System.out.println("Which history do you wanna see? Type: ");
            System.out.println("    \'c\' - for current session's history");
            System.out.println("    \'p\' - for history from previous sessions");
            System.out.println("    \'x\' - to go back to the menu");
            System.out.print("\nEnter choice: ");
            choice = sc.nextLine().toLowerCase();

            validChoice = choice.equals("p") || choice.equals("c") || choice.equals("x");

            switch (choice) {
                case "p":
                    printAllHistory();
                    break;
                case "c":
                    printHistory();
                    break;
                case "x":
                    return;
                default:
                    //this shouldn't be reached
                    System.out.println("This is impossible");
            }

            if (!validChoice)
                System.out.println("\nInvalid choice.\n");

        } while (!validChoice);
    }

    static void printGameHeading() {
        /*
            this prints:
             __  __  __      __      __  __ __    __ __  __ __ __  __  __
            |__)/  \/  |_/  |__) /\ |__)|_ |__)  (_ /  |(_ (_ /  \|__)(_
            | \ \__/\__| \  |   /--\|   |__| \   __)\__|__)__)\__/| \ __)
         */
        String uLCorner = String.format("%c", getAscii(200));
        String uRCorner = String.format("%c", getAscii(186));
        String lLCorner = String.format("%c", getAscii(199));
        String lRCorner = String.format("%c", getAscii(187));
        String side = String.format("%c", getAscii(185));

        System.out.println(uLCorner + getLengthySide(63, true) + uRCorner);
        System.out.println(side + "  __  __  __      __      __  __ __    __ __  __ __ __  __  __ " + side);   //61 characters
        System.out.println(side + " |__)/  \\/  |_/  |__) /\\ |__)|_ |__)  (_ /  |(_ (_ /  \\|__)(_  " + side);
        System.out.println(side + " | \\ \\__/\\__| \\  |   /--\\|   |__| \\   __)\\__|__)__)\\__/| \\ __) " + side);
        System.out.println(side + "                                                               " + side);
        System.out.println(lLCorner + getLengthySide(63, true) + lRCorner);
    }

    static String getLengthySide(int length, boolean doubleBars) {
        String dSide = String.format("%c", getAscii(204));
        String sSide = String.format("%c", getAscii(195));
        String side = "";

        if (doubleBars) {
            for (int i = 0; i < length; i++) {
                side += dSide;
            }

        } else {
            for (int i = 0; i < length; i++) {
                side += sSide;
            }
        }
        return side;
    }

    static void printMenu() {

        String uLCorner = String.format("%c", getAscii(217));
        String uRCorner = String.format("%c", getAscii(190));
        String lLCorner = String.format("%c", getAscii(191));
        String lRCorner = String.format("%c", getAscii(216));
        String side = String.format("%c", getAscii(178));

        System.out.println("\n\n");
        System.out.println(uLCorner + getLengthySide(11, false) + uRCorner);
        System.out.println(side + " MAIN MENU " + side);
        System.out.println(lLCorner + getLengthySide(11, false) + lRCorner);

        System.out.println("");
        System.out.println("1. Type \'p\' to play");
        System.out.println("2. Type \'h\' to view your game history");
        System.out.println("3. Type \'q\' to save game history and stop playing");
        System.out.println("");

    }

    static String getResult(String userChoice, String computerChoice) {
        switch (userChoice) {
            case "rock":
                if (computerChoice.equals("scissors")) {
                    return "You win!";
                } else if (computerChoice.equals("paper")) {
                    return "You lose!";
                } else
                    return "It's a tie.";

            case "paper":
                if (computerChoice.equals("rock")) {
                    return "You win!";
                } else if (computerChoice.equals("scissors")) {
                    return "You lose!";
                } else
                    return "It's a tie.";
            case "scissors":
                if (computerChoice.equals("paper")) {
                    return "You win!";
                } else if (computerChoice.equals("rock")) {
                    return "You lose!";
                } else
                    return "It's a tie.";
            default:
                return "I don't know, I'm just saying.";
        }

    }

    static void printThankYou(){
        System.out.println("\n\n ______ _                    _                          _                      _                               \n" +
                "(_) |  | |                  | |                        | |                    | |             o               |\n" +
                "    |  | |     __,   _  _   | |           __           | |  __   ,_        _  | |  __,            _  _    __, |\n" +
                "  _ |  |/ \\   /  |  / |/ |  |/_)   |   | /  \\_|   |    |/  /  \\_/  |     |/ \\_|/  /  |  |   | |  / |/ |  /  | |\n" +
                " (_/   |   |_/\\_/|_/  |  |_/| \\_/   \\_/|/\\__/  \\_/|_/  |__/\\__/    |_/   |__/ |__/\\_/|_/ \\_/|/|_/  |  |_/\\_/|/o\n" +
                "                                      /|               |\\               /|                 /|              /|  \n" +
                "                                      \\|               |/               \\|                 \\|              \\|  ");
    }

    public static void main(String[] args) {

        String choice;
        printGameHeading();
        do {
            printMenu();
            System.out.print("Enter choice: ");
            choice = getUserChoiceMenu();

            switch (choice) {
                case "play":
                    playGame();
                    break;
                case "history":
                    System.out.println("");
                    askWhichHistory();
                    break;
                case "quit":
                    saveHistoryToFile();
                    printThankYou();
//                    System.out.println("\nThanks for playing!");
                    break;
                default:
                    System.out.println("Unknown command.");
            }
        } while (!choice.equals("quit"));
    }

    //for special characters

    public static final char[] EXTENDED = {0x00C7, 0x00FC, 0x00E9, 0x00E2,
            0x00E4, 0x00E0, 0x00E5, 0x00E7, 0x00EA, 0x00EB, 0x00E8, 0x00EF,
            0x00EE, 0x00EC, 0x00C4, 0x00C5, 0x00C9, 0x00E6, 0x00C6, 0x00F4,
            0x00F6, 0x00F2, 0x00FB, 0x00F9, 0x00FF, 0x00D6, 0x00DC, 0x00A2,
            0x00A3, 0x00A5, 0x20A7, 0x0192, 0x00E1, 0x00ED, 0x00F3, 0x00FA,
            0x00F1, 0x00D1, 0x00AA, 0x00BA, 0x00BF, 0x2310, 0x00AC, 0x00BD,
            0x00BC, 0x00A1, 0x00AB, 0x00BB, 0x2591, 0x2592, 0x2593, 0x2502,
            0x2524, 0x2561, 0x2562, 0x2556, 0x2555, 0x2563, 0x2551, 0x2557,
            0x255D, 0x255C, 0x255B, 0x2510, 0x2514, 0x2534, 0x252C, 0x251C,
            0x2500, 0x253C, 0x255E, 0x255F, 0x255A, 0x2554, 0x2569, 0x2566,
            0x2560, 0x2550, 0x256C, 0x2567, 0x2568, 0x2564, 0x2565, 0x2559,
            0x2558, 0x2552, 0x2553, 0x256B, 0x256A, 0x2518, 0x250C, 0x2588,
            0x2584, 0x258C, 0x2590, 0x2580, 0x03B1, 0x00DF, 0x0393, 0x03C0,
            0x03A3, 0x03C3, 0x00B5, 0x03C4, 0x03A6, 0x0398, 0x03A9, 0x03B4,
            0x221E, 0x03C6, 0x03B5, 0x2229, 0x2261, 0x00B1, 0x2265, 0x2264,
            0x2320, 0x2321, 0x00F7, 0x2248, 0x00B0, 0x2219, 0x00B7, 0x221A,
            0x207F, 0x00B2, 0x25A0, 0x00A0};

    public static final char getAscii(int code) {
        if (code >= 0x80 && code <= 0xFF) {
            return EXTENDED[code - 0x7F];
        }
        return (char) code;
    }
}
