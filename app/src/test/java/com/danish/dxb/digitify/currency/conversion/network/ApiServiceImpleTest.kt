package com.danish.dxb.digitify.currency.conversion.network

import com.danish.dxb.digitify.currency.conversion.model.CurrencyConversionResponse
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
 import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class ApiServiceImpleTest {

    lateinit var apiServiceImple: ApiServiceImple

    @Mock
    lateinit var apiService: ApiService

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        apiServiceImple = ApiServiceImple(apiService)
    }

    @Test
    fun `getCurrencyConversionAPI is working or not`() {
        runBlocking {
            Mockito.`when`(
                apiServiceImple.getCurrencyConversionAPI(
                    "USD",
                    ""
                )
            ).thenReturn(CurrencyConversionResponse(0, mapOf(), "", true, 0))
            val response = apiServiceImple.getCurrencyConversionAPI(
                "USD",
                ""
            )
            assertEquals(true, response.success)
        }

    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun getCurrencyConversionAPI() {
    }


}