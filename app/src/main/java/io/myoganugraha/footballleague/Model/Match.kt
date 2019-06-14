package io.myoganugraha.footballleague.Model

import com.google.gson.annotations.SerializedName
import java.text.SimpleDateFormat
import java.util.*

data class Match(
    @SerializedName("dateEvent")
    val dateEvent: String,
    @SerializedName("datetimeEventUTC")
    val datetimeEventUTC: Any,
    @SerializedName("idAwayTeam")
    val idAwayTeam: String,
    @SerializedName("idEvent")
    val idEvent: String,
    @SerializedName("idHomeTeam")
    val idHomeTeam: String,
    @SerializedName("idLeague")
    val idLeague: String,
    @SerializedName("idSoccerXML")
    val idSoccerXML: String,
    @SerializedName("intAwayScore")
    val intAwayScore: String? = "0",
    @SerializedName("intAwayShots")
    val intAwayShots: String? = "0",
    @SerializedName("intHomeScore")
    val intHomeScore: String? = "0",
    @SerializedName("intHomeShots")
    val intHomeShots: String? = "0",
    @SerializedName("intRound")
    val intRound: String? = "0",
    @SerializedName("intSpectators")
    val intSpectators: String,
    @SerializedName("strAwayFormation")
    val strAwayFormation: String? = "-",
    @SerializedName("strAwayGoalDetails")
    val strAwayGoalDetails: String,
    @SerializedName("strAwayLineupDefense")
    val strAwayLineupDefense: String,
    @SerializedName("strAwayLineupForward")
    val strAwayLineupForward: String,
    @SerializedName("strAwayLineupGoalkeeper")
    val strAwayLineupGoalkeeper: String,
    @SerializedName("strAwayLineupMidfield")
    val strAwayLineupMidfield: String,
    @SerializedName("strAwayLineupSubtitutes")
    val strAwayLineupSubstitutes: String,
    @SerializedName("strAwayRedCards")
    val strAwayRedCards: String? = "0",
    @SerializedName("strAwayTeam")
    val strAwayTeam: String,
    @SerializedName("strAwayYellowCards")
    val strAwayYellowCards: String? = "0",
    @SerializedName("strBanner")
    val strBanner: Any,
    @SerializedName("strCircuit")
    val strCircuit: Any,
    @SerializedName("strCity")
    val strCity: Any,
    @SerializedName("strCountry")
    val strCountry: Any,
    @SerializedName("strDate")
    val strDate: String,
    @SerializedName("strDescriptionEN")
    val strDescriptionEN: Any,
    @SerializedName("strEvent")
    val strEvent: String,
    @SerializedName("strFanart")
    val strFanart: Any,
    @SerializedName("strFilename")
    val strFilename: String,
    @SerializedName("strHomeFormation")
    val strHomeFormation: String? = "-",
    @SerializedName("strHomeGoalDetails")
    val strHomeGoalDetails: String,
    @SerializedName("strHomeLineupDefense")
    val strHomeLineupDefense: String,
    @SerializedName("strHomeLineupForward")
    val strHomeLineupForward: String,
    @SerializedName("strHomeLineupGoalkeeper")
    val strHomeLineupGoalkeeper: String,
    @SerializedName("strHomeLineupMidfield")
    val strHomeLineupMidfield: String,
    @SerializedName("strHomeLineupSubtitutes")
    val strHomeLineupSubstitutes: String,
    @SerializedName("strHomeRedCards")
    val strHomeRedCards: String? = "0",
    @SerializedName("strHomeTeam")
    val strHomeTeam: String,
    @SerializedName("strHomeYellowCards")
    val strHomeYellowCards: String? = "0",
    @SerializedName("strLeague")
    val strLeague: String,
    @SerializedName("strLocked")
    val strLocked: String,
    @SerializedName("strMap")
    val strMap: Any,
    @SerializedName("strPoster")
    val strPoster: Any,
    @SerializedName("strResult")
    val strResult: Any,
    @SerializedName("strSeason")
    val strSeason: String,
    @SerializedName("strSport")
    val strSport: String,
    @SerializedName("strTVStation")
    val strTVStation: Any,
    @SerializedName("StrThumb")
    val strThumb: Any,
    @SerializedName("strTime")
    val strTime: String,
    @SerializedName("strTweet1")
    val strTweet1: Any,
    @SerializedName("strTweet2")
    val strTweet2: Any,
    @SerializedName("strTweet3")
    val strTweet3: Any,
    @SerializedName("strVideo")
    val strVideo: Any
)