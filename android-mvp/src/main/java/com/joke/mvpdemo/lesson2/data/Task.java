package com.joke.mvpdemo.lesson2.data;


import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

//import com.google.common.base.Strings;

import java.util.UUID;

public final class Task {
    @NonNull
    private final String mId;
    private final String mTitle;
    private final String mDescription;
    private final boolean mCompleted;

    public Task(@Nullable String title, @Nullable String description) {
        this(title, description, UUID.randomUUID().toString(), false);
    }

    public Task(@Nullable String title, @Nullable String description, @NonNull String id) {
        this(title, description, id, false);
    }

    public Task(@Nullable String title, @Nullable String description, boolean completed) {
        this(title, description, UUID.randomUUID().toString(), completed);
    }

    public Task(@Nullable String title, @Nullable String description,
                @NonNull String id, boolean completed) {
        mId = id;
        mTitle = title;
        mDescription = description;
        mCompleted = completed;
    }

    @Nullable
    public String getTitleForList() {
//        if (!Strings.isNullOrEmpty(mTitle)) {
//            return mTitle;
//        } else {
//            return mDescription;
//        }
        return "";
    }

    @Nullable
    public String getDescription() {
        return mDescription;
    }

    public boolean isCompleted() {
        return mCompleted;
    }

    public boolean isActive() {
        return !mCompleted;
    }

    public boolean isEmpty() {
//        return Strings.isNullOrEmpty(mTitle) &&
//                Strings.isNullOrEmpty(mDescription);
        return false;
    }
}
