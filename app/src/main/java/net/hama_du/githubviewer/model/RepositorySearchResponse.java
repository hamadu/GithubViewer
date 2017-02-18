package net.hama_du.githubviewer.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hama_du on 2017/02/18.
 */

public class RepositorySearchResponse {
    public int total_count;

    @SerializedName("items")
    public List<Repository> repositories;
}
