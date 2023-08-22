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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = requireActivity()
        meetingRoom = ViewModelProvider(activity)[MeetingRoomViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewRoomSheetBinding.inflate(inflater, container, false)
        binding.apply {

            recycleRooms.adapter = roomsAdapter

            apply.setOnClickListener {
                saveAction()
            }
            newRoom.setOnClickListener {
                if (listRooms.size < 9) {
                    listRooms.add(
                        RoomDetails(
                            roomNumber = roomCounter,
                            adults = 1,
                            children = 0
                        )
                    )
                    roomsAdapter.submitList(listRooms)
                    roomCounter++
                } else {
                    Toast.makeText(context, "MAX 9 Rooms Can be Created", Toast.LENGTH_SHORT).show()
                }
            }
        }
        return binding.root
    }

    private fun saveAction() {
        meetingRoom.roomDetails.value
    }

    private fun onItemClicked(roomDetails: RoomDetails) {
        listRooms.add(roomDetails)
    }

}