package com.test;

import com.google.inject.PrivateModule;
import com.google.inject.multibindings.Multibinder;
import com.google.inject.name.Names;
import com.test.module.ModuleA;
import com.test.module.ModuleB;
import com.test.module.ModuleC;

public class Owner extends PrivateModule {
    @Override
    protected void configure() {
        Multibinder<String> multi = Multibinder.newSetBinder(binder(), String.class);
        install(new Integration());
        install(new ModuleWrapper<>(new ModuleA(), multi));
        install(new ModuleWrapper<>(new ModuleB(), multi));
        install(new ModuleWrapper<>(new ModuleC(), multi));
        expose(String.class).annotatedWith(Names.named("result"));
    }
}