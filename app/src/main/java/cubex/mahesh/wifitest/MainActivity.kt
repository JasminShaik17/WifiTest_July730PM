package cubex.mahesh.wifitest

import android.content.Context
import android.net.wifi.ScanResult
import android.net.wifi.WifiConfiguration
import android.net.wifi.WifiManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.CompoundButton
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var wManager:WifiManager = applicationContext.
                getSystemService(Context.WIFI_SERVICE) as WifiManager
        var state = wManager.wifiState
        if(state == 0 || state == 1){
            s1.isChecked = false
        }else{
            s1.isChecked = true
        }
        s1.setOnCheckedChangeListener(
                { compoundButton: CompoundButton, b: Boolean ->
                     wManager.setWifiEnabled(b)
        })

  gwd.setOnClickListener({
      var list:MutableList<ScanResult> = wManager.scanResults
      var temp_list = mutableListOf<String>()
      for(device in list){
          temp_list.add(device.SSID+"   "+device.frequency)
      }
      var adapter = ArrayAdapter<String>(this@MainActivity,
              android.R.layout.simple_list_item_single_choice,
              temp_list)
      lview.adapter = adapter
  })

        gcd.setOnClickListener({
   var list:MutableList<WifiConfiguration> = wManager.configuredNetworks
            var temp_list = mutableListOf<String>()
            for(device in list){
                temp_list.add(device.SSID+"   "+device.status)
            }
            var adapter = ArrayAdapter<String>(this@MainActivity,
                    android.R.layout.simple_list_item_single_choice,
                    temp_list)
            lview.adapter = adapter
        })

    } // onCreate
}
