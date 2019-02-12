package com.vbytsyuk.domain

import com.google.gson.annotations.SerializedName

data class Player(
    @SerializedName("tracked_until") val trackedUntil: String? = null,
    @SerializedName("solo_competitive_rank") val soloCompetitiveRank: String? = null,
    @SerializedName("competitive_rank") val competitiveRank: String? = null,
    @SerializedName("rank_tier") val rankTier: Int? = null,
    @SerializedName("leaderboard_rank") val leaderboardRank: Int? = null,
    @SerializedName("mmr_estimate") val mmrEstimate: MmrEstimate? = null,
    @SerializedName("profile") val profile: Profile? = null
)

data class MmrEstimate(
    @SerializedName("estimate") val estimate: Int? = null,
    @SerializedName("stdDev") val stdDev: Int? = null,
    @SerializedName("n") val n: Int? = null
)

data class Profile(
    @SerializedName("account_id") val accountId: Int? = null,
    @SerializedName("personaname") val personName: String? = null,
    @SerializedName("name") val name: String? = null,
    @SerializedName("cheese") val cheese: Int? = null,
    @SerializedName("steamid") val steamId: String? = null,
    @SerializedName("avatar") val avatar: String? = null,
    @SerializedName("avatarmedium") val avatarMedium: String? = null,
    @SerializedName("avatarfull") val avatarFull: String? = null,
    @SerializedName("profileUrl") val profileUrl: String? = null,
    @SerializedName("last_login") val lastLogin: String? = null,
    @SerializedName("loccountrycode") val locationCountryCode: String? = null,
    @SerializedName("is_contributor") val isContributor: Boolean? = null
)
