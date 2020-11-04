package arunkbabu90.filimibeat.data.network

import arunkbabu90.filimibeat.data.database.MovieNowPlaying
import com.google.gson.annotations.SerializedName

data class NowPlayingMovieResponse (val page: Int,
                                    @SerializedName("results") val movies: List<MovieNowPlaying>,
                                    @SerializedName("total_pages") val totalPages: Int,
                                    @SerializedName("total_results") val totalMovies: Int)