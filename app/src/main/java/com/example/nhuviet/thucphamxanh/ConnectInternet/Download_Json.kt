package com.example.nhuviet.thucphamxanh.ConnectInternet

import android.net.Uri
import android.os.AsyncTask
import android.util.Log

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.io.OutputStream
import java.io.OutputStreamWriter
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.ProtocolException
import java.net.URL
import java.util.HashMap

/**
 * Created by nhuvi on 16/05/2017.
 */

class Download_Json : AsyncTask<String, Void, String> {
    internal var dudieu: StringBuilder
    internal var duongdan: String
    internal var attrs: List<HashMap<String, String>>
    var method = true

    constructor(duongdan: String) {
        this.duongdan = duongdan
        this.method = true
    }

    constructor(duongdan: String, attrs: List<HashMap<String, String>>) {
        this.duongdan = duongdan
        this.attrs = attrs
        this.method = false
    }

    override fun doInBackground(vararg params: String): String {
        var data = ""
        try {
            val url = URL(duongdan)
            val httpURLConnection = url.openConnection() as HttpURLConnection


            if (!method) {
                data = methodPost(httpURLConnection)
            } else {
                data = methodGet(httpURLConnection)
            }
        } catch (e: MalformedURLException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        Log.d("dulieu", data)
        return data

    }

    private fun methodGet(httpURLConnection: HttpURLConnection): String {
        var data = ""
        //get
        var inputStream: InputStream? = null
        try {
            httpURLConnection.connect()
            inputStream = httpURLConnection.inputStream
            val reader = InputStreamReader(inputStream!!)
            val bufferedReader = BufferedReader(reader)

            dudieu = StringBuilder()
            var line = ""
            while ((line = bufferedReader.readLine()) != null) {
                dudieu.append(line)
            }
            //            Log.d("kiemtra:",dudieu.toString());
            data = dudieu.toString()
            bufferedReader.close()
            reader.close()
            inputStream.close()

        } catch (e: IOException) {
            e.printStackTrace()
        }

        return data
    }

    fun methodPost(httpURLConnection: HttpURLConnection): String {
        var data = ""
        var key = ""
        var value = ""
        try {
            httpURLConnection.requestMethod = "POST"
            httpURLConnection.doOutput = true
            httpURLConnection.doInput = true

            val uri = Uri.Builder()

            val count = attrs.size
            for (i in 0..count - 1) {

                for ((key1, value1) in attrs[i]) {
                    key = key1
                    value = value1
                }
                uri.appendQueryParameter(key, value)
            }
            val query = uri.build().encodedQuery

            val outputStream = httpURLConnection.outputStream
            val outputStreamWriter = OutputStreamWriter(outputStream)
            val bufferedWriter = BufferedWriter(outputStreamWriter)

            bufferedWriter.write(query)
            bufferedWriter.flush()
            bufferedWriter.close()
            outputStreamWriter.close()
            outputStream.close()

            data = methodGet(httpURLConnection)


        } catch (e: ProtocolException) {
            e.printStackTrace()
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return data
    }
}
