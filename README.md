# Word-Count-and-Word-Frequency

A program that counts the number of words and their occurrences in J.R.R. Tolkien's Lord of the Rings Trilogy.
It spell checks the document and reports how many words are not found in the specified dictionary, in addition to counting words.
It also uses proximity searching to solve the issue of which character is the most closely related with the Ring.
For the words in the novel and the words in the dictionary, a class called BookWord is used to store each word.
Lowercase must be used to store the words.
A word is defined as a sequence of one or more characters separated by white space, a comma, period, exclamation point, or a question mark.
Quotes may appear in a word, but they must be ignored and not included.
A word can also contain the apostrophe letter. 
Using a regex, these characters will be filtered out during the file reading process.


![image](https://user-images.githubusercontent.com/107233739/173896972-88d0d7c5-948a-4874-87c6-d0cbdd9ff87b.png)

The characters for each unique word will be stored as a string in BookWord, along with a count that will contain the number of times that word appears in the text.
The novel's Words will be stored in an ArrayList of BookWord created by the main method.
The starting code is used to read the words from the LordoftheRings.txt file.
The us.txt file is a word dictionary.
The dictionary is used to find the number of misspelled words in the novel. 
For this program, two dictionaries were created.

• The first is an ArrayList<BookWord>. Once all of the dictionary has been saved in the ArrayList, the Collections.sort method was used to sort it.

  • The second is a SimpleHashSet<BookWord>.
