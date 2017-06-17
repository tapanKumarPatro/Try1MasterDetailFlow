package com.tapan.try1masterdetailflow;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by DELL on 6/17/2017.
 */

public class RecipeDetailFragment extends Fragment {

    public static final String RECIPE_INGREDIENTS = "ingredient_id";
    public static final String RECIPE_STEPS = "steps_id";

    public RecipeDetailFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return super.onCreateView(inflater, container, savedInstanceState);
    }


}
