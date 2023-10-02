package com.example.task1_android_components.items_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.task1_android_components.R
import com.example.task1_android_components.base.BaseFragment
import com.example.task1_android_components.databinding.FragmentItemsListBinding
import com.example.task1_android_components.item_details.ItemDetailsFragment
import com.example.task1_android_components.items_list.adapter.MyItemRecyclerViewAdapter
import com.example.task1_android_components.model.Item
import com.example.task1_android_components.preferences.PreferencesManager

class ItemsListFragment :
    BaseFragment<ItemsListEvent, ItemsListState, ItemsListViewModel, ItemsListViewModelFactory>(
        ItemsListViewModel::class.java
    ) {

    private lateinit var binding: FragmentItemsListBinding

    private val listAdapter by lazy {
        MyItemRecyclerViewAdapter {
            onItemClick(it)
        }
    }

    override fun initView() {
        binding.list.adapter = listAdapter
    }

    override fun initData() {
        viewModel.loadItems()
    }

    override fun render(state: ItemsListState) {
        showItems(state.itemsList)
    }

    override fun initViewModel() {
        viewModel = createViewModel(
            ItemsListViewModelFactory(
                PreferencesManager(requireContext().applicationContext),
                ItemsListReducer()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemsListBinding.inflate(inflater, container, false)

        return binding.root
    }

    private fun onItemClick(item: Item) {
        saveItemInLocalStorage(itemId = item.id)
        showItemDetailsScreen(item = item)
    }

    private fun saveItemInLocalStorage(itemId: Int) {
        viewModel.saveLastOpenedItemId(itemId)
    }

    private fun showItems(itemsList: List<Item>) {
        listAdapter.submitList(itemsList)
    }

    private fun showItemDetailsScreen(item: Item) {
        activity?.supportFragmentManager
            ?.beginTransaction()
            ?.replace(R.id.fragmentContainerView, ItemDetailsFragment.newInstance(item))
            ?.addToBackStack(null)
            ?.commit()
    }
}