package SortSearchTimeTesting;

import java.util.ArrayList;
import java.util.List;

public class ResultSet {
    private String fileName;
    private String csvHeader;

    private final List<Result> results;

    public ResultSet(String fileName, String csvHeader) {
        this.fileName = fileName;
        this.csvHeader = csvHeader;
        results = new ArrayList<>();
    }

    public void addResult(Result result){
        results.add(result);
    }

    public boolean isEmpty(){
        return results.isEmpty();
    }

    private void sortResults(){
        results.sort((resultOne, resultTwo) -> {
            if(resultOne.getKey() < resultTwo.getKey()){
                return -1;
            }else if(resultOne.getDuration() > resultTwo.getDuration()){
                return 1;
            }else{
                return 0;
            }
        });
    }

    public String getFileName(){
        return fileName;
    }

    public String toCsvString(){
        sortResults();
        StringBuilder sb = new StringBuilder();
        String lineSeparator = System.lineSeparator();
        sb.append(csvHeader);
        sb.append(lineSeparator);

        for(Result result : results) {
            sb.append(result.toCsvString());
            sb.append(lineSeparator);
        }
        return sb.toString();
    }
}
