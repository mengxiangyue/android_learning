package io.mshare.criminalintent.database

class CrimeDbSchema {
    companion object {
        class CrimeTable {
            companion object {
                val NAME = "crimes"
                class Cols {
                    companion object {
                        val UUID = "uuid"
                        val TITLE = "title"
                        val DATE = "date"
                        val SOLVED = "solved"
                    }
                }
            }
        }

    }
}