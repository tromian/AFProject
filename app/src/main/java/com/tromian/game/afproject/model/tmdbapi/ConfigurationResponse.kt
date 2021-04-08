package com.tromian.game.afproject.model.tmdbapi

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationResponse(
    @SerializedName("change_keys")
    val change_keys: List<String>,
    @SerializedName("images")
    val images: Images
)
@Serializable
data class Images(
    @SerializedName("backdrop_sizes")
    val backdrop_sizes: List<String>,
    @SerializedName("base_url")
    val base_url: String,
    @SerializedName("logo_sizes")
    val logo_sizes: List<String>,
    @SerializedName("poster_sizes")
    val poster_sizes: List<String>,
    @SerializedName("profile_sizes")
    val profile_sizes: List<String>,
    @SerializedName("secure_base_url")
    val secure_base_url: String,
    @SerializedName("still_sizes")
    val still_sizes: List<String>
)