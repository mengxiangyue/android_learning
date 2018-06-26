package io.mshare.criminalintent

import java.util.*

object CrimeLab {
    private var mCrimes = mutableListOf<Crime>()

    init {
        for (i in 1..100) {
            val crime = Crime(mTitle = "Crime #${i}", mSolved = (i % 2 == 0))
            mCrimes.add(crime)
        }
    }

    fun getCrimes(): List<Crime> {
        return mCrimes
    }

    fun getCrime(id: UUID): Crime? {
        return mCrimes.filter { it.mId.equals(id) }.first()
    }

}