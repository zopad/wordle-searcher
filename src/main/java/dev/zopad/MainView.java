package dev.zopad;

import com.vaadin.flow.component.dependency.CssImport;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextArea;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.PWA;
import dev.zopad.words.WordListHolder;

import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Route
@PageTitle("Wordle Solver")
@PWA(name = "Wordle Solver",
        shortName = "Wordle Solver",
        description = "Wordle Solver",
        enableInstallPrompt = false)
@CssImport("./styles/shared-styles.css")
@CssImport(value = "./styles/vaadin-text-field-styles.css", themeFor = "vaadin-text-field")
public class MainView extends VerticalLayout {

    private static final String DEFAULT_REGEX = "[a-z][^fjk][a-z][b][^ia]";

    public MainView() {
        WordListHolder wordListHolder = new WordListHolder();
        List<String> wordList = wordListHolder.getWordList();

        TextField queryField = new TextField();
        queryField.setPlaceholder(DEFAULT_REGEX);
        queryField.setValue(DEFAULT_REGEX);

        TextArea resultsArea = new TextArea();
        resultsArea.setSizeFull();
        resultsArea.setValue("Results will be here...");

        queryField.addValueChangeListener(changed -> {
            String query = changed.getValue();
            Pattern regex = Pattern.compile(query, Pattern.CASE_INSENSITIVE);
            List<String> matchedWords = wordList.stream().filter(w -> regex.matcher(w).find()).collect(Collectors.toList());
            resultsArea.setValue(String.join(" ", matchedWords));
        });

        // Use custom CSS classes to apply styling. This is defined in shared-styles.css.
        //addClassName("centered-content");
        setSizeFull();
        add(queryField, resultsArea);
    }

}
