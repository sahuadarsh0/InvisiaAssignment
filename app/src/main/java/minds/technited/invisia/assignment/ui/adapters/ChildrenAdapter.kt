package minds.technited.invisia.assignment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import minds.technited.invisia.assignment.data.ChildDetails
import minds.technited.invisia.assignment.databinding.ItemAddRoomBinding
import minds.technited.invisia.assignment.databinding.ItemChildBinding

class ChildrenAdapter(private val onItemClicked: (ChildDetails) -> Unit) :
    ListAdapter<ChildDetails, ChildrenAdapter
    .ChildrenViewHolder>(DIFFUTIL_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChildrenViewHolder =
        ChildrenViewHolder(
            ItemChildBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )


    companion object {
        private val DIFFUTIL_CALLBACK = object : DiffUtil.ItemCallback<ChildDetails>() {
            override fun areItemsTheSame(oldItem: ChildDetails, newItem: ChildDetails): Boolean =
                oldItem.id == newItem.id


            override fun areContentsTheSame(oldItem: ChildDetails, newItem: ChildDetails): Boolean =
                oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: ChildrenViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    inner class ChildrenViewHolder(private val binding: ItemChildBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(childDetails: ChildDetails, onItemClicked: (ChildDetails) -> Unit) {
            binding.apply {
//                var adultsCounterData = add.text.toString().toInt()
//
//                fun updateAdultCounter() {
//                    childDetails.adults = adultsCounterData
//                }
                childRemove.setOnClickListener {
                    onItemClicked(childDetails)
                }

            }
        }

    }
}