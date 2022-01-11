package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

//import com.amap.api.location.AMapLocation;
//import com.amap.api.location.AMapLocationClient;
//import com.amap.api.location.AMapLocationClientOption;
//import com.amap.api.location.AMapLocationListener;
import com.amap.api.location.AMapLocationClient;
import com.amap.api.location.AMapLocationClientOption;

//implements AMapLocationListener
public class GpslocationActivity extends AppCompatActivity  {

    private TextView tx_latitude;
    private TextView tx_longitude;
    //声明mlocationClient对象
    public AMapLocationClient mlocationClient;
    //声明mLocationOption对象
    public AMapLocationClientOption mLocationOption = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gpslocation);

        initView();

//
//        Location locations = LocationUtil.getInstance().getLocations(this);
//        if(locations == null){  //没有获取到经纬度
//            Log.e("TAGTAGTAG","没有获取");
//        }else{
//            DecimalFormat df = new DecimalFormat("#####0.0000");
//            String longitude = df.format(locations.getLongitude());
//            String latitude=df.format(locations.getLatitude());
//            Log.d("latitude::: ",latitude);
//            Log.d("longitude::: ",longitude);
//            tx_latitude.setText(latitude);
//            tx_longitude.setText(longitude);
//        }



    }




//    /*开启定位*/
//    private void location() throws Exception {
//        //初始化定位
//        AMapLocationClient mLocationClient = new AMapLocationClient(getApplicationContext());
//        //设置定位回调监听
//        mLocationClient.setLocationListener(this);
//        //初始化定位参数
//
//        mLocationOption = new AMapLocationClientOption();
//        //设置定位模式为Hight_Accuracy高精度模式，Battery_Saving为低功耗模式，Device_Sensors是仅设备模式
//        mLocationOption.setLocationMode(AMapLocationClientOption.AMapLocationMode.Hight_Accuracy);
//        //设置是否返回地址信息（默认返回地址信息）
//        mLocationOption.setNeedAddress(true);
//        //设置是否只定位一次,默认为false
//        mLocationOption.setOnceLocation(true);
//        //设置是否强制刷新WIFI，默认为强制刷新
//        mLocationOption.setWifiActiveScan(true);
//        //设置是否允许模拟位置,默认为false，不允许模拟位置
//        mLocationOption.setMockEnable(false);
//        //设置定位间隔,单位毫秒,默认为2000ms
//        mLocationOption.setInterval(2000);
//        //给定位客户端对象设置定位参数
//        mLocationClient.setLocationOption(mLocationOption);
//        //启动定位
//        mLocationClient.startLocation();
//    }
//
//    @Override
//    public void onLocationChanged(AMapLocation aMapLocation) {
//        if (aMapLocation != null) {
//            if (aMapLocation.getErrorCode() == 0) {
//                //可在其中解析amapLocation获取相应内容。
//
//                aMapLocation.getLocationType();//获取当前定位结果来源，如网络定位结果，详见定位类型表
//                lat = aMapLocation.getLatitude();//获取纬度
//                lon = aMapLocation.getLongitude();//获取经度
//                aMapLocation.getAccuracy();//获取精度信息
//                aMapLocation.getAddress();//地址，如果option中设置isNeedAddress为false，则没有此结果，网络定位结果中会有地址信息，GPS定位不返回地址信息。
//                aMapLocation.getCountry();//国家信息
//                aMapLocation.getProvince();//省信息
//                aMapLocation.getCity();//城市信息
//                aMapLocation.getDistrict();//城区信息
//                aMapLocation.getStreet();//街道信息
//                aMapLocation.getStreetNum();//街道门牌号信息
//                aMapLocation.getCityCode();//城市编码
//                aMapLocation.getAdCode();//地区编码
//                aMapLocation.getAoiName();//获取当前定位点的AOI信息
//                //获取定位时间
//                SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//                Date date = new Date(aMapLocation.getTime());
//                df.format(date);
//
//
//                // 如果不设置标志位，此时再拖动地图时，它会不断将地图移动到当前的位置
//                if (isFirstLoc) {
//                    //获取定位信息
//                    StringBuffer buffer = new StringBuffer();
//                    buffer.append(aMapLocation.getCountry() + ""
//                            + aMapLocation.getProvince() + ""
//                            + aMapLocation.getCity() + ""
//                            + aMapLocation.getProvince() + ""
//                            + aMapLocation.getDistrict() + ""
//                            + aMapLocation.getStreet() + ""
//                            + aMapLocation.getStreetNum());
//                    Toast.makeText(getApplicationContext(), buffer.toString(), Toast.LENGTH_LONG).show();
//                    isFirstLoc = false;
//                }
//
//            }else {
//                //定位失败时，可通过ErrCode（错误码）信息来确定失败的原因，errInfo是错误信息，详见错误码表。
//                Log.e("地图错误","定位失败, 错误码:" + aMapLocation.getErrorCode() + ", 错误信息:"
//                        + aMapLocation.getErrorInfo());
//            }
//        }
//    }

    public void initView(){
        tx_latitude = findViewById(R.id.gpsLocation_latitude);
        tx_longitude = findViewById(R.id.gpsLocation_longitude);

    }
}