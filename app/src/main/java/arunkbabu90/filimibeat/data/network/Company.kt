package arunkbabu90.filimibeat.data.network

import com.google.gson.annotations.SerializedName

data class Company(val id: Int,
                   val name: String,
                   @SerializedName("logo_path") val logoPath: String,
                   @SerializedName("origin_country") val country: String)