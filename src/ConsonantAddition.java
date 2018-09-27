import java.util.ArrayList;

public class ConsonantAddition {

    public static void main(String[] args) {


        if (args.length != 2) {
            System.out.println("Expected 2 command line arguments, but got " + args.length + ".");
            System.out.println("Please provide the path to the dictionary file as the first argument and a sentence "
                    + "as the second argument.");
            System.exit(0);
        }

        ArrayList<String> lines = FileUtil.readLines(args[0]);

        // If lines is empty, then display "Invalid dictionary, aborting." and exit!
        if (lines.size() == 0) {
            System.out.println("Invalid dictionary, aborting.");
            System.exit(0);
        }

        //Make the whole of the dictionary lowercase so that only lowercase words need to be tested
        for (int j = 0; j < lines.size(); j++) {
            String oldItem = lines.get(j);
            lines.set(j, oldItem.toLowerCase());
        }


        // Take word or phrase and save it to "input"
        String input = args[1];

        // Create an array of consonants
        String[] consonants = {"b","c","d","f","g","h","j","k","l","m","n","p","q","r","s","t","v","w","x","y","z"};

        int numOfAlternativesFound = 0;

        for(int i=0; i<input.length()+1; i++) {

            // Add the input into a string builder
            StringBuilder sb = new StringBuilder(input);

            // Add a letter into each
            for(String letter : consonants ) {
                sb.insert(i, letter);
                String newInput = sb.toString();

                System.out.println("DEBUG: " + newInput);

                //Split string into words
                String[] words = newInput.split(" ");

                //Test if the words are contained in the dictionary
                int counter = 0;
                for (int x = 0; x < words.length; x++) { // Look at each word and check if its in the dictionary

                    words[x] = words[x].replaceAll("[^\\w]", ""); // remove punctuation

                    ////System.out.println("DEBUG: Searched word = "+ words[x]);
                    if (lines.contains(words[x].toLowerCase())) {

                        ////System.out.println("DEBUG: Found word location = "+ lines.indexOf(words[x]));
                        counter++; // if the word is in the dictionary add one to the counter
                    }


                    /*
                    else {
                        ////System.out.println("DEBUG: Word not found!");
                    }
                    */
                }

                // If all the words were validated
                ////System.out.println("DEBUG: Counter = " + counter);
                ////System.out.println("DEBUG: Length = "+words.length);
                if (counter == words.length) {
                    System.out.println(newInput);
                    numOfAlternativesFound++; // Add one to the counter
                }


                //if so then print that phrase and increment the counter

                // reset the phrase
                sb.deleteCharAt(i);
            }



        }


    }
}
