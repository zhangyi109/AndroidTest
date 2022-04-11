package com.example.forceoffline;

import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

public class ActivityController {

    public static List<Activity> activities = new ArrayList<>();

    //添加活动
    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    //删除活动
    public static void removeActivity(Activity activity){
        activities.remove(activity);

    }

    //删除所有活动
    public static void finishAll(){
        for(Activity activity:activities){
            if (!activity.isFinishing()){
                activity.finish();
            }
        }
        activities.clear();
    }
}
