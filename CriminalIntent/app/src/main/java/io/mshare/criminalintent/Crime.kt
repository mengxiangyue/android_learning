package io.mshare.criminalintent

import java.util.*

data class Crime(var mTitle: String = "", var mSolved: Boolean = false , val mId: UUID = UUID.randomUUID(), var mDate: Date = Date())