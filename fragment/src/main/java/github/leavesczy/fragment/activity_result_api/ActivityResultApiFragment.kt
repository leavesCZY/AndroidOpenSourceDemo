package github.leavesczy.fragment.activity_result_api

import android.Manifest
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import github.leavesczy.fragment.R

/**
 * @Author: leavesCZY
 * @Date: 2021/9/6 16:03
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */
class ActivityResultApiFragment :
    Fragment(R.layout.fragment_activity_result_api) {

    private val getLocation: ActivityResultLauncher<String> =
        registerForActivityResult(
            GetLocation()
        ) { result ->
            Toast.makeText(activity, result, Toast.LENGTH_SHORT).show()
        }

    private val requestPermissions: ActivityResultLauncher<Array<String>> =
        registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) { result ->
            for (entry in result) {
                Toast.makeText(activity, entry.key + " " + entry.value, Toast.LENGTH_SHORT)
                    .show()
            }
        }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val btnStartActivity = view.findViewById<Button>(R.id.btnStartActivity)
        val btnRequestPermissions = view.findViewById<Button>(R.id.btnRequestPermissions)
        btnStartActivity.setOnClickListener {
            getLocation.launch("业志陈")
        }
        btnRequestPermissions.setOnClickListener {
            requestPermissions.launch(
                arrayOf(
                    Manifest.permission.ACCESS_NETWORK_STATE,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            )
        }
    }

}