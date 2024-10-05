package com.example.tasteybites.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.tasteybites.R;
import com.example.tasteybites.models.Ingredient;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionsIngredientsAdapter extends RecyclerView.Adapter<InstructionIngeredientsViewHolder>{
    Context context;
    List<Ingredient> list;

    public InstructionsIngredientsAdapter(Context context, List<Ingredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionIngeredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionIngeredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_step_items,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionIngeredientsViewHolder holder, int position) {
        holder.textView_instruction_step_item.setText(list.get(position).name);
        holder.textView_instruction_step_item.setSelected(true);
        Picasso.get().load("https://img.spoonacular.com/ingredients_100x100/"+ list.get(position).image).into(holder.imageView_instructions_step_item);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionIngeredientsViewHolder extends RecyclerView.ViewHolder{
    ImageView imageView_instructions_step_item;
    TextView textView_instruction_step_item;
    public InstructionIngeredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        imageView_instructions_step_item = itemView.findViewById(R.id.imageView_instructions_step_item);
        textView_instruction_step_item = itemView.findViewById(R.id.textView_instruction_step_item);
    }
}
