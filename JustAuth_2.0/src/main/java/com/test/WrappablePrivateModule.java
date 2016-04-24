package com.test;

import com.google.inject.Key;
import com.google.inject.PrivateModule;

public abstract class WrappablePrivateModule<T> extends PrivateModule {

    @Override
    protected void configure() {

    }

    public abstract Key<T> getExposedKey();
}
