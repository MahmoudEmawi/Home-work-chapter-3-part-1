package ass3;

import java.util.*;

import java.lang.*;

import java.io.*;

/**
 *
 * @author Mahmoud Emawi
 */
public class question_1 {

    public static void main(String[] args) throws java.lang.Exception {

        Random rand = new Random(100);
        LinkedList<Integer> list = new LinkedList<Integer>();

        for (int i = 0; i < 30; i++) {
            list.add(rand.nextInt(100));
        }       
        System.out.println("Elements before sorting : ");
        System.out.println(list);

        Collections.sort(list);
        System.out.println("Elements afte sorting : ");
        System.out.println(list);
        
        int sum = 0;
        for (Integer n : list) {
            sum = sum + n;
        }
        System.out.println("Sum of the elements : ");
        System.out.println(sum);

        int avg=0;
        for ( Integer n : list ) {
            avg =sum / (n+1);
        }
        System.out.println("Average of the elements : ");
        System.out.println(avg);
    }
}


