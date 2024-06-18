package com.example;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;

public class Translate {
    public Scene scene(){
        FlowPane fPane = new FlowPane();
        fPane.setStyle("-fx-padding: 10px;");

        Label label = new Label("Sentence to translate :");
        fPane.getChildren().add(label);        
        label.setStyle("-fx-padding: 0 10 0 0;");

        TextField text = new TextField("nom");
        fPane.getChildren().add(text);

        Label cheat = new Label("");
        fPane.getChildren().add(cheat);        
        cheat.setStyle("-fx-padding: 15px;");

        Button button = new Button("Translate");
        fPane.getChildren().add(button);

        Label inputLabel = new Label("Input mode :");
        fPane.getChildren().add(inputLabel);        
        inputLabel.setStyle("-fx-padding: 0 10 0 0;");

        ChoiceBox<String> choiceBox = new ChoiceBox<>();
        choiceBox.getItems().addAll("Text", "Hexadecimal", "Decimal", "Octal", "Binary");
        choiceBox.setValue("Text");
        fPane.getChildren().add(choiceBox);

        Label cheat2 = new Label("");
        fPane.getChildren().add(cheat2);        
        cheat2.setStyle("-fx-padding: 0 200 0 0;");

        Label translateLabel = new Label("Translate mode :");
        fPane.getChildren().add(translateLabel);        
        translateLabel.setStyle("-fx-padding: 0 10 0 0;");

        ChoiceBox<String> choiceBox2 = new ChoiceBox<>();
        choiceBox2.getItems().addAll("Text", "Hexadecimal", "Decimal", "Octal", "Binary");
        choiceBox2.setValue("Text");
        fPane.getChildren().add(choiceBox2);

        Label cheat3 = new Label("");
        fPane.getChildren().add(cheat3);        
        cheat3.setStyle("-fx-padding: 0 180 0 0;");

        Label answer = new Label("sdfgzedrf");
        fPane.getChildren().add(answer);        
        answer.setStyle("-fx-top: 60vh;");
        
        button.setOnAction(event -> {
            String translatedText = textTranslate(text.getText(), choiceBox.getValue(), choiceBox2.getValue());
            if (translatedText != "&"){
                answer.setText(translatedText);
            }
            else if (translatedText == "&"){
                answer.setText("Error : input doesn't match mode selected");
            }
        });
        
        Scene scene = new Scene(fPane, 400, 200);
        return scene;
    }

    private String textTranslate(String text, String input, String translate){
        String answer = "";

        if (!verify(text, input, translate)){
            return "&";
        }

        if (input == translate){
            return text;
        }

        // translate to decimal
        String temp = "";
        if (input == "Text"){
            if (translate == "Hexadecimal"){
                return textToHexa(text);
            }
            else if (translate == "Decimal"){
                return textToDeci(text);
            }
            else if (translate == "Octal"){
                return textToOcta(text);
            }
            else if (translate == "Binary"){
                return textToBina(text);
            }
        }
        else if (translate == "Text"){
            if (input == "Hexadecimal"){
                return hexaToText(text);
            }
            else if (input == "Decimal"){
                return deciToText(text);
            }
            else if (input == "Octal"){
                return octaToText(text);
            }
            else if (input == "Binary"){
                return binaToText(text);
            }
        }
        else{
            if (input == "Hexadecimal"){
                temp = hexaToDeci(text);
            }
            else if (input == "Decimal"){
                temp = text;
            }
            else if (input == "Octal"){
                temp = octaToDeci(text);
            }
            else if (input == "Binary"){
                temp = binaToDeci(text);
            }
        }
        

        // decimal to destination type
        answer = temp;
        if (translate == "Hexadecimal"){
            answer = deciToHexa(temp);
        }
        else if (translate == "Octal"){
            answer = deciToOcta(temp);
        }
        else if (translate == "Binary"){
            answer = deciToBina(temp);
        }

        return answer;
    }

    private boolean verify(String text, String input, String translate){
        boolean b = true;
        if (input == "Text"){
            for (int i = 0; i < text.length(); i++) {
                if (!Character.isLetter(text.charAt(i)) && text.charAt(i) != ' '){
                    b = false;
                }
            }
        }
        else if (input == "Hexadecimal"){
            int space = 1;
            for (int i = 0; i < text.length(); i++) {
                if (!Character.isDigit(text.charAt(i)) && text.charAt(i) != 'A' && text.charAt(i) != 'B' && text.charAt(i) != 'C' && text.charAt(i) != 'D' && text.charAt(i) != 'E' && text.charAt(i) != 'F'){
                    if (text.charAt(i) != ' '){
                        b = false;
                    }
                }
                else if (space == 3 && text.charAt(i) != ' ' && translate == "Text"){
                    b = false;
                }

                if (space == 3){
                    space = 0;
                }
                space++;
            }
        }
        else if (input == "Decimal"){
            int space = 1;
            for (int i = 0; i < text.length(); i++) {
                if (!Character.isDigit(text.charAt(i)) && text.charAt(i) != ' '){
                    b = false;
                }
                else if (space == 4 && text.charAt(i) != ' ' && translate == "Text"){
                    b = false;
                }

                if (space == 4){
                    space = 0;
                }
                space++;
            }
        }
        else if (input == "Octal"){
            int space = 1;
            for (int i = 0; i < text.length(); i++) {
                if (!Character.isDigit(text.charAt(i))){
                    if (text.charAt(i) != ' '){
                        b = false;
                    }
                }
                else if (Integer.parseInt(String.valueOf(text.charAt(i))) > 7){
                    b = false;
                }
                else if (space == 4 && text.charAt(i) != ' ' && translate == "Text"){
                    b = false;
                }

                if (space == 4){
                    space = 0;
                }
                space++;
            }
        }
        else if (input == "Binary"){
            int space = 1;
            for (int i = 0; i < text.length(); i++) {
                if (text.charAt(i) != '0' && text.charAt(i) != '1'){
                    if (text.charAt(i) != ' '){
                        b = false;
                    }
                }
                else if (space == 9 && translate == "Text"){
                    b = false;
                }

                if (space == 9){
                    space = 0;
                }
                space++;
            }
        }

        return b;
    }

    private String textToHexa(String input){
        byte[] buf = input.getBytes();
        char[] chars = new char[3 * buf.length];
        char[] HEX_CHARS = "0123456789ABCDEF".toCharArray();
        for (int i = 0; i < buf.length; ++i)
        {
            chars[3 * i] = HEX_CHARS[(buf[i] & 0xF0) >>> 4];
            chars[3 * i + 1] = HEX_CHARS[buf[i] & 0x0F];
            chars[3 * i + 2] = ' ';
        }
        return new String(chars);
    }

    private String textToDeci(String text){
        String s = "";
        for (int i = 0; i < text.length(); i++) {
            char c = text.charAt(i);
            int x = c;
            s += x + " ";
        }
        return s;
    }

    private String textToOcta(String text){
        String str = text;
        String toOctal ="";
        for(char c : str.toCharArray()){
            toOctal += Integer.toOctalString(c)+" ";
        }
        return toOctal;
    }

    private String textToBina(String text){
        String str = text;
        String result = "";
        char[] messChar = str.toCharArray();

        for (int i = 0; i < messChar.length; i++) {
            result += Integer.toBinaryString(messChar[i]) + " ";
        }

        return result;
    }

    private String hexaToText(String text){
        String n = "";
        String answer = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' '){
                n += String.valueOf(text.charAt(i));
            }
            else{
                answer += unHex(n);
                n = "";
            }
        }
        if (n != ""){
            answer += unHex(n);
        }
        return answer;
    }

    private String unHex(String arg) {        

        String str = "";
        for(int i=0;i<arg.length();i+=2)
        {
            String s = arg.substring(i, (i + 2));
            int decimal = Integer.parseInt(s, 16);
            str = str + (char) decimal;
        }       
        return str;
    }

    private String deciToText(String text){
        String n = "";
        String answer = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' '){
                n += String.valueOf(text.charAt(i));
            }
            else{
                answer += unDeci(n);
                n = "";
            }
        }
        if (n != ""){
            answer += unDeci(n);
        }
        return answer;
    }

    private String unDeci(String arg) {        

        String str = "";
        for(int i=0;i<arg.length();i+=3)
        {
            String s = arg.substring(i, (i + 3));
            int decimal = Integer.parseInt(s, 10);
            str = str + (char) decimal;
        }       
        return str;
    }

    private String octaToText(String text){
        String n = "";
        String answer = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' '){
                n += String.valueOf(text.charAt(i));
            }
            else{
                answer += unOcta(n);
                n = "";
            }
        }
        if (n != ""){
            answer += unOcta(n);
        }
        return answer;
    }

    private String unOcta(String arg) {        

        String str = "";
        for(int i=0;i<arg.length();i+=3)
        {
            String s = arg.substring(i, (i + 3));
            int decimal = Integer.parseInt(s, 8);
            str = str + (char) decimal;
        }       
        return str;
    }

    private String binaToText(String text){
        String n = "";
        String answer = "";
        for (int i = 0; i < text.length(); i++) {
            if (text.charAt(i) != ' '){
                n += String.valueOf(text.charAt(i));
            }
            else{
                answer += unBina(n);
                n = "";
            }
        }
        if (n != ""){
            answer += unBina(n);
        }
        return answer;
    }

    private String unBina(String arg) {        

        String str = "";
        for(int i=0;i<arg.length();i+=8)
        {
            String s = arg.substring(i, (i + 8));
            int decimal = Integer.parseInt(s, 2);
            str = str + (char) decimal;
        }       
        return str;
    }

    private String hexaToDeci(String text){
        int n = 0;
        for (int i = text.length()-1; i >= 0; i--) {
            char c = text.charAt(i);
            int x = 0;
            if (c == 'A'){
                x = 10;
            }
            else if (c == 'B'){
                x = 11;
            }
            else if (c == 'C'){
                x = 12;
            }
            else if (c == 'D'){
                x = 13;
            }
            else if (c == 'E'){
                x = 14;
            }
            else if (c == 'F'){
                x = 15;
            }
            else{
                x = Integer.parseInt(String.valueOf(c));
            }
            n += Math.pow(16, text.length()-i-1) * x;
        }
        return n + "";
    }

    private String octaToDeci(String text){
        String s = "";
        int n = -1;
        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) != ' '){
                if (n == -1){
                    n = Integer.parseInt(String.valueOf(text.charAt(i)));
                }
                else{
                    n*=10;
                    n += Integer.parseInt(String.valueOf(text.charAt(i)));
                }
            }
            else{
                s += getDecimal(n) + " ";
                n = -1;
            }
        }
        if (n != -1){
            s += getDecimal(n);
        }

        return s;
    }

    private int getDecimal(int octal){    
        //Declaring variable to store decimal number  
        int decimal = 0;    
        //Declaring variable to use in power  
        int n = 0;    
        //writing logic   
        while(true){    
            if(octal == 0){    
                break;    
            } else {    
                int temp = octal%10;    
                decimal += temp*Math.pow(8, n);    
                octal = octal/10;    
                n++;    
            }    
        }    
        return decimal;    
    }

    private String binaToDeci(String text){
        int n = 0;
        for (int i = text.length()-1; i >= 0; i--) {
            char c = text.charAt(i);
            int x = Integer.parseInt(String.valueOf(c));
            n += Math.pow(2, text.length()-i-1) * x;
        }
        return String.valueOf(n);
    }

    private String deciToHexa(String text){
        String s = "";
        int n = -1;
        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) != ' '){
                if (n == -1){
                    n = Integer.parseInt(String.valueOf(text.charAt(i)));
                }
                else{
                    n*=10;
                    n += Integer.parseInt(String.valueOf(text.charAt(i)));
                }
            }
            else{
                s += toHex(n) + " ";
                n = -1;
            }
        }
        if (n != -1){
            s += toHex(n);
        }

        return s;
    }

    public static String toHex(int decimal){    
        int rem;  
        String hex="";   
        char hexchars[]={'0','1','2','3','4','5','6','7','8','9','A','B','C','D','E','F'};  
        while(decimal>0)  
            {  
            rem=decimal%16;   
            hex=hexchars[rem]+hex;   
            decimal=decimal/16;  
            }  
        return hex;  
    }

    private String deciToOcta(String text){
        String s = "";
        int n = -1;
        for (int i = 0; i < text.length(); i++){
            if (text.charAt(i) != ' '){
                if (n == -1){
                    n = Integer.parseInt(String.valueOf(text.charAt(i)));
                }
                else{
                    n*=10;
                    n += Integer.parseInt(String.valueOf(text.charAt(i)));
                }
            }
            else{
                s += toOctal(n) + " ";
                n = -1;
            }
        }
        if (n != -1){
            s += toOctal(n);
        }

        return s;
    }

    private String toOctal(int decimal){    
        int rem; //declaring variable to store remainder  
        String octal=""; //declareing variable to store octal  
        //declaring array of octal numbers  
        char octalchars[]={'0','1','2','3','4','5','6','7'};  
        //writing logic of decimal to octal conversion   
        while(decimal>0)  
        {  
            rem=decimal%8;   
            octal=octalchars[rem]+octal;   
            decimal=decimal/8;  
        }  
        return octal;  
    }

    private String deciToBina(String text){
        String s = "";
        int n = -1;
        for (int i = text.length()-1; i >= 0 ; i--){
            if (text.charAt(i) != ' '){
                if (n == -1){
                    n = Integer.parseInt(String.valueOf(text.charAt(i)));
                }
                else{
                    n*=10;
                    n += Integer.parseInt(String.valueOf(text.charAt(i)));
                }
            }
            else{
                s += Integer.toString(n, 2) + " ";
                n = -1;
            }
        }
        if (n != -1){
            s += Integer.toString(n, 2);
        }

        return s;
    }

    public static String toBinary(int decimal) {
        String binaryArray = "";

        while (decimal > 0) {
            binaryArray += decimal % 2;
            decimal /= 2;
        }

        return binaryArray;
    }
}
