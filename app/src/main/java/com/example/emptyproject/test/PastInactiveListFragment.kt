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

class PastInactiveListFragment : Fragment() {

    private var _binding: TestLayoutBinding? = null
    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("LifecycleTest", "PastInactiveListFragment: onCreateView")
        _binding = TestLayoutBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding?.textText?.text = "Past Inactive List"

        binding?.btnNav?.setOnClickListener {
            (activity as? MainActivity)?.navigateToScreen(
                deeplink = MainActivity.DETAIL,
                navigationType = NavigationType.Replace
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onResume() {
        super.onResume()
        Log.d("LifecycleTest", "PastInactiveListFragment: onResume")
    }

    override fun onPause() {
        super.onPause()
        Log.d("LifecycleTest", "PastInactiveListFragment: onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("LifecycleTest", "PastInactiveListFragment: onDestroy")
    }

}