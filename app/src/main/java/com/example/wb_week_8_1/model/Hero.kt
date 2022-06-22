package com.example.wb_week_8_1.model

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize


@JsonClass(generateAdapter = true)
@Parcelize
data class Hero(
    val id: Int,
    @Json(name = "localized_name") val name: String,
    @Json(name = "primary_attr") val primaryAttr: String,
    @Json(name = "attack_type") val attackType: String,
    val img: String,
    val icon: String,
    @Json(name = "base_health") val baseHealth: Int,
    @Json(name = "base_mana") val baseMana: Int,
    @Json(name = "base_armor") val baseArmor: Double,
    @Json(name = "base_attack_min") val baseAttackMin: Int,
    @Json(name = "base_attack_max") val baseAttackMax: Int,
    @Json(name = "base_str") val baseStr: Int,
    @Json(name = "base_agi") val baseAgi: Int,
    @Json(name = "base_int") val baseInt: Int,
    @Json(name = "attack_range") val attackRange: Int
) : Parcelable

