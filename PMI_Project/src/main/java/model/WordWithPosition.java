package model;

public class WordWithPosition {

    private int x;
    private int y;
    private double MPIvalue;
    private String word;

    public WordWithPosition(double MPIvalue) {
        this.MPIvalue = MPIvalue;
    }

    public WordWithPosition(int x, int y, double MPIvalue) {
        this.x = x;
        this.y = y;
        this.MPIvalue = MPIvalue;
    }

    public WordWithPosition(int x, int y, double MPIvalue, String word) {
        this.x = x;
        this.y = y;
        this.MPIvalue = MPIvalue;
        this.word = word;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getMPIvalue() {
        return MPIvalue;
    }

    public void setMPIvalue(double MPIvalue) {
        this.MPIvalue = MPIvalue;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }
}
