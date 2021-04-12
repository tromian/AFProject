package com.tromian.game.afproject.model.tmdbapi

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigurationResponse(
    @SerializedName("change_keys")
    val change_keys: List<String>? = null,
    @SerializedName("images")
    val images: Images? = null
)
@Serializable
data class Images(
    @SerializedName("backdrop_sizes")
    val backdrop_sizes: List<String>? = null,
    @SerializedName("base_url")
    val base_url: String? = null,
    @SerializedName("logo_sizes")
    val logo_sizes: List<String>? = null,
    @SerializedName("poster_sizes")
    val poster_sizes: List<String>? = null,
    @SerializedName("profile_sizes")
    val profile_sizes: List<String>? = null,
    @SerializedName("secure_base_url")
    val secure_base_url: String? = null,
    @SerializedName("still_sizes")
    val still_sizes: List<String>? = null
)