package com.example.myclient2.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

/**
 * Created by iddan on 14/08/17.
 */

public class IntentHandler {

    public static final void goToActivity(Context context, Activity fromActivity, Class toActivity, boolean isFinish) {
        Intent intent = new Intent(fromActivity, toActivity);
        context.startActivity(intent);

        if (isFinish) {
            ((Activity) context).finish();
        }
    }
}
