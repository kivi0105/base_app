# base_app

## How to
- Step 1:Add the JitPack repository to your build file
```
allprojects {
    repositories {
        ...
        maven { url "https://jitpack.io" }
    }
}
```
- step 2:Add the dependency

```
compile 'com.github.kivi0105:base_app:master-SNAPSHOT'
```



## [JitPack](https://jitpack.io/)



### NetworkUtil配置
监听网络变化

Application配置,继承BaseApp

```
import com.kivi.base_app_lib.base.BaseApp;

public class MyApp extends BaseApp {}
```


在需要检测网络变化后通知前台界面的activity中配置

```
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //注册
        NetworkChangedReceiver.add(this);
    }
    ...
    @Override
    protected void onDestroy() {
        super.onDestroy();
        //移除
        NetworkChangedReceiver.remove(this);
    }
    
```

并实现该接口
```
public interface NetworkChangedInterface {
        void mobileNet();

        void wifiNet();

        void unconnectedNet();

    }
```
