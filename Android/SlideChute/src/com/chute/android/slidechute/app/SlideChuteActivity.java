package com.chute.android.slidechute.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.chute.android.slidechute.R;
import com.chute.android.slidechute.util.intent.SlideChuteIntentWrapper;

public class SlideChuteActivity extends Activity {

    public static final String TAG = SlideChuteActivity.class.getSimpleName();

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViewById(R.id.btnChoose).setOnClickListener(new OnChooseClickListener());
    }

    private class OnChooseClickListener implements OnClickListener {

        @Override
        public void onClick(View v) {
            SlideChuteIntentWrapper.startSlideChute(SlideChuteActivity.this);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }
}
