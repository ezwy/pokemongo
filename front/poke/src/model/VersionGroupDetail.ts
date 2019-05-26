import {VersionGroup} from "./VersionGroup";
import {MoveLearnMethod} from "./MoveLearnMethod";

export interface VersionGroupDetail {
    level_learned_at: number;
    move_learn_method: MoveLearnMethod;
    version_group: VersionGroup;
}