/*
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
import androidx.lifecycle.lifecycleScope
import com.sample.github.R
import com.sample.github.databinding.FragmentUserListBinding
import com.sample.github.network.model.UserListItem
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragmentV2 : Fragment() {
    private val viewModel: UserListViewModel by viewModels()

    @Inject
    lateinit var adapter: UsersListAdapter

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
        callingUserList()
        testCoroutines()
        GlobalScope.launch(Dispatchers.IO) {}
        GlobalScope.launch{}
        CoroutineScope(Dispatchers.IO).launch{}
        lifecycleScope.launch(Dispatchers.IO){}
        lifecycleScope.launch{}
        runBlocking { // this: CoroutineScope
            launch {
                delay(200L)
                Log.d("CheezyCodeS","Task from runBlocking")
                println("Task from runBlocking")
            }

            coroutineScope { // Creates a new coroutine scope
                launch {
                    delay(900L)
                    Log.d("CheezyCodeS","Task from nested launch")

                    //println("Task from nested launch")
                }

                delay(100L)
                Log.d("CheezyCodeS","Task from coroutine scope")

                println("Task from coroutine scope") // This line will be printed before nested launch
            }
            Log.d("CheezyCodeS","Coroutine scope is over")
            println("Coroutine scope is over") // This line is not printed until nested launch completes
        }

        GlobalScope.launch {
        val data = producer()
            .onStart {
                Log.d("CheezyCode","Starting out")
            }
            .onEach {
                Log.d("CheezyCode","About to emit -"+it)
            }
            .onCompletion {
                Log.d("CheezyCode","Completed"+it)
            }
            data.collect {
                Log.d("CheezyCode",it.toString())
            }
        }

        GlobalScope.launch(Dispatchers.Main){

            producer()
                .flowOn(Dispatchers.IO)
                .collect{
                    Log.d("CheezyCode1",Thread.currentThread().name)
                }
        }

    }

    fun producer() = flow<Int> {
        val list = listOf(1,2,3,4,5,7,8,10)
        list.forEach{
            delay(1000)
            Log.d("CheezyCode0",Thread.currentThread().name)
            emit(it)
        }
    }

    fun testCoroutines() {
        Log.e("+++", "+++ enter testCoroutines_3")
        runBlocking {
            launch {
                println("+++ start Task from runBlocking, with 200L delay")
                delay(200L)
                println("+++ end Task from runBlocking, with 200L delay")
            }

            launch {
                println("+++ start Task from runBlocking, with 50L delay")
                delay(50L)
                println("+++ end Task from runBlocking, with 50L delay")
            }

            launch {
                println("+++ start Task from runBlocking, with 70L delay")
                delay(70L)
                println("+++ end Task from runBlocking, with 70L delay")
            }

            coroutineScope {
                println("+++ enter Task from coroutineScope")
                // Creates a coroutine scope
                launch {
                    Log.v("+++", "+++ === start Task from nested launch, 500L")
                    delay(500L)
                    Log.v("+++", "+++ --- end Task from nested launch, 500L")
                }

                delay(100L)
                println("+++ in Task from coroutineScope after delay(100L)")

                launch {
                    Log.v("+++", "+++ === start Task from nested launch, 300L")
                    delay(300L)
                    Log.v("+++", "+++ --- end Task from nested launch, 300L")
                }

                println("+++ --- exit Task from coroutine scope") // This line will be printed before the nested launch
            }

            launch {
                println("+++ start Task from runBlocking, with 30L delay")
                delay(30L)
                println("+++ end Task from runBlocking, with 30L delay")
            }

            launch {
                println("+++ start Task from runBlocking, with 100L delay")
                delay(100L)
                println("+++ end Task from runBlocking, with 100L delay")
            }

        }

        Log.e("+++", "--- exit  testCoroutines_3 scope is over") // This line is not printed until the nested launch completes

    }

    fun callingUserList() {
        //Observe on viewmodel for data
        */
/*viewModel.data.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }*//*


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
        viewModel.fetchUsers()
        lifecycleScope.launch {
            viewModel.dataFlow.debounce(1000).collect {
                Toast.makeText(activity, "123collect" + it, Toast.LENGTH_SHORT).show()
                adapter.submitList(it)
            }
            */
/*viewModel.dataFlow.collect {
                Toast.makeText(activity, "" + it, Toast.LENGTH_SHORT).show()
                //  Log.i("Get","=")
                adapter.submitList(it)
            }*//*

        }

        */
/*CoroutineScope(Dispatchers.Main).launch {
            dataFlow.collect {
                // when it is done
            }
        }*//*


        binding.searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                Toast.makeText(activity, ":" + newText, Toast.LENGTH_SHORT).show()
                viewModel.queryLiveData.value = newText
                viewModel.fetchUsers()
                return false
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding.recyclerView.adapter = null
        _binding = null
    }


}*/
