package com.aman.foodium

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.google.common.base.Charsets
import com.google.common.truth.Truth.assertThat
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.runBlocking
import okhttp3.internal.http2.Header
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okio.buffer
import okio.source
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.time.Instant

@RunWith(JUnit4::class)
class FoodiumServiceTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var service: FoodiumService

    private lateinit var mockWebServer: MockWebServer

    @Before
    fun CreateService(){
        mockWebServer = MockWebServer()
        service = Retrofit.Builder()
            .baseUrl(mockWebServer.url("/"))
            .addConverterFactory(
                MoshiConverterFactory.create(
                    Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
                )
            ).build()
            .create(FoodiumService::class.java)

    }

    @After
    fun stopService(){
        mockWebServer.shutdown()
    }

    @Test
    fun getPostsTest() = runBlocking {
        enqueueResponse("posts.json")

        val posts = service.getPosts().body()

        assertThat(posts).isNotNull()
        assertThat(posts!!.size).isEqualTo(3)
        assertThat(posts[0].title).isEqualTo("Title 1")
    }

    private fun enqueueResponse(fileName:String,header: Map<String,String> = emptyMap()){
        val inputStream = javaClass.classLoader!!
            .getResourceAsStream("api-response/$fileName")
        val source = inputStream.source().buffer()
        val mockResponse = MockResponse()
        for((key,value) in header) {
            mockResponse.addHeader(key,value)

        }
        mockWebServer.enqueue(
            mockResponse
                .setBody(source.readString(Charsets.UTF_8))
        )
    }

}