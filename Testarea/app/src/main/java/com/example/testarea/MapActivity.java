package com.example.testarea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.CameraUpdate;
import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.MapView;
import com.amap.api.maps2d.model.CameraPosition;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.Marker;
import com.amap.api.maps2d.model.MarkerOptions;
import com.amap.api.maps2d.model.MyLocationStyle;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;
    private TextView tx_latitude;
    private TextView tx_longitude;
    private Button bt_save;
    private Button bt_exchangeMapType;
    private Button bt_toSaved;
    private EditText ed_locationName;

    private SharedPreferences sp;
    private SharedPreferences.Editor editor;

    public static final int LOCATION_CODE = 301;
    private LocationManager locationManager;
    private String locationProvider = null;

    private AMap aMap = null;
    private CameraUpdate cameraUpdate;
    private double mlatitude;
    private double mlongitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initView();
        initAction();
        mapView = (MapView) findViewById(R.id.map);
        //在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);
        getLocation();
        drawMap();


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //在activity执行onDestroy时执行mMapView.onDestroy()，销毁地图
        mapView.onDestroy();
    }
    @Override
    protected void onResume() {
        super.onResume();
        //在activity执行onResume时执行mMapView.onResume ()，重新绘制加载地图
        mapView.onResume();
    }
    @Override
    protected void onPause() {
        super.onPause();
        //在activity执行onPause时执行mMapView.onPause ()，暂停地图的绘制
        mapView.onPause();
    }
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //在activity执行onSaveInstanceState时执行mMapView.onSaveInstanceState (outState)，保存地图当前的状态
        mapView.onSaveInstanceState(outState);
    }

    public void drawMap(){
        //获取保存在sp中的定位数据
        sp = getSharedPreferences("LocationData",MODE_PRIVATE);
        String singal_LocationData = ed_locationName.getText().toString()+":"+tx_latitude.getText().toString()+":"+tx_longitude.getText().toString();
        Gson gson = new Gson();
        ArrayList<String> list_locationData = new ArrayList<>();
        if(sp.getString("locationDatas",null)!=null){
            list_locationData = gson.fromJson(sp.getString("locationDatas",null), new TypeToken<ArrayList<String>>(){}.getType());
        }

        //绘制地图
        if(aMap == null){
            aMap = mapView.getMap();
        }
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_FOLLOW);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。
        //画面中心的经纬度
        LatLng latLng_project =new LatLng(mlatitude,mlongitude);

        myLocationStyle.showMyLocation(true);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        //设置地图类型为卫星图
        aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
        //设置地图的缩放等级（3~19）地图的缩放级别一共分为 17 级，从 3 到 19。数字越大，展示的图面信息越精细。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));

        cameraUpdate= CameraUpdateFactory.newCameraPosition(new CameraPosition(latLng_project,17,0,30));
        aMap.moveCamera(cameraUpdate);//地图移向指定区域

        drawMapPoints(list_locationData);
//        LatLng latLng1 = new LatLng(30.590851,104.085295);
//        LatLng latLng2 = new LatLng(30.623032,103.685967);
//        LatLng latLng3 = new LatLng(30.591460,104.084758);
//        LatLng latLng4 = new LatLng(30.590971,104.084560);
//        final Marker maker1 = aMap.addMarker(new MarkerOptions().position(latLng1).title("公司").snippet("华睿康"));
//        final Marker maker2 = aMap.addMarker(new MarkerOptions().position(latLng2).title("崇州万达").snippet(""));
//        final Marker maker3 = aMap.addMarker(new MarkerOptions().position(latLng3).title("点1").snippet(""));
//        final Marker maker4 = aMap.addMarker(new MarkerOptions().position(latLng4).title("点2").snippet(""));

        // 定义 Marker 点击事件监听
        AMap.OnMarkerClickListener markerClickListener = new AMap.OnMarkerClickListener() {
            // marker 对象被点击时回调的接口
            // 返回 true 则表示接口已响应事件，否则返回false
            @Override
            public boolean onMarkerClick(Marker marker) {
                if(marker.getTitle().equals("公司")){
                    Toast.makeText(MapActivity.this, "点击了公司地址", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(MapActivity.this,Maptopage1.class);
                    startActivity(intent);
                }else if(marker.getTitle().equals("崇州万达")){
                    Toast.makeText(MapActivity.this, "点击了崇州万达地址", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        };
        aMap.setOnMarkerClickListener(markerClickListener);
    }

    /**
     * 在地图上标记点位
     * @param list_location
     */
    public void drawMapPoints(ArrayList<String> list_location){
        for(String data : list_location){
            LatLng latLng = new LatLng(Double.parseDouble(data.split(":")[1]),Double.parseDouble(data.split(":")[2]));
            Marker maker1 = aMap.addMarker(new MarkerOptions().position(latLng).title(data.split(":")[0]).snippet(""));
            Log.d("location",data);
        }
    }

    public void initView(){
        tx_latitude = findViewById(R.id.mapPage_tx_latitude);
        tx_longitude = findViewById(R.id.mapPage_tx_longitude);
        bt_save = findViewById(R.id.mapPage_bt_save);
        bt_toSaved = findViewById(R.id.mapPage_bt_toSaved);
        bt_exchangeMapType = findViewById(R.id.mapPage_bt_exchangeMapType);
        ed_locationName = findViewById(R.id.mapPage_ed_locationName);
    }

    public void initAction(){
        bt_save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tx_latitude.getText().toString()==null || tx_longitude.getText().toString() == null || ed_locationName.getText().toString() == null){
                    Toast.makeText(MapActivity.this, "请输入完整数据！", Toast.LENGTH_SHORT).show();
                }else{
                    sp = getSharedPreferences("LocationData",MODE_PRIVATE);
                    String singal_LocationData = ed_locationName.getText().toString()+":"+tx_latitude.getText().toString()+":"+tx_longitude.getText().toString();
                    Gson gson = new Gson();
                    ArrayList<String> list_locationData = new ArrayList<>();
                    if(sp.getString("locationDatas",null)!=null){
                        list_locationData = gson.fromJson(sp.getString("locationDatas",null), new TypeToken<ArrayList<String>>(){}.getType());
                    }
                    list_locationData.add(singal_LocationData);
                    drawMapPoints(list_locationData);
                    editor = sp.edit();
                    editor.putString("locationDatas",gson.toJson(list_locationData));
                    editor.apply();
                    Toast.makeText(MapActivity.this, "已保存！", Toast.LENGTH_SHORT).show();
                    ed_locationName.setText("");
                }

            }
        });

        bt_exchangeMapType.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(aMap.getMapType() == AMap.MAP_TYPE_NORMAL){
                    //设置地图类型为卫星图
                    aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
                }else if(aMap.getMapType() == AMap.MAP_TYPE_SATELLITE){
                    //设置地图类型为普通图
                    aMap.setMapType(AMap.MAP_TYPE_NORMAL);
                }

            }
        });

        bt_toSaved.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MapActivity.this,LocationList.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 获取经纬度信息
     */
    private void getLocation () {
        locationManager = (LocationManager) getApplicationContext().getSystemService(Context.LOCATION_SERVICE);
        //获取所有可用的位置提供器
        List<String> providers = locationManager.getProviders(true);
        for(String name : providers){
            Log.d("provider_frag02::",name);
        }
        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
            //如果是Network
            locationProvider = LocationManager.NETWORK_PROVIDER;
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //获取权限（如果没有开启权限，会弹出对话框，询问是否开启权限）
            if (ContextCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                    || ActivityCompat.checkSelfPermission(MapActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                //请求权限
                ActivityCompat.requestPermissions(MapActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION}, LOCATION_CODE);
            } else {
                //监视地理位置变化
                locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
                Location location = locationManager.getLastKnownLocation(locationProvider);
                if (location != null) {
                    //输入经纬度
                    Toast.makeText(MapActivity.this, location.getLongitude() + " " + location.getLatitude() + "", Toast.LENGTH_SHORT).show();
                    mlatitude = location.getLatitude()-0.002356;
                    mlongitude = location.getLongitude()+0.002481;
                    tx_latitude.setText(String.valueOf(mlatitude));
                    tx_longitude.setText(String.valueOf(mlongitude));
                }
            }
        } else {
            //监视地理位置变化
            locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
            Location location = locationManager.getLastKnownLocation(locationProvider);
            if (location != null) {
                //不为空,显示地理位置经纬度
                Toast.makeText(MapActivity.this, location.getLongitude() + " " + location.getLatitude() + "", Toast.LENGTH_SHORT).show();
//                tx_latitude.setText(String.valueOf(location.getLatitude()));
//                tx_longitude.setText(String.valueOf(location.getLongitude()));
                mlatitude = location.getLatitude()-0.002356;
                mlongitude = location.getLongitude()+0.002481;
                tx_latitude.setText(String.valueOf(mlatitude));
                tx_longitude.setText(String.valueOf(mlongitude));
            }
        }
    }

    public LocationListener locationListener = new LocationListener() {
        // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
        }
        // Provider被enable时触发此函数，比如GPS被打开
        @Override
        public void onProviderEnabled(String provider) {
        }
        // Provider被disable时触发此函数，比如GPS被关闭
        @Override
        public void onProviderDisabled(String provider) {
        }
        //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                //不为空,显示地理位置经纬度
                Toast.makeText(MapActivity.this, location.getLongitude() + " " + location.getLatitude() + "", Toast.LENGTH_SHORT).show();
//                tx_latitude.setText(String.valueOf(location.getLatitude()));
//                tx_longitude.setText(String.valueOf(location.getLongitude()));
                mlatitude = location.getLatitude()-0.002356;
                mlongitude = location.getLongitude()+0.002481;
                tx_latitude.setText(String.valueOf(mlatitude));
                tx_longitude.setText(String.valueOf(mlongitude));
            }
        }
    };


    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case LOCATION_CODE:
                if(grantResults.length > 0 && grantResults[0] == getApplicationContext().getPackageManager().PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(MapActivity.this, "申请权限", Toast.LENGTH_LONG).show();
                    try {
                        List<String> providers = locationManager.getProviders(true);
                        if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
                            //如果是Network
                            locationProvider = LocationManager.NETWORK_PROVIDER;
                        }else if (providers.contains(LocationManager.GPS_PROVIDER)) {
                            //如果是GPS
                            locationProvider = LocationManager.GPS_PROVIDER;
                        }
                        //监视地理位置变化
                        locationManager.requestLocationUpdates(locationProvider, 3000, 1, locationListener);
                        Location location = locationManager.getLastKnownLocation(locationProvider);
                        if (location != null) {
                            //不为空,显示地理位置经纬度
                            Toast.makeText(MapActivity.this, location.getLongitude() + " " + location.getLatitude() + "", Toast.LENGTH_SHORT).show();
                        }
                    }catch (SecurityException e){
                        e.printStackTrace();
                    }
                } else {
                    Toast.makeText(MapActivity.this, "缺少权限", Toast.LENGTH_LONG).show();
//                    finish();
                }
                break;
        }
    }
}