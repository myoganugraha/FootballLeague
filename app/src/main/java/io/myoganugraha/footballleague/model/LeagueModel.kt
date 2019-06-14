package io.myoganugraha.footballleague.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class LeagueModel (val leagueID: String, val leagueName: String, val leagueImage: Int, val leageDescription: String) : Parcelable