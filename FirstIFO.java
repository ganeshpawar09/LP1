
import java.util.*;

public class FirstIFO {

    static int FIFO_Page_Replacement(int[] refString, int len, int frames) {
        HashSet<Integer> h = new HashSet<>();
        Queue<Integer> q = new LinkedList<>();
        int pageFaults = 0;

        for (int i = 0; i < len; i++) {
            if (!h.contains(refString[i])) {
                pageFaults++;
                if (h.size() < frames) {
                    h.add(refString[i]);
                    q.add(refString[i]);
                } else {
                    int front = q.poll();
                    h.remove(front);
                    h.add(refString[i]);
                    q.add(refString[i]);
                }
            }
            System.out.print(refString[i] + "\t\t");
            System.out.print(q + " \n");

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

        int len = 15;
        int[] refString = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 1, 2, 0 };
        System.out.print("Enter the no of frames: ");
        int frames = sc.nextInt();

        int pageFaults = FIFO_Page_Replacement(refString, len, frames);

        System.out.println("\n\nNo of Page Faults: " + pageFaults);
        System.out.println("No of Page Hits: " + (len - pageFaults));
        System.out.println("Page Fault Ratio: " + ((double) pageFaults / len * 100) + "%");
        System.out.println("Page Hit Ratio: " + ((double) (len - pageFaults) / len * 100) + "%");

        sc.close();
    }
}
