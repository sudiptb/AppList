package sudipta.`in`.newwaytocode.ui.fragment.tab

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import com.applist.R
import com.applist.databinding.FragmentApplicationBinding
import com.applist.ui.adapter.ItemAdapter
import com.applist.viewmodel.MyViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import sudipta.`in`.newwaytocode.network.dto.Item

class ApplicationFragment : Fragment(R.layout.fragment_application) {

    private lateinit var binding: FragmentApplicationBinding
    private lateinit var localList : ArrayList<Item>
    private val viewModel by viewModel<MyViewModel>()
    private lateinit var itemAdapter: ItemAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding = FragmentApplicationBinding.bind(view)

        viewModel.getData()

        viewModel.loading.observe(viewLifecycleOwner){
            if (it){
                binding.progress.isVisible = true
            }else{
                binding.progress.isVisible = false
            }
        }
        viewModel.loginResponse.observe(viewLifecycleOwner){
            it?.let {
                println("result : $it")
                localList = it.itemList as ArrayList<Item>
                setUpList(it.itemList)
            }
        }

        binding.etSearch.addTextChangedListener {
            filter(it.toString())
        }

    }

    private fun filter(text: String){
        val filterList = ArrayList<Item>()
        localList.forEach {
            if (it.app_name?.lowercase()!!.contains(text.lowercase())){
                filterList.add(it)
            }
        }
        itemAdapter.addData(filterList)
    }

    private fun setUpList(itemList: List<Item>?) {
        itemAdapter = ItemAdapter()
        itemAdapter.submitList(itemList)
        binding.rvList.adapter = itemAdapter
    }
}