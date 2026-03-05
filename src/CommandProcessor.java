import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 *
 * The CommandProcessor class is responsible for reading and processing
 * commands from a specified command file, facilitating the execution of
 * operations on a Seminar database (SeminarDB).
 *
 * @author Pranay Dhalwani
 * @version 10.12.2023
 */

public class CommandProcessor {
    /**
     * Constructor for CommandProcessor.
     * 
     * @param semDb
     *            Database for seminar
     * @param fileName
     *            Input file
     * @throws Exception
     */

    public CommandProcessor(SeminarDB semDb, String fileName) throws Exception {
        try {
            Scanner scan = new Scanner(new File(fileName));
            while (scan.hasNextLine()) {
                String line = scan.nextLine();
                String[] parts = line.trim().split("\\s+");
                String command = parts[0];

                if (command.equals("insert")) {
                    // Store ID
                    int id = Integer.parseInt(parts[1]);
                    // There should be 4 more lines
                    // First: title, trim leading and trailing whitespace
                    line = scan.nextLine();
                    line = line.trim();
                    String title = line;
                    // Second: date/time
                    line = scan.nextLine();
                    String[] secLine = line.trim().split("\\s+");
                    String dateTime = secLine[0];
                    int len = Integer.parseInt(secLine[1]);
                    short x = Short.parseShort(secLine[2]);
                    short y = Short.parseShort(secLine[3]);
                    int cost = Integer.parseInt(secLine[4]);
                    // Third: keyword list
                    line = scan.nextLine();
                    String[] keyList = line.trim().split("\\s+");
                    // Fourth: description
                    line = scan.nextLine();
                    String desc = line.trim();

                    Seminar sem = new Seminar(id, title, dateTime, len, x, y,
                        cost, keyList, desc);

                    semDb.insert(sem);
                }

                else if (command.equals("delete")) {
                    int id = Integer.parseInt(parts[1]);
                    semDb.delete(id);
                }

                else if (command.equals("print")) {
                    if (parts[1].equals("ID")) {
                        semDb.print(parts[1]);
                    }
                    else if (parts[1].equals("cost")) {
                        semDb.print(parts[1]);
                    }
                    else if (parts[1].equals("keyword")) {
                        semDb.print(parts[1]);
                    }
                    else if (parts[1].equals("date")) {
                        semDb.print(parts[1]);
                    }
                    else if (parts[1].equals("location")) {
                        semDb.print(parts[1]);
                    }
                    else {
                        System.out.println("Invalid parameter");
                    }
                }

                else if (command.equals("search")) {
                    if (parts[1].equals("ID") || parts[1].equals("keyword")) {
                        semDb.searchExact(parts[1], parts[2]);
                    }
                    else if (parts[1].equals("cost") || parts[1].equals(
                        "date")) {
                        semDb.searchRange(parts[1], parts[2], parts[3]);
                    }
                    else if (parts[1].equals("location")) {
                        int x = Integer.parseInt(parts[2]);
                        int y = Integer.parseInt(parts[3]);
                        int radius = Integer.parseInt(parts[4]);
                        semDb.searchLocation(x, y, radius);
                    }
                    else {
                        System.out.println("Invalid parameter");
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("Error: File not found");
        }
    }
}
