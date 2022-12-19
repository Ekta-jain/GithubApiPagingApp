package com.sample.github.views.userList

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.sample.github.R
import com.sample.github.databinding.FragmentUserListBinding
import com.sample.github.domain.UserListItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : Fragment() {
    private val viewModel: UserListViewModel by viewModels()

    @Inject
    lateinit var adapter: UserPagingAdapter

    private var _binding: FragmentUserListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_user_list, container, false
        )
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.recyclerView.adapter = adapter

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.list.observe(viewLifecycleOwner){
            Log.i("GetData","="+it)
            adapter.submitData(lifecycle, it)
        }
       // callingUserList()

       /* val job = GlobalScope.launch(Dispatchers.Default) {
            Log.d("TAG","Starting the long calculation...")

            // running the loop from 30 to 40
            for(i in 30..40)
            {
                delay(1000)
                Log.d("TAG", "Result for i =$i : ")
            }
            Log.d("TAG", "Ending the long calculation...")
        }

        runBlocking {
            delay(2000)
            job.cancel()
            Log.d("TAG", "Main Thread is Running")
        }
       var job2 =    CoroutineScope(Dispatchers.IO).launch{
            repeat(4){
                delay(1000)
                Log.d("CoroutineExample","=started"+Thread.currentThread().name)
            }

        }


       runBlocking {

           withContext(Dispatchers.Default){
               job2.join()
               Log.d("CoroutineExample","=thread is continue"+Thread.currentThread().name)

           }
       }
*/

    }

    fun callingUserList() {
        //Observe on viewmodel for data
        /*viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }*/

        var dataFlow = flow<List<UserListItem>> {
            //Fetch user
            // val response = userListRepository.getUserList(query).body()
            //Log.i("Response","="+response?.items)
            var list = ArrayList<UserListItem>()
            list.add(
                UserListItem(
                    5,
                    "https://avatars.githubusercontent.com/u/65956?v=4",
                    "username"
                )
            )
            list.add(
                UserListItem(
                    6,
                    "https://avatars.githubusercontent.com/u/65956?v=4",
                    "username 1"
                )
            )
            list.add(
                UserListItem(
                    7,
                    "https://avatars.githubusercontent.com/u/65956?v=4",
                    "username 2"
                )
            )
            list.add(
                UserListItem(
                    8,
                    "https://avatars.githubusercontent.com/u/65956?v=4",
                    "username 3"
                )
            )
            list.add(
                UserListItem(
                    9,
                    "https://avatars.githubusercontent.com/u/65956?v=4",
                    "username 4"
                )
            )
            emit(list)

        }.flowOn(Dispatchers.Default)
       // viewModel.fetchUsers()
        lifecycleScope.launch {
            viewModel.dataFlow.debounce(1000).collect {
                Toast.makeText(activity, "123collect" + it, Toast.LENGTH_SHORT).show()
               // adapter.submitList(it)
            }
            /*viewModel.dataFlow.collect {
                Toast.makeText(activity, "" + it, Toast.LENGTH_SHORT).show()
                //  Log.i("Get","=")
                adapter.submitList(it)
            }*/
        }

        /*CoroutineScope(Dispatchers.Main).launch {
            dataFlow.collect {
                // when it is done
            }
        }*/

        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(activity, ":" + newText, Toast.LENGTH_SHORT).show()
                viewModel.queryLiveData.value = newText
               // viewModel.fetchUsers()
                return false
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }


}