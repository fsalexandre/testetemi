package com.bsc.testtemi.ui.setor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.bsc.testtemi.R
import com.bsc.testtemi.data.api.Repository
import com.bsc.testtemi.data.model.ListSetor
import com.bsc.testtemi.ui.adapter.SetorListAdapter
import com.bsc.testtemi.ui.listener.SetorListListener
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.setor_fragment.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.java.KoinJavaComponent.get
import org.koin.java.KoinJavaComponent.inject


class SetorFragment() : Fragment(), SetorListListener {

    private var setorListAux: MutableList<ListSetor> = mutableListOf()
    private lateinit var setorAdapter: SetorListAdapter
    private val viewModel: SetorViewModel by viewModel()

    companion object {
        fun newInstance() = SetorFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        setObservers()
       return inflater.inflate(R.layout.setor_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            val savedList=savedInstanceState.getSerializable("listState") as Collection<ListSetor>
            setorListAux.addAll(savedList)
        }
        viewModel.requestSetor()
    }

    private fun setObservers(){
            viewModel.setorList.observe(viewLifecycleOwner) {
                it?.apply {
                    setorListAux.clear()
                    setorListAux.addAll(it)
                    rvListSetor.adapter = SetorListAdapter(setorListAux, requireContext(), this@SetorFragment)
                }

            }

    }

    private inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = f() as T
    }

    override fun onSelectVolume(setorItem: ListSetor, v: View) {
        val bundle = bundleOf("strSetor" to setorItem.descSetor)
        Navigation.findNavController(v).navigate(R.id.SubSetorFragment, bundle)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("listState", ArrayList(setorListAux))
    }

}