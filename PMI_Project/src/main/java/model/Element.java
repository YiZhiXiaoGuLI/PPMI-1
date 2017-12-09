package model;

public class Element {

    private String id;
    private String name;
    private String freq;
    private String probability;

    public Element(String id, String name, String freq) {
        this.id = id;
        this.name = name;
        this.freq = freq;
    }

    public Element(String id, String name, String freq, String probability) {
        this.id = id;
        this.name = name;
        this.freq = freq;
        this.probability = probability;
    }

    public String getProbability() {
        return probability;
    }

    public void setProbability(String probability) {
        this.probability = probability;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFreq() {
        return freq;
    }

    public void setFreq(String freq) {
        this.freq = freq;
    }
}
