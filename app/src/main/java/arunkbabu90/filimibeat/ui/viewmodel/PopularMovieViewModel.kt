package arunkbabu90.filimibeat.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.PagedList
import arunkbabu90.filimibeat.data.model.Movie
import arunkbabu90.filimibeat.data.repository.MoviePopularRepository
import arunkbabu90.filimibeat.data.repository.NetworkState
import io.reactivex.disposables.CompositeDisposable

class PopularMovieViewModel(private val repository: MoviePopularRepository) : ViewModel() {
    private val disposable = CompositeDisposable()

    val popularMovies: LiveData<PagedList<Movie>> by lazy {
        repository.fetchPopularMovies(disposable)
    }

    val networkState: LiveData<NetworkState> by lazy {
        repository.getNetworkState()
    }

    fun isEmpty(): Boolean = popularMovies.value?.isEmpty() ?: true

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}