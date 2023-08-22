package minds.technited.invisia.assignment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import minds.technited.invisia.assignment.databinding.FragmentNewRoomSheetBinding

class NewRoomSheetFragment : BottomSheetDialogFragment() {

    private lateinit var binding : FragmentNewRoomSheetBinding
    private lateinit var meetingRoom: MeetingRoomViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val activity = requireActivity()
        meetingRoom = ViewModelProvider(activity)[MeetingRoomViewModel::class.java]

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNewRoomSheetBinding.inflate(inflater,container,false)
        binding.apply.setOnClickListener{
            saveAction()
        }
        return binding.root
    }

    private fun saveAction() {
        meetingRoom.roomDetails.value
    }

}