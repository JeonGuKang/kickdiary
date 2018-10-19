package com.strongbulb.kickdiary.backup;

import com.google.android.gms.common.api.GoogleApiClient;

import android.app.Activity;
import android.support.annotation.NonNull;

public interface Backup {
    void init(@NonNull final Activity activity);

    void start();

    void stop();

    GoogleApiClient getClient();
}
