package com.xx1ee;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class GermanConverter {
    private final Map<String, String> edinitsy = new HashMap<>();
    private final Map<String, String> desyatki = new HashMap<>();
    private final Map<String, String> elevenNineteen= new HashMap<>();
    private String vvodNumber;
    public GermanConverter(String vvodNumber) {
        this.vvodNumber = vvodNumber;
        zapoln();
    }
    private void zapoln() {
        edinitsy.put("eins", "1");
        edinitsy.put("zwei", "2");
        edinitsy.put("drei", "3");
        edinitsy.put("vier", "4");
        edinitsy.put("funf", "5");
        edinitsy.put("sechs", "6");
        edinitsy.put("sieben", "7");
        edinitsy.put("acht", "8");
        edinitsy.put("neun", "9");
        elevenNineteen.put("elf", "11");
        elevenNineteen.put("zwolf", "12");
        elevenNineteen.put("dreizehn", "13");
        elevenNineteen.put("vierzehn", "14");
        elevenNineteen.put("funfzehn", "15");
        elevenNineteen.put("sechzehn", "16");
        elevenNineteen.put("siebzehn", "17");
        elevenNineteen.put("achtzehn", "18");
        elevenNineteen.put("neunzehn", "19");
        desyatki.put("zehn", "10");
        desyatki.put("zwanzig", "20");
        desyatki.put("dreibig", "30");
        desyatki.put("vierzig", "40");
        desyatki.put("funfzig", "50");
        desyatki.put("sechzig", "60");
        desyatki.put("siebzig", "70");
        desyatki.put("achtzig", "80");
        desyatki.put("neunzig", "90");
    }
    public String convert() throws InterruptedException {
        var numbersString = vvodNumber.replaceAll("\\s+", "");
        var numbersArray = vvodNumber.replaceAll("\\s+", "").toCharArray();
        if (!numbersString.contains("hundert") && !numbersString.contains("und")) {
            return vvodIsEdinitsyDesyatkiOrElevenNinenteen(numbersString);
        } else {
            if (numbersString.contains("und") && !numbersString.contains("hundert")) {
                return vvodIsSostavnoeDesyatichnoye(numbersArray, numbersString);
            } else {
                if (numbersString.contains("hundert")) {
                    return vvodIsSotni(numbersArray, numbersString);
                }
            }
        }
        return "Нет такого числа";
    }
    private String vvodIsEdinitsyDesyatkiOrElevenNinenteen(String numbersString) {
        if (edinitsy.containsKey(numbersString)) {
            return edinitsy.get(numbersString);
        } else {
            if (desyatki.containsKey(numbersString)) {
                return desyatki.get(numbersString);
            } else {
                return elevenNineteen.getOrDefault(numbersString, "Нет такого числа");
            }
        }
    }
    private String vvodIsSostavnoeDesyatichnoye(char[] numbersArray, String numbersString) {
        int i = 0;
        String slovo = "";
        while (!slovo.contains("und")) {
            slovo+=numbersArray[i];
            i++;
        }
        if (edinitsy.containsKey(slovo.replace("und", ""))) {
            if (desyatki.containsKey(numbersString.replace(slovo, ""))) {
                return desyatki.get(numbersString.replace(slovo, "")).replace("0", "") + edinitsy.get(slovo.replace("und", ""));
            }
        }
        return "Нет такого числа";
    }
    private String vvodIsSotni(char[] numbersArray, String numbersString) {
        if (edinitsy.containsKey(numbersString.replace("hundert", ""))) {
            return edinitsy.get(numbersString.replace("hundert", "")) + "00";
        } else {
            return vvodIsSotniSostavnoye(numbersArray, numbersString);
        }
    }
    private String vvodIsSotniSostavnoye(char[] numbersArray, String numbersString) {
        String slovo = "";
        int i = 0;
        while (!slovo.contains("hundert")) {
            slovo += numbersArray[i];
            i++;
        }
        if (edinitsy.containsKey(slovo.replace("hundert", ""))) {
            if (edinitsy.containsKey(numbersString.replace(slovo, ""))) {
                return edinitsy.get(slovo.replace("hundert", "")) + "0" +
                        edinitsy.get(numbersString.replace(slovo, ""));
            }
            if (elevenNineteen.containsKey(numbersString.replace(slovo, ""))) {
                return edinitsy.get(slovo.replace("hundert", "")) + elevenNineteen.get(numbersString.replace(slovo, ""));
            } else {
                if (desyatki.containsKey(numbersString.replace(slovo, ""))) {
                    return edinitsy.get(slovo.replace("hundert", "")) + desyatki.get(numbersString.replace(slovo, ""));
                } else {
                    if (numbersString.replace(slovo, "").contains("und")) {
                        return vvodIsSotniDesyatkiEdinitsy(numbersString, slovo);
                    }
                }
            }
        }
        return "Нет такого числа";
    }
    private String vvodIsSotniDesyatkiEdinitsy(String numbersString, String slovo) {
        var wordWithoutSotni = numbersString.replace(slovo, "").toCharArray();
        String edinitsWord = "";
        int i = 0;
        while (!edinitsWord.contains("und")) {
            edinitsWord += wordWithoutSotni[i];
            i++;
        }
        if (edinitsy.containsKey(edinitsWord.replace("und", "")) &&
                desyatki.containsKey(numbersString.replace(slovo, "").replace(edinitsWord, ""))) {
            return edinitsy.get(slovo.replace("hundert", "")) +
                    desyatki.get(numbersString.replace(slovo, "").replace(edinitsWord, "")).replace("0", "") +
                    edinitsy.get(edinitsWord.replace("und", ""));
        }
        return "Нет такого числа";
    }

}
