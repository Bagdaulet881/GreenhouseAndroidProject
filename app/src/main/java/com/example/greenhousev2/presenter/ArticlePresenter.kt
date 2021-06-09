package com.example.greenhousev2.presenter

import com.example.greenhousev2.model.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ArticlePresenter(var view: ArticleView?){

    val repository = ArticleRepository()
    val disposable = CompositeDisposable()
    fun getSensors(){
        val sensors = repository.getSensorsObs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({vents->
                view?.showSensors(vents)
            }, {
                it.printStackTrace()
            })
        disposable.add(sensors)
    }
    fun getVents(){
        val vents = repository.getVentsObs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({vents->
                view?.showVents(vents)
            }, {
                it.printStackTrace()
            })
        disposable.add(vents)
    }
    fun updateVent(id:Int, nVent: VentParcel){
        val vent = repository.updateVent(id, nVent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({vent->
                view?.updatedVent(vent)
            }, {
                it.printStackTrace()
            })
        disposable.add(vent)
    }
//
    fun getWaterDs(){
        val wds = repository.getWdsObs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({vents->
                view?.showWd(vents)
            }, {
                it.printStackTrace()
            })
        disposable.add(wds)
    }
    fun updateWaterD(id:Int, nVent: WaterD){
        val vent = repository.updateWd(id, nVent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({vent->
                view?.updatedWd(vent)
            }, {
                it.printStackTrace()
            })
        disposable.add(vent)
    }
//
//
fun getFans(){
    val wds = repository.getFansObs()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe({vents->
            view?.showFans(vents)
        }, {
            it.printStackTrace()
        })
    disposable.add(wds)
}
    fun updateFan(id:Int, nVent: Fan){
        val vent = repository.updateFan(id, nVent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({vent->
                view?.updatedFan(vent)
            }, {
                it.printStackTrace()
            })
        disposable.add(vent)
    }
    //
    //
    fun getLamps(){
        val wds = repository.getLampsObs()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({vents->
                view?.showLamps(vents)
            }, {
                it.printStackTrace()
            })
        disposable.add(wds)
    }
    fun updateLamps(id:Int, nVent: Phytolamp){
        val vent = repository.updateLamp(id, nVent)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({vent->
                view?.updatedLamp(vent)
            }, {
                it.printStackTrace()
            })
        disposable.add(vent)
    }
    //
    fun destroy(){
        disposable.dispose()
        view = null
    }

}

interface ArticleView{
    fun showSensors(sensors: List<Sensor>)
    fun showVents(vents: List<VentParcel>)
    fun updatedVent(vent: VentParcel)

    fun showWd(wds: List<WaterD>)
    fun updatedWd(wd: WaterD)

    fun showFans(fans: List<Fan>)
    fun updatedFan(fan: Fan)

    fun showLamps(lamps: List<Phytolamp>)
    fun updatedLamp(lamp: Phytolamp)
}