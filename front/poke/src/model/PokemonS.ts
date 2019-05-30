import {Species} from "./Species";
import {StatD} from "./StatD";
import {Stat} from "./Stat";

export class PokemonS{
    public name?:string = "";
    public height?:number = 0;
    public base_experience?:number = 0;
    public id?:number = 0;
    public sprite_img?:string = "";
    public species?: Species= new Species();
    public weight?:number = 0;
    public url?:string;
    public averageBaseExperience?:number;
    public stats?:Stat[];
}