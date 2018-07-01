package io.mshare.criminalintent

import android.content.ContentValues
import java.util.*
import io.mshare.criminalintent.database.CrimeDbSchema.Companion.CrimeTable.Companion.Cols

data class Crime(var mTitle: String = "", var mSolved: Boolean = false , val mId: UUID = UUID.randomUUID(), var mDate: Date = Date())

fun Crime.getContentValues(): ContentValues {
    val values = ContentValues()
    values.put(Cols.UUID, mId.toString())
    values.put(Cols.TITLE, mTitle)
    values.put(Cols.DATE, mDate.time)
    values.put(Cols.SOLVED, if (mSolved) 1 else 0)
    return values
}