package dev.zopad.words;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class WordListHolder {

    private final String CACHED_WORD_FILE_NAME = "filtered-words.txt";
    private final List<String> wordList = new ArrayList<>();

    public List<String> getWordList() {
        if (wordList.isEmpty()) {
            init(wordList);
        }
        return wordList;
    }

    private void init(List<String> wordList) {
        if (isFileExists(CACHED_WORD_FILE_NAME)) {
            readFromCachedFile(wordList);
        } else {
            downloadFromSource(wordList);
        }
    }

    private void readFromCachedFile(List<String> wordList) {
        try {
            BufferedReader in = new BufferedReader(new FileReader(CACHED_WORD_FILE_NAME));
            String word;
            while ((word = in.readLine()) != null) {
                wordList.add(word);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void downloadFromSource(List<String> wordList) {
        List<String> wordsFromExternalSource = new ExternalWordSourceDownloader().getWordsFromExternalSource();
        wordList.addAll(WordsFilter.filterFiveLength(wordsFromExternalSource));
        saveFilteredListToFile(wordList);
    }

    private boolean isFileExists(String fileName) {
        File file = new File(fileName);
        return file.exists();
    }

    private void saveFilteredListToFile(List<String> filteredList) {
        try {
            FileWriter fileWriter = new FileWriter(CACHED_WORD_FILE_NAME);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            filteredList.forEach(printWriter::println);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
