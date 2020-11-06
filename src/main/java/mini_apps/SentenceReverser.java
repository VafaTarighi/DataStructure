package mini_apps;

import list.DoublyLinkedList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SentenceReverser {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("_-_-__Sentence Reverser__-_-_\n");
        System.out.println(" Enter a sentence to reverse.\n" +
                " to exit enter 'exit'\n" +
                "-----------------------\n");

        String input;
        System.out.print(">> ");
        while (!(input = reader.readLine()).equalsIgnoreCase("exit")) {
            System.out.println("reversed by characters: \"" + reverseChars(input) + "\"");
            System.out.println("reversed by words:      \"" + reverseWords(input) + "\"");
            System.out.print(">> ");
        }
    }

    private static String reverseChars(String input) {

        DoublyLinkedList<Character> dll = new DoublyLinkedList<>();
        StringBuilder reversed = new StringBuilder(input.length());

        for (char c :
                input.toCharArray()) {
            dll.addFirst(c);
        }

        while (!dll.isEmpty()) {
            reversed.append(dll.removeFirst());
        }

        return reversed.toString();

    }

    private static String reverseWords(String input) {
        Matcher extractor = Pattern.compile("\\w+|\\p{Punct}|\\s", Pattern.MULTILINE).matcher(input);
        DoublyLinkedList<String> dll = new DoublyLinkedList<>();
        StringBuilder reversed = new StringBuilder(input.length() + 1);

        while (extractor.find())
            dll.addFirst(extractor.group());

        while (!dll.isEmpty())
            reversed.append(dll.removeFirst());


        return reversed.toString().trim();
    }


}
