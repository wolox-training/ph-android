package ar.com.wolox.android.training.model

import com.google.gson.annotations.SerializedName
import java.util.*

data class News (var userID: String,
                 var title: String,
                 var text: String,
                 @SerializedName("picture") var pictureString: String,
                 @SerializedName("createdAt") var date: String,
                 var likes: ArrayList<Int>) {
}