package com.example.task1_android_components.items_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.task1_android_components.R
import com.example.task1_android_components.databinding.FragmentItemsListBinding
import com.example.task1_android_components.item_details.ItemDetailsFragment
import com.example.task1_android_components.items_list.adapter.MyItemRecyclerViewAdapter
import com.example.task1_android_components.model.GetItemsUseCase
import com.example.task1_android_components.model.Item

class ItemsListFragment : Fragment(), ItemsListContract.View {

    private lateinit var binding: FragmentItemsListBinding
    private val presenter: ItemsListContract.Presenter by lazy {
        ItemsListPresenter(this, GetItemsUseCase())
    }

    private val listAdapter by lazy {
        MyItemRecyclerViewAdapter { item ->
            context?.applicationContext?.let { context ->
                presenter.onItemClick(item, context)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.list.adapter = listAdapter
        presenter.loadItems()
    }

    override fun showItems(itemsList: List<Item>) {
        listAdapter.submitList(itemsList)
    }

    override fun showItemDetailsScreen(item: Item) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmentContainerView, ItemDetailsFragment.newInstance(item))
            ?.addToBackStack(null)
            ?.commit()
    }
}