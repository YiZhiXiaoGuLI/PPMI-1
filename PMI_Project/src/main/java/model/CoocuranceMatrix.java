package model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.stream.Stream;

public class CoocuranceMatrix {

    // słowa bez powtorzen - slownik
    static public List<WordWithPosition> createLiblary(List<WordWithPosition> allwords)
    {
        List<WordWithPosition> deduplicateWords = new ArrayList<>(new LinkedHashSet<>(allwords));
       return deduplicateWords;

    }

    //tworzenie macierzy coocurencji
   public int[][] createCoocurance(List<WordWithPosition> allwords)
    {
        int x =0;
        int y =0;
        List<Key> keys = new ArrayList<Key>();

        for(int i=0; i<allwords.size()-1; i++)
        {
            Key key = new Key();
            key.setSmallKey1(allwords.get(i).getWord());
            key.setSmallKey2(allwords.get(i+1).getWord());
            keys.add(key);

        }
        //0 i 1
        List<WordWithPosition> deduplicateWords =createLiblary(allwords);
        int len = deduplicateWords.size();
        int[][] matrix = new int [len][len];

        for(int i=0; i<keys.size(); i++)
        {
            x= fingPosition(keys.get(i).getSmallKey1(),deduplicateWords);
            y= fingPosition(keys.get(i).getSmallKey2(),deduplicateWords);
            matrix[x][y]++;
        }





        printMatrix(matrix,len);

        return matrix;
    }



    static int fingPosition(String key, List<WordWithPosition> allwords)
    {
        int wynik = 0;
        for(int i=0; i<allwords.size(); i++)
        {
            if(key.equals(allwords.get(i).getWord()))
            {

                wynik = i;
                break;
            }
        }
        return wynik;
    }

    void printMatrix(int[][] matrix, int len)
    {
        for(int i =0; i<len;i++)
        {
            for (int j=0; j<len; j++) {
                System.out.print(matrix[i][j]);
            }
            System.out.println("");
        }

        System.out.println(len);

    }

}
