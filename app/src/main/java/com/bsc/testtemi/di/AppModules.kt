package com.bsc.testtemi.di


import com.bsc.testtemi.data.api.Repository
import com.bsc.testtemi.data.api.ServiceBuilder
import com.bsc.testtemi.ui.produtos.ProdutosViewModel
import com.bsc.testtemi.ui.setor.SetorFragment
import com.bsc.testtemi.ui.setor.SetorViewModel
import com.bsc.testtemi.ui.subsetor.SubSetorViewModel
import org.koin.androidx.fragment.dsl.fragment
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val setorVMModule = module {
    viewModel { SetorViewModel(repository = get()) }
}

val subSetorVMModulo = module {
    viewModel { ProdutosViewModel(repository = get()) }
}

val produtosVMModulo = module {
    viewModel { SubSetorViewModel(repository = get()) }
}

val repositoryModule = module {
    single  { Repository(api = ServiceBuilder.getRetrofitInstance()) }
}