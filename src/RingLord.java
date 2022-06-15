import java.util.ArrayList;
import java.util.Objects;

public class RingLord implements Comparable{
    private final String name;
    private int position;
    static ArrayList<String> names = new ArrayList<String>() {
        {
            add("legolas");
            add("frodo");
            add("sam");
            add("gandalf");
            add("boromir");
            add("merry");
            add("treebeard");
            add("elrond");
            add("denethor");
            add("aragorn");
            add("gollum");
            add("pippin");
            add("galadriel");
            add("gimli");
            add("sauron");
            add("saruman");
            add("faramir");
            add("bilbo");
            add("ring");
        }
    };
    public RingLord(String name, int position) {
        this.name = name;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int pos) {
        this.position = pos;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RingLord)) return false;
        RingLord ringLord = (RingLord) o;
        return getName().equals(ringLord.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName());
    }

    @Override
    public int compareTo(Object o) {
        int position = ((RingLord) o).getPosition();
        return position - this.getPosition();
    }

    @Override
    public String toString() {
        return "RingLord{" +
                "word='" + name + '\'' +
                ", position=" + position +
                '}';
    }

    public static boolean matchWord(String lord) {
        return names.contains(lord);
    }
}


