package com.arista.word;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

public class WordCounter {
    public static void main(String[] args) {
        WordCounter wc = new WordCounter();
        String en = "This is a test. This is a T.L.A. test. Now with a Dr. in it. This is new in the U.S from Jan. 4, 1983.";
        TreeMap<String, List<Integer>> result = wc.frequencyMap(en);
        System.out.println(result);
    }

    public TreeMap<String, List<Integer>> frequencyMap(String en) {
        TreeMap<String, List<Integer>> wordCounterMap = new TreeMap<>();
        BreakIterator iterator = BreakIterator.getSentenceInstance(Locale.ENGLISH);
        iterator.setText(en);
        int counter =1;
        int start = iterator.first();
        for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
            String subString = en.substring(start,end);
            addWordsIntoMap(subString.toLowerCase(), wordCounterMap, counter);
            counter++;
        }
        return wordCounterMap;
     }



    private void addWordsIntoMap(String subString, TreeMap<String, List<Integer>> wordCounterMap, int counter) {
            BreakIterator iterator = BreakIterator.getWordInstance();
            iterator.setText(subString);
            int start = iterator.first();
            for (int end = iterator.next(); end != BreakIterator.DONE; start = end, end = iterator.next()) {
                String word = subString.substring(start,end);
                if(!isSpecialCharacter(word))
                   wordCounterMap.compute(word, (k,v) ->{
                    v = v == null? new ArrayList<>():v;
                    v.add(counter);
                    return v;
                });
            }
        }

    private boolean isSpecialCharacter(String word) {
        return word.trim().equals("") || word.matches("[-/@#$%^&_+=(),:.]+");    }

}