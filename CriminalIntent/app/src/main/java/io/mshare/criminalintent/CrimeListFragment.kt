package io.mshare.criminalintent

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.*
import android.widget.Toast
import kotlinx.android.synthetic.main.fragment_crime_list.view.*
import kotlinx.android.synthetic.main.list_item_crime.*
import kotlinx.android.synthetic.main.list_item_crime.view.*

class CrimeListFragment : Fragment() {
    private var mAdapter: CrimeAdapter? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_crime_list, container, false)
        view.crime_recycler_view.layoutManager = LinearLayoutManager(activity)


        return view
    }

    override fun onResume() {
        super.onResume()
        updateUI()
    }

    fun updateUI() {

        val crimes = CrimeLab.getInstance(activity as Context).getCrimes()
        if (mAdapter == null) {
            mAdapter = CrimeAdapter(crimes)
            view?.crime_recycler_view?.adapter = mAdapter
        } else {
            mAdapter?.setCrimes(crimes)
            mAdapter?.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.fragment_crime_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.new_crime -> {
                val crime = Crime()
                CrimeLab.getInstance(activity as Context).addCrime(crime)

                val intent = CrimePagerActivity.newIntent(activity as Context, crime.mId)
                startActivity(intent)
                return true
            }
            R.id.show_subtitle -> {
                updateSubtitle()
                return true
            }
            else -> {
                return super.onOptionsItemSelected(item)
            }
        }
    }

    private fun updateSubtitle() {
        val crimeCount = CrimeLab.getInstance(activity as Context).getCrimes().size
        val subTitle = getString(R.string.subtitle_format, crimeCount)

        val activity = activity as AppCompatActivity
        activity.supportActionBar?.subtitle = subTitle
    }

    private inner class CrimeHolder : RecyclerView.ViewHolder, View.OnClickListener {
        private var mCrime: Crime? = null
        constructor(inflater: LayoutInflater, parent: ViewGroup): super(inflater.inflate(R.layout.list_item_crime, parent, false)) {
            itemView.setOnClickListener(this)
        }

        fun bind(crime: Crime) {
            mCrime = crime
            itemView.tv_title.text = crime.mTitle
            itemView.crime_date.text = crime.mDate.toString()
            itemView.crime_solved.visibility = if (crime.mSolved) View.VISIBLE else View.GONE
        }

        override fun onClick(p0: View?) {
//            Toast.makeText(activity, "${mCrime?.mTitle ?: ""} clicked", Toast.LENGTH_SHORT).show();
//            val intent = Intent(activity, CrimeActivity::class.java)
            if (activity != null && mCrime != null) {
//                val intent = CrimeActivity.newIntent(activity as Context, mCrime!!.mId)
                val intent = CrimePagerActivity.newIntent(activity as Context, mCrime!!.mId)
                startActivity(intent)
            }


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

        fun setCrimes(crimes: List<Crime>) {
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