package com.Demo.functions;

public class UpdateText {

    static public String insertToString(String originalString, String addedString, int pos) {
        StringBuilder stringBuilder = new StringBuilder(originalString);
        stringBuilder.insert(pos, addedString);
        return stringBuilder.toString();
    }

    static public String deleteFromString(String originalText, int length, int pos) {
        if (pos < 0 || pos >= originalText.length()) {
            System.out.println("Position is out of bounds.");
            return originalText;
        }
        
        int endPos = pos + length;
        if (endPos > originalText.length()) {
            endPos = originalText.length();
        }
                String deletedText = originalText.substring(0, pos) + originalText.substring(endPos);
        return deletedText;
    }
}
