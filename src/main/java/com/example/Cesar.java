package com.example;

import java.util.ArrayList;
import java.util.Arrays;

public class Cesar {
    private final ArrayList<Character> alphabet = new ArrayList<>(Arrays.asList('a', 'b', 'c', 'd', 'e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'));
    
    public String Encrypt_And_Decrypt(String type, String text, int key) {
        StringBuilder result = new StringBuilder();
        if (type.equals("Encrypt")) {
            for (char character : text.toCharArray()) {
                if (alphabet.contains(character)) {
                    int index = alphabet.indexOf(character);
                    int newIndex = (index + key) % 26;
                    result.append(alphabet.get(newIndex));
                } else {
                    result.append(character); // Caractères non alphabétiques
                }
            }
        } else if (type.equals("Decrypt")) {
            for (char character : text.toCharArray()) {
                if (alphabet.contains(character)){
                    int index = alphabet.indexOf(character);
                    int newIndex = (index - key) % 26;
                    if (newIndex < 0) {
                        newIndex = 26 + newIndex;
                    }
                    result.append(alphabet.get(newIndex));
                } else {
                    result.append(character); // Caractères non alphabétiques
                }
            }
        }
        System.out.println(result.toString());
        return result.toString();
    }

    public boolean isValidInput(String text) {
        return text.matches("[a-zA-Z ]+");
    }
}
