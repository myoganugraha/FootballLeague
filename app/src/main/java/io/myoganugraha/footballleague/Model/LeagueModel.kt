package io.myoganugraha.footballleague.Model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueModel (val leagueName: String, val leagueImage: Int, val leageDescription: String) : Parcelable