package com.example.testarea;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.amap.api.map3d.*;
import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.google.android.gms.maps.model.LatLng;

public class MapActivity extends AppCompatActivity {

    private MapView mapView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        initView();
        mapView = (MapView) findViewById(R.id.map);
//在activity执行onCreate时执行mMapView.onCreate(savedInstanceState)，创建地图
        mapView.onCreate(savedInstanceState);

        AMap aMap = null;
        if(aMap == null){
            aMap = mapView.getMap();

        }
        MyLocationStyle myLocationStyle;
        myLocationStyle = new MyLocationStyle();//初始化定位蓝点样式类
        myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE);//连续定位、且将视角移动到地图中心点，定位点依照设备方向旋转，并且会跟随设备移动。（1秒1次定位）如果不设置myLocationType，默认也会执行此种模式。
        myLocationStyle.interval(2000); //设置连续定位模式下的定位间隔，只在连续定位模式下生效，单次定位模式下不会生效。单位为毫秒。
        //myLocationStyle.myLocationType(MyLocationStyle.LOCATION_TYPE_LOCATION_ROTATE_NO_CENTER);//连续定位、蓝点不会移动到地图中心点，定位点依照设备方向旋转，并且蓝点会跟随设备移动。

        myLocationStyle.showMyLocation(true);
        aMap.setMyLocationStyle(myLocationStyle);//设置定位蓝点的Style
        aMap.getUiSettings().setMyLocationButtonEnabled(true);//设置默认定位按钮是否显示，非必需设置。
        aMap.setMyLocationEnabled(true);// 设置为true表示启动显示定位蓝点，false表示隐藏定位蓝点并不进行定位，默认是false。
        //设置地图类型为卫星图
        aMap.setMapType(AMap.MAP_TYPE_SATELLITE);
        //设置地图的缩放等级（3~19）地图的缩放级别一共分为 17 级，从 3 到 19。数字越大，展示的图面信息越精细。
        aMap.moveCamera(CameraUpdateFactory.zoomTo(16));

        com.amap.api.maps.model.LatLng latLng1 = new com.amap.api.maps.model.LatLng(30.590851,104.085295);
        com.amap.api.maps.model.LatLng latLng2 = new com.amap.api.maps.model.LatLng(30.623032,103.685967);
        com.amap.api.maps.model.LatLng latLng3 = new com.amap.api.maps.model.LatLng(30.591460,104.084758);
        com.amap.api.maps.model.LatLng latLng4 = new com.amap.api.maps.model.LatLng(30.590971,104.084560);
        final Marker maker1 = aMap.addMarker(new MarkerOptions().position(latLng1).title("公司").snippet("华睿康"));
        final Marker maker2 = aMap.addMarker(new MarkerOptions().position(latLng2).title("崇州万达").snippet(""));
        final Marker maker3 = aMap.addMarker(new MarkerOptions().position(latLng3).title("点1").snippet(""));
        final Marker maker4 = aMap.addMarker(new MarkerOptions().position(latLng4).title("点2").snippet(""));

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

    public void initView(){

    }
}