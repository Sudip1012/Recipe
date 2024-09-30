package com.example.tasteybites.listeners;

import com.example.tasteybites.models.RandomRecipeApiResponse;

public interface RandomRecipeApiResponseListener {
    void didFetch(RandomRecipeApiResponse response, String message);
    void didError(String message);
}
