package com.si.pushnotifications;

import static com.si.pushnotifications.CommonUtilities.SENDER_ID;
import static com.si.pushnotifications.CommonUtilities.displayMessage;
import com.si.pushnotifications.*;
import com.si.pushnotifications.AlertDialogManager;
import android.app.Notification;
import android.app.Notification.Builder;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gcm.GCMBaseIntentService;

public class GCMIntentService extends GCMBaseIntentService {
	
	
	
	private static final String TAG = "GCMIntentService";

    public GCMIntentService() {
        super(SENDER_ID);
    }
    /**
     * Method called on Receiving a new message
     * */
    @Override
    protected void onMessage(Context context, Intent intent) 
    {
        Log.i(TAG, "Received message");
        String message = intent.getExtras().getString("price");
        
        displayMessage(context, message);
        // notifies user
        generateNotification(context, message);
    }

    /**
     * Issues a notification to inform the user that server has sent a message.
     */
    private static void generateNotification(Context context, String message) 
    {
    	AlertDialogManager dialogManager = new AlertDialogManager();
        //int icon = R.drawable.ic_launcher;
        //long when = System.currentTimeMillis();
        String subject = "subject";
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE);
        	Notification notification = new NotificationCompat.Builder(context)
            .setContentTitle("We want your attention")
            .setContentText(subject)
            .setSmallIcon(R.drawable.ic_launcher)
            .build();
        	
        	String title = context.getString(R.string.app_name);
            
            Intent notificationIntent = new Intent(context, MainActivity.class);
            // set intent so it does not start a new activity
            notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP |
                    Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent intent =
                    PendingIntent.getActivity(context, 0, notificationIntent, 0);
            notification.setLatestEventInfo(context, title, message, intent);
            notification.flags |= Notification.FLAG_AUTO_CANCEL;
            
            // Play default notification sound
            notification.defaults |= Notification.DEFAULT_SOUND;
            
            //notification.sound = Uri.parse("android.resource://" + context.getPackageName() + "your_sound_file_name.mp3");
            
            // Vibrate if vibrate is enabled
            notification.defaults |= Notification.DEFAULT_VIBRATE;
            notificationManager.notify(1, notification);
            dialogManager.showAlertDialog(context, title, message, true);
    }
	@Override
	protected void onRegistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onUnregistered(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected void onError(Context arg0, String arg1) {
		// TODO Auto-generated method stub
		
	}

}

