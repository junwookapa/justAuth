package com.test;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.google.inject.name.Names;


public class GuiceCircularDependencyTest {
	//http://stackoverflow.com/questions/14266995/how-to-handle-circular-dependencies-across-multiple-guice-private-modules
	public static void main(String[] args) {
        Injector in = Guice.createInjector(new Owner());
        String result = in.getInstance(Key.get(String.class, Names.named("result")));
        System.out.println("Result is: " + result);
        
        
    }


    

    

   
}
