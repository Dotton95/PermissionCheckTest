package my.dotton.permissiontest

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {
    var PERMISSION_REQUEST_CODE = 10001 // 아래 권한들에 대한 request 변수
    val PERMISSION_LIST = arrayOf( // 공통으로 받는 초기 권한 목록
        Manifest.permission.ACCESS_FINE_LOCATION
    )

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_REQUEST_CODE -> {
                if (grantResults.all { it == PackageManager.PERMISSION_GRANTED }) {
                    Toast.makeText(this,"권한이 승인되었습니다.", Toast.LENGTH_LONG).show()
                }else{
                    Toast.makeText(this,"권한이 거부되었습니다.",Toast.LENGTH_LONG).show()
                    finish()
                }
            }
        }
    }
    private fun checkPermissions(permissions: Array<String>): Boolean {
        return permissions.all {
            ContextCompat.checkSelfPermission(this,it) == PackageManager.PERMISSION_GRANTED
        }
    }

    private fun initCheckPermission(){
        if(!checkPermissions(PERMISSION_LIST)) requestPermissions(PERMISSION_LIST,PERMISSION_REQUEST_CODE)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initCheckPermission()
    }
}