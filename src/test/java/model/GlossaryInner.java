package model;

import com.google.gson.annotations.SerializedName;

public class GlossaryInner {

    @SerializedName("SortAs")
    private String sortAs;
    @SerializedName("GlossTerm")
    private String glossTerm;
    @SerializedName("Acronym")
    private String acronym;

    public void setSortAs(String sortAs) {
        this.sortAs = sortAs;
    }

    public void setGlossTerm(String glossTerm) {
        this.glossTerm = glossTerm;
    }

    public void setAcronym(String acronym) {
        this.acronym = acronym;
    }

    public String getSortAs() {
        return sortAs;
    }

    public String getGlossTerm() {
        return glossTerm;
    }

    public String getAcronym() {
        return acronym;
    }
}
