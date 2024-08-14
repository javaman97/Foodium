package com.aman.foodium

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class PostDaoTest {
    private lateinit var mDatabase: FoodiumPostsDatabase


    @Before
    fun init(){
        mDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            FoodiumPostsDatabase::class.java
        ).build()
    }

    @Test
    @Throws(InterruptedException::class)
    fun insert_and_select_posts() = runBlocking {
        val posts = listOf(
            Post(1,"Test 1","Test 1 Author","Test 1 Body"),
        Post(2,"Test 2","Test 2 Author","Test 2 Body"),
            Post(3,"Test 3","Test 3 Author","Test 2 Body"),  )

        mDatabase.getPostsDao().addPosts(posts)

        val dbPosts = mDatabase.getPostsDao().getAllPosts().first()

        assertThat(dbPosts,equalTo(posts))
    }

    @Test
    @Throws(InterruptedException::class)
    fun select_post_by_id()= runBlocking {
        val posts = listOf(
            Post(1,"Test 1","Test 1 Author","Test 1 Body"),
            Post(2,"Test 2","Test 2 Author","Test 2 Body"),
            Post(3,"Test 3","Test 3 Author","Test 2 Body"),  )


        mDatabase.getPostsDao().addPosts(posts)

        var dbPost = mDatabase.getPostsDao().getPostById(1).first()
        assertThat(dbPost, equalTo(posts[0]))

        dbPost = mDatabase.getPostsDao().getPostById(2).first()
        assertThat(dbPost, equalTo(posts[1]))
        dbPost = mDatabase.getPostsDao().getPostById(3).first()
        assertThat(dbPost, equalTo(posts[2]))
    }

    @After
    fun cleanup(){
        mDatabase.close()
    }

}