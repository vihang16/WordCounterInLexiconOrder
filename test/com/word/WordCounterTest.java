package test.com.word;

import com.arista.word.WordCounter;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

class WordCounterTest {
    static WordCounter wc;

    @BeforeAll
    static void setUp(){
     wc = new WordCounter();
    }

    @Test
    @DisplayName("normal test case with i.e and colon")
    void frequencyMap() {

        String test = """
                Given an arbitrary text document written in English, write a program that will generate a
                concordance, i.e. an alphabetical list of all word occurrences, labeled with word
                        frequencies. Bonus: label each word with the sentence numbers in which each occurrence appeared.""";
        TreeMap<String, List<Integer>> wordCountList = wc.frequencyMap(test);
        Assertions.assertEquals(34, wordCountList.size());
        Assertions.assertEquals(wordCountList.get("i.e"), Arrays.asList(1));

    }

    @Test
    @DisplayName("Sentences with U.S and date")
    void freqWithDateAndUS(){
        String test = "This is a test. This is a T.L.A. test. Now with a Dr. or Ph.D. in it. This is new in the U.S from Jan. 4, 1983.";
        TreeMap<String, List<Integer>> wordCountList = wc.frequencyMap(test);
        Assertions.assertEquals(Arrays.asList(3), wordCountList.get("dr"));
        Assertions.assertEquals(Arrays.asList(4), wordCountList.get("jan"));
        Assertions.assertEquals(3, wordCountList.get("this").size());
        Assertions.assertEquals(1, wordCountList.get("ph.d").size());
    }

    @Test
    @DisplayName("Sentences with incorrect English, output would not be accurate")
    void freqWithIncorrectEnglishWord(){
        String test = "this is going to be example of incorrect english, where I would not use capital letter at the beginning of the sentence and some incorrect english words e.i nance. this is a bad example.";
        TreeMap<String, List<Integer>> wordCountMap = wc.frequencyMap(test);
        Assertions.assertEquals(Arrays.asList(1, 1), wordCountMap.get("this"));
        Assertions.assertEquals(Arrays.asList(1),wordCountMap.get("e.i"));
    }
}