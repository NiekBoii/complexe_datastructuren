package RegexTests;

import datastructuren.SeperateChainingHashTable;
import org.junit.jupiter.api.Test;
import regex.TracksValidation;

import static org.junit.jupiter.api.Assertions.*;

public class RegexTracksTests {

    @Test
    public void testIfValidRowIsValid(){
        assertTrue(TracksValidation.validateRow().test(new String[]{"ac", "ashd", "2", "2", "30"}));
    }

    @Test
    public void testIfUnvalidRowAtColum2IsUnvalid(){
        assertFalse(TracksValidation.validateRow().test(new String[]{"af", "asdfasdfasdf", "asdf", "asdfasdf", "asdf", "asdf"}));
    }

    @Test
    public void testIfUnvalidRowAtColumn3IsUnvalid(){
        assertFalse(TracksValidation.validateRow().test(new String[]{"asdf", "jkdf", "df", "324", "23", "23"}));
    }

    @Test
    public void testIfUnvalidRowAtColumn4IsUnvalid(){
        assertFalse(TracksValidation.validateRow().test(new String[]{"asdf", "jkdf", "23", "dsf", "23", "23"}));
    }

    @Test
    public void testIfUnvalidRowAtColumn5IsUnvalid(){
        assertFalse(TracksValidation.validateRow().test(new String[]{"asdf", "jkdf", "32", "32", "dfdfd", "23"}));
    }

    @Test
    public void testIfPredicateAndHashTableReturnsPositiveResult(){
        SeperateChainingHashTable<String, String> stationCodes = new SeperateChainingHashTable<>(30);
        stationCodes.put("AC", "BC");
        stationCodes.put("BC", "AC");
        assertTrue(TracksValidation.matchStationCodes(stationCodes).test(new String[]{"AC", "BC"}));
    }

    @Test
    public void testIfPredicateAndHashTableReturnsNegativeResult(){
        SeperateChainingHashTable<String, String> stationCodes = new SeperateChainingHashTable<>(30);
        stationCodes.put("AC", "AF");
        stationCodes.put("HC", "DF");
        assertFalse(TracksValidation.matchStationCodes(stationCodes).test(new String[]{"KC", "GC"}));
    }
}
