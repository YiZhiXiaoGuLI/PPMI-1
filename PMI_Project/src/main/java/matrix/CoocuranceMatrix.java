package matrix;

import dataset.model.Key;
import dataset.model.Word;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class CoocuranceMatrix {

    // słowa bez powtorzen - slownik
   static public List<Word> createLiblary(List<Word> allwords) {
       return allwords.stream().distinct().collect(Collectors.toList());
    }

    //tworzenie macierzy coocurencji
   public short[][] createCoocurance(List<Word> allwords)
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
        List<Word> deduplicateWords =createLiblary(allwords);
        int len = deduplicateWords.size();
        short[][] matrix = new short [len][len];

        for(int i=0; i<keys.size(); i++)
        {
            x= findPosition(keys.get(i).getSmallKey1(),deduplicateWords);
            y= findPosition(keys.get(i).getSmallKey2(),deduplicateWords);
            matrix[x][y]++;
        }





      //  printMatrix(matrix,len);

        return matrix;
    }



    public static int findPosition(String key, List<Word> deduplicatewords)
    {
        int wynik = 0;
        for(int i=0; i<deduplicatewords.size(); i++)
        {
            if(key.equals(deduplicatewords.get(i).getWord()))
            {

                wynik = i;
                break;
            }
        }
        return wynik;
    }

    public void printMatrix(short[][] matrix, int len)
    {
        for(int i =0; i<len;i++)
        {
            for (int j=0; j<len; j++) {
                if(matrix[i][j]>0)
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("");
        }

        System.out.println(len);

    }

}
