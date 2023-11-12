package station;

import interfaces.*;

public class Station implements AStarSearchable<Station>, GeoCoordinateProvider<Station>, MinimumCostSpannable<Station> {

    private int id;
    private String code;
    private int uic;
    private String name_short;
    private String name_medium;
    private String name_long;
    private String slug;
    private String country;
    private String type;

    private float geo_lat;
    private float geo_lng;

    public Station(int id, String code, int uic, String name_short, String name_medium, String name_long, String slug, String country, String type, float geo_lat, float geo_lng) {
        this.id = id;
        this.code = code;
        this.uic = uic;
        this.name_short = name_short;
        this.name_medium = name_medium;
        this.name_long = name_long;
        this.slug = slug;
        this.country = country;
        this.type = type;
        this.geo_lat = geo_lat;
        this.geo_lng = geo_lng;
    }

    public Station(String name_long) {
        this.name_long = name_long;
    }

    public String getName() {
        return this.name_long;
    }

    @Override
    public int compareTo(Station otherStation) {
        return name_short.toLowerCase().compareTo(otherStation.name_short.toLowerCase());
    }

    public void setName(String stationNaam) {
        this.name_long = stationNaam;
    }

    public String getCode() {
        return this.code;
    }

    public void setNameShort(String shortName){
        this.name_short = shortName;
    }

    @Override
    public String toString() {
        return name_long;
    }

    public String fullString() {
        return "Station{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", uic=" + uic +
                ", name_short='" + name_short + '\'' +
                ", name_medium='" + name_medium + '\'' +
                ", name_long='" + name_long + '\'' +
                ", slug='" + slug + '\'' +
                ", country='" + country + '\'' +
                ", type='" + type + '\'' +
                ", geo_lat=" + geo_lat +
                ", geo_lng=" + geo_lng +
                '}';
    }

    public static MyOwnComparator<Station> containsPartOfStationsComparator() {
        return (thisStation  , otherStation) -> {
            String thisStationString = thisStation.name_short.replaceAll("\s", "").toLowerCase();
            String otherStationString = otherStation.name_short.replaceAll("\s", "").toLowerCase();

            if (thisStationString.length() == otherStationString.length()  && thisStationString.compareTo(otherStationString) == 0) {
                return 0;
            } else {
                return !otherStationString.contains(thisStationString) ? thisStationString.compareTo(otherStationString) : 0;
            }
        };
    }

    @Override
    public double latitude() {
        return geo_lat;
    }

    @Override
    public double longitude() {
        return geo_lng;
    }

    @Override
    public double provideDistance(Station otherStation) {
        return haversineDistance(this, otherStation);
    }

    public MCSTBoundChecker<Station> xAndYSmallerOrEqualBoundChecker(){
        Station thisStation = this;
        return new MCSTBoundChecker<Station>() {
            @Override
            public boolean isInBounds(Station other) {
                return other.latitude() < thisStation.latitude() && other.longitude() < thisStation.longitude();
            };
        };
    }

    public MCSTBoundChecker<Station> xAndYGreatherOrEqualBoundChecker(){
        Station thisStation = this;
        return new MCSTBoundChecker<Station>() {
            @Override
            public boolean isInBounds(Station other) {
                return other.latitude() > thisStation.latitude() && other.longitude() > thisStation.longitude();
            };
        };
    }

    @Override
    public boolean isInBounds(Station other) {
        return false;
    }
}
