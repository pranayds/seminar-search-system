/**
 * @author pranaydhalwani
 * @version 09.30.2023
 * 
 * The main class for the program. It serves as the entry point
 * for executing spatial searches and operations on Seminar
 * records within a specified world size.
 */

public class SemSearch {
    /**
     * @param args
     *     Command line parameters
     * @throws Exception 
     */
    public static void main(String[] args) throws Exception {
        // This is the main file for the program.
        if (args.length != 2) {
            System.out.println(
                "Usage: java SemSearch {world-size} {command-file}");
            return;
        }
        int worldSize = Integer.parseInt(args[0]);
        SeminarDB semDb = new SeminarDB(worldSize);
        CommandProcessor cp = new CommandProcessor(semDb, args[1]); 
    }
}