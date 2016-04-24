package com.test.module;

import com.google.inject.Exposed;
import com.google.inject.Key;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.name.Names;
import com.test.WrappablePrivateModule;
import com.test.Wrapper;

public class ModuleC extends WrappablePrivateModule<String> {

    private static final String SUFFIX = "C";

    @Override
    public Key<String> getExposedKey() {
        return Key.get(String.class, Names.named(SUFFIX));
    }

    @Provides @Exposed @Named(SUFFIX)
    public String expose(Wrapper prefix) {
        return prefix + SUFFIX;
    }
}
