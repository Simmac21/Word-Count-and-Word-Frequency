public class LordsCounts implements Comparable{
    private String name;
    private double nameCount;
    private double closenessCount;
    private int closenessFactor;

    public LordsCounts(String name, double nameCount, double closenessCount, int closenessFactor) {
        this.name = name;
        this.nameCount = nameCount;
        this.closenessCount = closenessCount;
        this.closenessFactor = closenessFactor;
    }

    @Override
    public String toString() {
        return "LordsCounts{" +
                "name='" + name + '\'' +
                ", nameCount=" + nameCount +
                ", closenessCount=" + closenessCount +
                ", closenessFactor=" + (double) closenessFactor / (double) 10000+
                '}';
    }

    public double getNameCount() {
        return nameCount;
    }

    public double getClosenessCount() {
        return closenessCount;
    }

    public String getName() {
        return name;
    }
    public int getClosenessFactor() {
        return closenessFactor;
    }

    @Override
    public int compareTo(Object o) {
        int closenessFactor = ((LordsCounts) o).getClosenessFactor();
        return closenessFactor - this.closenessFactor;
    }
}
