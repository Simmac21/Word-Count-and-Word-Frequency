import java.util.Objects;
// Book word class to store each word and its count of book
public class BookWord<T> implements Comparable<T>{
    private String text;
    private int count;

    BookWord (String wordText) {
        this.text = wordText;
        this.count = 1;
    }

    public String getText() {
        return text;
    }

    public int getCount() {
        return count;
    }

    public void incrementCount() {
        this.count++;
    }

    // equals method to compare two objects
    // returns true if both object has same bookWord and count
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof BookWord)) return false;
        BookWord bookWord = (BookWord) o;
        return getCount() == bookWord.getCount() &&
                getText().equals(bookWord.getText());
    }

    // Compare to method
    // return true if count of Object is equal
//    @Override
//    public int compareTo(Object object) {
//        int obj = ((BookWord) object).getCount();
//        return obj - this.getCount();
//    }


    @Override
    public int hashCode() {
        return Objects.hash(getText(), getCount());
    }

    @Override
    public String toString() {
        return "Word: \t" + this.getText() + "\tCount:\t" + this.getCount();
    }

    @Override
    public int compareTo(T object) {
        int obj = ((BookWord) object).getCount();
        return obj - this.getCount();
    }
}

