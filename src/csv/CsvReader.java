package csv;

import java.io.BufferedReader;
import java.io.FileReader;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;

public class CsvReader {
    private ArrayList<String[]> rows;
    private ArrayList<?> objects;

    public CsvReader(){
        rows = null;
        objects = null;
    }


    public <T> CsvReader  mapStringArrayToObjects(Class<T> type){
        ArrayList<T> generatedObjects = new ArrayList<>();
        try{
            for(String[] row : rows){
                T object = maakObject(row, type);
                if(object != null){
                    generatedObjects.add(object);
                }
            }
        } catch(Exception exception){

        }
        objects = generatedObjects;
        return this;
    }

    public <T> ArrayList<T> fetchObjects(){
        return (ArrayList<T>) this.objects;
    }

    public <T> CsvReader leesCsvBestand(String bestandsnaam, boolean ignoreQuotations,boolean skipheader, Predicate<String[]>[] filters){
         return verwerkCsvBestand(bestandsnaam, ignoreQuotations, skipheader, filters);
    }

    public <T> CsvReader leesCsvBestand(String bestandsnaam, boolean ignoreQuotations, boolean skipheader){
         return verwerkCsvBestand(bestandsnaam, ignoreQuotations, skipheader,null);
    }

    private <T> CsvReader verwerkCsvBestand(String bestandsnaam,boolean ignoreQuotations, boolean skipHeader, Predicate<String[]>[] filters) {
        ArrayList<String[]> stringArrays = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(bestandsnaam))) {
            String regel;

            if (skipHeader) {
                br.readLine();
            }

            while ((regel = br.readLine()) != null) {
                // Split de regel op basis van het scheidingsteken (bijvoorbeeld komma)
                String[] gegevens = regel.split(",");

                if(ignoreQuotations){
                    for(String string : gegevens){
                        string = string.replaceAll("\"", "");
                    }
                }

                // Maak object van het juiste type en voeg het toe aan de lijst
                boolean isValid = true;
                if(filters != null){
                    for(Predicate<String[]> filter : filters){
                        if(!filter.test(gegevens)){
                            isValid = false;
                            break;
                        }
                    }
                }
                if(isValid){
                    stringArrays.add(gegevens);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.rows = stringArrays;
        return this;
    }

    private <T> T maakObject(String[] gegevens, Class<T> type) throws Exception {
        Field[] velden = type.getDeclaredFields();
        Constructor<?>[] constructors = type.getDeclaredConstructors();
        gegevens = Arrays.stream(gegevens).map(str -> str.replaceAll("\"", "")).toArray(String[]::new);

        for (Constructor constructor : constructors) {
            try {
                T object = null;
                int constructorParamsCount = constructor.getParameterCount();
                if (constructorParamsCount == gegevens.length) {
                    Object[] arguments = convertStringsToParameterTypes(constructor.getParameterTypes(), gegevens);
                    object = (T) constructor.newInstance(arguments);
                }

                if (object != null) {
                    return object;
                }
            } catch (Exception exception) {

            }
        }

        throw new Exception("Object kon niet aangemaakt worden.");
    }

    private static Object[] convertStringsToParameterTypes(Class<?>[] declaredConstructorParameters, String[] csvParts) throws IndexOutOfBoundsException {
        Object[] convertedArguments = new Object[declaredConstructorParameters.length];

        for (int i = 0; i < declaredConstructorParameters.length; i++) {
            Class<?> clazz = declaredConstructorParameters[i];
            Object object = null;
            String part = csvParts[i];
            object = convert(clazz, part);
            convertedArguments[i] = object;
        }

        return convertedArguments;
    }

    public static Object convert(Class<?> clazz, String toBeConverted){
        Object object = null;
        if (clazz == String.class) {
            object = toBeConverted;
        } else if (clazz == int.class) {
            object = Integer.parseInt(toBeConverted);
        } else if (clazz == float.class) {
            object = Float.parseFloat(toBeConverted);
        }
        return object;
    }
}


