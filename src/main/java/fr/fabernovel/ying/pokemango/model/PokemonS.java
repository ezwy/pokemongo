package fr.fabernovel.ying.pokemango.model;

import java.util.List;

public class PokemonS {
    private String name;
    private int height;
    private int base_experience;
    private int averageBaseExperience;
    private int id;
    private String sprite_img;
    private Species species;
    private String url;
    private List<Stat> stats;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getBase_experience() {
        return base_experience;
    }

    public void setBase_experience(int base_experience) {
        this.base_experience = base_experience;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSprite_img() {
        return sprite_img;
    }

    public void setSprite_img(String sprite_img) {
        this.sprite_img = sprite_img;
    }

    public Species getSpecies() {
        return species;
    }

    public void setSpecies(Species species) {
        this.species = species;
    }

    public int getAverageBaseExperience() {
        return averageBaseExperience;
    }

    public void setAverageBaseExperience(int averageBaseExperience) {
        this.averageBaseExperience = averageBaseExperience;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<Stat> getStats() {
        return stats;
    }

    public void setStats(List<Stat> stats) {
        this.stats = stats;
    }
}
