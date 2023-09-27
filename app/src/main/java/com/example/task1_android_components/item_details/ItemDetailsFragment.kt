package com.example.task1_android_components.item_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.task1_android_components.databinding.FragmentItemDetailsBinding
import com.example.task1_android_components.model.Item

private const val ARG_ID = "id"
private const val ARG_NAME = "name"
private const val ARG_DESCRIPTION = "description"

class ItemDetailsFragment : Fragment() {

    private lateinit var binding: FragmentItemDetailsBinding

    private val viewModel: ItemDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        loadArguments()
    }

    private fun initObservers() {
        viewModel.itemId.observe(viewLifecycleOwner) { id ->
            binding.textViewId.text = id.toString()
        }

        viewModel.itemName.observe(viewLifecycleOwner) { name ->
            binding.textViewName.text = name
        }

        viewModel.itemDescription.observe(viewLifecycleOwner) { description ->
            binding.textViewDescription.text = description
        }
    }

    private fun loadArguments() {
        viewModel.itemId.value = arguments?.getInt(ARG_ID)
        viewModel.itemName.value = arguments?.getString(ARG_NAME)
        viewModel.itemDescription.value = arguments?.getString(ARG_DESCRIPTION)

    }

    companion object {
        fun newInstance(item: Item) = ItemDetailsFragment().apply {
            arguments = Bundle().apply {
                putInt(ARG_ID, item.id)
                putString(ARG_NAME, item.name)
                putString(ARG_DESCRIPTION, item.description)
            }
        }
    }
}