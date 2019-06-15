package io.myoganugraha.footballleague.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

class DBHelper(context: Context) : ManagedSQLiteOpenHelper(context, "FavoriteMatch.db", null, 1) {

    companion object {
        private var instances : DBHelper? = null

        fun getInstance(context: Context) : DBHelper{
            if (instances == null) {
                instances = DBHelper(context.applicationContext)
            }
            return instances!!
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            PreviousMatchFavorite.PREVIOUS_MATCH_FAVORITE_TB, true,
            PreviousMatchFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            PreviousMatchFavorite.ID_EVENT to TEXT + UNIQUE,
            PreviousMatchFavorite.ID_HOME_TEAM to TEXT,
            PreviousMatchFavorite.ID_AWAY_TEAM to TEXT,
            PreviousMatchFavorite.DATE_EVENT to TEXT,
            PreviousMatchFavorite.HOME_TEAM_NAME to TEXT,
            PreviousMatchFavorite.AWAY_TEAM_NAME to TEXT,
            PreviousMatchFavorite.HOME_TEAM_SCORE to TEXT,
            PreviousMatchFavorite.AWAY_TEAM_SCORE to TEXT
        )

        db?.createTable(
            NextMatchFavorite.NEXT_MATCH_FAVORITE_TB, true,
            NextMatchFavorite.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
            NextMatchFavorite.ID_EVENT to TEXT + UNIQUE,
            NextMatchFavorite.ID_HOME_TEAM to TEXT,
            NextMatchFavorite.ID_AWAY_TEAM to TEXT,
            NextMatchFavorite.DATE_EVENT to TEXT,
            NextMatchFavorite.HOME_TEAM_NAME to TEXT,
            NextMatchFavorite.AWAY_TEAM_NAME to TEXT,
            NextMatchFavorite.HOME_TEAM_SCORE to TEXT,
            NextMatchFavorite.AWAY_TEAM_SCORE to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(PreviousMatchFavorite.PREVIOUS_MATCH_FAVORITE_TB, true)
        db?.dropTable(NextMatchFavorite.NEXT_MATCH_FAVORITE_TB, true)
    }
}

val Context.database : DBHelper
    get() = DBHelper.getInstance(applicationContext)