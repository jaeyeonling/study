package com.jaeyeonling.study;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

public class MasulsaAgent {

    public static void premain(final String agentArgs,
                               final Instrumentation instrumentation) {
        new AgentBuilder.Default()
                .type(ElementMatchers.any())
                .transform(MasulsaAgent::getTransformer)
                .installOn(instrumentation);
    }

    private static DynamicType.Builder<?> getTransformer(final DynamicType.Builder<?> builder,
                                                         final TypeDescription typeDescription,
                                                         final ClassLoader classLoader,
                                                         final JavaModule module) {
        return builder.method(ElementMatchers.named("pullOut"))
                .intercept(FixedValue.value("Rabbit!"));
    }
}
