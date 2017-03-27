/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cipher;

/**
 *
 * @author brian
 */
import java.util.*;
import java.util.concurrent.LinkedBlockingDeque;


public class Cipher
{
    private Queue<Integer> keyQueueE;
    private Queue<Integer> keyQueueD;
    private int[] key;

    public Cipher(int... key)
    {
        this.key = key;
        this.keyQueueE = new LinkedBlockingDeque<Integer>();
        this.keyQueueD = new LinkedBlockingDeque<Integer>();

        for (int i = 0; i < key.length; i++)
        {
            this.keyQueueE.offer(key[i]);
            this.keyQueueD.offer(key[i]);
        }
    }


    public String encode(String message)
    {
        StringBuilder encoded = new StringBuilder();
        int key;

        for (int i = 0; i < message.length();i++)
        {
            key = this.keyQueueE.poll();
            encoded.append((char)(message.charAt(i)+key));
            this.keyQueueE.offer(key);
        }

        return encoded.toString();
    }



    public String decode(String encoded)
    {
        StringBuilder decoded = new StringBuilder();
        int key;
        for (int i = 0; i < encoded.length();i++)
        {
            key = this.keyQueueD.poll();
            decoded.append((char)(encoded.charAt(i) - key));
            this.keyQueueD.offer(key);
        }

        return decoded.toString();
    }


    public static void main(String args[])
    {
        System.out.println("**************  TESTING THE CIPHER  **************\n");
        Cipher cipher = new Cipher(5, 12, -3, 8, -9, 4, 10);
        String encoded = cipher.encode("All programmers are playwrights and all computers are lousy actors.");
        System.out.println("--->The original message encoded is:");
        System.out.println(encoded);
        String decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);

        encoded = cipher.encode("There is no elevator to success, You have to take the stairs...");
        System.out.println("\n--->The original message encoded is:");
        System.out.println(encoded);
        decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);

        cipher = new Cipher(3, 1, 7, 4, 2, 5);
        encoded = cipher.encode("knowledge is power");
        System.out.println("\n--->The original message encoded is:");
        System.out.println(encoded);
        decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);

        cipher = new Cipher(3, 1, 7, 4, 2, 5);
        encoded = cipher.encode("race car");
        System.out.println("\n--->The original message encoded is:");
        System.out.println(encoded);
        decoded = cipher.decode(encoded);
        System.out.println("--->The original message decoded is:");
        System.out.println(decoded);

    }
} // end SpecialStack

