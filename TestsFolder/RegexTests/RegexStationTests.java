package RegexTests;

import org.junit.jupiter.api.Test;
import regex.StationValidation;

import static org.junit.jupiter.api.Assertions.*;

public class RegexStationTests {

    @Test
    public void testIfValidRowIsValid(){
        assertTrue(StationValidation.validateRow()
        .test(new String[]{"1",
                "ashd",
                "324324123",
                "Den Bosch",
                "S-hertogenbosch",
                "'s-Hertogenbosch",
                "s-hertogenbosch",
                "NL",
                "knooppuntIntercitystation",
                "51.69048",
                "3.55235235"}));
    }

    @Test
    public void testIfUnvalidRowIsUnvalidAtColumn1(){
        assertFalse(StationValidation.validateRow()
        .test(new String[]{"asdf",
                "23",
                "324324123",
                "Den Bosch",
                "S-hertogenbosch",
                "'s-Hertogenbosch",
                "s-hertogenbosch",
                "NL",
                "knooppuntIntercitystation",
                "51.69048",
                "asdfsd"}));
    }

    @Test
    public void testIfUnvalidRowIsUnvalidAtColumn2(){
        assertFalse(StationValidation.validateRow()
                .test(new String[]{"1",
                        "asdfasdfadf",
                        "324324123",
                        "Den Bosch",
                        "S-hertogenbosch",
                        "'s-Hertogenbosch",
                        "s-hertogenbosch",
                        "NL",
                        "knooppuntIntercitystation",
                        "51.69048",
                        "asdfsd"}));
    }

    @Test
    public void testIfUnvalidRowIsUnvalidAtColumn7(){
        assertFalse(StationValidation.validateRow()
                .test(new String[]{"1",
                        "asd",
                        "324324123",
                        "Den Bosch",
                        "S-hertogenbosch",
                        "'s-Hertogenbosch",
                        "s-hertogenbosch",
                        "BK",
                        "knooppuntIntercitystation",
                        "51.69048",
                        "51.69048"}));
    }

    @Test
    public void testIfUnvalidRowIsUnvalidAtColumn11(){
        assertFalse(StationValidation.validateRow()
                .test(new String[]{"1",
                        "asdf",
                        "324324123",
                        "Den Bosch",
                        "S-hertogenbosch",
                        "'s-Hertogenbosch",
                        "s-hertogenbosch",
                        "NL",
                        "knooppuntIntercitystation",
                        "51.69048",
                        "asdfsd"}));
    }
}
