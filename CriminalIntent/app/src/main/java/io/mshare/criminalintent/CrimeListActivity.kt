package io.mshare.criminalintent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.Fragment

class CrimeListActivity : SingleFragmentActivity() {

    override fun createFragment(): Fragment {
        return CrimeListFragment()
    }
}
