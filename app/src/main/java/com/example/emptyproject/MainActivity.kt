package com.example.emptyproject


import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityOptionsCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.emptyproject.databinding.ActivityMainBinding
import com.example.emptyproject.test.CommunityDetailFrag
import com.example.emptyproject.test.DashboardFragment
import com.example.emptyproject.test.NavigationType
import com.example.emptyproject.test.PastInactiveListFragment
import com.example.emptyproject.test.Util
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

    companion object {
        const val DETAIL = "DETAIL"
        const val DASHBOARD = "DASHBOARD"
        const val PAST_INACTIVE = "PAST_INACTIVE"
    }

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var fileUrl = ""
        var fileName = ""

//        val permissionLauncher = registerForActivityResult(
//            ActivityResultContracts.RequestMultiplePermissions()
//        ) { isGranted ->
//            if (
//                isGranted[Manifest.permission.READ_EXTERNAL_STORAGE] == true &&
//                isGranted[Manifest.permission.WRITE_EXTERNAL_STORAGE] == true
//            ) {
//                Toast.makeText(this@MainActivity, "Permission Granted", Toast.LENGTH_LONG).show()
//                lifecycleScope.launch(Dispatchers.IO) {
//                    Util.downloadFileWithUrl(
//                        url = fileUrl,
//                        fileName = fileName,
//                        filePath = null,
//                        context = this@MainActivity,
//                        null
//                    )
//                }
//            } else {
//                Toast.makeText(this@MainActivity, "Please grant permission", Toast.LENGTH_LONG)
//                    .show()
//            }
//        }

        binding.btnCommunity.setOnClickListener {
            if (!hasCameraPermission()) {
                ActivityCompat.requestPermissions(
                    this, arrayOf(Manifest.permission.CAMERA), 0
                )
            }
//            lifecycleScope.launch(Dispatchers.IO) {
//                Util.downloadFileWithUrl(
//                    url = "https://research.nhm.org/pdfs/10840/10840-001.pdf",
//                    "testing",
//                    null,
//                    this@MainActivity
//                ) { url, name ->
//                    fileUrl = url
//                    fileName = name
//                    permissionLauncher.launch(
//                        arrayOf(
//                            Manifest.permission.WRITE_EXTERNAL_STORAGE,
//                            Manifest.permission.READ_EXTERNAL_STORAGE
//                        )
//                    )
//                }
//            }
        }
    }

    private fun hasCameraPermission() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.CAMERA
    ) == PackageManager.PERMISSION_GRANTED

    /**
     * @param data taken from 1.0 code
     * @param dataFeed taken from 1.0 code referred as extra data
     * @param dataRemoveList taken from 1.0 code
     * @param navigationType Describe how the navigation should be performed
     * be called of the fragment below this fragment when back pressed)
     */
    fun navigateToScreen(
        deeplink: String,
        navigationType: NavigationType = NavigationType.AddToBackStackAndReplace,
    ) {
        if (navigationType is NavigationType.CreateNewStack) {
//            openCommunityActivityWithResult(
//                context = this,
//                deepLinkText = deeplink,
//                bModel = data,
//                extraData = dataFeed,
//                analyticsUIInfo = analyticsInfo,
//                removedList = dataRemoveList,
//                activityLauncher = resultLauncher,
//                toolBarTitle = toolBarTitle
//            )
//            return
        }
        val frag = when (deeplink) {
            DETAIL -> {
                val fragment = CommunityDetailFrag()
                fragment
            }

            DASHBOARD -> {
                DashboardFragment()
            }

            PAST_INACTIVE -> {
                PastInactiveListFragment()
            }

            else -> null
        }
        addFragment(frag, navigationType)
    }

    private fun addFragment(
        frag: Fragment?,
        navigationType: NavigationType
    ) {
        frag?.let {
            when (navigationType) {
                is NavigationType.AddToBackStackAndAdd -> {
                    supportFragmentManager.beginTransaction().apply {
                        add(R.id.flCommunity, frag)
                        addToBackStack(frag.tag)
                        commit()
                    }
                }

                is NavigationType.Replace -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.flCommunity, frag)
                        commit()
                    }
                }

                is NavigationType.AddToBackStackAndReplace -> {
                    supportFragmentManager.beginTransaction().apply {
                        replace(R.id.flCommunity, frag)
                        addToBackStack(frag.tag)
                        commit()
                    }
                }

                is NavigationType.DestroyBackStack -> {
                    setResult(RESULT_OK, intent)
                    finish()
                }

                is NavigationType.PopFromStack -> {
                    supportFragmentManager.popBackStackImmediate()
                    navigationType.deepLink?.let {
                        navigateToScreen(
                            deeplink = navigationType.deepLink,
                            navigationType = NavigationType.Replace
                        )
                    }
                }

                is NavigationType.Refresh -> {
                    val currFrag = supportFragmentManager.fragments.last()
                    supportFragmentManager.beginTransaction().apply {
                        detach(currFrag)
                        commitNow()
                    }
                    supportFragmentManager.beginTransaction().apply {
                        attach(currFrag)
                        commit()
                    }
                }

                else -> {}
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (supportFragmentManager.fragments.last() is CommunityDetailFrag) {

        }
    }
}