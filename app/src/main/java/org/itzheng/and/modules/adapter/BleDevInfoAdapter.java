package org.itzheng.and.modules.adapter;

import android.bluetooth.BluetoothDevice;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import org.itzheng.and.ble.bean.BluetoothDeviceInfo;

import java.util.List;

/**
 * Title:<br>
 * Description: <br>
 *
 * @email ItZheng@ZoHo.com
 * Created by itzheng on 2018-1-19.
 */
public class BleDevInfoAdapter extends BaseAdapter {
    List<BluetoothDeviceInfo> mItems;

    public BleDevInfoAdapter(List<BluetoothDeviceInfo> deviceInfoList) {
        setDataSet(deviceInfoList);
    }

    @Override
    public int getCount() {
        return mItems == null ? 0 : mItems.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        TextView textView = new TextView(parent.getContext());
        BluetoothDeviceInfo info = mItems.get(position);
        BluetoothDevice device = info.device;
        textView.setText("" + device.getName() + "," + device.getAddress());
        return textView;
    }

    public void setDataSet(List<BluetoothDeviceInfo> deviceInfoList) {
        mItems = deviceInfoList;

    }
}
