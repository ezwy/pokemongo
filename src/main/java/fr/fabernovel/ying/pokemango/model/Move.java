package fr.fabernovel.ying.pokemango.model;

import java.util.List;

public class Move {
    private String name;
    private String url;
    private List<VersionGroupDetail> versionGroupDetailList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<VersionGroupDetail> getVersionGroupDetailList() {
        return versionGroupDetailList;
    }

    public void setVersionGroupDetailList(List<VersionGroupDetail> versionGroupDetailList) {
        this.versionGroupDetailList = versionGroupDetailList;
    }
}
