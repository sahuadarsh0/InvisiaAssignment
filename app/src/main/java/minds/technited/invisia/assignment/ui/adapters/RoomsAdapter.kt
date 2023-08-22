package minds.technited.invisia.assignment.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import minds.technited.invisia.assignment.data.RoomDetails
import minds.technited.invisia.assignment.databinding.ItemAddRoomBinding

class RoomsAdapter(private val onItemClicked: (RoomDetails) -> Unit) :
    ListAdapter<RoomDetails, RoomsAdapter
    .RoomsViewHolder>(DIFFUTIL_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RoomsViewHolder =
        RoomsViewHolder(
            ItemAddRoomBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )


    companion object {
        private val DIFFUTIL_CALLBACK = object : DiffUtil.ItemCallback<RoomDetails>() {
            override fun areItemsTheSame(oldItem: RoomDetails, newItem: RoomDetails): Boolean =
                oldItem.roomNumber == newItem.roomNumber


            override fun areContentsTheSame(oldItem: RoomDetails, newItem: RoomDetails): Boolean =
                oldItem == newItem

        }
    }

    override fun onBindViewHolder(holder: RoomsViewHolder, position: Int) =
        holder.bind(getItem(position), onItemClicked)

    inner class RoomsViewHolder(private val binding: ItemAddRoomBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(roomDetails: RoomDetails, onItemClicked: (RoomDetails) -> Unit) {
            binding.apply {
                var adultsCounterData = adultsCounter.text.toString().toInt()

                fun updateAdultCounter() {
                    adultsCounter.text = adultsCounterData.toString()
                    roomDetails.adults = adultsCounterData
                    onItemClicked(roomDetails)
                }

                adultsAdd.setOnClickListener {
                    if (adultsCounterData < 4) {
                        adultsCounterData++
                        updateAdultCounter()
                    } else {
                        Toast.makeText(binding.root.context, "MAX Adults Reached", Toast.LENGTH_SHORT).show()
                    }
                }
                adultsRemove.setOnClickListener {
                    adultsCounterData--
                    updateAdultCounter()
                }
            }
        }

    }
}