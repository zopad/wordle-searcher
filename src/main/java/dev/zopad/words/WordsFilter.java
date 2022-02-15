package dev.zopad.words;

import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

class WordsFilter {

    static List<String> filterFiveLength(List<String> words) {
        return filterCustom(words, w -> w.length() == 5);
    }

    static List<String> filterCustom(List<String> words, Predicate<String> wordPredicate) {
        return words.stream().filter(wordPredicate).collect(Collectors.toList());
    }

}
