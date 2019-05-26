package fr.fabernovel.ying.pokemango.service;

import fr.fabernovel.ying.pokemango.model.Pokemon;
import fr.fabernovel.ying.pokemango.model.PokemonS;
import fr.fabernovel.ying.pokemango.model.Type;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.SSLContext;
import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.X509Certificate;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.stream.Collectors;

@Service
public class PokemonService {

    private static final String urlApiPokeman = "https://pokeapi.co/api/v2/pokemon/";

    @Autowired
    public PokemonService() {
    }

    public PokemonS getPokemonByName(String name) {
        PokemonS pokemonS = new PokemonS();
        try {

            HttpComponentsClientHttpRequestFactory requestFactory = getHttpComponentsClientHttpRequestFactory();
            RestTemplate restTemplate = new RestTemplate(requestFactory);
            HttpHeaders headers = new HttpHeaders();
            setHeaders(headers);
            Map<String, String> params = new HashMap<>();
            StringBuilder urlS = new StringBuilder();
            urlS.append(urlApiPokeman);

            if (null != name && "" != name.trim()) {
                params.put("name", name);
                urlS.append("/");
                urlS.append(name);
            } else {
                urlS.append("?offset=20");
                urlS.append("&limit=20");
            }

            HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);

            Pokemon pokemon = restTemplate.exchange(urlS.toString(), HttpMethod.GET, entity, new ParameterizedTypeReference<Pokemon>() {
            }, params).getBody();


            if (null == pokemon) {
                return pokemonS;
            }
            int averageBaseExperience = getAverageBaseExpe(pokemon.getTypes(), restTemplate);
            setPokemon(pokemonS, pokemon, averageBaseExperience);

            System.out.println(pokemonS);

        } catch (NoSuchAlgorithmException e1) {
            e1.printStackTrace();
        } catch (KeyManagementException e1) {
            e1.printStackTrace();
        } catch (KeyStoreException e1) {
            e1.printStackTrace();
        }
        return pokemonS;
    }

    private int getAverageBaseExpe(List<Type> types, RestTemplate restTemplate) {
        HttpHeaders headers = new HttpHeaders();
        setHeaders(headers);
        Map<String, String> params = new HashMap<>();
        StringBuilder urlS = new StringBuilder();
        urlS.append(urlApiPokeman);
        urlS.append("?offset=1000");
        urlS.append("&limit=1000");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        List<Pokemon> pokemons = restTemplate.exchange(urlS.toString(), HttpMethod.GET, entity, new ParameterizedTypeReference<List<Pokemon>>() {
        }, params).getBody();

        if (pokemons == null) {
            return 0;
        }
        List<Integer> baseExperiences = pokemons.stream().filter(pokemon -> {
            AtomicBoolean containsType = new AtomicBoolean(false);
            pokemon.getTypes().forEach(type -> {
                if (types.contains(type)) {
                    containsType.set(true);
                }
            });
            return containsType.get();
        }).map(Pokemon::getBase_experience).collect(Collectors.toList());

        int averageBaseExpe = baseExperiences.stream().mapToInt(i -> i).sum() / baseExperiences.size();

        return averageBaseExpe;
    }

    private void setPokemon(PokemonS pokemonS, Pokemon pokemon, int averageBaseExperience) {
        pokemonS.setBase_experience(pokemon.getBase_experience());
        pokemonS.setHeight(pokemon.getHeight());
        pokemonS.setId(pokemon.getId());
        pokemonS.setName(pokemon.getName());
        pokemonS.setSpecies(pokemon.getSpecies());
        pokemonS.setSprite_img(pokemon.getSprites().getFront_default());
        pokemonS.setAverageBaseExperience(averageBaseExperience);
    }

    private void setHeaders(HttpHeaders headers) {
        headers.setAccessControlAllowCredentials(true);
        headers.setAccessControlAllowOrigin("*");
        List<HttpMethod> httpMethods = new ArrayList<>();
        httpMethods.add(HttpMethod.OPTIONS);
        httpMethods.add(HttpMethod.POST);
        httpMethods.add(HttpMethod.GET);
        httpMethods.add(HttpMethod.PUT);
        headers.setAccessControlAllowMethods(httpMethods);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
    }


    private HttpComponentsClientHttpRequestFactory getHttpComponentsClientHttpRequestFactory() throws NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
        TrustStrategy acceptingTrustStrategy = (X509Certificate[] chain, String authType) -> true;

        SSLContext sslContext = org.apache.http.ssl.SSLContexts.custom()
                .loadTrustMaterial(null, acceptingTrustStrategy)
                .build();

        SSLConnectionSocketFactory csf = new SSLConnectionSocketFactory(sslContext);

        CloseableHttpClient httpClient = HttpClients.custom()
                .setSSLSocketFactory(csf)
                .build();

        HttpComponentsClientHttpRequestFactory requestFactory =
                new HttpComponentsClientHttpRequestFactory();

        requestFactory.setHttpClient(httpClient);
        return requestFactory;
    }
}
