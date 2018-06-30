package io.mshare.criminalintent

import android.app.Activity
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.support.v7.app.AlertDialog
import android.view.LayoutInflater
import kotlinx.android.synthetic.main.dialog_date.*
import kotlinx.android.synthetic.main.dialog_date.view.*
import java.util.*

class DatePickerFragment : DialogFragment() {
    companion object {
        private val ARG_DATE = "date"
        public val EXTRA_DATE = "io.mshare.criminalintent.date"
        fun newInstance(date: Date): DialogFragment {
            val args = Bundle()
            args.putSerializable(ARG_DATE, date)
            val fragment = DatePickerFragment()
            fragment.arguments = args
            return fragment
        }
    }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val date = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val year = calendar.get(Calendar.YEAR)
        val month = calendar.get(Calendar.MONTH)
        val day = calendar.get(Calendar.DAY_OF_MONTH)
        val v = LayoutInflater.from(activity as Context).inflate(R.layout.dialog_date, null)
        v.dialog_date_picker.init(year, month, day, null)
        return AlertDialog.Builder(activity as Context)
                .setView(v)
                .setTitle(R.string.date_picker_title)
                .setPositiveButton(android.R.string.ok, object : DialogInterface.OnClickListener {
                    override fun onClick(p0: DialogInterface?, p1: Int) {
                        val dialog_date_picker = v.dialog_date_picker
                        val date = GregorianCalendar(dialog_date_picker.year, dialog_date_picker.month, dialog_date_picker.dayOfMonth).time
                        sendResult(Activity.RESULT_OK, date)
                    }
                })
                .create()
    }

    private fun sendResult(resultCode: Int, date: Date) {
        if (targetFragment == null) {
            return
        }
        val intent = Intent()
        intent.putExtra(EXTRA_DATE, date)
        targetFragment?.onActivityResult(targetRequestCode, resultCode, intent)
    }
}
