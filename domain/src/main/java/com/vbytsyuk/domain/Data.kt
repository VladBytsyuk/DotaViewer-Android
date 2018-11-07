package com.vbytsyuk.domain

import com.google.gson.annotations.SerializedName
import sun.java2d.cmm.Profile

data class Player(
    @SerializedName("tracked_until") val trackedUntil: String,
    @SerializedName("solo_competitive_rank") val soloCompetitiveRank: String,
    @SerializedName("competitive_rank") val competitiveRank: String,
    @SerializedName("rank_tier") val rankTier: Int,
    @SerializedName("leaderboard_rank") val leaderboardRank: Int,
    @SerializedName("mmr_estimate") val mmrEstimate: MmrEstimate,
    @SerializedName("profile") val profile: Profile
)

data class MmrEstimate(
    @SerializedName("estimate") val estimate: Int,
    @SerializedName("stdDev") val stdDev: Int,
    @SerializedName("n") val n: Int
)

data class Profile(
    @SerializedName("account_id") val accountId: Int,
    @SerializedName("personaname") val personName: String,
    @SerializedName("name") val name: String,
    @SerializedName("cheese") val cheese: Int,
    @SerializedName("steamid") val steamId: String,
    @SerializedName("avatar") val avatar: String,
    @SerializedName("avatarmedium") val avatarMedium: String,
    @SerializedName("avatarfull") val avatarFull: String,
    @SerializedName("profileUrl") val profileUrl: String,
    @SerializedName("last_login") val lastLogin: String,
    @SerializedName("loccountrycode") val locationCountryCode: String,
    @SerializedName("is_contributor") val isContributor: Boolean
)
