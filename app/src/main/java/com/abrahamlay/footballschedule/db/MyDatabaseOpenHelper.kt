package com.abrahamlay.footballschedule.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import org.jetbrains.anko.db.*

/**
 * Created by Abraham on 26/05/2018.
 */
class MyDatabaseOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "FavoriteMatch.db", null, 1) {
    companion object {
        private var instance: MyDatabaseOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): MyDatabaseOpenHelper {
            if (instance == null) {
                instance = MyDatabaseOpenHelper(ctx.applicationContext)
            }
            return instance!!
        }
    }

    override fun onCreate(db: SQLiteDatabase) {
        // Here you create tables
        db.createTable(Favorite.TABLE_FAVORITE, true,
                Favorite.ID_EVENT to INTEGER + PRIMARY_KEY + AUTOINCREMENT,
                Favorite.HOME_TEAM_ID to TEXT + UNIQUE,
                Favorite.HOME_TEAM_NAME to TEXT,
                Favorite.HOME_TEAM_BADGE to TEXT,
                Favorite.AWAY_TEAM_ID to TEXT + UNIQUE,
                Favorite.AWAY_TEAM_NAME to TEXT,
                Favorite.AWAY_TEAM_BADGE to TEXT)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        // Here you can upgrade tables, as usual
        db.dropTable(Favorite.TABLE_FAVORITE, true)
    }
}

// Access property for Context
val Context.database: MyDatabaseOpenHelper
    get() = MyDatabaseOpenHelper.getInstance(applicationContext)