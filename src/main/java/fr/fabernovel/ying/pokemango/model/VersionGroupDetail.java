package fr.fabernovel.ying.pokemango.model;

public class VersionGroupDetail {
    private String level_learned_at;
    private String move_learn_method_name;
    private String version_group_name;

    public String getLevel_learned_at() {
        return level_learned_at;
    }

    public void setLevel_learned_at(String level_learned_at) {
        this.level_learned_at = level_learned_at;
    }

    public String getMove_learn_method_name() {
        return move_learn_method_name;
    }

    public void setMove_learn_method_name(String move_learn_method_name) {
        this.move_learn_method_name = move_learn_method_name;
    }

    public String getVersion_group_name() {
        return version_group_name;
    }

    public void setVersion_group_name(String version_group_name) {
        this.version_group_name = version_group_name;
    }
}
