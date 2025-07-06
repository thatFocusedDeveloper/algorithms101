package course.challenges.interviews;

import course.challenges.interviews.CreditCardProcessor;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class CreditCardProcessorTest {

    private CreditCardProcessor processor;

    // The Interview Question

    /*

    Given a credit card number, write an algoritm that will return
    the following credit card names under the following conditions.

     getCardType("1111222233334444")
     returns "Unknown"

     getCardType("4111111111111111") // Begins with a 4
     returns "Visa"

     getCardType("4111") // 16 digits -> return unknown

     getCardType("341111111111111") // Begins with a 34 - 15 digits
     getCardType("371111111111111") // Begins with a 37 - 15 digits
     return "Amex"

     "60", "65", Discover, 16
     "30", "36", "38", Diners Club, 14
     "5", "2", MC, 16
     "35", JCB, 16, 17, 18, 19

     */


    @BeforeEach
    public void SetUp() {
        processor = new CreditCardProcessor();
    }

    @Test
    public void GetCardType() {
        assertEquals("VISA", processor.getCardType("4111111111111111"));
        assertEquals("Unknown", processor.getCardType("4111"));

        assertEquals("Amex", processor.getCardType("341111111111111"));
        assertEquals("Amex", processor.getCardType("371111111111111"));

        assertEquals("Discovery", processor.getCardType("6011111111111111"));
        assertEquals("Discovery", processor.getCardType("6511111111111111"));

        assertEquals("JCB", processor.getCardType("3511111111111111"));
        assertEquals("JCB", processor.getCardType("35111111111111111"));
        assertEquals("JCB", processor.getCardType("351111111111111111"));
        assertEquals("JCB", processor.getCardType("3511111111111111111"));
    }

}
