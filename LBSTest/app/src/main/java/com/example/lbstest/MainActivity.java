package com.example.lbstest;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.SDKInitializer;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.MapStatusUpdate;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MyLocationConfiguration;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.model.LatLng;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public LocationClient mLocationClient;

    private TextView positionText;

    private MapView bMapView;

    private BaiduMap baiduMap;//baidumap是地图的总控制器，使用getMap获得相应的实例，然后对地图进行相关的操作

    private boolean isFirstLocate = true;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
//        创建LocationClient实例，接收一个context参数
        try {
            mLocationClient = new LocationClient(getApplicationContext());
        } catch (Exception e) {
            e.printStackTrace();
        }
//        注册一个定位监听器，当获取位置时，就会回调这个定位监听器

        //mapview初始化操作必须位于setContentView之前
        bMapView = (MapView) findViewById(R.id.bauduMapView);

        baiduMap = bMapView.getMap();//获取地图总控制器的实例
        baiduMap.setMyLocationEnabled(true);//设置自己的位置可见

        SDKInitializer.initialize(getApplicationContext());//初始化操作
        mLocationClient.registerLocationListener(new MYLocationListener());
        setContentView(R.layout.activity_main);

        positionText = (TextView) findViewById(R.id.position_tv);
//        获取相关权限
        List<String> permissionList = new ArrayList<>();
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.ACCESS_FINE_LOCATION);
        }
        if(ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.READ_PHONE_STATE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.READ_PHONE_STATE);
        }
        if (ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED){
            permissionList.add(Manifest.permission.WRITE_EXTERNAL_STORAGE);
        }
        //判断permissionList是否为空，否则申请的权限不满足，将不能继续执行程序
        if (!permissionList.isEmpty()){
//            一次性申请权限
            String [] permissions = permissionList.toArray(new String[permissionList.size()]);
            ActivityCompat.requestPermissions(MainActivity.this,permissions,1);
        }
        else{
            requestLocation();
        }
    }
    public void requestLocation(){
        initlocation();

        mLocationClient.start();

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode){
            case 1:
                if (grantResults.length > 0){
                    for (int result :grantResults){
                        if (result != PackageManager.PERMISSION_GRANTED){
                            Toast.makeText(this, "必须同意所有权限才能使用此程序", Toast.LENGTH_SHORT).show();
                            finish();
                            return;
                        }
                    }
                    requestLocation();
                }else{
                    Toast.makeText(this, "未知错误", Toast.LENGTH_SHORT).show();
                    finish();
                }
                break;
            default:
        }
    }

//    initlocation：
    public  void initlocation(){
//        新建一个LocationClientOption对象，设置定位间隔
        LocationClientOption locationClientOption = new LocationClientOption();
//        设置定位间隔
        locationClientOption.setScanSpan(5000);
//        设置定位模式
        locationClientOption.setLocationMode(LocationClientOption.LocationMode.Device_Sensors);
//        获取位置的详细信息
        locationClientOption.setIsNeedAddress(true);
//        使用client启动选项
        mLocationClient.setLocOption(locationClientOption);
    }

    public class  MYLocationListener extends BDAbstractLocationListener {

        @Override
        public void onReceiveLocation(BDLocation bdLocation) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    StringBuilder currentPosition = new StringBuilder();

//                    添加数据
                    currentPosition.append("latitude: ").append(bdLocation.getLatitude()).append("\n");
                    currentPosition.append("longtitude : ").append(bdLocation.getLongitude()).append("\n");
                    currentPosition.append("定位方式： ");
                    if (bdLocation.getLocType() == BDLocation.TypeGpsLocation){
                        currentPosition.append("GPS").append("\n");
                    }
                    else if (bdLocation.getLocType() == BDLocation.TypeOffLineLocation){
                        currentPosition.append("Network").append("\n");
                    }

                    currentPosition.append("cuuntry : ").append(bdLocation.getCountry()).append("\n");
                    currentPosition.append("province: ").append(bdLocation.getProvince()).append("\n");
                    currentPosition.append("city ： ").append(bdLocation.getCity()).append("\n");
                    currentPosition.append("district : ").append(bdLocation.getDistrict()).append("\n");
                    currentPosition.append("street : ").append(bdLocation.getStreet()).append("\n");
                    positionText.setText(currentPosition);


                }
            });
            //移动到“我”的位置
            if (bdLocation.getLocType() == BDLocation.TypeGpsLocation || bdLocation.getLocType() ==BDLocation.TypeNetWorkLocation){
                navigationTo(bdLocation);
            }
        }
        public  void  navigationTo(BDLocation bdLocation){
            if (isFirstLocate){//如果是第一次进行加载，显示自己的位置
                //LatLng用来存储一个经纬度信息
                LatLng ll = new LatLng(bdLocation.getLatitude(),bdLocation.getLongitude());
                MapStatusUpdate update = MapStatusUpdateFactory.newLatLng(ll);
                baiduMap.animateMapStatus(update);
                //设置缩放尺寸
                update = MapStatusUpdateFactory.zoomTo(16f);
                baiduMap.animateMapStatus(update);
                isFirstLocate = false;
            }
            MyLocationData.Builder builder = new MyLocationData.Builder();//MyLocationData.Builder用于封装当前的位置信息
            //设置当前位置信息
            builder.latitude(bdLocation.getLatitude());
            builder.longitude(bdLocation.getLongitude());
            //生成MyLocationData对象
            MyLocationData myLocationData = builder.build();
            //调用setMyLocationData将当前设备的位置显示在地图上
            baiduMap.setMyLocationData(myLocationData);
        }

        @Override
        public void onConnectHotSpotMessage(String s, int i) {

        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        bMapView.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        bMapView.onPause();
    }

    //活动销毁时关闭定位服务，省电
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLocationClient.stop();
        bMapView.onDestroy();
        baiduMap.setMyLocationEnabled(false);

    }
}