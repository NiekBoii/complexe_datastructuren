package DatastructurenTests;

import datastructuren.MyEntry;
import datastructuren.SeperateChainingHashTable;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class SeperateChainingHashTableTests {

    private SeperateChainingHashTable<String, Integer> hashTable;
    private SeperateChainingHashTable<String, Integer> nullHashTable = null;

    private String keyOne = "super_unique_key";
    private String keyTwo = "not_so_unique";
    private Integer one = 1;
    private Integer two = 2;


    private final int MAX_SIZE = 10;

    @BeforeEach
    public void init(){
        hashTable = new SeperateChainingHashTable<>(MAX_SIZE);
    }

    @Test
    public void testIfSizeReturnsRightNumber(){
        hashTable.put(keyOne, one);
        assertEquals(1, hashTable.size());
    }

    @Test
    public void testIfClearReturnsRightNumber(){
        hashTable.put(keyOne, one);
        hashTable.clear();
        assertEquals(0, hashTable.size());
    }

    @Test
    public void testIfAddingWorks(){
        hashTable.put(keyOne, two);
        assertEquals(two, hashTable.get(keyOne));
    }

    @Test
    public void testIfEntrySetReturnsCorrectEntrySet(){
        hashTable.put(keyOne, one);
        hashTable.put(keyTwo, two);
        List<Integer> integers = List.of(1, 2);
        List<Integer> empty = new ArrayList<>();

        for(MyEntry<String, Integer> entry : hashTable.entrySet()){
            empty.add(entry.getValue());
        }
        assertIterableEquals(empty, integers);
    }

    @Test
    public void testIfRemoveWorks(){
        hashTable.put(keyOne, 1);
        hashTable.remove(keyOne);
        assertNull(hashTable.get(keyOne));
    }

    @Test
    public void testIfContainsKeyWorks(){
        hashTable.put(keyOne, one);
        assertTrue(hashTable.contains(keyOne));
    }

    @Test
    public void testIfEmpty(){
        assertTrue(hashTable.isEmpty());
    }

    @Test
    public void testIfNoChainReturnsNullWhenRemoveIsCalled(){
        assertNull(hashTable.remove(keyTwo));
    }

    @Test
    public void testIfHashTableReturnsNullIfRemoveIsCalledWhenKeyNotInTable(){
        hashTable.put(keyTwo, one);
        assertNull(hashTable.remove(keyOne));
    }

    @Test
    public void testSizeAfterHashTableRemovesElement(){
        hashTable.put(keyOne, one);
        hashTable.put(keyTwo, two);
        hashTable.remove(keyTwo);
        assertEquals(1, hashTable.size());
    }

    @Test
    public void testIfSizeChangesWhenRemoveIsCalled(){
        hashTable.put(keyOne, one);
        hashTable.put(keyTwo, two);
        hashTable.remove(keyOne);
        assertEquals(1, hashTable.size());
    }

    @Test
    public void testIfTableThrowsExceptionWhenTryingToInstantiateWithCapacityOfZero(){
        assertThrows(IllegalArgumentException.class, () -> new SeperateChainingHashTable<Integer, String>(0));
    }

    @Test
    public void assertThrowsIllegalArgumentExceptionWhenKeyIsNull(){
        assertThrows(IllegalArgumentException.class, () -> new SeperateChainingHashTable<String, Integer>(20).checkIfKeyIsNull(null));
    }

    @Test
    public void testIfRemoveReturnesFalseWhenRemoveIsCalledByElementNotInTable(){
        hashTable.put(keyOne, one);
        assertNull(hashTable.remove(keyTwo));
    }

    @Test
    public void testIfValuesReturnRightValues(){
        hashTable.put(keyOne, one);
        hashTable.put(keyTwo, two);
        List<Integer> integers = List.of(1, 2);
        List<Integer> empty = new ArrayList<>();

        for(Integer value : hashTable.values()){
            empty.add(value);
        }
        assertIterableEquals(empty, integers);
    }

    @Test
    public void testIfThrowsIllegalArgumentExceptionOnDuplicateKeys(){
        hashTable.put(keyOne, one);
        assertThrows(IllegalArgumentException.class, () -> hashTable.put(keyOne, one));
    }
}
