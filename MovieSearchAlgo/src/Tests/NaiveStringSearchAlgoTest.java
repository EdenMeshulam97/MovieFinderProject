package Tests;

import com.hit.algorithhm.NaiveStringSearchAlgo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class NaiveStringSearchAlgoTest {

	private NaiveStringSearchAlgo naive = new NaiveStringSearchAlgo();

    @Test
    public void testBasicSearch() {
        ArrayList<Integer> result = naive.search("abcabcabc", "abc");
        assertEquals(3, result.size());
        assertEquals(0, result.get(0));
        assertEquals(3, result.get(1));
        assertEquals(6, result.get(2));
    }

    @Test
    public void testNoMatch() {
        ArrayList<Integer> result = naive.search("abcabcabc", "xyz");
        assertEquals(0, result.size());
    }

    @Test
    public void testPatternLargerThanText() {
        ArrayList<Integer> result = naive.search("abc", "abcdef");
        assertEquals(0, result.size());
    }

    @Test
    public void testPatternAtTheEndOfText() {
        ArrayList<Integer> result = naive.search("abcabcabc", "abc");
        assertEquals(3, result.size());
        assertEquals(0, result.get(0));
        assertEquals(3, result.get(1));
        assertEquals(6, result.get(2));
    }

    @Test
    public void testPatternIsEntireText() {
        ArrayList<Integer> result = naive.search("abc", "abc");
        assertEquals(1, result.size());
        assertEquals(0, result.get(0));
    }
    @Test
    public void testEmptyTextAndPattern() {
        ArrayList<Integer> result = naive.search("", "");
        assertEquals(true, result.isEmpty());
    }

    @Test
    public void testNonEmptyTextAndEmptyPattern() {
        ArrayList<Integer> result = naive.search("abc", "");
        assertEquals(true, result.isEmpty());
    }

    @Test
    public void testEmptyTextAndNonEmptyPattern() {
        ArrayList<Integer> result = naive.search("", "abc");
        assertEquals(true, result.isEmpty());
    }
    
    @Test
    public void testPartialPattern() {
        ArrayList<Integer> result = naive.search("abacbcacaba", "abc");
        assertEquals(true, result.isEmpty());
    }

    @Test
    public void testPatternAtStartOfText() {
        ArrayList<Integer> result = naive.search("abcefgefg", "abc");
        assertEquals(1, result.size());
        assertEquals(0, result.get(0));
    }

    @Test
    public void testPatternAtEndOfText() {
        ArrayList<Integer> result = naive.search("efgefgabc", "abc");
        assertEquals(1, result.size());
        assertEquals(6, result.get(0));
    }

    @Test
    public void testNotCaseSensitive() {
        ArrayList<Integer> result = naive.search("ABC", "abc");
        assertEquals(1, result.size());
        assertEquals(0, result.get(0));
    }

    @Test
    public void testRepeatedPattern() {
        ArrayList<Integer> result = naive.search("abcabcabc", "abc");
        assertEquals(3, result.size());
        assertEquals(0, result.get(0));
        assertEquals(3, result.get(1));
        assertEquals(6, result.get(2));
    }

}
