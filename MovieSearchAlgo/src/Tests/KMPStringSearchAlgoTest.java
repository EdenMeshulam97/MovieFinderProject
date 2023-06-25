package Tests;

import com.hit.algorithhm.KMPStringSearchAlgo;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.assertEquals;

class KMPStringSearchAlgoTest {

    private KMPStringSearchAlgo kmp = new KMPStringSearchAlgo();

    @Test
    public void testBasicSearch() {
        ArrayList<Integer> result = kmp.search("abcabcabc", "abc");
        assertEquals(3, result.size());
        assertEquals(0, result.get(0));
        assertEquals(3, result.get(1));
        assertEquals(6, result.get(2));
    }

    @Test
    public void testNoMatch() {
        ArrayList<Integer> result = kmp.search("abcabcabc", "xyz");
        assertEquals(0, result.size());
    }

    @Test
    public void testPatternLargerThanText() {
        ArrayList<Integer> result = kmp.search("abc", "abcdef");
        assertEquals(0, result.size());
    }

    @Test
    public void testPatternAtTheEndOfText() {
        ArrayList<Integer> result = kmp.search("abcabcabc", "abc");
        assertEquals(3, result.size());
        assertEquals(0, result.get(0));
        assertEquals(3, result.get(1));
        assertEquals(6, result.get(2));
    }

    @Test
    public void testPatternIsEntireText() {
        ArrayList<Integer> result = kmp.search("abc", "abc");
        assertEquals(1, result.size());
        assertEquals(0, result.get(0));
    }
    @Test
    public void testEmptyTextAndPattern() {
        ArrayList<Integer> result = kmp.search("", "");
        assertEquals(true, result.isEmpty());
    }

    @Test
    public void testNonEmptyTextAndEmptyPattern() {
        ArrayList<Integer> result = kmp.search("abc", "");
        assertEquals(true, result.isEmpty());
    }

    @Test
    public void testEmptyTextAndNonEmptyPattern() {
        ArrayList<Integer> result = kmp.search("", "abc");
        assertEquals(true, result.isEmpty());
    }
    
    @Test
    public void testPartialPattern() {
        ArrayList<Integer> result = kmp.search("abacbcacaba", "abc");
        assertEquals(true, result.isEmpty());
    }

    @Test
    public void testPatternAtStartOfText() {
        ArrayList<Integer> result = kmp.search("abcefgefg", "abc");
        assertEquals(1, result.size());
        assertEquals(0, result.get(0));
    }

    @Test
    public void testPatternAtEndOfText() {
        ArrayList<Integer> result = kmp.search("efgefgabc", "abc");
        assertEquals(1, result.size());
        assertEquals(6, result.get(0));
    }

    @Test
    public void testNotCaseSensitive() {
        ArrayList<Integer> result = kmp.search("ABC", "abc");
        assertEquals(1, result.size());
        assertEquals(0, result.get(0));
    }

    @Test
    public void testRepeatedPattern() {
        ArrayList<Integer> result = kmp.search("abcabcabc", "abc");
        assertEquals(3, result.size());
        assertEquals(0, result.get(0));
        assertEquals(3, result.get(1));
        assertEquals(6, result.get(2));
    }
	
}
