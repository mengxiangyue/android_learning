package io.mshare.criminalintent

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_crime_list.view.*
import kotlinx.android.synthetic.main.list_item_crime.*
import kotlinx.android.synthetic.main.list_item_crime.view.*

class CrimeListFragment : Fragment() {
    private lateinit var mAdapter: CrimeAdapter
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        view.crime_recycler_view.layoutManager = LinearLayoutManager(activity)

        val crimes = CrimeLab.getCrimes()
        mAdapter = CrimeAdapter(crimes)
        view.crime_recycler_view.adapter = mAdapter
        return view
    }

    private inner class CrimeHolder : RecyclerView.ViewHolder, View.OnClickListener {
        private var mCrime: Crime? = null
        constructor(inflater: LayoutInflater, parent: ViewGroup): super(inflater.inflate(R.layout.list_item_crime, parent, false)) {
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime) {
            mCrime = crime
            itemView.tv_title.text = crime.mTitle
        }

        override fun onClick(p0: View?) {
            Toast.makeText(activity, "${mCrime?.mTitle ?: ""} clicked", Toast.LENGTH_SHORT).show();
        }
    }

    private inner class CrimeAdapter(crimes: List<Crime>): RecyclerView.Adapter<CrimeHolder>() {
        private lateinit var mCrimes: List<Crime>
//        constructor(crimes: List<Crime>): this() {
//            mCrimes = crimes
//        }
        init {
            mCrimes = crimes
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CrimeHolder {
            val layoutInflater = LayoutInflater.from(activity)
            return CrimeHolder(layoutInflater, parent)
        }

        override fun onBindViewHolder(holder: CrimeHolder, position: Int) {
            val crime = mCrimes.get(position)
            holder.bind(crime)

//            tv_title.text = crime.mTitle
        }

        override fun getItemCount(): Int {
            return mCrimes.size
        }
    }
}