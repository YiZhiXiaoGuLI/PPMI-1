package matrix;

public class Ctx {

    double multipleTopVectors(double[][] tab, int v1, int v2) {
        int quantitycolumn = tab[0].length;
        double wynik = 0;
        for (int i = 0; i < quantitycolumn; i++) {
            wynik += tab[v1][i] * tab[v2][i];
        }
        return wynik;

    }

    double calculateDenominator(double[][] tab, int v1, int v2) {
        int quantitycolumn = tab[0].length;
        double VR1 = 0;
        double VR2 = 0;

        for (int i = 0; i < quantitycolumn; i++) {
            VR1 += tab[v1][i] * tab[v1][i];
            VR2 += tab[v2][i] * tab[v2][i];
        }

        double w1 = Math.sqrt((double) VR1);
        double w2 = Math.sqrt((double) VR2);

        double wynik = w1 * w2;
        return wynik;
    }

    public double cosinusSimilarity(double[][] tab, int v1, int v2) {
        return (multipleTopVectors(tab, v1, v2)) / (calculateDenominator(tab, v1, v2));
    }
}
