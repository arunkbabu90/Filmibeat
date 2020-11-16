package arunkbabu90.filimibeat.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import arunkbabu90.filimibeat.data.api.FIRST_PAGE
import arunkbabu90.filimibeat.data.api.TMDBInterface
import arunkbabu90.filimibeat.data.database.Movie
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SearchDataSource(private val apiService: TMDBInterface,
                       private val disposable: CompositeDisposable,
                       private val searchTerm: String)
    : PageKeyedDataSource<Int, Movie>() {

    private val _networkState: MutableLiveData<NetworkState> = MutableLiveData()
    val networkState: LiveData<NetworkState>
        get() = _networkState

    private val TAG = SearchDataSource::class.java.simpleName

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, Movie>) {
        _networkState.postValue(NetworkState.LOADING)

        disposable.add(
            apiService.searchMovie(searchTerm, FIRST_PAGE)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { movieResponse ->
                        callback.onResult(movieResponse.movies, null, FIRST_PAGE + 1)
                        _networkState.postValue(NetworkState.LOADED)
                    },
                    { e ->
                        _networkState.postValue(NetworkState.ERROR)
                        Log.e(TAG, e.message ?: "")
                    }
                )
        )
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) { }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, Movie>) {
        _networkState.postValue(NetworkState.LOADING)

        disposable.add(
            apiService.searchMovie(searchTerm, params.key)
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { movieResponse ->
                        if (movieResponse.totalPages >= params.key) {
                            // Not in last page
                            callback.onResult(movieResponse.movies, params.key + 1)
                            _networkState.postValue(NetworkState.LOADED)
                        } else {
                            // Last Page
                            _networkState.postValue(NetworkState.EOL)
                        }
                    },
                    { e ->
                        _networkState.postValue(NetworkState.ERROR)
                        Log.e(TAG, e.message ?: "Message = null")
                    }
                )
        )
    }
}