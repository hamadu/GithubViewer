package net.hama_du.githubviewer.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by hama_du on 2017/02/18.
 */

public class Repository {
    public String name;

    @SerializedName("html_url")
    public String url;

    @SerializedName("stargazers_count")
    public int star;
}
