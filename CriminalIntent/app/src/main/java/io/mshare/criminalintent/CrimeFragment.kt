package io.mshare.criminalintent

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_crime.*
import java.util.*

class CrimeFragment: Fragment() {
    private lateinit var mCrime: Crime

    companion object {
        private val ARG_CRIME_ID = "crime_id"
        private val DIALOG_DATE = "DialgoDate"
        private val REQUEST_DATE = 0
        public fun newInstance(crimeId: UUID): CrimeFragment {
            val args = Bundle()
            args.putSerializable(ARG_CRIME_ID, crimeId)

            val fragment = CrimeFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.e("mxy", "CrimeFragment onAttach")

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCrime = Crime()
//        val crimeId = activity?.intent?.getSerializableExtra(CrimeActivity.EXTRA_CRIME_ID) as? UUID
        val crimeId = arguments?.getSerializable(ARG_CRIME_ID) as? UUID
        if (crimeId != null) {
            val tmpCrime = CrimeLab.getInstance(activity as Context).getCrime(crimeId)
            if (tmpCrime != null) {
                mCrime = tmpCrime
            }
        }
        Log.e("mxy", "CrimeFragment onCreate(savedInstanceState:)")
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        Log.e("mxy", "CrimeFragment onCreateView")
        val v = inflater.inflate(R.layout.fragment_crime, container, false)

        return v
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("mxy", "CrimeFragment onViewCreated")
        crime_title.setText(mCrime.mTitle)
        crime_title.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                mCrime.mTitle = p0.toString()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })

        crime_date.text = mCrime.mDate.toString()
//        crime_date.isEnabled = false
        crime_date.setOnClickListener {
//            val dialog = DatePickerFragment()
            val dialog = DatePickerFragment.newInstance(mCrime.mDate)
            dialog.setTargetFragment(this@CrimeFragment, REQUEST_DATE)
            dialog.show(fragmentManager, DIALOG_DATE)
        }

        crime_solved.isChecked = mCrime.mSolved
        crime_solved.setOnCheckedChangeListener { button, isChecked ->
            mCrime.mSolved = isChecked
        }
    }

    override fun onPause() {
        super.onPause()

        CrimeLab.getInstance(activity as Context).updateCrime(mCrime)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode != Activity.RESULT_OK) {
            return
        }

        if (requestCode == REQUEST_DATE) {
            val date = data?.getSerializableExtra(DatePickerFragment.EXTRA_DATE) as Date
            mCrime.mDate = date
            crime_date.text = mCrime.mDate.toString()
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.e("mxy", "CrimeFragment onActivityCreated")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.e("mxy", "CrimeFragment onDestroyView")
    }

    override fun onDetach() {
        super.onDetach()
        Log.e("mxy", "CrimeFragment onDetach")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("mxy", "CrimeFragment onDestroy")
    }
}