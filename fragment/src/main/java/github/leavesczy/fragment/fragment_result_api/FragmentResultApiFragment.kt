package github.leavesczy.fragment.fragment_result_api

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import github.leavesczy.fragment.R
import kotlin.random.Random

/**
 * @Author: leavesCZY
 * @Date: 2021/9/6 17:48
 * @Desc:
 * @Github：https://github.com/leavesCZY
 */

const val requestKeyToActivity = "requestKeyToActivity"

private const val requestKeyFirst = "requestKeyFirst"

private const val requestKeySecond = "requestKeySecond"

class FragmentResultApiAFragment : Fragment(R.layout.fragment_fragment_result_api_a) {

    private val requestKeyFirst = "requestKeyFirst"

    private val requestKeySecond = "requestKeySecond"

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnSend = view.findViewById<Button>(R.id.btnSend)
        val btnSendMsgToActivity = view.findViewById<Button>(R.id.btnSendMsgToActivity)
        val tvMessage = view.findViewById<TextView>(R.id.tvMessage)
        btnSend.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("fromFragmentResultApiAFragment", Random.nextInt().toString())
            //向对 requestKeyFirst 进行监听的 Fragment 传递数据
            parentFragmentManager.setFragmentResult(requestKeyFirst, bundle)
        }
        btnSendMsgToActivity.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("fromFragmentResultApiAFragment", Random.nextInt().toString())
            //向对 requestKeyToActivity 进行监听的 Activity 传递数据
            parentFragmentManager.setFragmentResult(requestKeyToActivity, bundle)
        }
        //对 requestKeySecond 进行监听
        parentFragmentManager.setFragmentResultListener(
            requestKeySecond,
            this,
            { requestKey, result ->
                tvMessage.text = "requestKey: $requestKey \n result: $result"
            })
    }

}

class FragmentResultApiBFragment : Fragment(R.layout.fragment_fragment_result_api_b) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val btnSend = view.findViewById<Button>(R.id.btnSend)
        val tvMessage = view.findViewById<TextView>(R.id.tvMessage)
        btnSend.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("fromFragmentResultApiBFragment", Random.nextInt().toString())
            //向对 requestKeySecond 进行监听的 Fragment 传递数据
            parentFragmentManager.setFragmentResult(requestKeySecond, bundle)
        }
        //对 requestKeyFirst 进行监听
        parentFragmentManager.setFragmentResultListener(
            requestKeyFirst,
            this,
            { requestKey, result ->
                tvMessage.text = "requestKey: $requestKey \n result: $result"
            })
    }

}