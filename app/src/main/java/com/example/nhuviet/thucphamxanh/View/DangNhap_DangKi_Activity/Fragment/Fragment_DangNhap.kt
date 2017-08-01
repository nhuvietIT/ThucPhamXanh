package com.example.nhuviet.thucphamxanh.View.DangNhap_DangKi_Activity.Fragment

import android.app.ProgressDialog
import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

import com.example.nhuviet.thucphamxanh.Model.Model_DangNhap_DangKi.Model_DangNhap
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu
import com.facebook.CallbackManager
import com.facebook.FacebookCallback
import com.facebook.FacebookException
import com.facebook.login.LoginManager
import com.facebook.login.LoginResult
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient

import java.util.Arrays

/**
 * Created by nhuvi on 16/05/2017.
 */

class Fragment_DangNhap : Fragment(), View.OnClickListener, GoogleApiClient.OnConnectionFailedListener {
    internal var callbackManager: CallbackManager
    internal var bt_DangNhap: Button
    internal var bt_Dangnhapgoogle: Button
    internal var bt_DangNhapg: Button
    internal var googleApiClient: GoogleApiClient
    internal var progressDialog: ProgressDialog? = null
    internal var model_dangNhap: Model_DangNhap
    internal var ed_dangnhap: EditText
    internal var ed_matkhau: EditText


    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.layout_fragment_dangnhap, container, false)


        // Longin google
        model_dangNhap = Model_DangNhap()
        googleApiClient = model_dangNhap.laythongtinGoogleApiClient(context, this)
        // Login Facebook
        callbackManager = CallbackManager.Factory.create()
        LoginManager.getInstance().registerCallback(callbackManager, object : FacebookCallback<LoginResult> {
            override fun onSuccess(loginResult: LoginResult) {
                Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_LONG).show()
                val intentdangnhap = Intent(activity, Activity_TrangChu::class.java)
                startActivity(intentdangnhap)

            }

            override fun onCancel() {
                Toast.makeText(context, "Đã đăng xuất", Toast.LENGTH_LONG).show()

            }

            override fun onError(error: FacebookException) {
                Toast.makeText(context, "Đăng nhập thất bại ", Toast.LENGTH_LONG).show()

            }
        })

        bt_DangNhap = view.findViewById(R.id.bt_facebook) as Button
        bt_Dangnhapgoogle = view.findViewById(R.id.bt_google) as Button
        bt_DangNhapg = view.findViewById(R.id.bt_dangnhap) as Button
        ed_dangnhap = view.findViewById(R.id.ed_diachiemail) as EditText
        ed_matkhau = view.findViewById(R.id.ed_matkhau) as EditText


        bt_DangNhap.setOnClickListener(this)
        bt_Dangnhapgoogle.setOnClickListener(this)
        bt_DangNhapg.setOnClickListener(this)

        return view

    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.bt_facebook -> LoginManager.getInstance().logInWithReadPermissions(this@Fragment_DangNhap, Arrays.asList("public_profile", "user_friends"))
            R.id.bt_google -> {
                val igoogle = Auth.GoogleSignInApi.getSignInIntent(googleApiClient)
                startActivityForResult(igoogle, SIGN_IN_GOOGLE_PLUS)
                showProgressDialog()
            }
            R.id.bt_dangnhap -> {
                val tendangnhap = ed_dangnhap.text.toString()
                val matkhaus = ed_matkhau.text.toString()
                val kiemtra = model_dangNhap.Kiemtradangnhap(activity, tendangnhap, matkhaus)
                if (kiemtra) {
                    Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_LONG).show()
                    val intentdangnhap = Intent(activity, Activity_TrangChu::class.java)
                    startActivity(intentdangnhap)
                } else {
                    Toast.makeText(context, "Đăng nhập thất bại ", Toast.LENGTH_LONG).show()
                }
            }
        }
    }

    private fun showProgressDialog() {
        if (progressDialog == null) {
            progressDialog = ProgressDialog(context)
            progressDialog!!.isIndeterminate = true
            progressDialog!!.show()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        // facebook
        callbackManager.onActivityResult(requestCode, resultCode, data)

        //google
        if (requestCode == SIGN_IN_GOOGLE_PLUS) {
            val result = Auth.GoogleSignInApi.getSignInResultFromIntent(data)
            if (result.isSuccess) {
                progressDialog!!.cancel()
                Toast.makeText(context, "Đăng nhập thành công", Toast.LENGTH_LONG).show()
                val intentdangnhap = Intent(activity, Activity_TrangChu::class.java)
                startActivity(intentdangnhap)
            }
        }

    }

    override fun onConnectionFailed(connectionResult: ConnectionResult) {
        progressDialog!!.cancel()
        Toast.makeText(context, "Đăng nhập thất bại ", Toast.LENGTH_LONG).show()
    }

    companion object {
        var SIGN_IN_GOOGLE_PLUS = 111
    }


}

//        try {
//            PackageInfo info = getActivity().getPackageManager().getPackageInfo (
//                "com.example.nhuviet.thucphamxanh",
//                PackageManager. GET_SIGNATURES );
//            for ( Signature signature : info.signatures ) {
//                MessageDigest md = MessageDigest.getInstance("SHA");
//                md.update ( signature . toByteArray ());
//                Log.d ( "KeyHash" , Base64. encodeToString ( md . digest (), Base64 . DEFAULT ));
//            }
//        } catch (PackageManager.NameNotFoundException e) {
//            e.printStackTrace();
//        } catch (NoSuchAlgorithmException e) {
//            e.printStackTrace();
//        }