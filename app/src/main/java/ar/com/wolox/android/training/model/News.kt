package ar.com.wolox.android.training.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
import java.util.*

data class News (@SerializedName("userId") var userId: Int,
                 @SerializedName("id") var newsId: Int,
                 var title: String,
                 var text: String,
                 @SerializedName("picture") var pictureString: String,
                 @SerializedName("createdAt") var date: String,
                 var likes: ArrayList<Int>) : Serializable {
}