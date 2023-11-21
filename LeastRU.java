
import java.util.*;

public class LeastRU {

    static int LRU(int[] refString, int len, int frames) {
        int pageFaults = 0;
        LinkedHashSet<Integer> h = new LinkedHashSet<>();

        for (int page : refString) {
            if (h.contains(page)) {
                h.remove(page);
                h.add(page);
            } else {
                pageFaults++;

                if (h.size() < frames) {
                    h.add(page);
                } else {
                    Iterator<Integer> iterator = h.iterator();
                    int leastRecentlyUsedPage = iterator.next();

                    h.remove(leastRecentlyUsedPage);
                    h.add(page);
                }
            }
            System.out.println(page);
            System.out.println(h);
        }

        return pageFaults;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // System.out.print("Enter the size of reference string: ");
        // int len = sc.nextInt();

        // int[] refString = new int[len];

        // for (int i = 0; i < len; i++) {
        // System.out.print("Enter the number of reference string element " + (i + 1) +
        // ": ");
        // refString[i] = sc.nextInt();
        // }

        int len = 20;
        int[] refString = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1 };
        System.out.print("Enter the no of frames: ");
        int frames = sc.nextInt();

        int pageFaults = LRU(refString, len, frames);

        System.out.println("\n\nNo of Page Faults: " + pageFaults);
        System.out.println("No of Page Hits: " + (len - pageFaults));
        System.out.println("Page Fault Ratio: " + ((double) pageFaults / len * 100) + "%");
        System.out.println("Page Hit Ratio: " + ((double) (len - pageFaults) / len * 100) + "%");
        sc.close();
    }

}
