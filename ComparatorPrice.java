import java.util.Comparator;

public class ComparatorPrice implements Comparator<Car> {
    public int compare (Car o1, Car o2) {
        int ans = o1.getPrice() - o2.getPrice();
        if (ans < 0) {
            return -1;
        } else if (ans > 0) {
            return 1;
        } else {
            return 0;
        }
    }
}
