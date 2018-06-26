package io.mshare.asynctaskdemo

import android.app.ProgressDialog
import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_run.setOnClickListener {
            val runner = AsyncTaskRunner()
            var sleepTime = in_time.text.toString()
            runner.execute(sleepTime)
        }
    }

    private inner class AsyncTaskRunner: AsyncTask<String, String, String>() {
        private lateinit var resp: String
        private var progressDialog: ProgressDialog? = null
        override fun doInBackground(vararg p0: String?): String {
            publishProgress("Sleeping....")
            try {
                var time = p0[0]?.toLong()
                if (time != null) {
                    Thread.sleep(time)
                    resp = "Slept for ${time} seconds"
                }
            } catch (e: InterruptedException) {
                e.printStackTrace()
                resp = e.message ?: ""
            } catch (e: Exception) {
                e.printStackTrace()
                resp = e.message ?: ""
            }
            return resp
        }

        override fun onPreExecute() {
            super.onPreExecute()
            progressDialog = ProgressDialog.show(this@MainActivity, "ProgressDialog", "Wait ${in_time.text} seconds")
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            progressDialog?.dismiss()
            tv_result.text = result
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            tv_result.text = values[0]
        }

    }
}


