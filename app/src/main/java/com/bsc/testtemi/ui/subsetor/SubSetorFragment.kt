package com.bsc.testtemi.ui.subsetor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ProgressBar
import androidx.core.os.bundleOf
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

class SubSetorFragment : Fragment(), SubSetorListListener {

    private var subSetorListAux: MutableList<ListSubSetor> = mutableListOf()
    private lateinit var subSetorAdapter: SubSetorListAdapter
    private var strSetor: String? = null
    private lateinit var viewModel: SubSetorViewModel

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
        return inflater.inflate(R.layout.subsetor_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProvider(this)[SubSetorViewModel::class.java]
        if (savedInstanceState != null) {
            val savedList=savedInstanceState.getSerializable("listState") as Collection<ListSubSetor>
            subSetorListAux.addAll(savedList)
        }

        if(subSetorListAux.size == 0){

            setObservers()
            lifecycleScope.launch {

                strSetor?.let { viewModel.requestSubSetor(it) }
            }
        }else{
            setSubSetorList()
        }

    }

    private fun setObservers(){

        viewModel.returnSubSetor().observe(viewLifecycleOwner) {
            it?.apply {
                subSetorListAux.addAll(it)
                setSubSetorList()
            }

        }
    }

    private fun setSubSetorList() {

        if (rvListSubSetor.adapter == null) {
            rvListSubSetor.adapter = SubSetorListAdapter(subSetorListAux, this.requireContext(), this)
        }else{
            subSetorAdapter.notifyDataSetChanged()
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