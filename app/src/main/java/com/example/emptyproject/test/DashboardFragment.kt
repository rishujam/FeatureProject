package com.example.emptyproject.test

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.emptyproject.MainActivity
import com.example.emptyproject.databinding.TestLayoutBinding

/*
 * Created by Sudhanshu Kumar on 23/10/24.
 */

class DashboardFragment : Fragment() {

    private var _binding: TestLayoutBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("LifecycleTest", "DashboardFrag: onCreateView")
        _binding = TestLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.textText?.text = "Dashboard Fragment"

        binding?.btnNav?.setOnClickListener {
            (activity as? MainActivity)?.navigateToScreen(
                deeplink = MainActivity.PAST_INACTIVE
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifecycleTest", "DashboardFrag: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifecycleTest", "DashboardFrag: onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("DashboardFrag", "onDestroy")
    }

}