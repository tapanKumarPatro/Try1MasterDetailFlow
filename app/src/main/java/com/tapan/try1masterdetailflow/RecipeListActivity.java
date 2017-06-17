package com.tapan.try1masterdetailflow;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class RecipeListActivity extends AppCompatActivity {

    public static boolean isTwoPane ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        if (findViewById(R.id.frame_detail) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            isTwoPane = true;
        }

    }
}
