import * as React from "react";
import {Pokemon} from "../model/Pokemon";
import {Link} from "react-router-dom";
import {PokemonS} from "../model/PokemonS";
import {Species} from "../model/Species";
import {Props} from "react";
import {Stat} from "../model/Stat";

interface State {
    pokemon: PokemonS;
}

// @ts-ignore
export class SearchResult extends React.Component<State, Props> {
    pokemonS: PokemonS;
    // @ts-ignore
    props: Props;

    // @ts-ignore
    constructor(props: Props, state: State) {
        super(props, state);
        this.props = props;
        this.state = ({pokemon: new PokemonS()});
        this.pokemonS =  (null != this.props.location.state.pokemon  &&  undefined !== this.props.location.state.pokemon ) ? this.props.location.state.pokemon : new PokemonS();
    }

    getStatsTable = () => {
        if (this.pokemonS.stats == undefined) {
            return;
        }
        // @ts-ignore
        // @ts-ignore
        let tableStat = this.pokemonS.stats.map((pStat: Stat) =>
          <ul className="ulHead">{pStat.stat.name} <li className="liBody">Stat name : {pStat.stat.name}</li>
           <li className="liBody">Base stat : {pStat.base_stat}</li>
            <li className="liBody">Compare to other pokemon the same type of this stat, the average score :  {pStat.averageStat}</li>
            </ul>

        )
        return tableStat;
    }

    render() {
        // @ts-ignore
        return (<div>
                <h2>Pokemon details :</h2>
                <br/>
                <ul>
                    <li className="ui-g-12 ui-lg-3">
                        <table>
                            <thead>
                            <tr>
                                <th> name</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>{this.pokemonS.name}</td>
                            </tr>
                            </tbody>
                        </table>
                    </li>
                    <li className="ui-g-12 ui-lg-3">
                        <table>
                            <thead>
                            <tr>
                                <th> weight</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td>{this.pokemonS.height}</td>
                            </tr>
                            </tbody>
                        </table>
                    </li>
                    <li className="ui-g-12 ui-lg-3">
                        <table>
                            <thead>
                            <tr>
                                <th> sprite</th>
                            </tr>
                            </thead>
                            <tbody>
                            <tr>
                                <td><img src={this.pokemonS.sprite_img}/></td>
                            </tr>
                            </tbody>
                        </table>
                    </li>
                    <li className="ui-g-12 ui-lg-3">
                        Here is the basic information of stats
                        {this.getStatsTable()}
                    </li>
                </ul>
            </div>
        );
    }
}
