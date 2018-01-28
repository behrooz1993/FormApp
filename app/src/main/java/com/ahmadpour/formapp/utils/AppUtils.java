package com.ahmadpour.formapp.utils;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.ahmadpour.formapp.R;

/**
 * Created by behrooz on 1/29/18.
 */

public final class AppUtils {

    //This class is not publicly instantiable
    private AppUtils() {

    }

//    public static void openPlayStoreForApp(Context context) {
//        final String appPackageName = context.getPackageName();
//        try {
//            context.startActivity(new Intent(Intent.ACTION_VIEW,
//                    Uri.parse(context
//                            .getResources()
//                            .getString(R.string.app_market_link) + appPackageName)));
//        } catch (android.content.ActivityNotFoundException e) {
//            context.startActivity(new Intent(Intent.ACTION_VIEW,
//                    Uri.parse(context
//                            .getResources()
//                            .getString(R.string.app_google_play_store_link) + appPackageName)));
//        }
//    }

}