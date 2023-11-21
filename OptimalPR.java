
import java.util.*;

public class OptimalPR {

    static int Optimal(int[] refString, int len, int frames) {
        int pagefaults = 0;
        HashSet<Integer> frameSet = new HashSet<>();
        HashMap<Integer, Integer> indexMap = new HashMap<>();

        for (int i = 0; i < len; i++) {
            if (frameSet.size() < frames) {
                if (!frameSet.contains(refString[i])) {
                    pagefaults++;
                    frameSet.add(refString[i]);
                    indexMap.put(refString[i], nextUse(refString, i));
                }
            } else {
                if (!frameSet.contains(refString[i])) {
                    pagefaults++;

                    int farthest = -1;
                    int val = 0;

                    Iterator<Integer> iterator = frameSet.iterator();

                    while (iterator.hasNext()) {
                        int temp = iterator.next();
                        if (indexMap.get(temp) > farthest) {
                            farthest = indexMap.get(temp);
                            val = temp;
                        }
                    }
                    frameSet.remove(val);
                    indexMap.remove(val);
                    System.out.println(val + "  " + refString[i]);

                    frameSet.add(refString[i]);
                    indexMap.put(refString[i], nextUse(refString, i));
                }
            }
        }

        return pagefaults;
    }

    static int nextUse(int[] refString, int currentIndex) {
        for (int i = currentIndex + 1; i < refString.length; i++) {
            if (refString[i] == refString[currentIndex]) {
                return i - currentIndex;
            }

        }
        return Integer.MAX_VALUE;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int len = 20;
        int[] refString = { 7, 0, 1, 2, 0, 3, 0, 4, 2, 3, 0, 3, 2, 1, 2, 0, 1, 7, 0, 1 };
        // System.out.print("Enter the No of pages: ");
        // int len = sc.nextInt();
        // int[] refString = new int[len];

        // for (int i = 0; i < len; i++) {
        // refString[i] = sc.nextInt();

        // }
        System.out.print("Enter the No of Frames: ");
        int frames = sc.nextInt();

        int pagefaults = Optimal(refString, len, frames);
        System.out.println(pagefaults);
        sc.close();

    }

}
