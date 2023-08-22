package minds.technited.invisia.assignment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import minds.technited.invisia.assignment.data.RoomDetails
import minds.technited.invisia.assignment.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    private lateinit var meetingRoom: MeetingRoomViewModel
    private lateinit var roomDetailStr: String
    private lateinit var adults: String
    private lateinit var children: String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        meetingRoom = ViewModelProvider(this)[MeetingRoomViewModel::class.java]
        binding.addRoom.setOnClickListener {
            NewRoomSheetFragment().show(supportFragmentManager, "New Room Tag")
        }
        meetingRoom.roomDetails.observe(this) {
            updateRoomDetail(it)
        }
    }

    private fun updateRoomDetail(roomDetails: List<RoomDetails>) {
        roomDetailStr = ""
        roomDetails.forEach { room ->
            roomDetailStr =  roomDetailStr.plus("Room ${room.roomNumber}")
                .plus(" Adults ${room.adults}")
                .plus(" Children ${room.children}")
                .plus(" ")
        }
        binding.roomDetails.text = roomDetailStr
    }
}