package com.example.greenhousev2.model

import io.reactivex.Single
import retrofit2.Call

class ArticleRepository {
    val remoteDataSource = ArticleRemoteDataSource()

    fun getSensorsObs(): Single<List<Sensor>> {
        return remoteDataSource.downloadSensorsObs()
    }
    fun getVentsObs(): Single<List<VentParcel>> {
        return remoteDataSource.downloadVentsObs()
    }
    fun updateVent(id:Int, ventParcel: VentParcel): Single<VentParcel>{
        return  remoteDataSource.updateVent(id, ventParcel)
    }
    fun getWdsObs(): Single<List<WaterD>> {
        return remoteDataSource.downloadWdsObs()
    }
    fun updateWd(id:Int, wd:WaterD ): Single<WaterD>{
        return  remoteDataSource.updateWd(id, wd)
    }
    fun getFansObs(): Single<List<Fan>> {
        return remoteDataSource.downloadFansObs()
    }
    fun updateFan(id:Int, fan: Fan ): Single<Fan>{
        return  remoteDataSource.updateFan(id, fan)
    }
    fun getLampsObs(): Single<List<Phytolamp>> {
        return remoteDataSource.downloadLampObs()
    }
    fun updateLamp(id:Int, phytolamp: Phytolamp): Single<Phytolamp>{
        return  remoteDataSource.updateLamp(id, phytolamp)
    }
}