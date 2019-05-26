package fr.fabernovel.ying.pokemango.model;

import java.util.List;

public class HeldItem {
    private String heldName;
    private String url;
    private List<VersionDetail> versionDetails;

    public String getHeldName() {
        return heldName;
    }

    public void setHeldName(String heldName) {
        this.heldName = heldName;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<VersionDetail> getVersionDetails() {
        return versionDetails;
    }

    public void setVersionDetails(List<VersionDetail> versionDetails) {
        this.versionDetails = versionDetails;
    }
}
