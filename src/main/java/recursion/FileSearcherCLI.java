package recursion;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.file.NotDirectoryException;

public class FileSearcherCLI {

    public static void main(String[] args) throws IOException, InterruptedException {
        System.out.println("___-_ File Finder _-___");
        System.out.println("\ncommands:" +
                "\n  find  [filename]        ------- list's all the file paths existing in this root path witch their name contain the specified name" +
                "\n  crt [directory path]    ------- changes current root path" +
                "\n  exit                    ------- to exit the program" +
                "\n attention: if specified directory path includes space, use (\" , ') to cover the path.");

        FileSearcher fs = new FileSearcher(System.getProperty("user.home"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String input;
        String command;
        String value;

        boolean running = true;

        while (running) {
            System.out.print(fs.getRoot() + "> ");

            input = reader.readLine().trim();
            command = input.split("\\s+")[0];
            value = input.replace(command, "").trim();

            switch (command) {
                case "find":
                    System.out.println("_______________________________");
                    fs.find(value);
                    System.out.println("_______________________________");
                    break;
                case "crt":
                    if (value.charAt(0) == '"' && value.charAt(value.length()-1) == '"')
                        value = value.substring(1, value.length()-1);
                    else if (value.charAt(0) == '\'' && value.charAt(value.length()-1) == '\'')
                        value = value.substring(1, value.length()-1);
                    try {
                        fs.setRoot(value);
                    } catch (NotDirectoryException e) {
                        System.err.println(e.getMessage());
                        Thread.sleep(100);
                    }
                    break;
                case "exit":
                    running = false;
                    break;
                default:
                    System.err.println("Command '" + command + "' not found.");
                    Thread.sleep(100);
            }

        }

    }
}
