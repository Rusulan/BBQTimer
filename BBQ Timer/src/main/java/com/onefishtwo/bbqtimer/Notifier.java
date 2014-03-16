// The MIT License (MIT)
//
// Copyright (c) 2014 Jerry Morrison
//
// Permission is hereby granted, free of charge, to any person obtaining a copy of this software and
// associated documentation files (the "Software"), to deal in the Software without restriction,
// including without limitation the rights to use, copy, modify, merge, publish, distribute,
// sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is
// furnished to do so, subject to the following conditions:
//
// The above copyright notice and this permission notice shall be included in all copies or
// substantial portions of the Software.
//
// THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT
// NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND
// NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM,
// DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
// OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.

package com.onefishtwo.bbqtimer;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;

/**
 * Manages the app's Android Notifications.
 */
public class Notifier {
    private static final int NOTIFICATION_ID = 7;

    private final Context context;
    private boolean showNotification = true;
    private boolean playChime = false;
    private boolean vibrateWithChime = true;

    public Notifier(Context context) {
        this.context = context;
    }

    /**
     * Builder-style setter: Whether {@link #open(TimeCounter)} should display the notification in
     * the notification area and drawer. Default = true.
     */
    public Notifier setShowNotification(boolean showNotification) {
        this.showNotification = showNotification;
        return this;
    }

    /**
     * Builder-style setter: Whether {@link #open(TimeCounter)} should play a notification chime,
     * light flash, and optionally vibrate. Default = false.
     */
    public Notifier setPlayChime(boolean playChime) {
        this.playChime = playChime;
        return this;
    }

    /**
     * Builder-style setter: Whether to vibrate when playing a notification chime. Default = true.
     */
    public Notifier setVibrateWithChime(boolean vibrateWithChime) {
        this.vibrateWithChime = vibrateWithChime;
        return this;
    }

    /**
     * <em>Opens</em> this app's notification with visible, audible, and/or tactile content
     * depending on {@link #setShowNotification(boolean)}, {@link #setPlayChime(boolean)}, and
     * {@link #setVibrateWithChime(boolean)}, <em>or cancels</em> the app's notification if
     * showNotification and playChime are both false.
     *
     * @param timer -- the TimeCounter state to display.
     */
    public void open(TimeCounter timer) {
        if (!(showNotification || playChime)) {
            cancelAll();
        }

        // TODO: builder.setNumber(chimeCount)?
        // TODO: builder.setContentText("n reminder chimes")? "chime every n minutes"?
        // TODO: Play a custom chime sound. OGG/MP3 Mono/Stereo 8-320Kbps CBR or VBR.
        // TODO: Large icon: mdpi 64x64 px, hdpi 96x96 px, xhdpi 128x128 px, xxhpdi 192x192 px.
        // TODO: Add button actions to pause/resume/reset the timer?
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                // Use PRIORITY_MAX when there's a time-critical chime?
                .setPriority(NotificationCompat.PRIORITY_HIGH);

        if (showNotification) {
            builder.setSmallIcon(R.drawable.notification_icon)
                    .setContentTitle(context.getString(R.string.app_name))
                    .setOngoing(true)
                    .setWhen(System.currentTimeMillis() - timer.getElapsedTime())
                    .setUsesChronometer(true);

            // Make an Intent to launch the Activity from the notification.
            Intent activityIntent = new Intent(context, MainActivity.class);

            // So navigating back from the Activity goes from the app to the Home screen.
            TaskStackBuilder stackBuilder = TaskStackBuilder.create(context)
                    .addParentStack(MainActivity.class)
                    .addNextIntent(activityIntent);
            PendingIntent activityPendingIntent =
                    stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(activityPendingIntent);
        }

        if (playChime) {
            int defaults = Notification.DEFAULT_LIGHTS;

            defaults |= Notification.DEFAULT_SOUND; // TODO: builder.setSound() instead.
            // Uri chimeUri = Uri.parse("android.resource://" + context.getPackageName() + "/" + R.raw.tone);
            // builder.setSound(chimeUri);

            if (vibrateWithChime) {
                defaults |= Notification.DEFAULT_VIBRATE;
            }
            builder.setDefaults(defaults);
        }

        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    /** Cancels all of this app's notifications. */
    public void cancelAll() {
        NotificationManager notificationManager =
                (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        notificationManager.cancelAll();
    }
}