package cn.nitsc.hlz.wechatdemo;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;

public class wechatDemo implements IXposedHookLoadPackage{
    @Override
    public void handleLoadPackage(final XC_LoadPackage.LoadPackageParam lpparam) throws Throwable {
        if (lpparam.packageName.equals("com.tencent.mm")){
            XposedHelpers.findAndHookConstructor("com.tencent.mm.modelsimple.q",
                    lpparam.classLoader,
                    String.class,
                    String.class,
                    String.class,
                    int.class,
                    new XC_MethodHook() {
                        @Override
                        protected void beforeHookedMethod(MethodHookParam param) throws Throwable {
                            super.beforeHookedMethod(param);
                            Object args0 = param.args[0];
                            Object args1 = param.args[1];
                            Object args2 = param.args[2];
                            Object args3 = param.args[3];
                            XposedBridge.log("Start Hooking");
                            XposedBridge.log("HOOK param:\n"+
                                    "args0:"+args0+"\n"+
                                    "args1:"+args1+"\n"+
                                    "args2:"+args2+"\n"+
                                    "args3:"+args3+"\n"
                            );
                            XposedBridge.log("Finish Hooking");
                        }
                    });
        }
    }
}
