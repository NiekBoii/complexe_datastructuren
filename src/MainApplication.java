import csv.CsvReader;
import datastructuren.LinkedList;
import datastructuren.SeperateChainingHashTable;
import regex.StationValidation;
import regex.TracksValidation;
import station.Station;
import station.StationManager;
import station.Track;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MainApplication {

    public static final String CURRENT_DIR = System.getProperty("user.dir");
    public static final String STATIONS_CSV_STRING = CURRENT_DIR + "/src/csv/stations.csv";
    public static final String TRACKS_CSV_STRING = CURRENT_DIR + "/src/csv/tracks.csv";

    public static void main(String[] args) {
        CsvReader reader = new CsvReader();
        ArrayList<Station> stationList = reader
                .leesCsvBestand(STATIONS_CSV_STRING, true, true, new Predicate[]{StationValidation.validateRow()})
                .mapStringArrayToObjects(Station.class)
                .fetchObjects();

        SeperateChainingHashTable<String, String> stations = new SeperateChainingHashTable<>(stationList.size());
        stationList.forEach(station -> {
            stations.put(station.getCode(), station.getCode());
        });

        List<Track> tracks = reader
                .leesCsvBestand(TRACKS_CSV_STRING, true, false, new Predicate[]{
                        TracksValidation.validateRow()})
                .mapStringArrayToObjects(Track.class)
                .fetchObjects();

        //Ik weet het flinke drama hier. Maar ik kreeg het niet voor elkaar om mijn csv lezer ook het predicaat van match de station codes te laten werken.
        tracks = tracks.stream().filter(track -> {
            return stations.contains(track.getStationToCode().toUpperCase()) && stations.contains(track.getStationFromCode().toUpperCase());
        }).collect(Collectors.toList());

        StationManager manager = new StationManager(LinkedList.of(stationList), LinkedList.of(tracks));
        System.out.println(stationList.size() + " stations loaded.");
        System.out.println(tracks.size() + " tracks loaded.");
        manager.runApplication();
    }
}
