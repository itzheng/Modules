package org.itzheng.and.ble.utils;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.os.SystemClock;
import android.util.Log;

import org.itzheng.and.ble.callback.OnConnectionStateChangeListener;
import org.itzheng.and.ble.callback.OnReceiveDataListener;
import org.itzheng.and.ble.servers.BluetoothLeService;

import java.util.ArrayList;
import java.util.List;

/**
 * Title:蓝牙操作工具类<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-1-19.
 */
public class BleOptionUtils {
    private static final String TAG = "BleOptionUtils";
    /**
     * 服务未连接时，监听临时保存到这里，等服务器创建时，将监听像服务赋值，然后清空
     */
    private List<OnReceiveDataListener> tempOnReceiveDataListener = new ArrayList<>();
    /**
     * 服务未连接时，监听临时保存到这里，等服务器创建时，将监听像服务赋值，然后清空
     */
    private List<OnConnectionStateChangeListener> tempOnConnectionStateChangeListener = new ArrayList<>();

    /**
     * 实例化一个对象
     *
     * @return
     */
    public static BleOptionUtils newInstance(Context context) {
        BleOptionUtils bleOptionUtils = new BleOptionUtils();
        bleOptionUtils.init(context);
        return bleOptionUtils;
    }

    /**
     * 蓝牙服务
     */
    private BluetoothLeService mBluetoothLeService;
    private final ServiceConnection mServiceConnection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName componentName, IBinder service) {
            Log.i(TAG, "onServiceConnected: ");
            mBluetoothLeService = ((BluetoothLeService.LocalBinder) service).getService();
            if (mBluetoothLeService == null) {
                return;
            }
            addOnReceiveDataListenerToServer();
            addOnConnectionStateChangeListenerToServer();

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBluetoothLeService = null;
        }
    };

    /**
     * 将接收数据的监听添加到服务
     */
    private void addOnReceiveDataListenerToServer() {
        if (tempOnReceiveDataListener != null && tempOnReceiveDataListener.size() > 0) {
            for (int i = 0; i < tempOnReceiveDataListener.size(); i++) {
                OnReceiveDataListener onReceiveDataListener = tempOnReceiveDataListener.get(i);
                mBluetoothLeService.addOnReceiveDataListener(onReceiveDataListener);
                tempOnReceiveDataListener.remove(onReceiveDataListener);
                i--;
            }
        }
    }

    /**
     * 将接收数据的监听添加到服务
     */
    private void addOnConnectionStateChangeListenerToServer() {
        if (tempOnConnectionStateChangeListener != null && tempOnConnectionStateChangeListener.size() > 0) {
            for (int i = 0; i < tempOnConnectionStateChangeListener.size(); i++) {
                OnConnectionStateChangeListener listener = tempOnConnectionStateChangeListener.get(i);
                mBluetoothLeService.addOnConnectionStateChangeListener(listener);
                tempOnConnectionStateChangeListener.remove(listener);
                i--;
            }
        }
    }

    private Context mContext;

    /**
     * 初始化
     *
     * @param context
     */
    private void init(Context context) {
        Log.i(TAG, "init: ");
        mContext = context;
        Intent intent = new Intent(context, BluetoothLeService.class);
        mContext.bindService(intent, mServiceConnection, Context.BIND_AUTO_CREATE);
    }

    public void recycle() {
        if (mBluetoothLeService != null) {
            Intent intent = new Intent(mContext, BluetoothLeService.class);
            mBluetoothLeService.stopService(intent);
        }

    }

    /**
     * 进行蓝牙连接，如果蓝牙不可连接则返回false
     *
     * @param address
     * @return
     */
    public boolean connect(String address) {
        return mBluetoothLeService.connect(address);

    }

    /**
     * 断开连接
     */
    public void disconnect() {
        mBluetoothLeService.disconnect();

    }


    /**
     * 添加收到消息的监听
     *
     * @param listener
     */
    public void addOnReceiveDataListener(OnReceiveDataListener listener) {
        if (listener == null) {
            return;
        }
        if (mBluetoothLeService == null) {
            tempOnReceiveDataListener.add(listener);
        } else {
            mBluetoothLeService.addOnReceiveDataListener(listener);
        }

    }

    /**
     * 移除监听
     *
     * @param listener
     */
    public void removeOnReceiveDataListener(OnReceiveDataListener listener) {
        if (listener == null) {
            return;
        }
        if (mBluetoothLeService == null) {
            tempOnReceiveDataListener.remove(listener);
        } else {
            mBluetoothLeService.removeOnReceiveDataListener(listener);
        }

    }

    /**
     * 添加收到消息的监听
     *
     * @param listener
     */
    public void addOnConnectionStateChangeListener(OnConnectionStateChangeListener listener) {
        if (listener == null) {
            return;
        }
        if (mBluetoothLeService == null) {
            tempOnConnectionStateChangeListener.add(listener);
        } else {
            mBluetoothLeService.addOnConnectionStateChangeListener(listener);
        }

    }

    /**
     * 移除监听
     *
     * @param listener
     */
    public void removeOnConnectionStateChangeListener(OnConnectionStateChangeListener listener) {
        if (listener == null) {
            return;
        }
        if (mBluetoothLeService == null) {
            tempOnConnectionStateChangeListener.remove(listener);
        } else {
            mBluetoothLeService.removeOnConnectionStateChangeListener(listener);
        }

    }

    public int post(byte[] data) {
        return mBluetoothLeService.post(data);
    }

    public int postDelayedCmd(byte[] data) {
        final byte[] finalBytes = data;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < finalBytes.length; i++) {
                    post(new byte[]{finalBytes[i]});
                    SystemClock.sleep(50);
                }
            }
        }).start();
        return 1;
    }
}
