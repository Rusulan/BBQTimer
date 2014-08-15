# BBQTimer Android app

[See it in the Google Play store](https://play.google.com/store/apps/details?id=com.onefishtwo.bbqtimer)

A stopwatch with a twist (or two):

1. A *lock screen widget* lets you access the stopwatch quickly, without unlocking your phone or
   tablet. (This requires Android 4.2 Jelly Bean or later.)
2. *Periodic alarms* remind you to check the food, however long it takes to cook. You get a
   count-down timer and a count-up stopwatch at the same time.

Of course it can time more things than BBQ cooking.

Requires few permissions. No network access. No ads. No private data gathering.
Simple and focused. Free.

[Note: The app doesn't install in external storage (SD card) because when you use USB to share files
with a computer, Android forces external storage apps to exit and deletes their lock/home screen
widgets.]

## System Requirements

BBQ Timer is for phones and tablets running Android 3.1 (Honeycomb MR1, Android API level 12) and
later. If it has problems on your Android device, send me the details (device model, Android
version, screen size, problem symptom).

## Status
Released.

## License

[MIT License](https://github.com/1fish2/BBQTimer/blob/master/LICENSE.md).

## Usage Tips
* Put the BBQ Timer widget on the Android lock screen for quick access. (This feature requires
  Android 4.2 Jelly Bean or later.)
* The BBQ Timer widget also works on the home screen (even in earlier versions of Android).
* To resize the home screen widget, long-press on it then drag its resize handles.
  The initial size fits minutes and seconds (e.g. "18:12"). Stretched wider, it can fit hours,
  minutes, and seconds (e.g. "1:15:09") depending on your screen size and font size setting.
* To remove the widget, long-press it, then drag it onto "X Remove".
* Tap the stopwatch time display to cycle between *reset* -> *running* -> *paused* -> *reset.*
* The app's main screen displays the stopwatch with more precision (e.g. "18:12.6") than the widget.
* On the app's main screen, tap the checkbox to enable/disable periodic reminder alarms.
* On the app's main screen, you can adjust the interval between periodic reminder alarms by
  dragging the number widget. Or tap the number then type in a new number.

## How to Use Android Lock Screen Widgets
* This feature requires Android 4.2 Jelly Bean through Android 4.4 KitKat.
* On Android 4.4 KitKat, you must turn on Settings > Security > Enable Widgets to enable this
  feature.
* For step-by-step instructions, see [Getting started with lock screen widgets on Android Jelly
  Bean](http://howto.cnet.com/8301-11310_39-57549747-285/getting-started-with-lock-screen-widgets-on-android-jelly-bean/
  "CNET How To")
  or [How to Add Lockscreen Widgets to Android 4.4
  KitKat](http://www.gottabemobile.com/2013/11/11/add-lockscreen-widgets-android-4-4-kitkat-nexus-5/
  "GottaBe Mobile").
* In short, to add a lock screen widget:
    * Requires Android 4.2 Jelly Bean through Android 4.4 KitKat.
    * On Android 4.4 KitKat, turn on Settings > Security > Enable Widgets.
    * Wake the screen.
    * Swipe from the left edge of the screen to the right until you see the "+" screen.
    * Tap "+".
    * Unlock the phone.
    * Tap the BBQ Timer widget.
    * (Optional) To move the BBQ Timer widget to the *main* (rightmost) lock screen position, swipe
      down over it to select it, then long-press it and drag it to the right, past the other
      widgets.
* When you wake the Android screen, it shows the main (rightmost) lock screen widget.
  To access your other lock screen widgets, swipe rightwards.
  To access the camera, swipe leftwards.
* To rearrange your lock screen widgets, long-press one and drag it left or right.
* You can rearrange lock screen widgets whenever you want. Example: When you start cooking, Move
  BBQ Timer to the rightmost position; afterwards, move the Digital Clock there.

## Known Issues
* The Android L preview emulator's Sans font used for the timer has different widths for
  "3", "5", and "6" vs. the other digits. This makes the ticking chronometer a little jerky.
  [Android Issue 330.]
* Android L Nexus 5 makes the home-screen widget tall enough to see a clipped date in the widget.
  This needs a workaround.
* Due to [Android OS bug 2880](https://code.google.com/p/android/issues/detail?id=2880), if the
  clock gets set backwards, the widget's date display might not update until the clock catches up to
  what was going to be the next day.
* In the emulator (Nexus 5 Kit Kat), the Activity's timer sometimes stops.
* On Android OS versions older than API level 15 (ICE_CREAM_SANDWICH_MR1), the app omits the alarm
  count from the notification area. This avoids an Android crash.
* On Android emulator API level 12, the notification area shows black text on a black background.
  Just an emulator bug?
* On Android emulator API level 15 (ICE_CREAM_SANDWICH_MR1), the notification area displays the
  stopwatch's start time instead of its elapsed time.
* Not yet tested on Android API level 13 (HONEYCOMB_MR2) or level 14 (ICE_CREAM_SANDWICH) (where the
  emulator takes most of an hour to launch, then croaks, every time). If you're on these versions of
  Android, let me know how it goes.
* There may be a fraction of a second clock skew between the stopwatch time displayed in the widget
  vs. the notification area. It might be possible to work around this at a cost in battery power.
  When you want to see precise times, go to the application's main screen.

## TODO
* Add a menu of recipes to set the reminder alarm time and suggest the cooking temp.
* When Android L is released, test and move to buildToolsVersion '20.0.0', targetSdkVersion 20.
* Android L: Support extended, lock-screen notifications.
* Android L: Use the new MediaStyle template to convert notification actions to compact buttons in
  an app media playback notification (since Android L doesn't support lock-screen widgets)? See
  http://developer.android.com/preview/api-overview.html#MediaPlaybackControl
* Android L: Call Notification.Builder.setVisibility(VISIBILITY_PUBLIC) [the default?] so the
  notification will appear on the lock-screen, and .setCategory(Notification.CATEGORY_ALARM).
  Note: These methods are not yet in the API.
* Support Android Wear. Requires Android 4.3, API level 18+.
* Support Material Design?
* Show a tip about adding the widget to the lock screen when first running the app.
* Show tips via a menu command: How to install widgets. What tapping the widget text does.
* Add a Settings dialog to pick {sound, vibrate, both, disable} for reminders.
* Add a Settings dialog to pick the reminder alarm sound.
* Localize into several languages.
* When stopped at 00:00, add the time of day to the widget? ... after a few minutes?
  (To track the time: Upon ACTION_SCREEN_ON when there are lock screen widgets, register a Service to
  listen for ACTION_TIME_TICK. Unregister upon ACTION_SCREEN_OFF or when the last widget is removed.)
* Accessibility.
* Display the time larger in the activity when the screen is wide and tall enough.
* Use a font-resizing text view fit long durations in the Activity. See
  [Stack Overflow auto-scale-textview-text-to-fit-within-bounds](http://stackoverflow.com/questions/5033012/auto-scale-textview-text-to-fit-within-bounds/),
  [Stack Overflow how-to-adjust-text-font-size-to-fit-textview](http://stackoverflow.com/questions/2617266/how-to-adjust-text-font-size-to-fit-textview/),
  [Stack Overflow how-to-catch-widget-size-changes-on-devices-where-onappwidgetoptionschanged-not](http://stackoverflow.com/questions/17396045/how-to-catch-widget-size-changes-on-devices-where-onappwidgetoptionschanged-not)
* Use a font-resizing heuristic to fit hours in a minimum width home screen widget without eliding
  the seconds.

* Unit tests.

## Building from Source Code
Use [Android Studio](http://developer.android.com/sdk/installing/studio.html) (an awesome tool!).

## Dedication
Dedicated to open source software developers.

Open source software used for this project includes: Android, Android Studio, Audacity, Chrome,
Gimp, git, git gui, Inkscape, Java, MinGW, Proguard, and much more that we use without thinking
about, like zlib.

Don't get me wrong -- commercial software is also great!

## Media Asset Sources
Notification sound composed from sampled cowbell sounds which are used by permission from Phil Burk,
Copyright (c) 2014 Mobileer Inc.

Launcher icon derived from a public domain image on [openclipart.org](http://openclipart.org).

## Keywords
Cooking, interval timer, lock screen widget, reminder alarm, stopwatch.

## Implementation notes
* The lock screen widget pretty much needs to use a Chronometer view for the ticking time display.
Using a Text view would show clock skew between multiple widgets and would use more CPU time and
battery power.
* When paused, a Chronometer view can't show a well-determined time value because its API doesn't
accommodate that case. You could fake it by sending it a start time that's about the right amount of
time ago but you can't control how long until it reads the system clock. Consequently, multiple
widgets would display different paused time values. The workaround is to switch the display from a
Chronometer view to a Text view when paused.
* Formatting the elapsed time like 0:12 would look nicer than 00:12 but Android's Chronometer view
only does the latter. The app's main screen and its paused text widget match that for consistency.
* The widget's ViewFlipper must explicitly set android:measureAllChildren="false", otherwise
flipping its subviews will resize the adjacent ImageButton on Galaxy Nexus Jelly Bean. (Why?)
