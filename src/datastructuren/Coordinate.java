package datastructuren;

public record Coordinate(double latitude, double longitude) {
    /**
     * Calculate the distance in kilometers between this and another coordinates using the Haversine formula.
     * Code adopted from https://www.geeksforgeeks.org/haversine-formula-to-find-distance-between-two-points-on-a-sphere/
     *
     * @param to calculating distance to this coordinate
     * @return distance in kilometers
     */
    public double haversineDistance(Coordinate to) {
        // distance between latitudes and longitudes
        double dLat = Math.toRadians(to.latitude - latitude);
        double dLon = Math.toRadians(to.longitude - longitude);

        // convert to radians
        double lat1 = Math.toRadians(latitude);
        double lat2 = Math.toRadians(to.latitude);

        // apply formulae
        double a = Math.pow(Math.sin(dLat / 2), 2) +
                Math.pow(Math.sin(dLon / 2), 2) *
                        Math.cos(lat1) *
                        Math.cos(lat2);
        double rad = 6371;
        double c = 2 * Math.asin(Math.sqrt(a));
        return Math.round(rad * c); // integer is big enough for earth distances
    }

    @Override
    public String toString() {
        return String.format("(%.2f;%.2f)", latitude, longitude);
    }
}