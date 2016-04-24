package com.test;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;

public class ModuleWrapper<T> extends AbstractModule {
    private final WrappablePrivateModule<T> inner;
    private final Multibinder<T> multi;

    public ModuleWrapper(WrappablePrivateModule<T> inner,
                         Multibinder<T> multi) {
        this.inner = inner;
        this.multi = multi;
    }

    @Override
    protected void configure() {
        install(inner);
        multi.addBinding().to(inner.getExposedKey());
    }
}