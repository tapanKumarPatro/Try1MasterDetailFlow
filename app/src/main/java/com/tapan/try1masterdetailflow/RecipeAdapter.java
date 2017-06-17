package com.tapan.try1masterdetailflow;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import static com.tapan.try1masterdetailflow.RecipeListActivity.isTwoPane;

/**
 * Created by DELL on 6/16/2017.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.MyViewHolder> {

    List<Recipe> recipeList;
    List<Ingredients> ingredientsList;
    List<Steps> stepsList;
    Context context;

    private RecipeListOnClickListenerInteface recipeListOnClickListenerInteface;

    public RecipeAdapter(List<Recipe> recipeList, List<Ingredients> ingredientsList, List<Steps> stepsList, Context context) {
        this.recipeList = recipeList;
        this.ingredientsList = ingredientsList;
        this.stepsList = stepsList;
        this.context = context;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageRecipe;
        TextView mTextNameRecipe;
        TextView mTextServings;
        LinearLayout ll_Layout;

        public MyViewHolder(View itemView) {
            super(itemView);

            mImageRecipe = (ImageView) itemView.findViewById(R.id.img_recipe);
            mTextNameRecipe = (TextView) itemView.findViewById(R.id.tv_recipeName);
            mTextServings = (TextView) itemView.findViewById(R.id.tv_servings);
            ll_Layout = (LinearLayout) itemView.findViewById(R.id.ll_container_recipe);

        }
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.single_item_recipe, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        Recipe recipe = recipeList.get(position);

        holder.ll_Layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isTwoPane) {
                    Bundle arguments = new Bundle();
                    arguments.putParcelableArrayList(RecipeDetailFragment.RECIPE_INGREDIENTS, ingredientsList.get(position));
                    arguments.putParcelableArrayList(RecipeDetailFragment.RECIPE_STEPS, stepsList.get(position));
                    RecipeDetailFragment fragment = new RecipeDetailFragment();
                    fragment.setArguments(arguments);
                    /*((RecipeListActivity) context).getSupportFragmentManager().beginTransaction()
                            .replace(R.id.frame_detail, fragment)
                            .commit();*/



                } else {
                    /*Context context = v.getContext();
                    Intent intent = new Intent(context, MovieDetailActivity.class);
                    intent.putExtra(RecipeDetailFragment.ARG_ITEM_ID, holder.mItem.id);

                    context.startActivity(intent);*/
                }
            }
        });


        holder.mTextNameRecipe.setText(recipe.getRecipeName());
        holder.mTextServings.setText(recipe.getServings());

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    //Interface to handle onClickListener on the recyclerview
    public interface RecipeListOnClickListenerInteface{
        void onRecipeItemClicked(Ingredients ingredients, Steps steps);
    }

}
