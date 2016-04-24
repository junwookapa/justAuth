package com.test;

import java.util.Set;

import com.google.inject.Exposed;
import com.google.inject.PrivateModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;

public class Integration extends PrivateModule {
    @Override
    protected void configure() {
        bind(Wrapper.class).toInstance(new Wrapper("Module"));
        expose(Wrapper.class);
    }

    @Provides @Exposed @Named("result")
    public String go(Set<String> in) {
        return in.toString();
    }
}
