import {VersionGroupDetail} from "./VersionGroupDetail";
import {MoveD} from "./MoveD";


export interface Move {
    move: MoveD;
    version_group_details: VersionGroupDetail[];
}