package SortSearchTimeTesting;

public class Result{
    private final int key;
    private final Double duration;

    public Result(Double duration, int key) {
        this.duration = duration;
        this.key = key;
    }

    public int getKey(){
        return key;
    }

    public Double getDuration(){
        return duration;
    }

    public String toCsvString() {
        return key + "," + duration;
    }
}
