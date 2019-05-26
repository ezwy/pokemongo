package fr.fabernovel.ying.pokemango.model;

import java.util.List;

public class PokemonType {
    private String name;
    private List<PokemonL> pokemon;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<PokemonL> getPokemon() {
        return pokemon;
    }

    public void setPokemon(List<PokemonL> pokemon) {
        this.pokemon = pokemon;
    }
}
