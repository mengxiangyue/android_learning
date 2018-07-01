package io.mshare.criminalintent.database

import android.database.Cursor
import android.database.CursorWrapper
import io.mshare.criminalintent.Crime
import io.mshare.criminalintent.database.CrimeDbSchema.Companion.CrimeTable.Companion.Cols
import java.util.*

class CrimeCursorWrapper(cursor: Cursor) : CursorWrapper(cursor) {
    fun getCrime(): Crime {
        val uuidString = getString(getColumnIndex(Cols.UUID))
        val title = getString(getColumnIndex(Cols.TITLE))
        val date = getLong(getColumnIndex(Cols.DATE))
        val isSolved = getInt(getColumnIndex(Cols.SOLVED))

        return Crime(title, if (isSolved == 0) false else true, UUID.fromString(uuidString), Date(date))
    }
}