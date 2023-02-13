package com.example.footballflow.pojo

data class Response2(
    val area: Area2,
    val code: String,
    val currentSeason: CurrentSeason2,
    val emblemUrl: String,
    val id: Int,
    val lastUpdated: String,
    val name: String,
    val plan: String,
    val seasons: List<Season>
)
data class CurrentSeason2(
    val currentMatchday: Int,
    val endDate: String,
    val id: Int,
    val startDate: String,
    val winner: Any
)
data class Area2(
    val id: Int,
    val name: String
)
data class Season(
    val currentMatchday: Int,
    val endDate: String,
    val id: Int,
    val startDate: String,
    val winner: Winner
)
data class Winner(
    val id:Int,
    val name:String,
    val shortName:String,
    val tla:String,
    val crestUrl:String
)