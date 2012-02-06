package com.chute.android.slidechute.util.intent;

import java.util.Iterator;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class IntentWrapper {

    public static final String TAG = IntentWrapper.class.getSimpleName();

    private final Intent intent;

    public IntentWrapper(Intent intent) {
        super();
        this.intent = intent;
    }

    public IntentWrapper(Context context, Class<?> cls) {
        super();
        this.intent = new Intent(context, cls);
    }

    public final Intent getIntent() {
        return intent;
    }

    /**
     * You always
     * 
     * @return true if there are extras in the intent, the bundle is <b>not</b> null or empty
     */
    public boolean hasExtras() {
        return intent.getExtras() != null && !intent.getExtras().isEmpty();
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("BaseIntentWrapper [");
        if (hasExtras()) {
            Iterator<String> it = intent.getExtras().keySet().iterator();
            while (it.hasNext()) {
                builder.append("Key: ");
                String key = it.next();
                builder.append(key);
                builder.append(" Value: ");
                builder.append(intent.getExtras().get(key).toString());
                builder.append("\n");
            }
        }
        builder.append(" ]");
        return builder.toString();
    }

    public void startActivity(Activity context) {
        context.startActivity(intent);
    }
}
