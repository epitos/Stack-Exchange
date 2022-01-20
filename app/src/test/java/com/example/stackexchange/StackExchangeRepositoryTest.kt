package com.example.stackexchange

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.stackexchange.data.api.ApiService
import com.example.stackexchange.data.model.Item
import com.example.stackexchange.data.model.TagsResponse
import com.example.stackexchange.data.model.UsersResponse
import com.example.stackexchange.data.repository.StackExchangeRepository
import com.example.stackexchange.ui.adapter.TagsAdapter
import com.example.stackexchange.utils.Const
import com.example.stackexchange.utils.Sort
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.stubbing.OngoingStubbing
import retrofit2.Response

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class StackExchangeRepositoryTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val testCoroutineRule = TestCoroutineRule()

    @Mock
    private lateinit var apiService: ApiService

    private lateinit var repository: StackExchangeRepository

    @Before
    fun setUp() {
        repository = StackExchangeRepository(apiService)
    }

    @Test
    fun getUsersApiSuccessResponse() {
        val name = "John"

        testCoroutineRule.runBlockingTest {

            val response: Response<UsersResponse> = Response.success(UsersResponse())

            `when`(apiService.getUsers(Const.ORDER_BY_OPTION_ASC, Sort.NAME.sortType, name,
                Const.SITE_NAME_STACKOVERFLOW)).thenReturn(response)

            assertEquals(response, repository.getUsers(Const.ORDER_BY_OPTION_ASC, Sort.NAME.sortType,
                name, Const.SITE_NAME_STACKOVERFLOW))

            verify(apiService).getUsers(
                Const.ORDER_BY_OPTION_ASC, Sort.NAME.sortType, name,
                Const.SITE_NAME_STACKOVERFLOW)
        }
    }

    @Test
    fun getTagsApiSuccessResponse() {
        testCoroutineRule.runBlockingTest {

            val response: Response<TagsResponse> = Response.success(TagsResponse())

            `when`(apiService.getTags(Const.ORDER_BY_OPTION_ASC,
                Sort.NAME.sortType, Const.SITE_NAME_STACKOVERFLOW)).thenReturn(response)

            assertEquals(response, repository.getTags(Const.ORDER_BY_OPTION_ASC,
                Sort.POPULAR.sortType, Const.SITE_NAME_STACKOVERFLOW))

            verify(apiService).getTags(
                Const.ORDER_BY_OPTION_ASC, Sort.POPULAR.sortType,Const.SITE_NAME_STACKOVERFLOW)
        }
    }
}


