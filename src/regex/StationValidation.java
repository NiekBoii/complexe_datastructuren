package regex;

import java.util.function.Predicate;

import static regex.RegexPatterns.*;

public class StationValidation {
    public static Predicate<String[]> validateRow() throws IllegalArgumentException {
        return new Predicate<String[]>() {
            @Override
            public boolean test(String[] strings) {
                return integersOnly.matcher(strings[0]).matches()
                        && inclusiveOneToInclusive6String.matcher(strings[1]).matches()
                        && integerOrDouble.matcher(strings[10]).matches()
                        && strings[7].equals("NL");
            }
        };
    }
}
