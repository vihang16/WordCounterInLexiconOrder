package test.com.word;

import com.arista.word.WordCounter;
import org.junit.jupiter.api.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
        TreeMap<String, List<Integer>> wordCountMap = wc.frequencyMap(test);
        assertEquals(34, wordCountMap.size());
        assertEquals(List.of(1), wordCountMap.get("i.e"));
    }

    @Test
    @DisplayName("Sentences with U.S and date")
    void freqWithDateAndUS(){
        String test = "This is a test. This is a T.L.A. test. Now with a Dr. or Ph.D. in it. This is new in the U.S from Jan. 4, 1983.";
        TreeMap<String, List<Integer>> wordCountMap = wc.frequencyMap(test);
        assertEquals(List.of(3), wordCountMap.get("dr"));
        assertEquals(List.of(4), wordCountMap.get("jan"));
        assertEquals(3, wordCountMap.get("this").size());
        assertEquals(1, wordCountMap.get("ph.d").size());
        assertEquals(new TreeSet<>(Arrays.asList("1983", "4", "a", "dr", "from", "in", "is", "it", "jan", "new", "now",
                                             "or", "ph.d", "t.l.a","test","the", "this","u.s", "with")), wordCountMap.keySet());
    }

    @Test
    @DisplayName("Sentences with incorrect English, output would not be accurate")
    void freqWithIncorrectEnglishWord(){
        String test = "this is going to be example of incorrect english, where I would not use capital letter at the beginning of the sentence and some incorrect english words e.i nance. this is a bad example.";
        TreeMap<String, List<Integer>> wordCountMap = wc.frequencyMap(test);
        assertEquals(Arrays.asList(1, 1), wordCountMap.get("this"));
        assertEquals(List.of(1),wordCountMap.get("e.i"));
    }

    @Test
    @DisplayName("Repetitive word with small and capital letters")
    void freqWithIRepetitiveWord(){
        String test = "With with wIth. With with with";
        TreeMap<String, List<Integer>> wordCountMap = wc.frequencyMap(test);
        assertEquals(Arrays.asList(1, 1,1,2,2,2), wordCountMap.get("with"));
    }

    @Test
    @DisplayName("Long Paragraph")
    void freqWithLongParagraph(){
        String test = """
                Avengers are part of the MCU. There main characters are Robert Downey Jr. as Iron-Man, Tom Holland as Spider-Man, Chris Hemsworth as Thor and Chris Pratt as Quill/Star-Lord.\040
                Justice league was part of DC comics. Ben Afleck and Christian Bale played role of Batman, Gal Godot as wonder woman, and Jason Momoa as Aqua-man.\040""";
        TreeMap<String, List<Integer>> wordCountMap = wc.frequencyMap(test);
        assertEquals(List.of(2), wordCountMap.get("jr"));
        assertEquals(List.of(2), wordCountMap.get("iron-man"));
        assertEquals(Arrays.asList(2,2,2,2, 4, 4), wordCountMap.get("as"));
        assertEquals(Arrays.asList(2,2), wordCountMap.get("chris"));
        assertEquals(List.of(2), wordCountMap.get("quill"));
        assertEquals(List.of(2), wordCountMap.get("star-lord"));
    }
}