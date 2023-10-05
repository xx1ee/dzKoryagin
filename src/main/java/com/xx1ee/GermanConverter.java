package com.xx1ee;

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
        desyatki.put("zehn", "10");
        elevenNineteen.put("elf", "11");
        elevenNineteen.put("zwolf", "12");
        elevenNineteen.put("dreizehn", "13");
        elevenNineteen.put("vierzehn", "14");
        elevenNineteen.put("funfzehn", "15");
        elevenNineteen.put("sechzehn", "16");
        elevenNineteen.put("siebzehn", "17");
        elevenNineteen.put("achtzehn", "18");
        elevenNineteen.put("neunzehn", "19");
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
        String otvet = "";
        String[] arrayOfNumber = vvodNumber.trim().split("\\s+");
        if (arrayOfNumber.length == 1 && !vvodNumber.contains("hundert")) {
            if (edinitsy.containsKey(arrayOfNumber[0])) {
                return edinitsy.get(arrayOfNumber[0]);
            } else {
                if (desyatki.containsKey(arrayOfNumber[0])) {
                    return desyatki.get(arrayOfNumber[0]);
                } else return elevenNineteen.getOrDefault(arrayOfNumber[0], "Нет такого числа");
            }
        } else {
            if (!vvodNumber.contains("hundert")) {
                arrayOfNumber[0] = arrayOfNumber[0].substring(0, arrayOfNumber[0].length() - 3);
                if (arrayOfNumber[0].equals("ein")) {
                    arrayOfNumber[0]+="s";
                }
                if (desyatki.containsKey(arrayOfNumber[1]) && edinitsy.containsKey(arrayOfNumber[0])) {
                    otvet+=desyatki.get(arrayOfNumber[1]).substring(0,1);
                    otvet+=edinitsy.get(arrayOfNumber[0]);
                    return otvet;
                } else {
                    return "Нет такого числа";
                }
            } else {
                if (vvodNumber.contains("hundert")) {
                    if (arrayOfNumber.length == 1) {
                        arrayOfNumber[0] = arrayOfNumber[0].substring(0, arrayOfNumber[0].length() - 7);
                        if (arrayOfNumber[0].equals("ein")) {
                            arrayOfNumber[0]+="s";
                        }
                        otvet+=edinitsy.get(arrayOfNumber[0]);
                        otvet+="00";
                        return otvet;
                    } else if (arrayOfNumber.length == 2) {
                        arrayOfNumber[0] = arrayOfNumber[0].substring(0, arrayOfNumber[0].length() - 7);
                        if (arrayOfNumber[0].equals("ein")) {
                            arrayOfNumber[0]+="s";
                        }
                        if (edinitsy.containsKey(arrayOfNumber[0]) && edinitsy.containsKey(arrayOfNumber[1])) {
                            otvet+=edinitsy.get(arrayOfNumber[0]);
                            otvet+="0";
                            otvet+=edinitsy.get(arrayOfNumber[1]);
                            return otvet;
                        } else {
                            if (desyatki.containsKey(arrayOfNumber[1]) && edinitsy.containsKey(arrayOfNumber[0])) {
                                otvet+=edinitsy.get(arrayOfNumber[0]);
                                otvet+=desyatki.get(arrayOfNumber[1]);
                                return otvet;
                            } else {
                                return "Нет такого числа";
                            }
                        }
                    } else if (arrayOfNumber.length == 3) {
                        arrayOfNumber[0] = arrayOfNumber[0].substring(0, arrayOfNumber[0].length() - 7);
                        arrayOfNumber[1] = arrayOfNumber[1].substring(0, arrayOfNumber[1].length() - 3);
                        if (arrayOfNumber[1].equals("ein")) {
                            arrayOfNumber[1]+="s";
                        }
                        if (arrayOfNumber[0].equals("ein")) {
                            arrayOfNumber[0]+="s";
                        }
                        if (edinitsy.containsKey(arrayOfNumber[0]) && edinitsy.containsKey(arrayOfNumber[1])
                                && desyatki.containsKey(arrayOfNumber[2])) {
                            otvet+=edinitsy.get(arrayOfNumber[0]);
                            otvet+=desyatki.get(arrayOfNumber[2]).substring(0,1);
                            otvet+=edinitsy.get(arrayOfNumber[1]);
                            return otvet;
                        } else return "Нет такого числа";
                    }
                }
            }
        }
        return "Нет такого числа";
    }
}
