package minds.technited.invisia.assignment

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import minds.technited.invisia.assignment.data.RoomDetails

class MeetingRoomViewModel : ViewModel() {
    var roomDetails = MutableLiveData<List<RoomDetails>>()
}