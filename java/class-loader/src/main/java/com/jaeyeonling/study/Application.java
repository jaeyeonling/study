package com.jaeyeonling.study;

public class Application {

    public static void main(final String... args) {
        System.out.println(Application.class.getClassLoader());                             // Application Classloader
        System.out.println(Application.class.getClassLoader().getParent());                 // Platform Classloader
        System.out.println(Application.class.getClassLoader().getParent().getParent());     // Bootstrap Classloader
    }
}
