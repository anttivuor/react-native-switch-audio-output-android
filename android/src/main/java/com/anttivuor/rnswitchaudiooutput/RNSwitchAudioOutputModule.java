package com.anttivuor.rnswitchaudiooutput;

import android.content.Context;

import android.media.AudioManager;
import android.media.AudioDeviceInfo;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;

import java.util.ArrayList;

public class RNSwitchAudioOutputModule extends ReactContextBaseJavaModule {
    private final ReactApplicationContext reactContext;

    public RNSwitchAudioOutputModule(ReactApplicationContext reactContext) {
        super(reactContext);
        this.reactContext = reactContext;
    }

    @Override
    public String getName() {
        return "RNSwitchAudioOutputModule";
    }

    private String getAudioRouteType(int type) {
        switch (type){
            case(AudioDeviceInfo.TYPE_BLUETOOTH_A2DP):
            case(AudioDeviceInfo.TYPE_BLUETOOTH_SCO):
                return "Bluetooth";
            case(AudioDeviceInfo.TYPE_WIRED_HEADPHONES):
            case(AudioDeviceInfo.TYPE_WIRED_HEADSET):
                return "Headset";
            case(AudioDeviceInfo.TYPE_BUILTIN_MIC):
                return "Phone";
            case(AudioDeviceInfo.TYPE_BUILTIN_SPEAKER):
                return "Speaker";
            default:
                return null;
        }
    }

    @ReactMethod
    public void getAudioDevices(Promise promise) {
        try {
            Context context = this.reactContext.getApplicationContext();

            AudioManager audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);
            WritableArray devices = Arguments.createArray();
            ArrayList<String> typeChecker = new ArrayList<>();
            AudioDeviceInfo[] audioDeviceInfo = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS);

            for (AudioDeviceInfo device : audioDeviceInfo){
                String type = getAudioRouteType(device.getType());
                if (type != null && !typeChecker.contains(type)) {
                    WritableMap deviceInfo = Arguments.createMap();
                    deviceInfo.putString("name", type);
                    deviceInfo.putString("type", type);
                    typeChecker.add(type);
                    devices.pushMap(deviceInfo);
                }
            }
            promise.resolve(devices);
        } catch (Exception e) {
            promise.reject("GetAudioRoutes Error", e.getMessage());
        }
    }

    @ReactMethod
    public void setAudioDevice(String deviceName, Promise promise) {
        try {
            Context context = this.reactContext.getApplicationContext();
            AudioManager audioManager = (AudioManager) context.getSystemService(context.AUDIO_SERVICE);

            if (deviceName.equals("Bluetooth")) {
                audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
                audioManager.startBluetoothSco();
                audioManager.setBluetoothScoOn(true);
                promise.resolve(true);
                return;
            } else if (deviceName.equals("Headset")) {
                audioManager.setMode(AudioManager.MODE_IN_COMMUNICATION);
                audioManager.stopBluetoothSco();
                audioManager.setBluetoothScoOn(false);
                audioManager.setSpeakerphoneOn(false);
                promise.resolve(true);
                return;
            } else if (deviceName.equals("Speaker")) {
                audioManager.setMode(AudioManager.MODE_NORMAL);
                audioManager.stopBluetoothSco();
                audioManager.setBluetoothScoOn(false);
                audioManager.setSpeakerphoneOn(true);
                return;
            }
            promise.resolve(false);
        } catch (Exception e) {
            promise.reject("SetAudioDevice", e.getMessage());
        }
    }
}
