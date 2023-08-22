package minds.technited.invisia.assignment.ui.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import minds.technited.invisia.assignment.data.ChildDetails
import minds.technited.invisia.assignment.data.RoomDetails
import minds.technited.invisia.assignment.databinding.ItemAddRoomBinding
import kotlin.math.log

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
        private val childrenAdapter = ChildrenAdapter(this::onItemClicked)
        private var listChildren: ArrayList<ChildDetails> = arrayListOf()
        private var childrenCounter = 1
        private fun updateUI(){
            childrenAdapter.submitList(listChildren)
            binding.recycleChild.adapter = childrenAdapter
        }
        private fun onItemClicked(childDetails: ChildDetails) {
            listChildren.remove(childDetails)
            updateUI()
        }

        fun bind(roomDetails: RoomDetails, onItemClicked: (RoomDetails) -> Unit) {
            binding.apply {
                var adultsCounterData = adultsCounter.text.toString().toInt()
                roomNo.text = "Room ${roomDetails.roomNumber}"

                fun updateAdultCounter() {
                    adultsCounter.text = adultsCounterData.toString()
                    roomDetails.adults = adultsCounterData
                    roomDetails.children =  listChildren.size
                }

                adultsAdd.setOnClickListener {
                    if (adultsCounterData < 4) {
                        adultsCounterData++
                        updateAdultCounter()
                    } else {
                        Toast.makeText(
                            binding.root.context,
                            "MAX Adults Reached",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                adultsRemove.setOnClickListener {
                    if (adultsCounterData > 1) {
                        adultsCounterData--
                        updateAdultCounter()
                    } else {
                        Toast.makeText(
                            binding.root.context,
                            "MIN Adults Reached",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }
                deleteRoom.setOnClickListener {
                    onItemClicked(roomDetails)
                    Toast.makeText(
                        binding.root.context,
                        "Room ${roomDetails.roomNumber} removed",
                        Toast.LENGTH_SHORT
                    ).show()

                }
                childAdd.setOnClickListener {
                    if (listChildren.size < 4) {
                        childrenCounter++
                        listChildren.add(
                            ChildDetails(
                                id = childrenCounter
                            )
                        )
                        roomDetails.children =  listChildren.size
                    } else {
                        Toast.makeText(
                            binding.root.context,
                            "MAX Children Reached",
                            Toast.LENGTH_SHORT
                        ).show()

                    }
                    updateUI()
                }

            }
        }

    }
}