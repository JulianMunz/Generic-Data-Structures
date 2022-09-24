import org.junit.jupiter.api.Test;
import za.ac.sun.cs.cs712.Utilities;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static za.ac.sun.cs.cs712.Utilities.isSorted;

public class Sort_Test {
    @Test
    public void testNumbers() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/home/julian/Documents/UNI/Algos/assignment1/src/test/java/integers.txt"), Charset.defaultCharset());

        String[] strings = lines.toArray(new String[lines.size()]);
        Integer[] data = new Integer[strings.length];
        for(int i = 0;i < data.length;i++) {
            data[i] = Integer.parseInt(strings[i]);
        }
        Utilities.sort(data);
        assertTrue(isSorted(data, Comparator.naturalOrder()));
    }

    @Test
    public void testWords() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/home/julian/Documents/UNI/Algos/assignment1/src/test/java/words.txt"), Charset.defaultCharset());
        String[] data = lines.toArray(new String[lines.size()]);
        Utilities.sort(data);
        assertTrue(isSorted(data, Comparator.naturalOrder()));
    }

    @Test
    public void testNumbersSorted() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/home/julian/Documents/UNI/Algos/assignment1/src/test/java/integers_sorted.txt"), Charset.defaultCharset());

        String[] strings = lines.toArray(new String[lines.size()]);
        Integer[] data = new Integer[strings.length];
        for(int i = 0;i < data.length;i++) {
            data[i] = Integer.parseInt(strings[i]);
        }
        Utilities.sort(data);
        assertTrue(isSorted(data, Comparator.naturalOrder()));
    }

    @Test
    public void testNumbersReverse() throws IOException {
        List<String> lines = Files.readAllLines(Paths.get("/home/julian/Documents/UNI/Algos/assignment1/src/test/java/integers_reverse.txt"), Charset.defaultCharset());

        String[] strings = lines.toArray(new String[lines.size()]);
        Integer[] data = new Integer[strings.length];
        for(int i = 0;i < data.length;i++) {
            data[i] = Integer.parseInt(strings[i]);
        }
        Utilities.sort(data);
        assertTrue(isSorted(data, Comparator.naturalOrder()));
    }
}
