import * as React from "react";
import {Pokemon} from "../model/Pokemon";
import {Link} from "react-router-dom";
import {PokemonS} from "../model/PokemonS";
import {Species} from "../model/Species";
import {Props} from "react";

interface State{
    pokemon: PokemonS;
}

// @ts-ignore
export class SearchResult extends React.Component<State, Props> {
    state: State;
    // @ts-ignore
    constructor(props:Props){
        super(props);
        this.state = ({pokemon: new PokemonS()});
    }

render() {
    return (<div>
            <h2>Search results</h2>
            <br/>
            <ul>
                <li className="ui-g-12 ui-lg-3">
                    <table>
                        <tr>
                            <th> name</th>
                        </tr>
                        <tr>
                            <td>{this.state.pokemon.name}</td>
                        </tr>
                    </table>
                </li>
                <li className="ui-g-12 ui-lg-3">
                    <table>
                        <tr>
                            <th> weight</th>
                        </tr>
                        <tr>
                            <td>{this.state.pokemon.height}</td>
                        </tr>
                    </table>
                </li>
                <li className="ui-g-12 ui-lg-3">
                    <table>
                        <tr>
                            <th> img</th>
                        </tr>
                        <tr>
                            <td>{this.state.pokemon.sprite_img}</td>
                        </tr>
                    </table>
                </li>
                <li className="ui-g-12 ui-lg-3">
                    <table>
                        <tr>
                            <th> experience</th>
                            <th> average experience</th>
                        </tr>
                        <tr>
                            <td>{this.state.pokemon.base_experience}</td>
                            <td>everage experience ????</td>
                        </tr>
                    </table>
                </li>
                <li className="ui-g-12 ui-lg-3">
                    <table>
                        <tr>
                            <th> species</th>
                        </tr>
                        <tr>
                            <td>{this.state.pokemon.species}</td>
                        </tr>
                    </table>
                </li>

            </ul>
        </div>
    );
}
}
