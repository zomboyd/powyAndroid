package com.example.alex.powy.controller;

import android.app.Activity;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.le.BluetoothLeScanner;
<<<<<<< HEAD
<<<<<<< HEAD
=======
>>>>>>> client bluetooth
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


=======
import android.content.Context;
import android.content.Intent;
import android.util.Log;


>>>>>>> add button for turn off/on
public class connectionController extends Activity {

    private static final int REQUEST_ENABLE_BT = 0;
    private BluetoothAdapter mBluetoothAdapter;
    private BluetoothLeScanner mBluetoothLeScanner;
<<<<<<< HEAD
    private ArrayList<String> disco = new ArrayList<>();
    boolean supported;
=======
    boolean isSupported;
>>>>>>> add button for turn off/on
    Context mContext;


    public connectionController(Context context){
        mContext = context;
        mBluetoothAdapter  = BluetoothAdapter.getDefaultAdapter();
<<<<<<< HEAD
        supported = mBluetoothAdapter != null;
=======
        isSupported = mBluetoothAdapter != null;
>>>>>>> add button for turn off/on
    }

    public boolean turnOn() {
        if (supported && !mBluetoothAdapter.isEnabled()) {
            Intent enableIntent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
            mContext.startActivity(enableIntent);
<<<<<<< HEAD
            return true;
=======
>>>>>>> client bluetooth
        }
        return false;
    }

    public boolean turnOff() {
        if (supported && mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
            return true;
        }
        return false;
    }

<<<<<<< HEAD
    public boolean visibleOn() {
        if (supported && mBluetoothAdapter.isEnabled()) {
            Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
            mContext.startActivity(getVisible);
            return true;
=======
    public boolean turnOff() {
        if (mBluetoothAdapter.isEnabled()) {
            mBluetoothAdapter.disable();
            return true;
        }
        return false;
    }

    public boolean visibleOn() {
        if (mBluetoothAdapter.isEnabled()) {
            Intent getVisible = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
<<<<<<< HEAD
>>>>>>> add button for turn off/on
=======
            mContext.startActivity(getVisible);
>>>>>>> client bluetooth
        }
        return false;
    }

    public boolean visibleOff() {
<<<<<<< HEAD
        if (supported && mBluetoothAdapter.isDiscovering() && mBluetoothAdapter.isEnabled()) {
=======
        if (mBluetoothAdapter.isDiscovering() && mBluetoothAdapter.isEnabled()) {
>>>>>>> add button for turn off/on
            mBluetoothAdapter.cancelDiscovery();
            return true;
        }
        return false;
    }

<<<<<<< HEAD
<<<<<<< HEAD
    public void discoverable() {
        final BroadcastReceiver mReceiver = new BroadcastReceiver() {
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                // When discovery finds a device
                if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                    // Get the BluetoothDevice object from the Intent
                    BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                    // Add the name and address to an array adapter to show in a ListView
                    disco.add(device.getName() + "\n" + device.getAddress());
                }
            }
        };
        mBluetoothAdapter.startDiscovery();
        // Register the BroadcastReceiver
        IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
        mContext.registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy
        //mContext.unregisterReceiver(mReceiver);
    }

    public ArrayList<String> getDiscoverable() {
        return disco;
    }

=======
>>>>>>> add button for turn off/on
=======
    private final BroadcastReceiver mReceiver = new BroadcastReceiver() {
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            // When discovery finds a device
            if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                // Get the BluetoothDevice object from the Intent
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                // Add the name and address to an array adapter to show in a ListView
                //mArrayAdapter.add(device.getName() + "\n" + device.getAddress());
            }
        }
    };
    // Register the BroadcastReceiver
    IntentFilter filter = new IntentFilter(BluetoothDevice.ACTION_FOUND);
    //registerReceiver(mReceiver, filter); // Don't forget to unregister during onDestroy

>>>>>>> client bluetooth
    public boolean getIsSupported() {
        return supported;
    }

}