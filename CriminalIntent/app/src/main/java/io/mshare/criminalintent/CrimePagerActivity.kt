package io.mshare.criminalintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentStatePagerAdapter
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_crime_pager.*
import java.util.*

class CrimePagerActivity : AppCompatActivity() {

    companion object {
        public val EXTRA_CRIME_ID = "com.mshare.android.criminalintent.crime_id"
        public fun newIntent(context: Context, crimeId: UUID): Intent {
            val intent = Intent(context, CrimePagerActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID, crimeId)
            return intent
        }
    }

    private  lateinit var mCrimes: List<Crime>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime_pager)

        val crimeId = intent.getSerializableExtra(EXTRA_CRIME_ID)
        mCrimes = CrimeLab.getCrimes()
        crime_view_pager.adapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                val crime = mCrimes[position]
                return CrimeFragment.newInstance(crime.mId)
            }

            override fun getCount(): Int {
                return mCrimes.size
            }
        }

        for ((index, value) in mCrimes.withIndex()) {
            if (value.mId.equals(crimeId)) {
                crime_view_pager.currentItem = index
                break
            }
        }
    }
}