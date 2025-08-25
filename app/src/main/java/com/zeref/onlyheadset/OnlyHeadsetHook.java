package com.zeref.onlyheadset;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

import android.media.AudioManager;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothProfile;

public class OnlyHeadsetHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (!lpparam.packageName.equals("com.miui.misound")) return;

        XposedHelpers.findAndHookMethod(
            AudioManager.class,
            "setParameters",
            String.class,
            new XC_MethodHook() {
                @Override
                protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                    AudioManager am = (AudioManager) param.thisObject;
                    boolean wiredHeadsetOn = am.isWiredHeadsetOn();
                    boolean btConnected = false;

                    BluetoothAdapter btAdapter = BluetoothAdapter.getDefaultAdapter();
                    if (btAdapter != null && btAdapter.isEnabled()) {
                        btConnected = btAdapter.getProfileConnectionState(BluetoothProfile.HEADSET)
                                       == BluetoothProfile.STATE_CONNECTED;
                    }

                    if (wiredHeadsetOn) {
                        param.args[0] = "routing=HEADSET";
                    } else if (btConnected) {
                        param.args[0] = "bt_sco_on=1;routing=A2DP";
                    } else {
                        param.args[0] = "routing=SPEAKER";
                    }
                }
            }
        );
    }
}
