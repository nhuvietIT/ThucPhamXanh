package com.example.nhuviet.thucphamxanh.View.DangNhap_DangKi_Activity.Fragment

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.support.v4.widget.CompoundButtonCompat
import android.support.v7.widget.SwitchCompat
import android.util.Log
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CompoundButton
import android.widget.EditText
import android.widget.Toast

import com.example.nhuviet.thucphamxanh.Model.ObjectClass.NhanVien
import com.example.nhuviet.thucphamxanh.Presenter.DangKi.Presenter_Code_DangKi
import com.example.nhuviet.thucphamxanh.R
import com.example.nhuviet.thucphamxanh.View.DangNhap_DangKi_Activity.DangNhap_Activity
import com.example.nhuviet.thucphamxanh.View.DangNhap_DangKi_Activity.View_DangKi
import com.example.nhuviet.thucphamxanh.View.TrangChu.Activity_TrangChu

/**
 * Created by nhuvi on 16/05/2017.
 */

class Fragment_Dangky : Fragment(), View_DangKi, View.OnClickListener, View.OnFocusChangeListener {
    internal var presenter_code_dangKi: Presenter_Code_DangKi
    internal var dangki: Button
    internal var hovaten: EditText
    internal var email: EditText
    internal var matkhau: EditText
    internal var nhaplaimatkhau: EditText
    internal var smadocquyen: SwitchCompat
    internal var inputhoten: TextInputLayout
    internal var inputemail: TextInputLayout
    internal var inputmatkhau: TextInputLayout
    internal var inputnhaplaimatkhau: TextInputLayout
    internal var kiemtrathongtin: Boolean? = false

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val view = inflater!!.inflate(R.layout.layout_fragment_dangki, container, false)

        dangki = view.findViewById(R.id.bt_dangky) as Button

        hovaten = view.findViewById(R.id.ed_hotenkt) as EditText
        email = view.findViewById(R.id.ed_emailkt) as EditText
        matkhau = view.findViewById(R.id.ed_passkt) as EditText
        nhaplaimatkhau = view.findViewById(R.id.ed_passnhaplaikt) as EditText

        smadocquyen = view.findViewById(R.id.s_madocquyen) as SwitchCompat

        inputhoten = view.findViewById(R.id.ed_input_hotenkt) as TextInputLayout
        inputemail = view.findViewById(R.id.ed_input_emailkt) as TextInputLayout
        inputmatkhau = view.findViewById(R.id.ed_input_passkt) as TextInputLayout
        inputnhaplaimatkhau = view.findViewById(R.id.ed_input_passnhaplaikt) as TextInputLayout

        hovaten.onFocusChangeListener = this
        email.onFocusChangeListener = this
        nhaplaimatkhau.onFocusChangeListener = this

        presenter_code_dangKi = Presenter_Code_DangKi(this)
        dangki.setOnClickListener(this)
        return view
    }

    override fun DangKiThanhCong() {
        Toast.makeText(activity, "Đăng ký thành công !", Toast.LENGTH_SHORT).show()
        val intentdangnhap = Intent(activity, DangNhap_Activity::class.java)
        startActivity(intentdangnhap)

    }


    override fun DAngKiThatBai() {
        Toast.makeText(activity, "Đăng ký thất bại !", Toast.LENGTH_SHORT).show()
    }

    override fun onClick(v: View) {
        val id = v.id
        when (id) {
            R.id.bt_dangky -> {
                Bt_DagKy()
            }
        }
    }

    internal var Madocquyen = ""
    private fun Bt_DagKy() {
        val Hoten = hovaten.text.toString()
        val Email = email.text.toString()
        val Matkhau = matkhau.text.toString()
        val NhapLaiMatKhau = nhaplaimatkhau.text.toString()
        smadocquyen.setOnCheckedChangeListener { buttonView, isChecked -> Madocquyen = isChecked.toString() + "" }

        if (kiemtrathongtin!!) {
            val nhanvien = NhanVien()
            nhanvien.tenNhanVien = Hoten
            nhanvien.tenDangNhap = Email
            nhanvien.matKhau = Matkhau
            nhanvien.maDocQuyen = Madocquyen
            nhanvien.maLoaiNhanVien = 2

            presenter_code_dangKi.ThucHienDangKi(nhanvien)
        } else {
            Log.d("kiemtra", "Dk that bai")
        }
    }

    override fun onFocusChange(v: View, hasFocus: Boolean) {
        val id = v.id
        when (id) {
            R.id.ed_hotenkt -> {
                if (!hasFocus) {
                    val chuoi = (v as EditText).text.toString()
                    if (chuoi.trim { it <= ' ' } == "" || chuoi == null) {
                        inputhoten.isErrorEnabled = true
                        inputhoten.error = "Bạn chưa nhập mục này !"
                        kiemtrathongtin = false
                    } else {
                        inputhoten.isErrorEnabled = false
                        inputhoten.error = ""
                        kiemtrathongtin = true
                    }
                }
            }
            R.id.ed_emailkt -> {
                if (!hasFocus) {
                    val chuoi = (v as EditText).text.toString()
                    if (chuoi.trim { it <= ' ' } == "" || chuoi == null) {
                        inputemail.isErrorEnabled = true
                        inputemail.error = "Bạn chưa nhập mục này !"
                        kiemtrathongtin = false
                    } else {
                        val kiemtraemail = Patterns.EMAIL_ADDRESS.matcher(chuoi).matches()
                        if (!kiemtraemail) {
                            inputemail.isErrorEnabled = true
                            inputemail.error = "Đây không phải là địa chỉ Email !"
                            kiemtrathongtin = false
                        } else {
                            inputemail.isErrorEnabled = false
                            inputemail.error = ""
                            kiemtrathongtin = true
                        }
                    }
                }
            }
            R.id.ed_passkt ->
                R.id.ed_passnhaplaikt
            -> if (!hasFocus) {
                val chuoi = (v as EditText).text.toString()
                val matkhaus = matkhau.text.toString()
                if (chuoi != matkhaus) {
                    inputnhaplaimatkhau.isErrorEnabled = true
                    inputnhaplaimatkhau.error = "Mật khẩu không trùng khớp !"
                    kiemtrathongtin = false
                } else {
                    inputnhaplaimatkhau.isErrorEnabled = false
                    inputnhaplaimatkhau.error = ""
                    kiemtrathongtin = true
                }
                break
            }
        }
    }
}
