package com.bsc.testtemi.ui.produtos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import com.bsc.testtemi.MainActivity
import com.bsc.testtemi.R
import com.bsc.testtemi.data.model.ListProdutos
import com.bsc.testtemi.data.model.ListSubSetor
import com.bsc.testtemi.ui.adapter.ProdutosListAdapter
import com.bsc.testtemi.ui.listener.ProdutosListListener
import com.bsc.testtemi.ui.subsetor.SubSetorViewModel
import kotlinx.android.synthetic.main.produtos_fragment.*
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class ProdutosFragment: Fragment(), ProdutosListListener {

    private var ProdutosListAux: MutableList<ListProdutos> = mutableListOf()
    private lateinit var ProdutosAdapter: ProdutosListAdapter
    private var strSubSetor: String? = null
    private var strQuery: String? = null
    private val viewModel: ProdutosViewModel by viewModel()

    companion object {
        fun newInstance() = ProdutosFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
            arguments?.let {
                strSubSetor = it.getString("strSubSetor")
                strQuery = it.getString("strQuery")
            }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setObservers()
        return inflater.inflate(R.layout.produtos_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (savedInstanceState != null) {
            val savedList=savedInstanceState.getSerializable("listState") as Collection<ListProdutos>
            ProdutosListAux.addAll(savedList)
        }

        if (strSubSetor != null) {
            strSubSetor?.let { viewModel.requestProdutos(it) }
        } else {
            strQuery?.let {
                viewModel.requestProdutosbyQuery(it)
            }
        }

    }


    private fun setObservers() {

        viewModel.produtoList.observe(viewLifecycleOwner) {
            it?.apply {
                ProdutosListAux.clear()
                ProdutosListAux.addAll(it)
                setProdutosList()
            }
        }

        viewModel.produtoByQueryList.observe(viewLifecycleOwner) {
            it?.apply {
                ProdutosListAux.clear()
                ProdutosListAux.addAll(it)
                setProdutosList()
            }
        }
    }

    private fun setProdutosList() {
        rvListProdutos.adapter = ProdutosListAdapter(ProdutosListAux, this.requireContext(), this)
    }

    private inline fun <VM : ViewModel> viewModelFactory(crossinline f: () -> VM) =
        object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T = f() as T
        }

    override fun onSelectVolume(ProdutosItem: ListProdutos, v: View) {
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putSerializable("listState", ArrayList(ProdutosListAux))
    }
}
