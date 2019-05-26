import * as React from "react";
import {Link, NavLink, RouteComponentProps} from "react-router-dom";
import TextField from "@material-ui/core/TextField";
import Button from "@material-ui/core/Button";
import Router from 'react-router-dom';
import {inspect} from "util";
import {WithStyles, createStyles} from "@material-ui/core/styles";
import {SearchResult} from "./SearchResult";
import {Pokemon} from "../model/Pokemon";
import {isFulfilled} from "q";
import {Props, RefObject} from "react";
import {List} from "@material-ui/icons";
import axios from 'axios';
import {PokemonS} from "../model/PokemonS";

export interface NameProps {
    id: string;
    name?: string;
}

const styles = (theme: any) =>
    createStyles({
        card: {
            maxWidth: 400,
            margin: "0 auto"
        }
    });

interface State {
    pokemons: PokemonS[];
    inputValue?:string;
}

// noinspection JSDeprecatedSymbols
// @ts-ignore
export class SearchPage extends React.Component<State, Props> {
   state: State;
   pokemongoEle: RefObject<HTMLInputElement>;
    // @ts-ignore
    constructor(props, browserHistory) {
        super(props, browserHistory);
        this.state = {pokemons: [], inputValue: ""};
        let pokemon:PokemonS = new PokemonS();
        this.state.pokemons.push(pokemon);
        this.pokemongoEle = React.createRef();
    }
    updateInputValue = (event: React.FormEvent<HTMLInputElement>) => {
        // if(document.getElementById("pokemongo") !== null && document.getElementById("pokemongo") !== undefined){
        //
        //     this.state.inputValue = document.getElementById("pokemongo").value;
        // }
        this.state.inputValue  = (event.target as HTMLInputElement).value;

    }

    getInputValue = () =>{
        if(this.state.inputValue){
            return this.state.inputValue;
        }
        // else if(this.refs.pokemongo){
        //     return this.refs.pokemongo.current.value;
        // }
    }

    getResults = () => {

        let pokemons: PokemonS[] = [];
        axios.get("http://localhost:8080/api/pokemon/",
            {
                method: 'get',
                headers: {
                    'Content-type': 'application/json'
                },
                params: {
                    name: this.state.inputValue
                }
            }
        ).then(function (response?: any) {
            let _pokemonS: PokemonS = response.data;
            if(_pokemonS !== undefined){

                pokemons.push(_pokemonS);
            }

        }).then((res: any) => {
            this.setState({pokemons: pokemons});
            this.render();
        });
    }

    getPokemonName = () => {
        if(this.state.pokemons && this.state.pokemons.length > 0){
            return this.state.pokemons[0].name;
        }
    }

    _handleButtonClick = () => {
        this.context.history.push({ //browserHistory.push should also work here
            pathname: SearchResult,
            state: {pokemon: this.state.pokemons[0]}
        });
    }

  public  render(){
        return (
            <div
                style={{
                    display: "flex",
                    flexDirection: "column",
                    justifyContent: "center"
                }}
            >
                <form>
                <input
                    placeholder="Enter a pokemon name"
                    id="pokemongo"
                    type="text"
                    ref="pokemongo"

                    onChange={(event: React.FormEvent<HTMLInputElement>) => this.updateInputValue(event)}
                />
                <Button variant="contained" color="primary" onClick={this.getResults}>
                    Search
                </Button>
                </form>
                <div className="ui-g-12 ui-lg-3">
                        {this.state &&
                            <Link to={{pathname:"/results", state: {pokemon: this.state.pokemons[0]}  }}>{this.state.pokemons[0].name}{this.state.pokemons[0].averageBaseExperience}</Link>

                        }
                </div>
            </div>

        )

    }

}

