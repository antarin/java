package com.mazsi;

import java.util.HashMap;
import java.util.Map;

public class MapProgram {

    public static void main(String[] args) {

        Map<String, String> language = new HashMap<>();

        language.put("Java", "egy magas szintű, objektum-orientált programnyelv");
        language.put("Python", "egy magas szintű értelmező, objektum-orientált programnyelv dinamikus mintákkal");
        language.put("Algol", "egy algoritmikus programnyelv");
        language.put("BASIC", "Kezdőknek");
        language.put("Lisp", "Ez egy rémálom...");

        System.out.println(language.get("Java"));
        language.put("Java", "Ez a tanfolyam a javaról szól");

        System.out.println(language.get("Java"));

        for(String key : language.keySet()) {
            System.out.println(key + " : " + language.get(key));
        }
    }
}
