package eu.napcode.recipes.repository;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import static eu.napcode.recipes.repository.Resource.Status.ERROR;
import static eu.napcode.recipes.repository.Resource.Status.LOADING;
import static eu.napcode.recipes.repository.Resource.Status.SUCCESS;

public class Resource<T> {

    @NonNull
    public final Status status;

    @Nullable
    public final String message;

    @Nullable
    public final T data;

    public Resource(@NonNull Status status, @Nullable T data, @Nullable String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public static <T> Resource<T> success(@Nullable T data) {
        return new Resource<>(SUCCESS, data, null);
    }

    public static <T> Resource<T> error(Throwable throwable) {
        return new Resource<>(ERROR, null, throwable.getLocalizedMessage());
    }

    public static <T> Resource<T> loading(@Nullable T data) {
        return new Resource<>(LOADING, data, null);
    }

    public enum Status {
        SUCCESS,
        ERROR,
        LOADING
    }
}