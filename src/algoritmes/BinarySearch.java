package algoritmes;

public class BinarySearch {

    public static <T extends Comparable<T>> T search(T[] haystack, T needle){
        int low = 0;
        int high = 0;

        if(haystack == null || needle == null){
            throw new IllegalArgumentException("Haystack or needle cannot be null.");
        }

        if(haystack.length > 1){
            high = haystack.length -1;
        } else if(haystack.length == 0){
            return null;

        }

        int comparison;
        while (low <= high) {
            int mid = low + (high - low) / 2;

            comparison = haystack[mid].compareTo(needle);
            if (comparison == 0) {
                return haystack[mid];
            }
            if (comparison < 0) {
                low = mid + 1;
            }
            else {
                high = mid - 1;
            }
        }
        return null;
    }
}
