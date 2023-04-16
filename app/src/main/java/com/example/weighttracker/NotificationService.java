package com.example.weighttracker;

import android.annotation.SuppressLint;
import android.app.IntentService;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

public class NotificationService extends IntentService {

    private static final String ACTION_CHECK_GOAL_WEIGHT = "com.example.weighttracker.action.CHECK_GOAL_WEIGHT";

    private static final String EXTRA_GOAL_WEIGHT = "com.example.weighttracker.extra.GOAL_WEIGHT";
    private static final String EXTRA_DAILY_WEIGHT = "com.example.weighttracker.extra.DAILY_WEIGHT";

    public NotificationService() {
        super("NotificationService");
    }

    /**
     * Starts this service to check if the goal weight has been reached.
     *
     * @see IntentService
     */
    public static void startCheckGoalWeight(Context context, String goalWeight, String dailyWeight) {
        Intent intent = new Intent(context, NotificationService.class);
        intent.setAction(ACTION_CHECK_GOAL_WEIGHT);
        intent.putExtra(EXTRA_GOAL_WEIGHT, goalWeight);
        intent.putExtra(EXTRA_DAILY_WEIGHT, dailyWeight);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (ACTION_CHECK_GOAL_WEIGHT.equals(action)) {
                final String goalWeight = intent.getStringExtra(EXTRA_GOAL_WEIGHT);
                final String dailyWeight = intent.getStringExtra(EXTRA_DAILY_WEIGHT);
                handleCheckGoalWeight(goalWeight, dailyWeight);
            }
        }
    }

    /**
     * Check if the goal weight has been reached and send a notification if it has.
     */
    @SuppressLint("MissingPermission")
    private void handleCheckGoalWeight(String goalWeight, String dailyWeight) {
        if (goalWeight.equals(dailyWeight)) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);

            NotificationCompat.Builder builder = new NotificationCompat.Builder(this, "channel_id")
                    //.setSmallIcon()
                    .setContentTitle("Goal weight achieved!")
                    .setContentText("Congratulations, you've reached your goal weight of " + goalWeight + "!")
                    .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true);

            NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
            notificationManager.notify(1, builder.build());
        }
    }

}
