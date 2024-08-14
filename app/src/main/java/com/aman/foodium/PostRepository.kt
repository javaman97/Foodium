package com.aman.foodium

import androidx.annotation.MainThread
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import retrofit2.Response
import javax.inject.Inject

interface PostRepository {
    fun getAllPosts(): Flow<Resource<List<Post>>>
    fun getPostById(postId:Int):Flow<Post>
}


@ExperimentalCoroutinesApi
class  DefaultPostRepository @Inject constructor(
    private val postsDao: PostsDao,
    private val foodiumService: FoodiumService
):PostRepository{

    override fun getAllPosts(): Flow<Resource<List<Post>>> {
        return object : NetworkBoundRepository<List<Post>, List<Post>>() {

            override suspend fun saveRemoteData(response: List<Post>) = postsDao.addPosts(response)

            override fun fetchFromLocal(): Flow<List<Post>> = postsDao.getAllPosts()

            override suspend fun fetchFromRemote(): Response<List<Post>> = foodiumService.getPosts()
        }.asFlow()
    }
    @MainThread
    override fun getPostById(postId: Int): Flow<Post> = postsDao.getPostById(postId).distinctUntilChanged()
}