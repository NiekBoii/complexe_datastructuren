package station;


import java.util.Locale;

public class Track implements Comparable<Track>{
    private String stationfromcode;
    private String stationtocode;
    private  int fromLength;
    private  int toLength;
    private  int type;

    public Track(String stationfromcode, String stationtocode, int fromLength, int toLength, int type) {
        this.stationfromcode = stationfromcode;
        this.stationtocode = stationtocode;
        this.fromLength = fromLength;
        this.toLength = toLength;
        this.type = type;
    }

    @Override
    public int compareTo(Track otherTrack) {
        return this.fromLength - otherTrack.fromLength;
    }

    public String getStationFromCode() {
        return stationfromcode;
    }

    public void setStationtocodeToUppercase(){
        this.stationtocode = stationtocode.toUpperCase();
    }

    public void setStationfromcodeToUppercase(){
        this.stationfromcode = stationfromcode.toUpperCase();
    }

    public String getStationToCode() {
        return stationtocode;
    }

    public int getTrackLength(){
        return this.fromLength;
    }

    @Override
    public String toString(){
        return  stationfromcode + " " +
         stationtocode + " " +
         fromLength + " " +
         toLength + " " +
         type;
    }
}
