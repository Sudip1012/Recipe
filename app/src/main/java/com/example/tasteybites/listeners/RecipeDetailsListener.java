package com.example.tasteybites.listeners;

import com.example.tasteybites.models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didFetch(RecipeDetailsResponse response, String message);
    void didError(String message);

}
