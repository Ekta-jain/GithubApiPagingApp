package com.sample.github.views.userList

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn

import com.sample.github.network.model.UserListItem
import com.sample.github.paging.repository.UserListRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userListRepository: UserListRepository
) : ViewModel() {
  //  fun <String> Array<String>.asFlow(): Flow<String> {}
    var dataFlow:Flow<List<UserListItem>> = flowOf()
    var queryLiveData = MutableLiveData<String> ()

    val _data = MutableLiveData<List<UserListItem>>()
    val data:LiveData<List<UserListItem>> = _data

    val query = MutableLiveData<String>()

   /* @FlowPreview
    @ExperimentalCoroutinesApi
    val repo = query.asFlow()
        .debounce(300)
        .filter {
            it.trim().isEmpty().not()
        }
        .distinctUntilChanged()
        .flatMapLatest {
            userListRepository.getUserList(it).asFlow()
        }.asLiveData()
*/

    var list = userListRepository.getUserList("q").cachedIn(viewModelScope)
    fun fetchUsers() {
        dataFlow =   flow<List<UserListItem>> {
            //Fetch user
               // val response = userListRepository.getUserList(queryLiveData.value.toString()).body()
                //Log.i("Response","="+response?.items)
              var list =   ArrayList<UserListItem>()
            list.add(UserListItem(5,"https://avatars.githubusercontent.com/u/65956?v=4","username"))
            list.add(UserListItem(6,"https://avatars.githubusercontent.com/u/65956?v=4","username 1"))
            list.add(UserListItem(7,"https://avatars.githubusercontent.com/u/65956?v=4","username 2"))
            list.add(UserListItem(8,"https://avatars.githubusercontent.com/u/65956?v=4","username 3"))
            list.add(UserListItem(9,"https://avatars.githubusercontent.com/u/65956?v=4","username 4"))
             //   emit(response!!.items)

        }.take(5)

        Log.i("ResponseFetchUser", "=$dataFlow")
    }
    fun fetchUsersLiveData(query:String) {

       viewModelScope.launch {
           //Fetch user
//         //  val response = userListRepository.getUserList(query).body()
//           Log.i("Response","="+response?.items)
//           _data.postValue(response!!.items)

       }

    }

    /*@FlowPreview
    @ExperimentalCoroutinesApi
    val repo = queryLiveData.asFlow()
        .debounce(300)
        .filter {
            it.trim().isEmpty().not()
        }
        .distinctUntilChanged()
        .flatMapLatest {
            searchRepository.searchRepo(it).asFlow()
        }.asLiveData()*/

}