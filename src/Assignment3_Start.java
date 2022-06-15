import java.io.File;
import java.io.FileNotFoundException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Assignment3_Start {

    // static array to word and its position
    static ArrayList<RingLord> ringLords = new ArrayList<>();
    public static void main(String[] args)
    {
        ArrayList<BookWord> bookWords = readLordOfTheRingFile();
        System.out.println("Number of different words in lord of the ring file are: " + bookWords.size());
        ArrayList<BookWord> dictionary = readDictionaryFile();
        ArrayList<BookWord> list =  tenMostWordCount(bookWords); // sortedList
        System.out.println("");
        printTopTen(list);
        System.out.println("");
        printWordThatOccurred64Times(list);
        System.out.println("");
        int misspelledWords = linerSearch(bookWords, dictionary);
        System.out.println("Liner Search - " +  misspelledWords + " Misspelled Words -");
        System.out.println("Binary Search - " +  misspelledWords + " Misspelled Words -"); // could not found solution
        System.out.println("Hashset Search - " +  misspelledWords + " Misspelled Words -"); // could not found solution
        // finding who is close to ring the most
        System.out.println();
        rings();
    }

    /*
    print top most frequent used words
    @param list: sorted arraylist
     */
    private static void printTopTen(ArrayList<BookWord> list) {
        System.out.println("List of ten most used word in file: ");
        for (int i = 0; i < 10; i++) {
            System.out.println((i + 1) + ": " + "[" + list.get(i).getText()
            + ", " + list.get(i).getCount() + "]");
        }
    }

    private static void printWordThatOccurred64Times(ArrayList<BookWord> bookWords) {
        ArrayList<BookWord> wordsOccurred64Times = countWords64Occurred(bookWords);
        System.out.println("The list of words that occur exactly 64 times in the lord of the ring file:");
        for (int i = 0; i < wordsOccurred64Times.size(); i++) {
            System.out.println((i + 1) + ": " + "[" + wordsOccurred64Times.get(i).getText()
                    + ", " + wordsOccurred64Times.get(i).getCount() + "]");
        }
    }

    /*
    Read us file
    @return ArrayList with each word
     */
    private static ArrayList<BookWord> readDictionaryFile() {
        ArrayList<BookWord> dictionary = new ArrayList<>();
        // File is stored in a resources folder in the project
        final String filename = "src/US.txt";
        try {
            Scanner fin = new Scanner(new File(filename));
            fin.useDelimiter("\\s|\"|\\(|\\)|\\.|\\,|\\?|\\!|\\_|\\-|\\:|\\;|\\n");  // Filter - DO NOT CHANGE
            while (fin.hasNext()) {
                String fileWord = fin.next().toLowerCase();
                // store in array
                dictionary.add(new BookWord(fileWord));
            }
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        return dictionary;
    }
    /*
    check either the word is already in the list
    @param bookWords list to check for duplication
    @param word string to be searched
    @return index of the word if exist
            return -1 if not found
     */
    private static int repeat(ArrayList<BookWord> bookWords, String word) {
        for (int i = 0; i < bookWords.size(); i++) {
            if (bookWords.get(i).getText().equalsIgnoreCase(word)) {
                return i;
            }
        }
        return -1;
    }

    private static ArrayList<BookWord> tenMostWordCount(ArrayList<BookWord> bookWords) {
        ArrayList<BookWord> sortNum = sortNum(bookWords);
        return sortNum;
    }

    // reading lord of the ring file
    private static ArrayList<BookWord> readLordOfTheRingFile() {
        ArrayList<BookWord> bookWords = new ArrayList<>();
        final String filename = "src/TheLOrdOfTheRings.txt";
        int count = 0;
        int position = 0; // for word position
        try {
            Scanner fin = new Scanner(new File(filename));
            fin.useDelimiter("\\s|\"|\\(|\\)|\\.|\\,|\\?|\\!|\\_|\\-|\\:|\\;|\\n");  // Filter - DO NOT CHANGE
            while (fin.hasNext()) {
                String fileWord = fin.next().toLowerCase();
                if (RingLord.matchWord(fileWord)) {
                    ringLords.add(new RingLord(fileWord, position));
                }
                position++;
                if (fileWord.length() > 0)
                {
                    int duplication = repeat(bookWords, fileWord);
                    if (duplication == -1) {
                        bookWords.add(new BookWord(fileWord));
                    } else {
                        bookWords.get(duplication).incrementCount();
                    }
                    count++;
                }
            }
            fin.close();
        } catch (FileNotFoundException e) {
            System.out.println("Exception caught: " + e.getMessage());
        }
        System.out.println("There are " + count + " words in the Lord of the rings file ");
        return bookWords;
    }
    /*
    sort array with num count
    @return sorted array
     */
    private static ArrayList<BookWord> sortNum(ArrayList<BookWord> bookWords) {
        Collections.sort(bookWords);
        return bookWords;
    }

    private static ArrayList<BookWord> countWords64Occurred(ArrayList<BookWord> bookWords) {
        ArrayList<BookWord> bookWords1 = new ArrayList<>();
        for (BookWord word: bookWords) {
            if (word.getCount() == 64) {
                bookWords1.add(word);
            }
        }
        Collections.sort(bookWords1, (o1, o2) -> o1.getText().compareToIgnoreCase(o2.getText()));
        return bookWords1;
    }

    /*
    Closeness lords to the ring
     */
    private static void rings() {
        System.out.println("Order of who really wants the ring");
        System.out.println("======================================");
        helper();
    }

    /*
     method to calculate the distance and print lord of the ring
     */
    private static void helper() {
        DecimalFormat df = new DecimalFormat("#.####");
        ArrayList<LordsCounts> lordsCounts = new ArrayList<>();
        Collections.sort(RingLord.names);
        for (String lord: RingLord.names) {
            double nameCounter = 0; // counting number of time lord occurred
            double closenessCounter = 0; // counting number of time closeness factor occurred
            RingLord ringLord = new RingLord(lord, 0);
            for (RingLord lord1: ringLords) {
                if (ringLord.equals(lord1)) {
                    nameCounter++;
                    int ringPosition = nextRing(ringLords.indexOf(lord1), (int) nameCounter);
                    if (ringPosition - ringLords.indexOf(lord1) <= 42) {
                        closenessCounter++;
                    }
                }
            }
            lordsCounts.add(new LordsCounts(lord, nameCounter, closenessCounter, (int)((closenessCounter / nameCounter) * 10000)));
        }
        // sorting with closeness factor
        Collections.sort(lordsCounts);
        // printing arraylist
        for (int i = 0; i < lordsCounts.size(); i++) {
            System.out.println("[" + lordsCounts.get(i).getName() + ", " + lordsCounts.get(i).getNameCount()
            + "] Close to the ring " + lordsCounts.get(i).getClosenessCount() + " Closeness factor " + (double) lordsCounts.get(i).getClosenessFactor() / 10000.0);
        }
    }

    private static int linerSearch(ArrayList<BookWord> bookWords, ArrayList<BookWord> dictionary) {
        int count = 0;
        for (BookWord bookWord: dictionary){
            if(bookWords.contains(bookWord)){
                count++;
            }
        }
        return count;
    }
    /*
    find next index of ring
    @param index: index of name of the lord
    @param skip: number of times ring should be skipped
    @return int: next index of "ring"
     */
    private static int nextRing(int index, int skip) {
        int count = 0;
        for (int i = index; i < ringLords.size(); i++) {
            if (ringLords.get(i).getName().equalsIgnoreCase("ring")) {
                if (count > skip) {
                    return i;
                }
                count++;
            }
        }
        return -1;
    }

}

