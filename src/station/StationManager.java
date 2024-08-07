package station;

import algoritmes.*;
import datastructuren.*;
import interfaces.MyOwnComparator;

import java.util.InputMismatchException;
import java.util.Scanner;

public class StationManager {
    private LinkedList<Station> stations;
    private LinkedList<Track> tracks;
    private SeperateChainingHashTable<String, Station> stationsMap;
    private Graph<Station> stationsGraph;
    private AvlTree<Station> stationsTree;

    private Scanner inputReader = new Scanner(System.in);
    private final MyOwnComparator<Station> containsComparator = Station.containsPartOfStationsComparator();

    private final String sourceStationQuestion = "Please enter the station you are coming from.";
    private final String destinationStationQuestion = "Please enter the destination station";

    private final String[] mainMenuOptionsStrings = {
            "Search for a station.",
            "Find shortest path between Stations.",
            "Sort all connections.",
            "Determine minimum cost spanning tree by coordinates.",
            "Write everything to csv file for data comparison.",
            "Exit application."
    };

    private final String[][] subMenuOptionsStrings = {
            {
                    "Search using linear search. This has to be strict input naming on station name_short",
                    "Search using binary search. This has to be strict input naming on station name_short"
            },
            {
                    "Create and show shortest path using A star.",
                    "Create and show shortest path using Dijkstras."
            },
            {
                    "Sort using mergesort.",
                    "Sort using insertionsort."
            },
            {
                    "Get MCST using prim of the whole train network.",
                    "Get all stations in rectangle in minimum cost spanning tree."
            }
    };


    public StationManager(LinkedList<Station> stations, LinkedList<Track> tracks) {
        this.stations = stations;
        this.tracks = tracks;
        stationsMap = new SeperateChainingHashTable<>(stations.size());
        stationsGraph = new Graph<>();
        stationsTree = new AvlTree<>();
    }

    public void runApplication() {
        initialiseDatastructures();

        Integer menuOption = -1;
        System.out.println("!Important: Don't use station names wrapped with quotations like in the csv for searching. !");
        System.out.println("!Important: These quotations are removed by the code. !");
        while (menuOption != mainMenuOptionsStrings.length) {
            printMenuHeader();
            printMenuOptions(mainMenuOptionsStrings);

            menuOption = askForIntegerInput();
            if (menuOption != mainMenuOptionsStrings.length) {
                printMenuOptions(subMenuOptionsStrings[menuOption - 1]);
                Integer subMenuOption = askForIntegerInput();
                System.out.println(menuOption);
                switch (menuOption) {
                    case 1:
                        if (subMenuOption == 1) {
                            String stationString = askForStringInput("Please enter the stations name you want to search for.");
                            searchStationLinear(stationString);
                        } else if(subMenuOption == 2){
                            String stationString = askForStringInput("Please enter the station name you want to search for");
                            searchStationBinary(stationString);
                        }

                        break;
                    case 2:
                        Station[] possibleStations = searchForStationsUsingAVLTree(sourceStationQuestion, destinationStationQuestion);
                        Station sourceStation = possibleStations[0];
                        Station destinationStation = possibleStations[1];

                        if (!stationsAreNull(sourceStation, destinationStation)) {
                            LinkedList<Station> shortestPathList;
                            String foundUsing = "Found the shortest path using: ";
                            if (subMenuOption == 1) {
                                shortestPathList = AStar.findShortestPath(stationsGraph, sourceStation, destinationStation);
                                foundUsing += "A star algorithm.";
                            } else {
                                shortestPathList = Dijkstra.findShortestPath(stationsGraph, sourceStation, destinationStation);
                                foundUsing += "Dijkstra's algorithm.";
                            }
                            handleResultOfShortestPath(shortestPathList, foundUsing);
                        }
                        break;

                    case 3:
                        if(subMenuOption == 1){
                            LinkedList<Track> sortedTracks = sortConnectionsWithMergeSort();
                            int showResults = askForIntegerInput("Do you want to print the result of sorting the tracks? \n1. Yes\n2. No");
                            if(showResults == 1){
                                sortedTracks.forEach(System.out::println);
                            }
                        } else if(subMenuOption == 2){
                            LinkedList<Track> sortedTracks = sortConnectionsWithInsertionSort();
                            int showResults = askForIntegerInput("Do you want to print the result of sorting the tracks? \n1. Yes\n2. No" +
                                    "\"\\n2. No  \"");
                            if(showResults == 1){
                                sortedTracks.forEach(System.out::println);
                            }
                        }

                        break;
                    case 4:
                        if(subMenuOption == 1){
                            String startingPoint = askForStringInput("Please enter the station you want to start from.");
                            Station startStation = stationsTree.get(getPossibleStation(startingPoint), containsComparator);
                            if(startStation == null){
                                System.out.println("Could not find for this station name. Please try again using name_short");
                            } else{
                                LinkedList<Edge<Station>> wholeTrainNetwork = Prim.determineMinimumCostSpanningTree(stationsGraph, startStation);
                                Graph<Station> subGraph = new Graph();
                                wholeTrainNetwork.forEach(subGraph::addEdge);
                                System.out.println(subGraph.graphViz());
                            }
                        }

                        if(subMenuOption == 2) {
                            possibleStations = searchForStationsUsingAVLTree("Enter the leftBottom station of the rectangle", "Enter the rightTop station of the rectangle");
                            Station recStationOne = possibleStations[0];
                            Station recStationTwo = possibleStations[1];

                            if (!stationsAreNull(recStationOne, recStationTwo)) {
                                LinkedList<Edge<Station>> minimumCostEdges = Prim.determineMinimumCostSpanningTree(stationsGraph, recStationOne, recStationOne.xAndYGreatherOrEqualBoundChecker(), recStationTwo.xAndYSmallerOrEqualBoundChecker());
                                Graph<Station> subGraph = new Graph();
                                minimumCostEdges.forEach(subGraph::addEdge);
                                System.out.println(subGraph.graphViz());
                            }
                            break;
                        }
                }
            }
            enterAKeyToContinue();
        }
        exitApplication();
    }

    public void enterAKeyToContinue() {
        String input = null;
        System.out.println("Enter some character to continue.");
        while (input == null) {
            input = inputReader.nextLine();
        }
    }

    private Station[] searchForStationsUsingAVLTree(String questionForStationOne, String questionForStationTwo) {
        System.out.println("\n\nYou can use partial station names here for searching.");
        String sourceStationString = askForStringInput(questionForStationOne);
        String destinationStationString = askForStringInput(questionForStationTwo);

        Station possibleSourceStation = getPossibleStation(sourceStationString);
        Station possibleDestinationStation = getPossibleStation(destinationStationString);

        possibleSourceStation.setNameShort(sourceStationString);
        possibleDestinationStation.setNameShort(destinationStationString);

        Station sourceStation = stationsTree.get(possibleSourceStation, containsComparator);
        Station destinationStation = stationsTree.get(possibleDestinationStation, containsComparator);

        return new Station[]{sourceStation, destinationStation};
    }

    private LinkedList<Track> sortConnectionsWithMergeSort(){
        return LinkedList.of(MergeSort.sort(tracks.toArray(Track.class)));
    }

    private LinkedList<Track> sortConnectionsWithInsertionSort(){
        return LinkedList.of(SelectionSort.sort(tracks.toArray(Track.class)));
    }

    private void handleResultOfShortestPath(LinkedList<Station> shortestPathStations, String foundUsing) {
        if (shortestPathStations.size() != 0) {
            System.out.println(foundUsing);
            for (Station s : shortestPathStations) {
                System.out.println(s);
            }
        } else {
            System.out.println("There is no route from this source station to your destination.");
        }
    }

    private void initialiseDatastructures() {
        //Zet stationcodes om naar hoofdletters. Zodat ze gematched op sleutel kunnen worden.
        tracks.forEach(track -> {
            track.setStationfromcodeToUppercase();
            track.setStationtocodeToUppercase();
        });

        //Vul de hashmap.
        stations.forEach(station -> {
            stationsMap.put(station.getCode(), station);
        });

        //Vul de avl boom.
        stations.forEach(station -> {
            stationsTree.add(station);
        });

        //Vul de graaf.
        tracks.forEach(track -> {
            stationsGraph.addEdge(stationsMap.get(track.getStationFromCode()), stationsMap.get(track.getStationToCode()), track.getTrackLength());
        });
    }

    private Integer askForIntegerInput() {
        return askForIntegerInput("");
    }

    private boolean stationsAreNull(Station station, Station otherStation) {
        if (station == null || otherStation == null) {
            System.out.println("There was no station found for either destination or source.");
            return true;
        } else {
            System.out.println("Source station found for search command: " + station);
            System.out.println("Destination station found for search command: " + otherStation);
            return false;
        }
    }

    private Integer askForIntegerInput(String question) {
        printMessage(question);
        Integer option = null;
        while (option == null || option < Integer.valueOf(1) || option > mainMenuOptionsStrings.length) {
            try {
                option = inputReader.nextInt();
                if (option < 1) {
                    System.out.println("Please provide a number higher than zero.");
                } else if (option > mainMenuOptionsStrings.length) {
                    System.out.println("Please provide a number withing the range of options.");
                }
            } catch (InputMismatchException exception) {
                System.out.println("Please use integers to navigate through the menu options.");
                inputReader.nextLine();
            }
        }
        return option;
    }

    private float askForFloatInput() {
        return askForFloatInput("");
    }

    private float askForFloatInput(String question) {
        printMessage(question);
        Float option = null;
        while (option == null) {
            try {
                option = inputReader.nextFloat();
            } catch (InputMismatchException exception) {
                System.out.println("Please provide a float using the . as number seperator.");
                inputReader.nextLine();
            }
        }
        return option;
    }

    private void printMessage(String question) {
        if (question.length() != 0) {
            System.out.println(question);
        }
    }

    private String askForStringInput() {
        return askForStringInput("");
    }

    private String askForStringInput(String question) {
        String input = "";
        printMessage(question);
        while (input.length() == 0) {
            input = "";
            try {
                input = inputReader.nextLine();
                if (input.length() == 0) {
                    System.out.println("Enter an answer: ");
                }
            } catch (InputMismatchException exception) {
                inputReader.nextLine();
            }
        }
        return input;
    }

    private void printMenuOptions(String[] menuOptions) {
        int index = 0;
        for (String menuOption : menuOptions) {
            System.out.println(++index + " " + menuOption);
        }
    }

    private void printMenuHeader() {
        System.out.println("NS Spoorwegen.\n");
    }

    public void printStationNames() {
        stations.forEach(System.out::println);
    }

    public int getStationListSize() {
        return this.stations.size();
    }

    public void searchStationBinary(String stationNaam) {
        Station mogelijkStation = getPossibleStation(stationNaam);
        Station gevondenOfNiet = BinarySearch.search(stationsTree.inOrderTraversal().toArray(Station.class), mogelijkStation);

        if (gevondenOfNiet != null) {
            System.out.println("Station gevonden: \n" + gevondenOfNiet.toString());
        } else {
            System.out.println("Could not find a station for this input.\n Please use the station name short.");
        }
    }


    public void searchStationLinear(String stationNaam) {
        Station possibleStation = getPossibleStation(stationNaam);

        Station gevondenOfNiet = LinearSearch.search(stations.iterator(), possibleStation);


        if (gevondenOfNiet != null) {
            System.out.println("Station gevonden: \n" + gevondenOfNiet.toString());
        } else {
            System.out.println("Could not find a station for this input.\n Please use the station name long.");
        }
    }

    public LinkedList<Station> getShortestPathWithAStar(Station source, Station destination) {
        return AStar.findShortestPath(stationsGraph, source, destination);
    }

    private void exitApplication() {
        inputReader.close();
        System.exit(0);
    }

    private Station getPossibleStation(String stationNaam) {
       Station newStation = new Station(stationNaam);
       newStation.setNameShort(stationNaam);
       return newStation;
    }
}
