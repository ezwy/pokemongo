package fr.fabernovel.ying.pokemango.model;

public class Stat {
    private int base_stat;
    private String effort;
    private StatD stat;
    private int averageStat;

    public int getBase_stat() {
        return base_stat;
    }

    public void setBase_stat(int base_stat) {
        this.base_stat = base_stat;
    }

    public int getAverageStat() {
        return averageStat;
    }

    public void setAverageStat(int averageStat) {
        this.averageStat = averageStat;
    }

    public String getEffort() {
        return effort;
    }

    public void setEffort(String effort) {
        this.effort = effort;
    }

    public StatD getStat() {
        return stat;
    }

    public void setStat(StatD stat) {
        this.stat = stat;
    }
}
