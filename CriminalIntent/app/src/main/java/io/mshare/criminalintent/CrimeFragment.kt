package io.mshare.criminalintent

import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.fragment_crime.*

class CrimeFragment: Fragment() {
    private lateinit var mCrime: Crime

    override fun onAttach(context: Context?) {
        super.onAttach(context)
        Log.e("mxy", "CrimeFragment onAttach")

    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mCrime = Crime()
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
        crime_date.isEnabled = false

        crime_solved.setOnCheckedChangeListener { button, isChecked ->
            mCrime.mSolved = isChecked
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