package com.example.greenhousev2

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.greenhousev2.model.*
import com.example.greenhousev2.presenter.ArticlePresenter
import com.example.greenhousev2.presenter.ArticleView
import es.dmoral.toasty.Toasty
import kotlinx.android.synthetic.main.fans_devices_layout.*
import kotlinx.android.synthetic.main.fragment_control.*
import kotlinx.android.synthetic.main.fragment_control.view.*
import kotlinx.android.synthetic.main.lamp_devices_layout.*
import kotlinx.android.synthetic.main.vent_devices_layout.*
import kotlinx.android.synthetic.main.watering_device_layout.*
import java.lang.reflect.Field


/**
 * A simple [Fragment] subclass.
 */
class ControlFragment : Fragment(), ArticleView, DevicesAdapter.OnItemClickListener {

    val presenter = ArticlePresenter(this)
    val CLOSED = "closed"
    val OPENED = "opened"
    val WORKING = "working"
    val STOPPED = "stopped"
    lateinit var sensors: List<Sensor>
    lateinit var vents: List<VentParcel>
    lateinit var wds: List<WaterD>
    lateinit var fans: List<Fan>
    lateinit var lamps: List<Phytolamp>

    lateinit var swipeRefresh: SwipeRefreshLayout
    var listOfDevices = arrayListOf<String>()
    val listOfViews = arrayListOf<Int>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var view = inflater.inflate(R.layout.fragment_control, container, false)
        listOfDevices.addAll(listOf("Vents", "Watering devices", "Fans", " Phytolamps"))
        listOfViews.addAll(listOf(R.id.llVent, R.id.llWd, R.id.llFans, R.id.llLamps))
        val adapter = DevicesAdapter(listOfDevices, this)
        view.rvDevices.adapter = adapter
        view.rvDevices.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )

        swipeRefresh = view.findViewById(R.id.srRefresh)
        swipeRefresh.setColorSchemeResources(R.color.start, R.color.center, R.color.black)


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        view.progressBar.visibility = View.VISIBLE

        reloadData()
        swipeRefresh.setOnRefreshListener {
            swipeRefresh.isRefreshing = false
            info("reload started")
            view.progressBar.visibility = View.VISIBLE

            reloadData()
        }
        ivCamera.setOnClickListener{
            Toasty.success(requireContext(), "Camer monitoring", Toast.LENGTH_SHORT, true).show()

            val fragment: Fragment = CameraFragment()
            activity?.supportFragmentManager
                ?.beginTransaction()
                ?.replace(R.id.main_container, fragment)
                ?.commitAllowingStateLoss()
        }
        btnVent1.setOnClickListener{
            val id = 1
            Toasty.success(requireContext(), "Switched id:$id", Toast.LENGTH_SHORT, true).show()

            presenter.updateVent(id, changeVentState(id))
        }
        btnVent2.setOnClickListener{
            val id = 2
            Toasty.success(requireContext(), "Switched id:$id", Toast.LENGTH_SHORT, true).show()

            presenter.updateVent(id, changeVentState(id))
        }
        btnWd1.setOnClickListener{
            val id = 1
            Toasty.success(requireContext(), "Switched id:$id", Toast.LENGTH_SHORT, true).show()

            presenter.updateWaterD(id, changeWdState(id))
        }
        btnWd2.setOnClickListener{
            val id = 2
            Toasty.success(requireContext(), "Switched id:$id", Toast.LENGTH_SHORT, true).show()

            presenter.updateWaterD(id, changeWdState(id))
        }
        btnWd3.setOnClickListener{
            val id = 3
            Toasty.success(requireContext(), "Switched id:$id", Toast.LENGTH_SHORT, true).show()

            presenter.updateWaterD(id, changeWdState(id))
        }
        btnFan1.setOnClickListener{
            val id = 1
            Toasty.success(requireContext(), "Switched id:$id", Toast.LENGTH_SHORT, true).show()
            presenter.updateFan(id, changeFanState(id))
        }
        btnFan2.setOnClickListener{
            val id = 2
            Toasty.success(requireContext(), "Switched id:$id", Toast.LENGTH_SHORT, true).show()
            presenter.updateFan(id, changeFanState(id))
        }
        btnLamp1.setOnClickListener{
            val id = 1
            Toasty.success(requireContext(), "Switched id:$id", Toast.LENGTH_SHORT, true).show()
            presenter.updateLamps(id, changeLampState(id))
        }
        btnLamp2.setOnClickListener{
            val id = 2
            Toasty.success(requireContext(), "Switched id:$id", Toast.LENGTH_SHORT, true).show()
            presenter.updateLamps(id, changeLampState(id))
        }
        btnLamp3.setOnClickListener{
            val id = 3
            Toasty.success(requireContext(), "Switched id:$id", Toast.LENGTH_SHORT, true).show()
            presenter.updateLamps(id, changeLampState(id))
        }
        btnLamp4.setOnClickListener{
            val id = 4
            Toasty.success(requireContext(), "Switched id:$id", Toast.LENGTH_SHORT, true).show()
            presenter.updateLamps(id, changeLampState(id))
        }
        btnLamp5.setOnClickListener{
            val id = 5
            Toasty.success(requireContext(), "Switched id:$id", Toast.LENGTH_SHORT, true).show()
            presenter.updateLamps(id, changeLampState(id))
        }
        btnLamp6.setOnClickListener{
            val id = 6
            Toasty.success(requireContext(), "Switched id:$id", Toast.LENGTH_SHORT, true).show()
            presenter.updateLamps(id, changeLampState(id))
        }
    }
    fun changeVentState(id: Int): VentParcel{
        var obj = getObjectById(id, vents) as VentParcel
        if(obj.state.equals(CLOSED)){
            obj.state = OPENED
        }else{
            obj.state = CLOSED
        }
        return obj
    }
    fun changeWdState(id: Int): WaterD{
        var obj = getObjectById(id, wds) as WaterD
        if(obj.state.equals(CLOSED)){
            obj.state = OPENED
        }else{
            obj.state = CLOSED
        }
        return obj
    }
    fun changeFanState(id: Int): Fan{
        var obj = getObjectById(id, fans) as Fan
        if(obj.state.equals(STOPPED)){
            obj.state = WORKING
            obj.rpm = "400"
        }else{
            obj.rpm = "0"
            obj.state = STOPPED
        }
        return obj
    }
    fun changeLampState(id: Int): Phytolamp{
        var obj = getObjectById(id, lamps) as Phytolamp
        if(obj.state.equals(STOPPED)){
            obj.state = WORKING
        }else{
            obj.state = STOPPED
        }
        return obj
    }
    fun getObjectById(id: Int, list: List<Any>):Any{
        var temp: Any = 0
        for (obj in list){
            if(obj::class == VentParcel::class){
                temp = obj as VentParcel
                if(temp.id == id){
                    return temp
                }
            }else if(obj::class == WaterD::class){
                temp = obj as WaterD
                if(temp.id == id){
                    return temp
                }
            }else if(obj::class == Fan::class){
                temp = obj as Fan
                if(temp.id == id){
                    return temp
                }
            }else if(obj::class == Phytolamp::class){
                temp = obj as Phytolamp
                if(temp.id == id){
                    return temp
                }
            }
        }
        return 0
    }
    fun reloadData(){
        presenter.getSensors()
        presenter.getVents()
        presenter.getFans()
        presenter.getWaterDs()
        presenter.getLamps()
        info("data got")
    }

    override fun onDestroy() {
        presenter.destroy()
        super.onDestroy()
    }
    private fun info(msg: String){
        Log.i("MSG", "$msg")
    }
    //-------------------------------PRESENTER-RESPONSE-------------------------------------------------
    override fun showSensors(sensors: List<Sensor>) {
        this.sensors = sensors
        Log.i("MSG", "Show students ${sensors.toString()}")
        obtainSensorData()
    }

    override fun showVents(vents: List<VentParcel>) {
        Log.i("MSG", "showVents called")
        Log.i("MSG", "Show students ${vents.toString()}")
        this.vents = vents
        initialDesign("vent")

    }

    override fun updatedVent(vent: VentParcel) {
        Log.i("MSG", "updatedVent called")
        Log.i("MSG", "UPDATE Vent ${vent.toString()}")
        initialDesign("vent")

    }
//--------------------------------------------------------------------------------------------------

    override fun showWd(wds: List<WaterD>) {
        this.wds = wds
        Log.i("MSG", "showWd CALLED")

        initialDesign("wd")

    }

    override fun updatedWd(wd: WaterD) {
        initialDesign("wd")
    }
//--------------------------------------------------------------------------------------------------

    override fun showFans(fans: List<Fan>) {
        this.fans = fans
        Log.i("MSG", "showFANS CALLED")

        initialDesign("fan")

    }

    override fun updatedFan(fan: Fan) {
        initialDesign("fan")
    }
//--------------------------------------------------------------------------------------------------

    override fun showLamps(lamps: List<Phytolamp>) {
        this.lamps = lamps
        progressBar.visibility = View.GONE
        Log.i("MSG", "showLAMPS CALLED")
        if(swipeRefresh.isRefreshing){
            swipeRefresh.isRefreshing = false
        }
        initialDesign("lamp")

    }

    override fun updatedLamp(lamp: Phytolamp) {

        initialDesign("lamp")
    }
//--------------------------------------------------------------------------------------------------

    override fun onItemClick(position: Int) {

        for (v in listOfViews.indices){
            if(v==position){
                val temp = view?.findViewById<ConstraintLayout>(listOfViews[v])
                temp?.visibility = View.VISIBLE
            }else{
                val temp = view?.findViewById<ConstraintLayout>(listOfViews[v])
                temp?.visibility = View.GONE
            }

        }
        val temp = listOfDevices[position]
        Toasty.info(requireContext(), "$temp", Toast.LENGTH_SHORT, true).show()

    }
//    -------------------------------DESIGN-CHANGE--------------------------------------------------
    fun obtainSensorData(){
        for(i in sensors){
            if(i.dataType=="Temp"){
                if(i.state!=WORKING){
                    tvTempVal.text = "LOST"
                    tvTempVal.setTextColor(resources.getColor(R.color.error))
                }else{
                    tvTempVal.text = i.info + "C"
                    tvTempVal.setTextColor(resources.getColor(R.color.black))
                }
            }else if(i.dataType=="Pressure"){
                if(i.state!=WORKING){
                    tvPressureVal.text = "LOST"
                    tvPressureVal.setTextColor(resources.getColor(R.color.error))
                }else{
                    tvPressureVal.text = i.info + "Bar"
                    tvPressureVal.setTextColor(resources.getColor(R.color.black))
                }
            }else if(i.dataType=="Humidity"){
                if(i.state!=WORKING){
                    tvHumidityVal.text = "LOST"
                    tvHumidityVal.setTextColor(resources.getColor(R.color.error))
                }else{
                    tvHumidityVal.text = i.info + "%"
                    tvHumidityVal.setTextColor(resources.getColor(R.color.black))
                }
            }
        }
    }
    fun changeDesign(
        logo: ImageView,
        tvName: TextView,
        tv: TextView,
        constraintLayout: ConstraintLayout,
        btn: ImageView,
        id: Int,
        option: String,
        rpm: TextView?
    ) {
        fun turnedOn(){
            constraintLayout.setBackgroundResource(R.drawable.btn_blocks)
            btn.setImageResource(R.drawable.btn_switch_on)
            tv.setTextColor(resources.getColor(R.color.white))
            tvName.setTextColor(resources.getColor(R.color.white))
        }
        fun turnedOff(){
            constraintLayout.setBackgroundResource(R.drawable.btn_blocks_off)
            btn.setImageResource(R.drawable.btn_switch_off)
            tv.setTextColor(resources.getColor(R.color.black))
            tvName.setTextColor(resources.getColor(R.color.black))
        }
        if(option=="vent"){
            var obj = getObjectById(id, vents) as VentParcel
            tv.text = "position: " + obj.position
            if(obj.state==OPENED){
                turnedOn()
            }else{
                turnedOff()
            }
        }else if(option=="lamp"){
            var obj = getObjectById(id, lamps) as Phytolamp
            tv.text = "id: " + obj.id
            if(obj.state==WORKING){
                turnedOn()
                logo.setImageResource(R.drawable.logo_sun)
            }else{
                turnedOff()
                logo.setImageResource(R.drawable.logo_sun_off)
            }
        }else if(option=="wd"){
            var obj = getObjectById(id, wds) as WaterD
            Log.i("MSG", "change Design called by WD ${obj.state}")

            tv.text = "id: " + obj.id
            if(obj.state==OPENED){
                turnedOn()
                logo.setImageResource(R.drawable.logo_water)
            }else{
                turnedOff()
                logo.setImageResource(R.drawable.logo_water_off)
            }
        }else if(option=="fan"){
            var obj = getObjectById(id, fans) as Fan
            tv.text = "id: " + obj.id
            rpm?.text = obj.rpm + "RPM"
            if(obj.state==WORKING){
                turnedOn()
                logo.setImageResource(R.drawable.logo_fan)
            }else{
                turnedOff()
                logo.setImageResource(R.drawable.logo_fan_off)
            }
        }


    }
    fun initialDesign(option: String){
        if(option=="vent"){
            changeDesign(
                ivVentLogo1,
                tvVentName1,
                tvVentId1,
                constraintLayoutV1,
                btnVent1,
                1,
                option,
                null
            )

            changeDesign(
                ivVentLogo2,
                tvVentName2,
                tvVentId2,
                constraintLayoutV2,
                btnVent2,
                2,
                option,
                null
            )
        }else if(option=="lamp"){
            changeDesign(
                ivSunLogo1,
                tvLampName1,
                tvLampId1,
                constraintLayoutL1,
                btnLamp1,
                1,
                option,
                null
            )
            changeDesign(
                ivSunLogo2,
                tvLampName2,
                tvLampId2,
                constraintLayoutL2,
                btnLamp2,
                2,
                option,
                null
            )
            changeDesign(
                ivSunLogo3,
                tvLampName3,
                tvLampId3,
                constraintLayoutL3,
                btnLamp3,
                3,
                option,
                null
            )
            changeDesign(
                ivSunLogo4,
                tvLampName4,
                tvLampId4,
                constraintLayoutL4,
                btnLamp4,
                4,
                option,
                null
            )
            changeDesign(
                ivSunLogo5,
                tvLampName5,
                tvLampId5,
                constraintLayoutL5,
                btnLamp5,
                5,
                option,
                null
            )
            changeDesign(
                ivSunLogo6,
                tvLampName6,
                tvLampId6,
                constraintLayoutL6,
                btnLamp6,
                6,
                option,
                null
            )
        }else if(option=="fan"){
            changeDesign(
                ivFanLogo1,
                tvFanName1,
                tvFanId1,
                constraintLayoutF1,
                btnFan1,
                1,
                option,
                tvRpm1
            )
            changeDesign(
                ivFanLogo2,
                tvFanName2,
                tvFanId2,
                constraintLayoutF2,
                btnFan2,
                2,
                option,
                tvRpm2
            )
        }else if(option=="wd"){
            Log.i("MSG", "initial Design called by WD")

            changeDesign(ivWdLogo1, tvWdName1, tvWdId1, constraintLayoutW1, btnWd1, 1, option, null)
            changeDesign(ivWdLogo2, tvWdName2, tvWdId2, constraintLayoutW2, btnWd2, 2, option, null)
            changeDesign(ivWdLogo3, tvWdName3, tvWdId3, constraintLayoutW3, btnWd3, 3, option, null)
        }
    }



}

