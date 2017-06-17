package com.tapan.try1masterdetailflow;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

/**
 * Created by DELL on 6/16/2017.
 */

public class RecipeListFragment extends Fragment implements RecipeAdapter.RecipeListOnClickListenerInteface {

    RecyclerView recyclerView;
    RecipeAdapter recipeAdapter;
    private String BASE_URL = "https://d17h27t6h515a5.cloudfront.net/topher/2017/May/59121517_baking/baking.json";


    private List<Recipe> recipeList;
    private List<Steps> stepsList;
    private List<Ingredients> ingredientsList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_item_list, container,
                false);
        Log.v("calledORNOT", "fragment Called");

        getRecipe_Ing_steps();

        Log.v("calledORNOT", "recuclerView Called");
        recyclerView = (RecyclerView) view.findViewById(R.id.rcylView_recipe);

        /*final Handler handler = new Handler();
        Runnable refresh = new Runnable() {
            @Override
            public void run() {


                //Toast.makeText(MainActivity.this,"refresh",Toast.LENGTH_SHORT).show();
                handler.postDelayed(this, 1000);
            }
        };
        handler.postDelayed(refresh, 1000);*/

       /* // Bind adapter to ListView
        lvItems = (ListView) view.findViewById(R.id.lvItems);
        lvItems.setAdapter(adapterItems);
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View item, int position,
                                    long rowId) {
                // Retrieve item based on position
                Item i = adapterItems.getItem(position);
                // Fire selected event for item
                listener.onItemSelected(i);
            }
        });*/


        return view;
    }

    public void getRecipe_Ing_steps() {

        AsyncHttpClient client = new AsyncHttpClient();
        client.get(BASE_URL, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, byte[] responseBody) {
                recipeList = new ArrayList<Recipe>();
                stepsList = new ArrayList<Steps>();
                ingredientsList = new ArrayList<Ingredients>();
                Log.v("calledORNOT", "getRecipeMetod Called");

                try {
                    JSONArray allDataArray = new JSONArray(new String(responseBody));

                    for (int i = 0; i < allDataArray.length(); i++) {

                        JSONObject recipeObj = allDataArray.getJSONObject(i);

                        Recipe recipe = new Recipe();
                        recipe.setRecipeName(recipeObj.getString("name"));
                        recipe.setServings(recipeObj.getString("servings"));

                        //Log.v("recipeListSize", String.valueOf(allDataArray.length()));
                        JSONArray ingredientsArray = recipeObj.getJSONArray("ingredients");
                        JSONArray stepsArray = recipeObj.getJSONArray("steps");
                        Log.v("arrayLength", String.valueOf(stepsArray.length()));

                        for (int j = 0; j < ingredientsArray.length(); j++) {

                            Log.v("recipeListSizeIng", String.valueOf(ingredientsArray.length()));
                            JSONObject ingredientsObj = ingredientsArray.getJSONObject(j);

                            Ingredients ingredients = new Ingredients();
                            ingredients.setIngredients(ingredientsObj.getString("ingredient"));
                            ingredients.setQuantity(ingredientsObj.getString("quantity"));
                            ingredients.setMeasure(ingredientsObj.getString("measure"));
                            //Log.v("nameIng",ingredientsObj.getString("ingredient"));

                            ingredientsList.add(ingredients);
                        }

                        for (int k = 0; k < stepsArray.length(); k++) {

                            JSONObject stepsObj = stepsArray.getJSONObject(k);

                            Steps steps = new Steps();
                            steps.setDescription(stepsObj.getString("description"));
                            steps.setShortDescription(stepsObj.getString("shortDescription"));
                            steps.setThumbNail(stepsObj.getString("thumbnailURL"));
                            steps.setVideoURL(stepsObj.getString("videoURL"));

                            stepsList.add(steps);
                        }

                        recipeList.add(recipe);
                    }

                    initRecipe();


                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(int statusCode, Header[] headers, byte[] responseBody, Throwable error) {

            }
        });

    }

    private void initRecipe() {
        recipeAdapter = new RecipeAdapter(recipeList,ingredientsList,stepsList, getActivity());
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(recipeAdapter);
    }

    @Override
    public void onRecipeItemClicked(Ingredients ingredients, Steps steps) {

       /*
            if (isTwoPane) {
                Bundle arguments = new Bundle();
                arguments.putParcelableArrayList("ing", ingredientsList.get());
                RecipeDetailFragment fragment = new RecipeDetailFragment();
                fragment.setArguments(arguments);
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.movie_detail_container, fragment)
                        .commit();
            } else {
                Context context = getActivity();
                Intent intent = new Intent(context, MovieDetailActivity.class);
                intent.putExtra(RecipeDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                context.startActivity(intent);
            }
        }*/

    }
}
