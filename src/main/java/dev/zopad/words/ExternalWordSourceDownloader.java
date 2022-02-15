package dev.zopad.words;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class ExternalWordSourceDownloader {

    private String SIMPLE_WORD_SOURCE_URL = "https://www.mit.edu/~ecprice/wordlist.10000";
    private String WORD_SOURCE_URL = "https://raw.githubusercontent.com/dwyl/english-words/master/words_alpha.txt";

    List<String> getWordsFromExternalSource() {
        try {
            URL url = new URL(WORD_SOURCE_URL);
            BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()));
            List<String> wordsFromExternalSource = new ArrayList<>();
            String line;
            while ((line = in.readLine()) != null) {
                wordsFromExternalSource.add(line);
            }
            in.close();
            return wordsFromExternalSource;
        } catch (IOException e) {
            System.err.println("Could not download from URL: " + WORD_SOURCE_URL + " " + e);
            return Collections.emptyList();
        }
    }
}
