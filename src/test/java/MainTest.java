import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private ByteArrayOutputStream outputStream;

    @BeforeEach
    void setUp() {
        outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));
    }

    @Test
    void testLanguageSelection_ValidFinnish() {
        String input = "1\n0\n";  // Choose Finnish and enter 0 items
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("Syötä ostettavien tuotteiden määrä:"), "Finnish language not selected correctly.");
    }

    @Test
    void testLanguageSelection_ValidSwedish() {
        String input = "2\n0\n";  // Choose Swedish and enter 0 items
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("Ange antalet varor att köpa:"), "Swedish language not selected correctly.");
    }

    @Test
    void testLanguageSelection_ValidJapanese() {
        String input = "3\n0\n";  // Choose Japanese and enter 0 items
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("購入する商品の数を入力してください:"), "Japanese language not selected correctly.");
    }

    @Test
    void testLanguageSelection_DefaultToEnglish() {
        String input = "99\n0\n";  // Invalid choice, defaults to English
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String output = outputStream.toString();
        assertTrue(output.contains("Enter the number of items to purchase:"), "Defaulting to English failed.");
    }

    @Test
    void testCartTotalCalculation() {
        String input = "4\n2\n10.0\n2\n20.0\n3\n";
        System.setIn(new ByteArrayInputStream(input.getBytes()));

        Main.main(new String[]{});

        String output = outputStream.toString();
        System.out.println(output);  // Debugging output

        assertTrue(output.contains("80"), "Cart total calculation is incorrect.");
    }
}