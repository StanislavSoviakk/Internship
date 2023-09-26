package com.example.task1_android_components.item_details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.task1_android_components.Item
import com.example.task1_android_components.databinding.FragmentItemDetailsBinding

private const val ARG_ID = "id"
private const val ARG_NAME = "name"
private const val ARG_DESCRIPTION = "description"

class ItemDetailsFragment : Fragment() {

    private lateinit var binding: FragmentItemDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentItemDetailsBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.textViewId.text = arguments?.getInt(ARG_ID).toString()
        binding.textViewName.text = arguments?.getString(ARG_NAME)
        binding.textViewDescription.text = arguments?.getString(ARG_DESCRIPTION)
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