package com.xx1ee;

import java.util.*;

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
        var numbersArray = vvodNumber.split("\\s+");
        if (numbersArray.length == 1) {
            if (edinitsy.containsKey(numbersString)) {
                return edinitsy.get(numbersString);
            } else {
                if (elevenNineteen.containsKey(numbersString)) {
                    return elevenNineteen.get(numbersString);
                } else {
                    return desyatki.getOrDefault(numbersString, "Нет такого числа " + numbersString);
                }
            }
        } else {
            if (numbersArray.length == 2) {
                List<String> oshibki = new ArrayList<>();
                if (!edinitsy.containsKey(numbersArray[0])) {
                    oshibki.add(numbersArray[0]);
                }
                if (!numbersArray[1].equals("hundert")) {
                    oshibki.add(numbersArray[1]);
                }
                if (oshibki.isEmpty()) {
                    return edinitsy.get(numbersArray[0]) + "00";
                } else {
                    if (oshibki.size() == 1) {
                        return "Нет такого слова " + oshibki.get(0);
                    } else {
                        return "Нет таких слов " + oshibki.get(0) + " " + oshibki.get(1);
                    }
                }
            } else {
                if (numbersArray.length == 3) {
                    if (desyatki.containsKey(numbersArray[2]) && edinitsy.containsKey(numbersArray[0])
                    && numbersArray[1].equals("und")) {
                        return desyatki.get(numbersArray[2]).replace("0", "") + edinitsy.get(numbersArray[0]);
                    } else {
                        if (edinitsy.containsKey(numbersArray[2]) && edinitsy.containsKey(numbersArray[0])
                                && numbersArray[1].equals("hundert")) {
                            return edinitsy.get(numbersArray[0]) + "0" + edinitsy.get(numbersArray[2]);
                        } else {
                            if (elevenNineteen.containsKey(numbersArray[2]) && edinitsy.containsKey(numbersArray[0])
                                    && numbersArray[1].equals("hundert")) {
                                return edinitsy.get(numbersArray[0]) + elevenNineteen.get(numbersArray[2]);
                            } else {
                                List<String> oshibki = new ArrayList<>();
                                if (!edinitsy.containsKey(numbersArray[0])) {
                                    oshibki.add(numbersArray[0]);
                                }
                                if (!numbersArray[1].equals("hundert") && !numbersArray[1].equals("und")) {
                                    oshibki.add(numbersArray[1]);
                                }
                                if (!desyatki.containsKey(numbersArray[2]) && !edinitsy.containsKey(numbersArray[2]) &&
                                !elevenNineteen.containsKey(numbersArray[2])) {
                                    oshibki.add(numbersArray[2]);
                                }
                                if (!oshibki.isEmpty()) {
                                    if (oshibki.size() == 1) {
                                        return "Нет такого слова " + oshibki.get(0);
                                    } else {
                                        String o = "Нет таких слов ";
                                        for (String s : oshibki) {
                                            o+=s;
                                            o+=" ";
                                        }
                                        return o;
                                    }
                                }
                            }
                        }
                    }
                } else {
                    if (numbersArray.length == 5) {
                        if (numbersArray[1].equals("hundert") && numbersArray[3].equals("und")
                        && edinitsy.containsKey(numbersArray[0]) && edinitsy.containsKey(numbersArray[2]) && desyatki.containsKey(numbersArray[4])) {
                            return edinitsy.get(numbersArray[0]) + desyatki.get(numbersArray[4]).replace("0", "") + edinitsy.get(numbersArray[2]);
                        } else {
                            List<String> oshibki = new ArrayList<>();
                            if (!numbersArray[1].equals("hundert")) {
                                oshibki.add(numbersArray[1]);
                            }
                            if (!numbersArray[3].equals("und")) {
                                oshibki.add(numbersArray[3]);
                            }
                            if (!edinitsy.containsKey(numbersArray[0])) {
                                oshibki.add(numbersArray[0]);
                            }
                            if (!edinitsy.containsKey(numbersArray[2])) {
                                oshibki.add(numbersArray[2]);
                            }
                            if (!desyatki.containsKey(numbersArray[4])) {
                                oshibki.add(numbersArray[4]);
                            }
                            if (!oshibki.isEmpty()) {
                                if (oshibki.size() == 1) {
                                    return "Нет такого слова " + oshibki.get(0);
                                } else {
                                    String o = "Нет таких слов ";
                                    for (String s : oshibki) {
                                        o+=s;
                                        o+=" ";
                                    }
                                    return o;
                                }
                            }
                        }
                    }
                }
            }
        }
        return "";
    }

}
