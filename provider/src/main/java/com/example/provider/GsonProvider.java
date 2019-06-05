package com.example.provider;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.lang.reflect.Type;

public class GsonProvider {
    private Gson gson;

    private GsonProvider() {
        gson = new GsonBuilder().create();
    }

    public Gson getGson() {
        return gson;
    }

    public <T> T parse(String json, Type type) {
        return gson.fromJson(json, type);
    }

    public static GsonProvider getInstance() {
        return GsonProviderHolder.INSTANCE;
    }

    private static class GsonProviderHolder {
        private static final GsonProvider INSTANCE = new GsonProvider();
    }
}
