package com.example.testarea;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothManager;
import android.bluetooth.le.BluetoothLeScanner;
import android.bluetooth.le.ScanCallback;
import android.bluetooth.le.ScanSettings;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Bluetooth2 extends AppCompatActivity {

    BluetoothAdapter mBluetoothAdapter;
    BluetoothManager manager;
    private Handler mHandler;
    private boolean mScanning;
    private LeDeviceListAdapter mLeDeviceListAdapter;
    private SimpleAdapter simpleAdapter;
    private ListView listView;

    // Stops scanning after 10 seconds.
    private static final long SCAN_PERIOD = 5000;

    private List<BluetoothDevice> list_device = new ArrayList<>();

    final int FLAG_SCAN_FINISH = 1;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            switch (msg.what){
                case FLAG_SCAN_FINISH:
                    listView.setAdapter(simpleAdapter);
                    if(!list_device.isEmpty()){
                        for(BluetoothDevice device : list_device){
                            Log.d("device::",device.getName()+"::"+device.getAddress());
                        }

                    }else{
                        Log.d("device::","empty");
                    }
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth2);

        initView();
        //初始化ble设配器
        manager = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
        mBluetoothAdapter = manager.getAdapter();
        mHandler = new Handler();
        //判断蓝牙是否开启，如果关闭则请求打开蓝牙
        if (mBluetoothAdapter == null || !mBluetoothAdapter.isEnabled()) {
            //方式一：请求打开蓝牙
            Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            startActivityForResult(intent, 1);
            //方式二：半静默打开蓝牙
            //低版本android会静默打开蓝牙，高版本android会请求打开蓝牙
            //mBluetoothAdapter.enable();
        }

        new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                //获取蓝牙广播  本地蓝牙适配器的状态改变时触发
                String action = intent.getAction();
                if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                    //获取蓝牙广播中的蓝牙新状态
                    int blueNewState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                    //获取蓝牙广播中的蓝牙旧状态
                    int blueOldState = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, 0);
                    switch (blueNewState) {
                        //正在打开蓝牙
                        case BluetoothAdapter.STATE_TURNING_ON:
                            Log.e("blueNewState::","正在打开蓝牙");
                            break;
                        //蓝牙已打开
                        case BluetoothAdapter.STATE_ON:
                            Log.e("blueNewState::","正在打开蓝牙");
                            break;
                        //正在关闭蓝牙
                        case BluetoothAdapter.STATE_TURNING_OFF:
                            Log.e("blueNewState::","正在打开蓝牙");
                            break;
                        //蓝牙已关闭
                        case BluetoothAdapter.STATE_OFF:
                            Log.e("blueNewState::","正在打开蓝牙");
                            break;
                    }
                }
            }
        };
    }

    public void initView(){
        listView = findViewById(R.id.bluetooth_listview);
    }

    /**
     * 开始扫描蓝牙设备
     * @param enable 设置是否扫描
     */
    private void scanLeDevice(final boolean enable) {
        if (enable) {
            // Stops scanning after a pre-defined scan period.
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mScanning = false;
                    mBluetoothAdapter.stopLeScan(mLeScanCallback);
                    refreshList(list_device);
                    invalidateOptionsMenu();
                }
            }, SCAN_PERIOD);

            mScanning = true;
            mBluetoothAdapter.startLeScan(mLeScanCallback);
        } else {
            mScanning = false;
            mBluetoothAdapter.stopLeScan(mLeScanCallback);
        }
        invalidateOptionsMenu();
    }

    // Device scan callback.
    private BluetoothAdapter.LeScanCallback mLeScanCallback =
            new BluetoothAdapter.LeScanCallback() {

                @Override
                public void onLeScan(final BluetoothDevice device, int rssi, byte[] scanRecord) {
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Log.e("BLE::",device.getName()+"::"+device.getAddress());
                            if(!list_device.contains(device)){
                                list_device.add(device);
                            }
//                            Log.e("list_device::",list_device.toString());
                        }
                    });
                }
            };
    // Adapter for holding devices found through scanning.
    private class LeDeviceListAdapter extends BaseAdapter {
        private ArrayList<BluetoothDevice> mLeDevices;
        private LayoutInflater mInflator;

        public LeDeviceListAdapter() {
            super();
            mLeDevices = new ArrayList<BluetoothDevice>();
            mInflator = Bluetooth2.this.getLayoutInflater();
        }

        public void addDevice(BluetoothDevice device) {
            if(!mLeDevices.contains(device)) {
                mLeDevices.add(device);
            }
        }

        public BluetoothDevice getDevice(int position) {
            return mLeDevices.get(position);
        }

        public void clear() {
            mLeDevices.clear();
        }

        @Override
        public int getCount() {
            return mLeDevices.size();
        }

        @Override
        public Object getItem(int i) {
            return mLeDevices.get(i);
        }

        @Override
        public long getItemId(int i) {
            return i;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {
            ViewHolder viewHolder;
            // General ListView optimization code.
            if (view == null) {
                view = mInflator.inflate(R.layout.listitem_device, null);
                viewHolder = new ViewHolder();
                viewHolder.deviceAddress = (TextView) view.findViewById(R.id.device_address);
                viewHolder.deviceName = (TextView) view.findViewById(R.id.device_name);
                view.setTag(viewHolder);
            } else {
                viewHolder = (ViewHolder) view.getTag();
            }

            BluetoothDevice device = mLeDevices.get(i);
            final String deviceName = device.getName();
            if (deviceName != null && deviceName.length() > 0)
                viewHolder.deviceName.setText(deviceName);
            else
                viewHolder.deviceName.setText(R.string.unknown_device);
                viewHolder.deviceAddress.setText(device.getAddress());

            return view;
        }
    }

    static class ViewHolder {
        TextView deviceName;
        TextView deviceAddress;
    }

    public void refreshList(List<BluetoothDevice> listDevice){
        Log.d("refreshList","");
        List<Map<String,String>> list_map = new ArrayList<>();
        for(BluetoothDevice device : listDevice){
            Map<String,String> map = new HashMap<>();
            map.put("name",device.getName());
            map.put("address",device.getAddress());
            map.put("BondState",String.valueOf(device.getBondState()));
            map.put("UUIDs",String.valueOf(device.getUuids()));
            list_map.add(map);
        }
        SimpleAdapter simpleAdapter = new SimpleAdapter(this,list_map,R.layout.item_bluetoothinfo,new String[]{"name","address","BondState","UUIDs"},new int[]{R.id.textView1,R.id.textView2,R.id.textView3,R.id.textView4});
        for(Map<String,String> m : list_map){
            Log.d("map::",m.toString());
        }
        listView = findViewById(R.id.bluetooth2_listview);
        listView.setAdapter(simpleAdapter);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mBluetoothAdapter!=null){
            scanLeDevice(true);
        }
    }

}