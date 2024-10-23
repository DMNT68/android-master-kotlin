package com.andres_sagadoc.androidmaster.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDetailResponse(
    @SerializedName("id") val superHeroId: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: Image,
    @SerializedName("powerstats") val powerstats: Powerstats,
    @SerializedName("biography") val biography: Biography
)

data class Powerstats(
    @SerializedName("intelligence") val intelligence: String,
    @SerializedName("strength") val strength: String,
    @SerializedName("speed") val speed: String,
    @SerializedName("durability") val durability: String,
    @SerializedName("power") val power: String,
    @SerializedName("combat") val combat: String
)

data class Biography(
    @SerializedName("full-name") val fullName: String,
    @SerializedName("publisher") val publisher: String,
)