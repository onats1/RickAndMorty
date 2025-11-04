package com.onats.rickandmorty.featurescomponents.characters.data.paging

import androidx.paging.PagingConfig
import androidx.paging.PagingSource
import androidx.paging.testing.TestPager
import com.google.common.truth.Truth.assertThat
import com.onats.rickandmorty.featurescomponents.characters.data.fakes.fakeCharacterResponse
import com.onats.rickandmorty.featurescomponents.characters.data.fakes.fakeCharactersDto
import com.onats.rickandmorty.featurescomponents.characters.data.remote.CharactersApi
import com.onats.rickandmorty.featurescomponents.characters.data.remote.models.toCharacter
import kotlinx.coroutines.test.runTest
import okhttp3.ResponseBody
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.junit.MockitoJUnitRunner
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever
import retrofit2.Response

@RunWith(MockitoJUnitRunner::class)
class CharacterPagingSourceTest {

    @Mock
    lateinit var mockCharacterApi: CharactersApi

    @Test
    fun `load returns page on successful load of data`() = runTest {
        whenever(mockCharacterApi.getAllCharacters(1)).thenReturn(Response.success(fakeCharacterResponse))
        val pagingSource = CharacterPagingSource(mockCharacterApi)

        val pager = TestPager(
            pagingSource = pagingSource,
            config = PagingConfig(pageSize = 10)
        )

        val characters = fakeCharactersDto.map { it.toCharacter() }

        val result = pager.refresh() as PagingSource.LoadResult.Page

        //Verify that getAllCharacters was called
        verify(mockCharacterApi).getAllCharacters(1)

        assertThat(result.data)
            .containsExactlyElementsIn(characters)
            .inOrder()
    }

    @Test
    fun `load returns error on error loading data`() = runTest {
        whenever(mockCharacterApi.getAllCharacters(1)).thenReturn(Response.error(404, ResponseBody.Companion.EMPTY))
        val pagingSource = CharacterPagingSource(mockCharacterApi)
        val pager = TestPager(
            pagingSource = pagingSource,
            config = PagingConfig(pageSize = 10)
        )

        val result = pager.refresh()

        //Verify that getAllCharacters was called
        verify(mockCharacterApi).getAllCharacters(1)
        assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
    }

    @Test
    fun `load returns error when network api throws exception`() = runTest {
        whenever(mockCharacterApi.getAllCharacters(1)).thenThrow(RuntimeException::class.java)

        val pagingSource = CharacterPagingSource(mockCharacterApi)
        val pager = TestPager(
            pagingSource = pagingSource,
            config = PagingConfig(pageSize = 10)
        )

        val result = pager.refresh()

        //Verify that getAllCharacters was called
        verify(mockCharacterApi).getAllCharacters(1)
        assertThat(result).isInstanceOf(PagingSource.LoadResult.Error::class.java)
    }
}