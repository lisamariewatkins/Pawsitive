package com.example.network

import com.example.models.organization.OrganizationJsonResponse
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface OrganizationService {
    @GET("/v2/organizations")
    fun getOrganizationsAsync(): Deferred<OrganizationJsonResponse>

    @GET
    fun getNextOrganizationsAsync(@Url url: String): Deferred<OrganizationJsonResponse>

    @GET("v2/organizations")
    fun getOrganizationsByPageAsync(@Query("page") page: Int): Deferred<OrganizationJsonResponse>
}

class OrganizationServiceImpl(retrofitFactory: RetrofitFactoryV2): OrganizationService {
    private val retrofitInstance = retrofitFactory.retrofit().create(OrganizationService::class.java)

    override fun getOrganizationsAsync(): Deferred<OrganizationJsonResponse> {
        return retrofitInstance.getOrganizationsAsync()
    }

    override fun getNextOrganizationsAsync(url: String): Deferred<OrganizationJsonResponse> {
        return retrofitInstance.getNextOrganizationsAsync(url)
    }

    override fun getOrganizationsByPageAsync(page: Int): Deferred<OrganizationJsonResponse> {
        return retrofitInstance.getOrganizationsByPageAsync(page)
    }
}