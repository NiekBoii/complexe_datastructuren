package regex;

import datastructuren.LinkedList;
import datastructuren.SeperateChainingHashTable;
import station.Station;

import java.util.List;
import java.util.function.Predicate;

import static regex.RegexPatterns.*;

public class TracksValidation {

    public static Predicate<String[]> validateRow() {
        return new Predicate<String[]>() {
            @Override
            public boolean test(String[] strings) {
                return inclusiveOneToInclusive6String.matcher(strings[0]).matches()
                        && inclusiveOneToInclusive6String.matcher(strings[1]).matches()
                        && integersOnly.matcher(strings[2]).matches()
                        && integersOnly.matcher(strings[3]).matches()
                        && integersOnly.matcher(strings[4]).matches();
            }
        };
    }

    public static Predicate<String[]> matchStationCodes(SeperateChainingHashTable<String, String> stationCodes){
        return new Predicate<String[]>() {
            @Override
            public boolean test(String[] strings) {
                return stationCodes.contains(strings[0]) && stationCodes.contains(strings[1]);
            }
        };
    }
}
