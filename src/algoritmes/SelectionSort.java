package algoritmes;

public class SelectionSort {

    public static <T extends Comparable<T>> T[] sort(T[] array){
        if(array == null){
            throw new IllegalArgumentException("Array cannot be null");
        }

        if(array.length <= 1){
            return array;
        }

        int currentIndex = 0;
        while(currentIndex < array.length){
            T firstObject = array[currentIndex];
            T smallest = firstObject;

            int unsortedIndex = currentIndex + 1;
            int smallestIndex = currentIndex;
            while(unsortedIndex < array.length) {
                T currentUnsorted = array[unsortedIndex];
                int comparison = smallest.compareTo(currentUnsorted);

                if(comparison > 0){
                    smallest = currentUnsorted;
                    smallestIndex = unsortedIndex;
                }
                unsortedIndex++;
            }

            array[currentIndex] = smallest;
            array[smallestIndex] = firstObject;
            currentIndex++;
        }
        return array;
    }
}
