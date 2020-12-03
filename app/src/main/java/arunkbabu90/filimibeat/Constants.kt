package arunkbabu90.filimibeat

class Constants {
    companion object {
        @JvmStatic
        var userType: Int = -1

        const val COLLECTION_USERS = "Users"
        const val COLLECTION_FAVOURITES = "Favourites"

        const val FIELD_USER_TYPE = "userType"
        const val FIELD_FULL_NAME = "name"
        const val FIELD_ACCOUNT_VERIFIED = "activated"
        const val FIELD_DP_PATH = "dpPath"
        const val FIELD_LANGUAGE = "language"

        const val FIELD_MOVIE_ID = "movieId"
        const val FIELD_TITLE = "title"
        const val FIELD_POSTER_PATH = "posterPath"
        const val FIELD_RELEASE_YEAR = "releaseYear"
        const val FIELD_RATING = "rating"
        const val FIELD_OVERVIEW = "overview"
        const val FIELD_BACKDROP_PATH = "backdropPath"

        const val FIELD_TIMESTAMP = "timestamp"

        const val USER_TYPE_GUEST = 0
        const val USER_TYPE_PERSON = 1
    }
}