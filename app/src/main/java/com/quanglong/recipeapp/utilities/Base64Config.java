package com.quanglong.recipeapp.utilities;

import java.util.ArrayList;
import java.util.List;

public class Base64Config {
    public static List<String> Base64Split(String base64Str){
        String input = base64Str;
        List<String> result = new ArrayList<>();
        try {
            int totalCharPerItem = 100000;
            if (input.length() % totalCharPerItem > 0 && input.length() > totalCharPerItem) {
                int totalItem = input.length() / totalCharPerItem;
                for (int i = 0; i < totalItem; i++) {

                    result.add(input.substring(totalCharPerItem * i, totalCharPerItem * (i + 1)));
                }
                result.add(input.substring(totalCharPerItem * totalItem, input.length()));
            } else {
                result.add(input);
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return result;
    }
}
