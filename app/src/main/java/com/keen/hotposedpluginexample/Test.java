package com.keen.hotposedpluginexample;

import android.util.Log;

import com.keen.hotposed.support.HotposedInterface.IHotposedPlugin;

import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class Test implements IHotposedPlugin {

    public static final String TAG = "HWLogHook";

    XC_MethodHook hook = new XC_MethodHook() {
        @Override
        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
            Log.i(TAG, param.args[0] + "\t\t" + param.args[1]);
        }
    };

    @Override
    public void startHotposedPlugin(XC_LoadPackage.LoadPackageParam loadPackageParam) {

        Log.i("HotposedPlugin", loadPackageParam.packageName + " changed twice with hook");

        XposedHelpers.findAndHookMethod("com.huawei.wallet.f.c.c", loadPackageParam.classLoader, "i", String.class, String.class, boolean.class, hook);
        XposedHelpers.findAndHookMethod("com.huawei.wallet.f.c.c", loadPackageParam.classLoader, "h", String.class, String.class, boolean.class, hook);
        XposedHelpers.findAndHookMethod("com.huawei.wallet.f.c.c", loadPackageParam.classLoader, "f", String.class, String.class, Throwable.class, hook);

    }

}
