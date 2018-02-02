package org.itzheng.and.modules.activity;

import android.bluetooth.BluetoothDevice;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import org.itzheng.and.baseutils.ui.UIUtils;
import org.itzheng.and.ble.bean.BluetoothDeviceInfo;
import org.itzheng.and.ble.callback.BleScanCallback;
import org.itzheng.and.ble.callback.OnConnectionStateChangeListener;
import org.itzheng.and.ble.callback.OnReceiveDataListener;
import org.itzheng.and.ble.utils.BleOptionUtils;
import org.itzheng.and.ble.utils.BleScanUtils;
import org.itzheng.and.ble.utils.BluetoothUtils;
import org.itzheng.and.ble.utils.ByteUtils;
import org.itzheng.and.modules.R;
import org.itzheng.and.modules.adapter.BleDevInfoAdapter;

import java.util.List;

public class BleActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    ListView listView;
    BleScanUtils bleScanUtils = BleScanUtils.newInstance();
    BleOptionUtils bleOptionUtils = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bleOptionUtils = BleOptionUtils.newInstance(getApplicationContext());
        bleScanUtils.setScanCallback(mScanCallback);
        UIUtils.init(this);
        setContentView(R.layout.activity_ble);
        listView = findViewById(R.id.lv);
        initViewClick();
        initViewData();
        initBle();
    }

    private void initBle() {
        bleOptionUtils.addOnReceiveDataListener(new OnReceiveDataListener() {
            @Override
            public void onReceiveData(byte[] value) {
                Log.i(TAG, "onReceiveData:" + ByteUtils.toHexString(value));
            }
        });
        bleOptionUtils.addOnConnectionStateChangeListener(new OnConnectionStateChangeListener() {
            @Override
            public void onConnected() {
                Log.i(TAG, "onConnected: ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        UIUtils.showToast("连接成功");
                    }
                });

            }

            @Override
            public void onDisconnected() {
                Log.i(TAG, "onDisconnected: ");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        UIUtils.showToast("断开连接");
                    }
                });

            }

            @Override
            public void onServicesDiscovered() {
                Log.i(TAG, "onServicesDiscovered: ");
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SystemClock.sleep(100);
                        startSync();
                    }
                }).start();

            }
        });
    }

    BleDevInfoAdapter adapter = null;

    private void initViewData() {

    }

    //    fafe03a700a0（恢复出厂设置）
//    fafe03a2ff5a(轮胎同步)
    byte[] syncBytes = new byte[]{(byte) 0xfa, (byte) 0xfe, 0x03, (byte) 0xa2, (byte) 0xff, 0x5a};

    private void initViewClick() {
        findViewById(R.id.btnStart).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                UIUtils.showToast("start");
                            }
                        });

                    }
                }).start();

                startScan();
            }
        });
        findViewById(R.id.btnStop).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.showToast("stop");
                bleScanUtils.stopLeScan();
            }
        });
        findViewById(R.id.btnIsScan).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(BleActivity.this, "isDiscovering:" + BluetoothUtils.isDiscovering(), Toast.LENGTH_LONG).show();
            }
        });
        findViewById(R.id.btnPost).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSync();

            }
        });
        findViewById(R.id.btnDis).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bleOptionUtils.disconnect();
            }
        });
        findViewById(R.id.btnConnectStatus).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean connect = bleOptionUtils.isConnect();
                if (connect) {
                    UIUtils.showToast(bleOptionUtils.getCurrentAddress());
                } else {
                    UIUtils.showToast("蓝牙未连接");
                }
            }
        });
        findViewById(R.id.btnNewActivity).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UIUtils.startActivity(BleActivity.class);
            }
        });
    }

    private void startSync() {
        int code = bleOptionUtils.postDelayedCmd(syncBytes);
        Log.i(TAG, "onClick: " + "post code:" + code + " data:" + ByteUtils.toHexString(syncBytes));
    }

    BleScanCallback mScanCallback = new BleScanCallback() {

        @Override
        public void onLeScan(List<BluetoothDeviceInfo> deviceInfoList) {
            Log.i(TAG, "deviceInfoList:" + deviceInfoList.size());
            setAdapter(deviceInfoList);
        }

        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

        }
    };

    private void setAdapter(final List<BluetoothDeviceInfo> deviceInfoList) {
        if (adapter == null) {
            adapter = new BleDevInfoAdapter(deviceInfoList);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    BluetoothDeviceInfo info = deviceInfoList.get(position);
                    Log.d(TAG, "isSuccess:" + bleOptionUtils.connect(info.device.getAddress()));
                }
            });
        } else {
            adapter.setDataSet(deviceInfoList);
            adapter.notifyDataSetChanged();
        }
    }

    private void startScan() {
        bleScanUtils.startLeScan();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        bleScanUtils.stopLeScan();
        bleOptionUtils.recycle();
        bleOptionUtils = null;
    }
}
