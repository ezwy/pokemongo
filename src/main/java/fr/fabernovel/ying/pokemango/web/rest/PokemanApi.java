package fr.fabernovel.ying.pokemango.web.rest;


import fr.fabernovel.ying.pokemango.model.Pokemon;
import fr.fabernovel.ying.pokemango.model.PokemonS;
import fr.fabernovel.ying.pokemango.service.PokemonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Collection;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class PokemanApi {
    private static final String urlApiPokeman = "https://pokeapi.co/api/v2/pokeman";

    @Autowired
    PokemonService pokemonService;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @GetMapping("api/pokemon")
    public ResponseEntity<PokemonS> getPokemons(@RequestParam(value="name", required = false)String name) throws IOException {
        PokemonS pokemon = this.pokemonService.getPokemonByName(name);

        return ResponseEntity.ok(pokemon);
    }


}
