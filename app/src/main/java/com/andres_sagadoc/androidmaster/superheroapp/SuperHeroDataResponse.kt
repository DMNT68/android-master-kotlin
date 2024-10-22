package com.andres_sagadoc.androidmaster.superheroapp

import com.google.gson.annotations.SerializedName

data class SuperHeroDataResponse(
    @SerializedName("response") val response: String,
    @SerializedName("results") val superHeros: List<SuperHeroResponse>
)

data class SuperHeroResponse(
    @SerializedName("id") val superHeroId: String,
    @SerializedName("name") val name: String,
    @SerializedName("image") val image: Image

//    val powerstats: Powerstats,
//    val biography: Biography,
//    val appearance: Appearance,
//    val work: Work,
//    val connections: Connections,
)

data class Image(
    @SerializedName("url") val url: String
)

/*
data class Appearance(
    val gender: String,
    val race: String,
    val height: List<String>,
    val weight: List<String>,
    val eyeColor: String,
    val hairColor: String
)

data class Biography(
    val fullName: String,
    val alterEgos: String,
    val aliases: List<String>,
    val placeOfBirth: String,
    val firstAppearance: String,
    val publisher: String,
    val alignment: String
)

data class Connections(
    val groupAffiliation: String,
    val relatives: String
)

data class Work(
    val occupation: String,
    val base: String
)
*/




