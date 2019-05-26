package fr.fabernovel.ying.pokemango.service;

import fr.fabernovel.ying.pokemango.model.*;
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
            System.out.println(e1.getCause() + e1.getMessage());
        } catch (KeyManagementException e) {
            System.out.println(e.getCause() + e.getMessage());
        } catch (KeyStoreException e2) {
            System.out.println(e2.getCause() + e2.getMessage());
        }
        return pokemonS;
    }

    private int getAverageBaseExpe(List<Type> types, RestTemplate restTemplate) {
        HttpHeaders headers = new HttpHeaders();
        setHeaders(headers);
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        List<String> typesUrls = types.stream().map(type -> type.getType()).map(t -> t.getUrl()).collect(Collectors.toList());
        List<PokemonType> pokemonTypes = typesUrls.stream().map(url ->
        {
            PokemonType pokemonType = restTemplate.exchange(url, HttpMethod.GET, entity, new ParameterizedTypeReference<PokemonType>() {
            }).getBody();
            return pokemonType;
        }).collect(Collectors.toList());

        List<Pokemon> pokemons = new ArrayList<>();
        pokemonTypes.stream().
                forEach(pokemonType ->
                        {
                            List<PokemonL> pokemonLList = pokemonType.getPokemon();
                            pokemons.addAll(pokemonLList.stream().map(pokemonL ->
                                    {
                                        Pokemon pokemon = restTemplate.exchange(pokemonL.getPokemon().getUrl(), HttpMethod.GET, entity, new ParameterizedTypeReference<Pokemon>() {
                                        }).getBody();
                                        return pokemon;
                                    }
                            ).collect(Collectors.toList()));
                        }
                );

        if (pokemons == null) {
            return 0;
        }

        List<Integer> baseExperiences = pokemons.stream().map(Pokemon::getBase_experience).collect(Collectors.toList());

        if (baseExperiences.size() != 0) {

            return baseExperiences.stream().mapToInt(i -> i).sum() / baseExperiences.size();
        }

        return 0;
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


    private HttpComponentsClientHttpRequestFactory getHttpComponentsClientHttpRequestFactory() throws
            NoSuchAlgorithmException, KeyManagementException, KeyStoreException {
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
