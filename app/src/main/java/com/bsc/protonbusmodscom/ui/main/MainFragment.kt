package com.bsc.protonbusmodscom.ui.main

import android.content.Intent
import android.net.Uri
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModel
import com.bsc.protonbusmodscom.R
import com.bsc.protonbusmodscom.data.adapter.ModListAdapter
import com.bsc.protonbusmodscom.data.model.ModsData
import com.bsc.protonbusmodscom.listener.ModsListListener
import android.util.DisplayMetrics
import com.google.android.gms.ads.*
import kotlinx.android.synthetic.main.main_fragment.*
import java.util.*


class MainFragment : Fragment(), ModsListListener {

    private var modsListAux: MutableList<ModsData> = mutableListOf()
    private lateinit var modsAdapter: ModListAdapter

    companion object {
        fun newInstance() = MainFragment()
        private val AD_UNIT_ID = "ca-app-pub-8654221348704464/9751371516"
    }



    private lateinit var viewModel: MainViewModel
    lateinit var mAdView : AdView
    private var initialLayoutComplete = false



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        return inflater.inflate(R.layout.main_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        setObservers()
        viewModel.requestMods()

        MobileAds.initialize(context) {}
        mAdView = AdView(context)
        adViewContainer.addView(mAdView)
        // Since we're loading the banner based on the adContainerView size, we need to wait until this
        // view is laid out before we can get the width.
        adViewContainer.viewTreeObserver.addOnGlobalLayoutListener {
            if (!initialLayoutComplete) {
                initialLayoutComplete = true
                loadBanner()
            }
        }

        setAdsListeners()
    }

    override fun onResume() {
        super.onResume()
    }

    private fun setAdsListeners(){
        mAdView.adListener = object: AdListener() {
            override fun onAdLoaded() {
                // Code to be executed when an ad finishes loading.
                Log.d("ads","onAdLoaded")
            }

            override fun onAdFailedToLoad(adError : LoadAdError) {
                // Code to be executed when an ad request fails.
                Log.d("ads","onAdFailedToLoad")
            }

            override fun onAdOpened() {
                // Code to be executed when an ad opens an overlay that
                // covers the screen.
            }

            override fun onAdClicked() {
                // Code to be executed when the user clicks on an ad.
            }

            override fun onAdClosed() {
                // Code to be executed when the user is about to return
                // to the app after tapping on an ad.
            }
        }
    }

    private fun setObservers(){

        viewModel.returnMods().observe(viewLifecycleOwner) {
            it?.apply {
                modsListAux.addAll(it)
                setModsList()
            }

        }

    }

    private fun setModsList() {

        if (rvListmod.adapter == null) {
            rvListmod.adapter = ModListAdapter(modsListAux, this.requireContext(), this)
        }else{
            modsAdapter.notifyDataSetChanged()
        }

    }

    private fun loadBanner() {
        mAdView.adUnitId = AD_UNIT_ID

        mAdView.adSize = AdSize.BANNER

        // Create an ad request.
        val adRequest = AdRequest.Builder().build()

        // Start loading the ad in the background.
        mAdView.loadAd(adRequest)
    }

    private inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = f() as T
    }

    override fun onSelectVolume(modItem: ModsData, v: View) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("http://www.protonbusmods.com/mod/"+modItem.fr_thumb_url+"-"+modItem.id_mod_bus))
        startActivity(browserIntent)
    }



}