package io.mshare.criminalintent

import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import io.mshare.criminalintent.database.CrimeBaseHelper
import io.mshare.criminalintent.database.CrimeCursorWrapper
import io.mshare.criminalintent.database.CrimeDbSchema
import io.mshare.criminalintent.database.CrimeDbSchema.Companion.CrimeTable.Companion.Cols
import java.util.*
import io.mshare.criminalintent.database.CrimeDbSchema.Companion.CrimeTable



class CrimeLab private constructor(context: Context) {
//    private var mCrimes = mutableListOf<Crime>()

    private lateinit var mDatabase: SQLiteDatabase
    private lateinit var mContext: Context

    companion object {
        var instance: CrimeLab? = null
        fun getInstance(context: Context): CrimeLab {
            if (instance == null) {
                synchronized(CrimeLab::class.java) {
                    if (instance == null) {
                        instance = CrimeLab(context)
                    }
                }
            }
            return instance!!
        }
    }

    init {

        mContext = context.applicationContext
        mDatabase = CrimeBaseHelper(mContext).writableDatabase
//        for (i in 1..100) {
//            val crime = Crime(mTitle = "Crime #${i}", mSolved = (i % 2 == 0))
//            mCrimes.add(crime)
//        }
    }

    fun getCrimes(): List<Crime> {
        var crimes = mutableListOf<Crime>()
        val cursor = queryCrimes(null, null)
        try {
            cursor.moveToFirst()
            while (!cursor.isAfterLast) {
                crimes.add(cursor.getCrime())
                cursor.moveToNext()
            }
        } finally {
            cursor.close()
        }
        return crimes
    }

    fun getCrime(id: UUID): Crime? {
//        return mCrimes.filter { it.mId.equals(id) }.first()
        val cursor = queryCrimes(
                Cols.UUID + " = ?",
                arrayOf(id.toString()))
        try {
            if (cursor.count == 0) {
                return null
            }
            cursor.moveToFirst()
            return cursor.getCrime()
        } finally {
            cursor.close()
        }
    }

    fun addCrime(c: Crime) {
//        mCrimes.add(c)
        mDatabase.insert(CrimeDbSchema.Companion.CrimeTable.Companion.NAME, null, c.getContentValues())
    }

    fun updateCrime(c: Crime) {
        val uuidString = c.mId.toString()
        mDatabase.update(CrimeDbSchema.Companion.CrimeTable.Companion.NAME, c.getContentValues(), "${CrimeDbSchema.Companion.CrimeTable.Companion.Cols.UUID} = ?", arrayOf(uuidString))
    }

    private fun queryCrimes(whereClause: String?, whereArgs: Array<String>?): CrimeCursorWrapper {
        val cursor = mDatabase.query(
                CrimeDbSchema.Companion.CrimeTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        )
        return CrimeCursorWrapper(cursor)
    }

}