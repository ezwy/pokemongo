import {Species} from "./Species";
import {Sprites} from "./Sprites";
import {Move} from "./Move";

export class Pokemon {
    private _abilities: Object[] = [];
    private _base_experience: number = 0;
    private _forms: Object[] = [];
    private _game_indices: any[] = [];
    private _height: number = 0;
    private _held_items: any[] = [];
    private _id: number = 0;
    private _is_default: boolean = false;
    private _location_area_encounters: string = "";
    private _moves: Move[] = [];
    private _name: string = "";
    private _order: number = 0;
    private _species: Species = new  Species();
    private _sprites: Sprites = new Sprites() ;
    private _stats: Object[] = [];
    private _types: Object[] = [];
    private _weight: number = 0;

    constructor() {
    }


    get abilities(): Object[] {
        return this._abilities;
    }

    set abilities(value: Object[]) {
        this._abilities = value;
    }

    get base_experience(): number {
        return this._base_experience;
    }

    set base_experience(value: number) {
        this._base_experience = value;
    }

    get forms(): Object[] {
        return this._forms;
    }

    set forms(value: Object[]) {
        this._forms = value;
    }

    get game_indices(): any[] {
        return this._game_indices;
    }

    set game_indices(value: any[]) {
        this._game_indices = value;
    }

    get height(): number {
        return this._height;
    }

    set height(value: number) {
        this._height = value;
    }

    get held_items(): any[] {
        return this._held_items;
    }

    set held_items(value: any[]) {
        this._held_items = value;
    }

    get id(): number {
        return this._id;
    }

    set id(value: number) {
        this._id = value;
    }

    get is_default(): boolean {
        return this._is_default;
    }

    set is_default(value: boolean) {
        this._is_default = value;
    }

    get location_area_encounters(): string {
        return this._location_area_encounters;
    }

    set location_area_encounters(value: string) {
        this._location_area_encounters = value;
    }


    get moves(): Move[] {
        return this._moves;
    }

    set moves(value: Move[]) {
        this._moves = value;
    }

    get name(): string {
        return this._name;
    }

    set name(value: string) {
        this._name = value;
    }

    get order(): number {
        return this._order;
    }

    set order(value: number) {
        this._order = value;
    }

    get species(): Species {
        return this._species;
    }

    set species(value: Species) {
        this._species = value;
    }

    get sprites(): Sprites {
        return this._sprites;
    }

    set sprites(value: Sprites) {
        this._sprites = value;
    }

    get stats(): Object[] {
        return this._stats;
    }

    set stats(value: Object[]) {
        this._stats = value;
    }

    get types(): Object[] {
        return this._types;
    }

    set types(value: Object[]) {
        this._types = value;
    }

    get weight(): number {
        return this._weight;
    }

    set weight(value: number) {
        this._weight = value;
    }
}
