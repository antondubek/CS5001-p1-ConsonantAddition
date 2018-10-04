
import java.util.ArrayList;

/**
 * A class which incrementally adds consonants in each location (including in front of and after) of the word/phrase
 * and prints new alternative statements if they are valid in the passed dictionary.
 *
 * @author CS5001 Student (acm35@st-andrews.ac.uk)
 *
 * @version 1
 * @since 1
 */

public class ConsonantAddition {

  /**
   * Prints the ConsonantAddition alternatives of a word or phrase compared against a passed dictionary.
   * @param args A file location of the dictionary, followed by a word or "phrase"
   *
   */
    public static void main(String[] args) {

        // Argument check
        if (args.length != 2) {
            System.out.println("Expected 2 command line arguments, but got " + args.length + ".");
            System.out.println("Please provide the path to the dictionary file as the first argument and a sentence "
                    + "as the second argument.");
            System.exit(0);
        }

        // Creation of dictionary Arraylist from source file
        ArrayList<String> lines = FileUtil.readLines(args[0]);

        //Make the whole of the dictionary lowercase so that words can be tested lowercase
        ArrayList<String> dictionary = parseDictionary(lines);

        // If lines is empty, then display "Invalid dictionary, aborting." and exit!
        if (lines.size() == 0) {
            System.out.println("Invalid dictionary, aborting.");
            System.exit(0);
        }

        // Take word or phrase and save it to "input"
        String input = args[1];

        // Create an array of consonants
        String[] consonants = {"b", "c", "d", "f", "g", "h", "j", "k", "l", "m", "n", "p", "q", "r", "s", "t",
         "v", "w", "x", "y", "z"};

        int numOfAlternativesFound = 0;

        for (int i = 0; i < input.length() + 1; i++) {

            // Iterate through consonants to add into each index
            for (String letter : consonants) {

                // Insert the consonant into the phrase
                StringBuilder sb = new StringBuilder(input);
                sb.insert(i, letter);
                String newInput = sb.toString();

                //Split string into words
                String[] words = newInput.split(" ");

                // If all the words were validated
                if (areWordsInDic(words, dictionary)) {
                    System.out.println(newInput); // Print out the line
                    numOfAlternativesFound++; // Add one to the counter
                }
            }
        }

        //If no alternatives found print phrase, else tell how many have been found
        if (numOfAlternativesFound == 0) {
            System.out.println("Could not find any alternatives.");
        } else {
            System.out.println("Found " + numOfAlternativesFound + " alternatives.");
        }
    }

    /**
     * Takes the dictionary array lines and makes it all lowercase.
     * @param lines Arraylist containing words of a dictionary
     * @return Returns the dictionary with all words lowercase
     */
    public static ArrayList<String> parseDictionary(ArrayList<String> lines) {

        for (int j = 0; j < lines.size(); j++) {
            String oldItem = lines.get(j);
            lines.set(j, oldItem.toLowerCase());
        }

        return lines;
    }

    /**
     * Given a list of words to test against a dictionary, the method splits the words, removes irrelevant punctuation
     * and tests whether the word exists within the dictionary.
     * @param words String array of words to check against the dictionary
     * @param dictionary Arraylist of strings forming a dictionary to test against
     * @return Returns True if ALL of the words are found in the dictionary, otherwise returns false.
     */
    public static boolean areWordsInDic(String[] words, ArrayList<String> dictionary) {
        // 'word is in the dictionary' counter
        int counter = 0;

        // Iterate through words in the array
        for (int x = 0; x < words.length; x++) {

            //Get the last character of word
            char lastCharacter = words[x].charAt(words[x].length() - 1);

            //Check last character and remove if punctuation
            if (".,/?!".indexOf(lastCharacter) != -1) {
                words[x] = words[x].replaceAll("\\p{Punct}", ""); // remove punctuation
            }

            if (dictionary.contains(words[x].toLowerCase())) {
                counter++; // if the word is in the dictionary add one to the counter
            }
        }

        // If all the words are in the dictionary return true, else false
        return counter == words.length;
    }
}
