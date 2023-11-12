package regex;

import java.util.regex.Pattern;

public class RegexPatterns {
    public static Pattern integerOrDouble =  Pattern.compile("(\"|')?\\d+(\\.\\d+)?(\"|')?");
    public static Pattern integersOnly = Pattern.compile("\\d+");
    public static Pattern inclusiveOneToInclusive6String =  Pattern.compile("(\"|')?([A-Z]|[a-z]){1,6}(\"|')?");
}
