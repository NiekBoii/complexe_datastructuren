package interfaces;

import datastructuren.Coordinate;

public interface Heuristic<T> {
    public double provideDistance(T other);

    //Vond het wel handig om een paar default implementaties te hebben voor iets wat een schatting oplevert.
    public default double haversineDistance(GeoCoordinateProvider<T> thisProvider, GeoCoordinateProvider<T> otherProvider){
        Coordinate from = new Coordinate(thisProvider.latitude(), thisProvider.longitude());
        Coordinate to = new Coordinate(otherProvider.latitude(), otherProvider.longitude());
        return from.haversineDistance(to);
    }

    public default double euclideanDistance(CoordinateProvider<T> thisProvider, CoordinateProvider<T> otherProvider){
        double deltaX = thisProvider.provideX() - otherProvider.provideX();
        double deltaY = thisProvider.provideY() - otherProvider.provideY();
        return Math.sqrt(Math.pow(deltaX, 2) + Math.pow(deltaY, 2));
    }
}
