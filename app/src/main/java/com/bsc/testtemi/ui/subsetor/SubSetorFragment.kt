package com.bsc.testtemi.ui.subsetor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.bsc.testtemi.MainActivity
import com.bsc.testtemi.R
import com.bsc.testtemi.data.model.ListSetor
import com.bsc.testtemi.data.model.ListSubSetor
import com.bsc.testtemi.ui.adapter.SetorListAdapter
import com.bsc.testtemi.ui.adapter.SubSetorListAdapter
import com.bsc.testtemi.ui.listener.SetorListListener
import com.bsc.testtemi.ui.listener.SubSetorListListener
import com.bsc.testtemi.ui.setor.SetorFragment
import com.bsc.testtemi.ui.setor.SetorViewModel
import kotlinx.android.synthetic.main.main_activity.*
import kotlinx.android.synthetic.main.setor_fragment.*
import kotlinx.android.synthetic.main.subsetor_fragment.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class SubSetorFragment : Fragment(), SubSetorListListener {

    private var subSetorListAux: MutableList<ListSubSetor> = mutableListOf()
    private lateinit var subSetorAdapter: SubSetorListAdapter
    private var strSetor: String? = null
    private val viewModel: SubSetorViewModel by viewModel()

    companion object {
        fun newInstance() = SubSetorFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            strSetor = it.getString("strSetor")
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setObservers()
        return inflater.inflate(R.layout.subsetor_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        if (savedInstanceState != null) {
            val savedList=savedInstanceState.getSerializable("listState") as Collection<ListSubSetor>
            subSetorListAux.addAll(savedList)
        }
        viewModel.requestSubSetor(strSetor.toString())
    }

    private fun setObservers(){

        viewModel.subSetorList.observe(viewLifecycleOwner)  {
            it?.apply {
                    subSetorListAux.clear()
                    subSetorListAux.addAll(it)
                    rvListSubSetor.adapter = SubSetorListAdapter(subSetorListAux, requireContext(), this@SubSetorFragment)
            }

        }
    }

    private inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) = object : ViewModelProvider.Factory {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = f() as T
    }

    override fun onSelectVolume(subSetorItem: ListSubSetor, v: View) {
        val bundle = bundleOf("strSubSetor" to subSetorItem.desc_subsetor)
        Navigation.findNavController(v).navigate(R.id.ProdutosFragment, bundle)

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("listState", ArrayList(subSetorListAux))
    }

}