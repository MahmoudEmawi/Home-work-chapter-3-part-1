/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ass3;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author Mahmoud Emawi
 */
public class question_2 {

    public static void main(String[] args) throws Exception {
        
        Scanner input = new Scanner(new File("file.txt"));
        HashMap<String, Integer> string = new HashMap<String, Integer>();
        
        while (input.hasNext()) {
            String character = input.next();
            if (string.containsKey(character)) {
                string.put(character, string.get(character) + 1);
            } else {
                string.put(character, 1);
            }
        }
        for (String k : string.keySet()) {
            int value = string.get(k);
            System.out.println(k + " : " + value);
            HashMap<Character, Integer> chars = new HashMap<Character, Integer>();
            for (int i = 0; i < k.length(); i++) {
                char ch = k.charAt(i);
                if (chars.containsKey(ch)) {
                    chars.put(ch, chars.get(ch) + 1);
                } else {
                    chars.put(ch, 1);
                }
            }
            for (Character c : chars.keySet()) {
                System.out.println("\t" +c + " : " + chars.get(c));
            }
        }
    }
}

