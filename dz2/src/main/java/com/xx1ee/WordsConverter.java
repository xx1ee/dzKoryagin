package com.xx1ee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class WordsConverter {
    private final List<String> iter = new ArrayList<>();
    private final Integer first;
    private final Integer second;
    public WordsConverter(String words, Integer first, Integer second) {
        this.iter.addAll(List.of(words.trim().split("\\s+")));
        this.first = first - 1;
        this.second = second - 1;
    }
    public String convert() {
        StringBuilder newWords = new StringBuilder();
        String w = "";
        if (first >= 0 && first < second && first < iter.size() && second >= 0 && second > first && second < iter.size()) {
            for (int i = 0; i < iter.size(); i++) {
                if (i >= first && i <= second) {
                    newWords.append(iter.get(i));
                    newWords.append(" ");
                } else {
                    w+=iter.get(i);
                    w+=" ";
                }
            }
        } else {
            return "Выход за пределы";
        }

        return w + newWords.toString();
    }
}
