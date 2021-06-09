package com.example.greenhousev2.`interface`

import com.example.greenhousev2.model.*
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.*

interface PostsService {

    @GET("main/sensors/")
    fun getSensorsObs(): Single<List<Sensor>>

    @GET("main/vents/")
    fun getVentsObs(): Single<List<VentParcel>>

    @Headers("Content-type:application/json")
    @PUT("main/vents/{id}")
    fun updateVent(
        @Path("id") id: Int,
        @Body vent: VentParcel
    ): Single<VentParcel>

    @GET("main/wd/")
    fun getWdObs(): Single<List<WaterD>>

    @Headers("Content-type:application/json")
    @PUT("main/wd/{id}")
    fun updateWd(
        @Path("id") id: Int,
        @Body wd: WaterD
    ): Single<WaterD>

    @GET("main/fans/")
    fun getFansObs(): Single<List<Fan>>

    @Headers("Content-type:application/json")
    @PUT("main/fans/{id}")
    fun updateFan(
        @Path("id") id: Int,
        @Body wd: Fan
    ): Single<Fan>

    @GET("main/lamps/")
    fun getLampsObs(): Single<List<Phytolamp>>

    @Headers("Content-type:application/json")
    @PUT("main/lamps/{id}")
    fun updateLamp(
        @Path("id") id: Int,
        @Body wd: Phytolamp
    ): Single<Phytolamp>






}