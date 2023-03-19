package com.kenzie.capstone.service.model.RecipeServiceLambdaModel;

import java.util.Objects;

public class Ingredients {
    String id;
    String data;

    public Ingredients(String id, String data) {
        this.id = id;
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredients that = (Ingredients) o;
        return id.equals(that.id) && data.equals(that.data);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, data);
    }
}
