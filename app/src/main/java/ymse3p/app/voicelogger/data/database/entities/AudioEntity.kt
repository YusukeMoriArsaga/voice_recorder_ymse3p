package ymse3p.app.voicelogger.data.database.entities

import android.net.Uri
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize
import ymse3p.app.voicelogger.models.GpsData
import ymse3p.app.voicelogger.util.Constants.Companion.AUDIO_TABLE
import java.util.*

@Parcelize
@Entity(tableName = AUDIO_TABLE)
class AudioEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    val audioUri: Uri,
    val audioCreateDate: Calendar,
    val audioTitle: String,
    val audioDuration: Int,
    val gpsDataList: List<GpsData>?,
    val startAddress: String,
    val endAddress: String
) : Parcelable {
    companion object {
        fun createAudioEntity(
            audioUri: Uri,
            audioCreateDate: Calendar,
            audioTitle: String,
            audioDuration: Int,
            gpsDataList: List<GpsData>?,
            startAddress: String = "",
            endAddress: String = ""
        ): AudioEntity {
            return AudioEntity(
                0,
                audioUri,
                audioCreateDate,
                audioTitle,
                audioDuration,
                gpsDataList,
                startAddress,
                endAddress
            )
        }
    }
}