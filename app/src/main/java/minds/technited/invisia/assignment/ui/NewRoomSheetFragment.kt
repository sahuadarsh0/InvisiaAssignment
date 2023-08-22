package minds.technited.invisia.assignment.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import minds.technited.invisia.assignment.data.RoomDetails
import minds.technited.invisia.assignment.databinding.FragmentNewRoomSheetBinding
import minds.technited.invisia.assignment.ui.adapters.RoomsAdapter

class NewRoomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding: FragmentNewRoomSheetBinding
    private lateinit var meetingRoom: MeetingRoomViewModel
    private val roomsAdapter = RoomsAdapter(this::onItemClicked)
    private lateinit var listRooms: ArrayList<RoomDetails>
    private var roomCounter = 1

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val activity = requireActivity()
        meetingRoom = ViewModelProvider(activity)[MeetingRoomViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewRoomSheetBinding.inflate(inflater, container, false)
        binding.apply {

            //Performing First Operation
            listRooms = arrayListOf(
                RoomDetails(
                    roomNumber = roomCounter,
                    adults = 1,
                    children = 0
                )
            )
            updateUI()


            apply.setOnClickListener {
                saveAction()
            }
            newRoom.setOnClickListener {
                if (listRooms.size < 9) {
                    roomCounter++
                    listRooms.add(
                        RoomDetails(
                            roomNumber = roomCounter,
                            adults = 1,
                            children = 0
                        )
                    )
                    Toast.makeText(context, "Room $roomCounter Added", Toast.LENGTH_SHORT).show()
                    updateUI()
                } else {
                    Toast.makeText(context, "MAX 9 Rooms Can be Created", Toast.LENGTH_SHORT).show()
                }
            }
            closeSheet.setOnClickListener {
                dismiss()
            }
        }
        return binding.root
    }

    private fun saveAction() {
        meetingRoom.roomDetails.value = listRooms
        dismiss()
    }

    private fun onItemClicked(roomDetails: RoomDetails) {
        if (listRooms.size > 1) {
            listRooms.remove(roomDetails)
            updateUI()
        } else Toast.makeText(context, "MIN 1 Room Needed", Toast.LENGTH_SHORT).show()

    }

    private fun updateUI() {
        roomsAdapter.submitList(listRooms)
        binding.recycleRooms.adapter = roomsAdapter
    }

}