package io.mshare.criminalintent

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class CrimeActivity : AppCompatActivity() {

    companion object {
        public val EXTRA_CRIME_ID = "com.mshare.android.criminalintent.crime_id"
        public fun newIntent(context: Context, crimeId: UUID): Intent {
            val intent = Intent(context, CrimeActivity::class.java)
            intent.putExtra(EXTRA_CRIME_ID, crimeId)
            return intent
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_crime)
        var fm = supportFragmentManager
        var fragment = fm.findFragmentById(R.id.fragment_container)

        if (fragment == null) {
//            fragment = CrimeFragment()
            var crimeId = intent.getSerializableExtra(EXTRA_CRIME_ID) as UUID
            fragment = CrimeFragment.newInstance(crimeId)
            fm.beginTransaction().add(R.id.fragment_container, fragment).commit()
        }
    }
}
