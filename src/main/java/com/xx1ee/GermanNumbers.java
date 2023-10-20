package com.xx1ee;

import java.util.HashMap;
import java.util.Map;

public class GermanNumbers {
    public static Map<String, Integer> unit = new HashMap<>();
    public static Map<String, Integer> eleven_nineteen = new HashMap<>();
    public static Map<String, Integer> tens = new HashMap<>();
    public GermanNumbers() {
        unit.put("eins", 1);
        unit.put("zwei", 2);
        unit.put("drei", 3);
        unit.put("vier", 4);
        unit.put("funf", 5);
        unit.put("sechs", 6);
        unit.put("sieben", 7);
        unit.put("acht", 8);
        unit.put("neun", 9);
        eleven_nineteen.put("elf", 11);
        eleven_nineteen.put("zwolf", 12);
        eleven_nineteen.put("dreizehn", 13);
        eleven_nineteen.put("vierzehn", 14);
        eleven_nineteen.put("funfzehn", 15);
        eleven_nineteen.put("sechzehn", 16);
        eleven_nineteen.put("siebzehn", 17);
        eleven_nineteen.put("achtzehn", 18);
        eleven_nineteen.put("neunzehn", 19);
        tens.put("zehn", 10);
        tens.put("zwanzig", 20);
        tens.put("dreibig", 30);
        tens.put("vierzig", 40);
        tens.put("funfzig", 50);
        tens.put("sechzig", 60);
        tens.put("siebzig", 70);
        tens.put("achtzig", 80);
        tens.put("neunzig", 90);
    }
}
