package com.example.testarea;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.text.PrecomputedText;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.example.testarea.Utils.HexStringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

public class BlueToothActivity extends AppCompatActivity {

    Button bt_01;
    ListView listView;

    BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
    List<BluetoothDevice> deviceList = new ArrayList<>();
    BluetoothGatt bluetoothGatt;
    BluetoothGattService service;
    BluetoothGattCharacteristic characteristic;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_tooth);

        initView();
        initAction();


    }

    public void initView(){
        bt_01 = findViewById(R.id.bluetooth_bt_01);
        listView = findViewById(R.id.bluetooth_listview);
    }

    public void initAction(){
        bt_01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchBlueTooth();
            }
        });

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Map<String,String> map = (Map<String,String>)listView.getItemAtPosition(position);
                BluetoothDevice mDevice = bluetoothAdapter.getRemoteDevice(map.get("address"));
                bluetoothGatt = mDevice.connectGatt(BlueToothActivity.this,false,new BluetoothGattCallback(){

                    @Override
                    public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
                        if(newState == BluetoothProfile.STATE_CONNECTED){
                            Log.i("121212","启动服务发现"+bluetoothGatt.discoverServices());
                            Log.i("121212","00"+gatt+"old::"+status+"new::"+newState);
                        }
                    }

                    @Override
                    public void onServicesDiscovered(BluetoothGatt gatt, int status) {
                        if(status == BluetoothGatt.GATT_SUCCESS){
                            List<BluetoothGattService> sup = bluetoothGatt.getServices();
                            for(BluetoothGattService gattService : sup){
                                List<BluetoothGattCharacteristic> gattCharacteristics = gattService.getCharacteristics();
                                for(BluetoothGattCharacteristic gattCharacteristic : gattCharacteristics){
                                    int charaProp = gattCharacteristic.getProperties();
                                    if((charaProp | BluetoothGattCharacteristic.PROPERTY_READ)>0){
                                        Log.i("aaa","serviceUUID"+gattService.getUuid()+"charUUID"+gattCharacteristic.getUuid());
                                    }
                                    if((charaProp | BluetoothGattCharacteristic.PROPERTY_WRITE)>0){
                                        Log.i("bbb","serviceUUID"+gattService.getUuid()+"charUUID"+gattCharacteristic.getUuid());
                                    }
                                    if((charaProp | BluetoothGattCharacteristic.PROPERTY_NOTIFY)>0){
                                        Log.i("bbb","serviceUUID"+gattService.getUuid()+"charUUID"+gattCharacteristic.getUuid());
                                    }

                                }
                            }
                        }else{
                            Log.i("121212",gatt.getServices().toString()+"111");
                            Log.i("121212",status+"111");
                        }

                        service = bluetoothGatt.getService(UUID.fromString("0000ffe0-0000-1000-8000-00805f9b34fb"));
                        characteristic = service.getCharacteristic(UUID.fromString("0000ffe1-0000-1000-8000-00805f9b34fb"));
                        bluetoothGatt.setCharacteristicNotification(characteristic,true);
                    }

                    @Override
                    public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
                        if(status == BluetoothGatt.GATT_SUCCESS){
                            Log.i("121212","aaaa");
                            byte[] datas = characteristic.getValue();
                            for(int i=0;i<datas.length;i++){
                                Log.i("3333"," "+datas[i]);
                            }
                            Log.i("121212","读取成功"+datas.toString());
                            Log.i("121212","bbbb");
                        }
                    }

                    @Override
                    public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
                        Log.i("121212","010101"+characteristic.getValue());
                        byte[] datas = characteristic.getValue();
                        String t = HexStringUtils.bytesToHexString(datas);
                        for(int i=0;i<datas.length;i++){
                            Log.i("3333"," "+datas[i]);
                        }
                        Log.i("121212","44 "+t);
                    }
                });
            }
        });
    }


    /**
     * 搜索蓝牙
     */
    public void searchBlueTooth(){
        Intent bl = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
        startActivityForResult(bl,1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            showBondDevice();
        }
    }

    private void showBondDevice() {
//        deviceList.clear();
        // 所有已绑定设备，一个Set集合
        List<Map<String,String>> listDevices = new ArrayList<>();
        Set<BluetoothDevice> tmp = bluetoothAdapter.getBondedDevices();
        int number = 1;
        for (BluetoothDevice d : tmp) {
            Map<String,String> map = new HashMap<>();
            deviceList.add(d);
//            Log.d("bluetooths :: name",d.getName());
//            Log.d("bluetooths :: address",d.getAddress());
//            Log.d("bluetooths :: BondState",String.valueOf(d.getBondState()));
//            Log.d("bluetooths :: UUIDs",d.getUuids().toString());
            map.put("name",d.getName());
            map.put("address",d.getAddress());
            map.put("BondState",String.valueOf(d.getBondState()));
            if(d.getUuids()!=null) map.put("UUIDs",d.getUuids().toString());

            listDevices.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,listDevices,R.layout.item_bluetoothinfo,new String[]{"name","address","BondState","UUIDs"},new int[]{R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4});
        listView.setAdapter(simpleAdapter);
    }
}