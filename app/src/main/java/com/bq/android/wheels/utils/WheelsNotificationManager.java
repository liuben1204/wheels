//package com.bq.android.wheels.utils;
//
//import android.app.Activity;
//import android.app.NotificationManager;
//import android.app.PendingIntent;
//import android.app.TaskStackBuilder;
//import android.content.Context;
//import android.content.Intent;
//import android.support.v7.app.NotificationCompat;
//import android.widget.RemoteViews;
//
//import com.bq.android.wheels.R;
//import com.bq.android.wheels.ui.MainActivity;
//
///**
// * Created by liuben on 16-12-16.
// */
//
//public class WheelsNotificationManager {
//
//    private static volatile WheelsNotificationManager mTestNotification = null;
//    private Context mContext;
//    private NotificationManager mNotificationManager;
//
//    private WheelsNotificationManager(Context context) {
//        mContext = context.getApplicationContext();
//        mNotificationManager = (NotificationManager)
//                mContext.getSystemService(Context.NOTIFICATION_SERVICE);
//    }
//
//    public static WheelsNotificationManager getInstance(Context context) {
//        if (mTestNotification == null) {
//            synchronized (WheelsNotificationManager.class) {
//                if (mTestNotification == null) {
//                    mTestNotification = new WheelsNotificationManager(context);
//                }
//            }
//        }
//        return mTestNotification;
//    }
//
//    /**
//     * 不带TaskStackBuilder，正常通过notification启动一个Activity
//     */
//    public void sendNormalNotification(int id, int icon, String title, String content,
//                                       Intent intent, boolean isClickGone) {
//        PendingIntent pendingIntent = PendingIntent.
//                getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
//        builder.setSmallIcon(icon)
//                .setContentTitle(title)
//                .setContentText(content)
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(isClickGone);
//        mNotificationManager.notify(id, builder.build());
//    }
//
//    /**
//     * 通过TaskStackBuilder，实现跳转Activity后按back键不直接返回HOME
//     * 而是返回指定的parentActivity（需要在manifest中注册）
//     */
//    public void sendSpecialNotification(int id, int icon, String title, String content,
//                                        Class<? extends Activity> activityClass, boolean isClickGone) {
//        Intent intent = new Intent(mContext, activityClass);
//        TaskStackBuilder stackBuilder = TaskStackBuilder.create(mContext);
//        stackBuilder.addParentStack(activityClass);
//        stackBuilder.addNextIntent(intent);
//        PendingIntent pendingIntent = stackBuilder.getPendingIntent(0,
//                PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
//        builder.setSmallIcon(icon)
//                .setContentTitle(title)
//                .setContentText(content)
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(isClickGone);
//        mNotificationManager.notify(id, builder.build());
//    }
//
//
//    public void sendCustomNotification() {
//        RemoteViews remoteViews = new RemoteViews(mContext.getPackageName(), R.layout.notification_remote_views);
//        remoteViews.setImageViewResource(R.id.icon, R.drawable.icon_test_app);
//        remoteViews.setTextViewText(R.id.title, "TitleTitle");
//        remoteViews.setTextViewText(R.id.content, "content");
//
//        Intent intent1 = new Intent(mContext, MainActivity.class);
//        PendingIntent pendingIntent1 = PendingIntent.getActivity(mContext, 1, intent1, PendingIntent.FLAG_UPDATE_CURRENT);
//        remoteViews.setOnClickPendingIntent(R.id.title, pendingIntent1);
//
//        Intent intent = new Intent();
//        intent.setAction("com.android.liuben.setting");
//        PendingIntent pendingIntent = PendingIntent.getActivity(mContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(mContext);
//        builder.setSmallIcon(R.drawable.icon_test_app)
//                .setContent(remoteViews)
//                .setContentIntent(pendingIntent)
//                .setAutoCancel(true);
//        mNotificationManager.notify(0, builder.build());
//    }
//
//    public void cancelNotification(int id) {
//        mNotificationManager.cancel(id);
//    }
//
//    public void cancelNotification(String tag, int id) {
//        mNotificationManager.cancel(tag, id);
//    }
//
//}
