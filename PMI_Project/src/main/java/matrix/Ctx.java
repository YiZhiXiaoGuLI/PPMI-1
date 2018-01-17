package matrix;

public class Ctx {

    float multipleTopVectors(float[][] tab, int v1, int v2)
    {
        int quantitycolumn = tab[0].length;
        float wynik =0;
        for(int i=0; i<quantitycolumn;i++)
        {
            wynik += tab[v1][i]*tab[v2][i];
        }
        return wynik;

    }

    float calculateDenominator(float[][] tab, int v1, int v2)
    {
        int quantitycolumn = tab[0].length;
        float VR1 =0;
        float VR2 =0;

        for(int i=0; i<quantitycolumn;i++)
        {
            VR1 += tab[v1][i]*tab[v1][i];
            VR2 += tab[v2][i]*tab[v2][i];
        }

        double w1 = Math.sqrt((double)VR1);
        double w2 = Math.sqrt((double)VR2);

        float wynik = (float)w1 * (float)w2;
        return wynik;
    }

    float cosinusSimilarity(float[][] tab, int v1, int v2)
    {
        return  (multipleTopVectors(tab, v1,  v2))/(calculateDenominator(tab, v1, v2));
    }
}
