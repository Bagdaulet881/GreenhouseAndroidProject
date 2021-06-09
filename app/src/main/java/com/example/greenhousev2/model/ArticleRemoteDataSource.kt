package com.example.greenhousev2.model

import com.example.greenhousev2.API
import io.reactivex.Single
import retrofit2.Call

class ArticleRemoteDataSource {

    fun downloadSensorsObs(): Single<List<Sensor>> {
        return API.postsService.getSensorsObs()
    }
    fun downloadVentsObs(): Single<List<VentParcel>> {
        return API.postsService.getVentsObs()
    }
    fun updateVent(id:Int, ventParcel: VentParcel): Single<VentParcel>{
        return API.postsService.updateVent(id, ventParcel)
    }
    fun downloadWdsObs(): Single<List<WaterD>> {
        return API.postsService.getWdObs()
    }
    fun updateWd(id:Int, wd: WaterD): Single<WaterD>{
        return API.postsService.updateWd(id, wd)
    }
    fun downloadFansObs(): Single<List<Fan>> {
        return API.postsService.getFansObs()
    }
    fun updateFan(id:Int, fan: Fan): Single<Fan>{
        return API.postsService.updateFan(id, fan)
    }
    fun downloadLampObs(): Single<List<Phytolamp>> {
        return API.postsService.getLampsObs()
    }
    fun updateLamp(id:Int, lamp: Phytolamp): Single<Phytolamp>{
        return API.postsService.updateLamp(id, lamp)
    }
}