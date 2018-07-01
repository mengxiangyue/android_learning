package io.mshare.criminalintent.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import io.mshare.criminalintent.database.CrimeDbSchema.Companion.CrimeTable

class CrimeBaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASEE_NAME, null, VERSION) {
    companion object {
        val VERSION = 1
        val DATABASEE_NAME = "crimeBase.db"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.execSQL("create table ${CrimeTable.NAME} (_id integer primary key autoincrement, ${CrimeTable.Companion.Cols.UUID}, ${CrimeTable.Companion.Cols.TITLE}, ${CrimeTable.Companion.Cols.DATE}, ${CrimeTable.Companion.Cols.SOLVED})")

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
}