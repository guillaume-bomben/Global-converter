package com.example;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Pane;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TranslateFilesScreen {
    private Stage stage;
    private File selectedFile;
    private TextArea fileContentTextArea;

    public TranslateFilesScreen(Stage stage) {
        this.stage = stage;
    }

    public void show(Stage primaryStage) {
        Pane root = new Pane();

        Button chooseFileButton = new Button("Choose File");
        chooseFileButton.setPrefSize(120, 10);
        chooseFileButton.setLayoutX((400 - 120) / 2);
        chooseFileButton.setLayoutY(50);

        ComboBox<String> sourceBaseCombo = new ComboBox<>();
        sourceBaseCombo.getItems().addAll("ASCII", "Binary", "Hexadecimal", "Decimal", "Octal");
        sourceBaseCombo.setValue("ASCII");
        sourceBaseCombo.setLayoutX(10);
        sourceBaseCombo.setLayoutY(50);

        ComboBox<String> targetBaseCombo = new ComboBox<>();
        targetBaseCombo.getItems().addAll("Binary", "Hexadecimal", "ASCII", "Decimal", "Octal");
        targetBaseCombo.setValue("Binary");
        targetBaseCombo.setLayoutX(270);
        targetBaseCombo.setLayoutY(50);

        Button convertButton = new Button("Convert");
        convertButton.setPrefSize(120, 10);
        convertButton.setLayoutX((400 - 120) / 2);
        convertButton.setLayoutY(200);
        convertButton.setDisable(true);

        fileContentTextArea = new TextArea();
        fileContentTextArea.setPrefSize(380, 100);
        fileContentTextArea.setLayoutX(10);
        fileContentTextArea.setLayoutY(80);
        fileContentTextArea.setEditable(false);

        Button backButton = new Button("Back to Menu");
        backButton.setLayoutX((400 - 120) /2);
        backButton.setLayoutY(250);
        backButton.setPrefSize(120, 10);

        chooseFileButton.setOnAction(e -> {
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Open Resource File");
            selectedFile = fileChooser.showOpenDialog(primaryStage);
            if (selectedFile != null) {
                convertButton.setDisable(false);  // Enable convert button when file is selected
                displayFileContent(selectedFile);
            }
        });

        backButton.setOnAction(e -> {
            Menu menu = new Menu();
            menu.show(primaryStage);
        });

        convertButton.setOnAction(e -> {
            String sourceBase = sourceBaseCombo.getValue();
            String targetBase = targetBaseCombo.getValue();
            if (selectedFile != null && sourceBase != null && targetBase != null) {
                try {
                    convertFile(selectedFile, sourceBase, targetBase);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        root.getChildren().addAll(chooseFileButton, sourceBaseCombo, targetBaseCombo, convertButton, fileContentTextArea, backButton);

        Scene scene = new Scene(root, 400, 300);
        primaryStage.setScene(scene);
    }

    private void displayFileContent(File file) {
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(file.getPath()));
            String content = new String(fileBytes, StandardCharsets.UTF_8);
            fileContentTextArea.setText(content);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void convertFile(File file, String sourceBase, String targetBase) throws IOException {
        byte[] fileBytes = Files.readAllBytes(Paths.get(file.getPath()));
        String content = new String(fileBytes, StandardCharsets.UTF_8);
        String convertedContent = "";

        switch (sourceBase) {
            case "ASCII":
                switch (targetBase) {
                    case "Binary":
                        convertedContent = asciiToBinary(content);
                        break;
                    case "Hexadecimal":
                        convertedContent = asciiToHexadecimal(content);
                        break;
                    case "Octal":
                        convertedContent = asciiToOctal(content);
                        break;
                    case "Decimal":
                        convertedContent = asciiToDecimal(content);
                        break;
                    default:
                        break;
                }
                break;
            case "Binary":
                switch (targetBase) {
                    case "ASCII":
                        convertedContent = binaryToAscii(content);
                        break;
                    case "Hexadecimal":
                        convertedContent = binaryToHexadecimal(content);
                        break;
                    case "Octal":
                        convertedContent = binaryToOctal(content);
                        break;
                    default:
                        break;
                }
                break;
            case "Hexadecimal":
                switch (targetBase) {
                    case "ASCII":
                        convertedContent = hexadecimalToAscii(content);
                        break;
                    case "Binary":
                        convertedContent = hexadecimalToBinary(content);
                        break;
                    case "Octal":
                        convertedContent = hexadecimalToOctal(content);
                        break;
                    default:
                        break;
                }
                break;
            case "Octal":
                switch (targetBase) {
                    case "ASCII":
                        convertedContent = octalToAscii(content);
                        break;
                    case "Binary":
                        convertedContent = octalToBinary(content);
                        break;
                    case "Hexadecimal":
                        convertedContent = octalToHexadecimal(content);
                        break;
                    default:
                        break;
                }
                break;
            case "Decimal":
                switch (targetBase) {
                    case "ASCII":
                        convertedContent = decimalToAscii(content);
                        break;
                    default:
                        break;
                }
                break;
            default:
                break;
        }

        String newFileName = file.getPath().replace(".txt", "_" + sourceBase + "_to_" + targetBase + ".txt");
        Files.write(Paths.get(newFileName), convertedContent.getBytes());
    }

    private String asciiToBinary(String input) {
        StringBuilder binary = new StringBuilder();
        for (char character : input.toCharArray()) {
            binary.append(String.format("%8s", Integer.toBinaryString(character)).replaceAll(" ", "0"));
        }
        return binary.toString();
    }

    private String asciiToHexadecimal(String input) {
        StringBuilder hex = new StringBuilder();
        for (char character : input.toCharArray()) {
            hex.append(String.format("%2s", Integer.toHexString(character)).replaceAll(" ", "0"));
        }
        return hex.toString();
    }

    private String asciiToOctal(String input) {
        StringBuilder octal = new StringBuilder();
        for (char character : input.toCharArray()) {
            octal.append(String.format("%3s", Integer.toOctalString(character)).replaceAll(" ", "0"));
        }
        return octal.toString();
    }

    private String asciiToDecimal(String input) {
        StringBuilder decimal = new StringBuilder();
        for (char character : input.toCharArray()) {
            decimal.append((int) character).append(" ");
        }
        return decimal.toString().trim();
    }

    private String binaryToAscii(String input) {
        StringBuilder ascii = new StringBuilder();
        for (int i = 0; i < input.length(); i += 8) {
            String chunk = input.substring(i, Math.min(input.length(), i + 8));
            int charCode = Integer.parseInt(chunk, 2);
            ascii.append((char) charCode);
        }
        return ascii.toString();
    }

    private String binaryToHexadecimal(String input) {
        StringBuilder hex = new StringBuilder();
        for (int i = 0; i < input.length(); i += 8) {
            String chunk = input.substring(i, Math.min(input.length(), i + 8));
            int charCode = Integer.parseInt(chunk, 2);
            hex.append(String.format("%2s", Integer.toHexString(charCode)).replaceAll(" ", "0"));
        }
        return hex.toString();
    }

    private String binaryToOctal(String input) {
        StringBuilder octal = new StringBuilder();
        for (int i = 0; i < input.length(); i += 8) {
            String chunk = input.substring(i, Math.min(input.length(), i + 8));
            int charCode = Integer.parseInt(chunk, 2);
            octal.append(String.format("%3s", Integer.toOctalString(charCode)).replaceAll(" ", "0"));
        }
        return octal.toString();
    }

    private String hexadecimalToAscii(String input) {
        StringBuilder ascii = new StringBuilder();
        for (int i = 0; i < input.length(); i += 2) {
            String hexPair = input.substring(i, Math.min(input.length(), i + 2));
            int charCode = Integer.parseInt(hexPair, 16);
            ascii.append((char) charCode);
        }
        return ascii.toString();
    }

    private String hexadecimalToBinary(String input) {
        StringBuilder binary = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String hexChar = String.valueOf(input.charAt(i));
            int nibble = Integer.parseInt(hexChar, 16);
            String nibbleBinary = String.format("%4s", Integer.toBinaryString(nibble)).replaceAll(" ", "0");
            binary.append(nibbleBinary);
        }
        return binary.toString();
    }

    private String hexadecimalToOctal(String input) {
        StringBuilder octal = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            String hexChar = String.valueOf(input.charAt(i));
            int nibble = Integer.parseInt(hexChar, 16);
            octal.append(String.format("%3s", Integer.toOctalString(nibble)).replaceAll(" ", "0"));
        }
        return octal.toString();
    }

    private String octalToAscii(String input) {
        StringBuilder ascii = new StringBuilder();
        String[] octalChunks = input.split("\\s+");
        for (String chunk : octalChunks) {
            int charCode = Integer.parseInt(chunk, 8);
            ascii.append((char) charCode);
        }
        return ascii.toString();
    }

    private String octalToBinary(String input) {
        StringBuilder binary = new StringBuilder();
        String[] octalChunks = input.split("\\s+");
        for (String chunk : octalChunks) {
            int charCode = Integer.parseInt(chunk, 8);
            binary.append(String.format("%3s", Integer.toBinaryString(charCode)).replaceAll(" ", "0"));
        }
        return binary.toString();
    }

    private String octalToHexadecimal(String input) {
        StringBuilder hex = new StringBuilder();
        String[] octalChunks = input.split("\\s+");
        for (String chunk : octalChunks) {
            int charCode = Integer.parseInt(chunk, 8);
            hex.append(String.format("%2s", Integer.toHexString(charCode)).replaceAll(" ", "0"));
        }
        return hex.toString();
    }

    private String decimalToAscii(String input) {
        StringBuilder ascii = new StringBuilder();
        String[] numbers = input.split("\\s+");
        for (String number : numbers) {
            int charCode = Integer.parseInt(number);
            ascii.append((char) charCode);
        }
        return ascii.toString();
    }
}


