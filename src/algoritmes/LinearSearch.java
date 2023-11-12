package algoritmes;

import interfaces.List;

import java.util.Iterator;

public class LinearSearch {

    public static <T extends Comparable<T>> T search(Iterator<T> iterator, T target){
        if(iterator == null || target == null){
            throw new IllegalArgumentException("Target and iterator cannot be null");
        }

        while(iterator.hasNext()){
            T object = iterator.next();
            int comparison = object.compareTo(target);

            if(comparison == 0){
                return object;
            }
        }
        return null;
    }
}
