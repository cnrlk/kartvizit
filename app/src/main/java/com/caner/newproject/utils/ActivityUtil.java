package com.caner.newproject.utils;

import android.content.Context;
import android.content.Intent;

public class ActivityUtil {
    public static void startActivity(Context context, Class type) {
        Intent intent = new Intent(context, type);
        context.startActivity(intent);
    }
}
