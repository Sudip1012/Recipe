package com.example.tasteybites;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasteybites.adapters.IngrediensAdapter;
import com.example.tasteybites.adapters.InstructionsAdapter;
import com.example.tasteybites.listeners.InstructionsListener;
import com.example.tasteybites.listeners.RecipeDetailsListener;
import com.example.tasteybites.models.InstructionsResponse;
import com.example.tasteybites.models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipeDetailsActivity extends AppCompatActivity {
    int id;

    TextView textView_meal_name,tecViw_meal_summary,textView_meal_source;
    ImageView imageView_meal_image;
    RecyclerView recycler_meal_ingredients,recycler_meal_instructions;
    RequestManager manager;
    ProgressDialog dialog;
    IngrediensAdapter ingrediensAdapter;
    InstructionsAdapter instructionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_detail);

        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener,id);
        manager.getInstruction(instructionsListener,id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details...");
        dialog.show();
    }

    private void findViews() {
        textView_meal_name = findViewById(R.id.textView_meal_name);
        textView_meal_source = findViewById(R.id.textView_meal_source);
        tecViw_meal_summary=findViewById(R.id.textView_meal_source);
        imageView_meal_image = findViewById(R.id.imageView_meal_image);
        recycler_meal_ingredients = findViewById(R.id.recycler_meal_ingredients);
        recycler_meal_instructions = findViewById(R.id.recycler_meal_instructions);
    }

    private  final RecipeDetailsListener recipeDetailsListener= new RecipeDetailsListener() {
        @Override
        public void didFetch(RecipeDetailsResponse response, String message) {
            dialog.dismiss();
            textView_meal_name.setText(response.title);
            textView_meal_source.setText(response.sourceName);
            tecViw_meal_summary.setText(response.summary);
            Picasso.get().load(response.image).into(imageView_meal_image);

            recycler_meal_ingredients.setHasFixedSize(true);
            recycler_meal_ingredients.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this,LinearLayoutManager.HORIZONTAL,false));
            ingrediensAdapter = new IngrediensAdapter(RecipeDetailsActivity.this,response.extendedIngredients);
            recycler_meal_ingredients.setAdapter(ingrediensAdapter);
        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipeDetailsActivity.this,message,Toast.LENGTH_SHORT).show();

        }
    };
    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(List<InstructionsResponse> response, String message) {
              recycler_meal_instructions.setHasFixedSize(true);
              recycler_meal_instructions.setLayoutManager(new LinearLayoutManager(RecipeDetailsActivity.this,LinearLayoutManager.VERTICAL,false));
              instructionsAdapter = new InstructionsAdapter(RecipeDetailsActivity.this,response);
              recycler_meal_instructions.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };
}