export class Sprites {
    private _back_default: string = "";
    private _back_female?: any;
    private _back_shiny: string = "";
    private _back_shiny_female?: any;
    private _front_default: string = "";
    private _front_female?: any;
    private _front_shiny: string = "";
    private _front_shiny_female?: any;


    get back_default(): string {
        return this._back_default;
    }

    set back_default(value: string) {
        this._back_default = value;
    }

    get back_female(): any {
        return this._back_female;
    }

    set back_female(value: any) {
        this._back_female = value;
    }

    get back_shiny(): string {
        return this._back_shiny;
    }

    set back_shiny(value: string) {
        this._back_shiny = value;
    }

    get back_shiny_female(): any {
        return this._back_shiny_female;
    }

    set back_shiny_female(value: any) {
        this._back_shiny_female = value;
    }

    get front_default(): string {
        return this._front_default;
    }

    set front_default(value: string) {
        this._front_default = value;
    }

    get front_female(): any {
        return this._front_female;
    }

    set front_female(value: any) {
        this._front_female = value;
    }

    get front_shiny(): string {
        return this._front_shiny;
    }

    set front_shiny(value: string) {
        this._front_shiny = value;
    }

    get front_shiny_female(): any {
        return this._front_shiny_female;
    }

    set front_shiny_female(value: any) {
        this._front_shiny_female = value;
    }
}