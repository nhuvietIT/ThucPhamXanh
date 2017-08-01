package com.example.nhuviet.thucphamxanh.Model.Model_DangNhap_DangKi

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity

import com.example.nhuviet.thucphamxanh.ConnectInternet.Download_Json
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu
import com.facebook.AccessToken
import com.facebook.AccessTokenTracker
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.common.api.OptionalPendingResult

import org.json.JSONException
import org.json.JSONObject

import java.util.ArrayList
import java.util.HashMap
import java.util.concurrent.ExecutionException

/**
 * Created by nhuvi on 22/05/2017.
 */

class Model_DangNhap {
    internal var accessToken: AccessToken
    internal var accessTokenTracker: AccessTokenTracker


    fun Laytoken_facebook(): AccessToken {
        accessTokenTracker = object : AccessTokenTracker() {
            override fun onCurrentAccessTokenChanged(oldAccessToken: AccessToken,
                                                     currentAccessToken: AccessToken) {
                accessToken = currentAccessToken
            }
        }
        accessToken = AccessToken.getCurrentAccessToken()

        return accessToken
    }

    fun huyTokenTracker() {
        accessTokenTracker.stopTracking()
    }

    fun LaycacheDangNhap(context: Context): String {
        val cacheDangnhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE)
        val tennv = cacheDangnhap.getString("tennv", "")

        return tennv
    }

    fun CapnhatcacheDangNhap(context: Context, tennv: String) {
        val cacheDangnhap = context.getSharedPreferences("dangnhap", Context.MODE_PRIVATE)
        val editor = cacheDangnhap.edit()
        editor.putString("tennv", tennv)
        editor.commit()
    }

    fun Kiemtradangnhap(context: Context, tenDangNhap: String, matKhau: String): Boolean {
        var kiemtra = false
        val duongdan = Activity_TrangChu.Server_Name
        val attrs = ArrayList<HashMap<String, String>>()

        val shham = HashMap<String, String>()
        shham.put("ham", "Kiemtradangnhap")

        val shtenDangNhap = HashMap<String, String>()
        shtenDangNhap.put("tenDangNhap", tenDangNhap)

        val shmatKhau = HashMap<String, String>()
        shmatKhau.put("matKhau", matKhau)

        attrs.add(shham)
        attrs.add(shtenDangNhap)
        attrs.add(shmatKhau)

        val download_json = Download_Json(duongdan, attrs)
        download_json.execute()

        try {
            val dulieu = download_json.get()
            val jsonObject = JSONObject(dulieu)
            val ketquajson = jsonObject.getString("ketqua")
            if (ketquajson == "true") {
                kiemtra = true
                val tenNV = jsonObject.getString("tennv")

                CapnhatcacheDangNhap(context, tenNV)

            } else
                kiemtra = false

        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }

        return kiemtra
    }

    fun laythongtinGoogleApiClient(context: Context, failedListener: GoogleApiClient.OnConnectionFailedListener): GoogleApiClient {
        val googleApiClient: GoogleApiClient
        // Longin google
        val googleSignInOptions = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build()

        googleApiClient = GoogleApiClient.Builder(context)
                .enableAutoManage(context as AppCompatActivity, failedListener)
                .addApi(Auth.GOOGLE_SIGN_IN_API, googleSignInOptions)
                .build()

        return googleApiClient
    }

    fun laythongtinnguoidung(googleApiClient: GoogleApiClient): GoogleSignInResult? {
        val opr = Auth.GoogleSignInApi.silentSignIn(googleApiClient)
        if (opr.isDone) {
            return opr.get()
        } else {
            return null
        }
    }


}
