package com.jaeyeonling;

import com.google.auto.service.AutoService;
import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.JavaFile;
import com.squareup.javapoet.MethodSpec;
import com.squareup.javapoet.TypeSpec;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.ElementKind;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import java.io.IOException;
import java.util.Set;

@AutoService(Processor.class)
public class MagicMojaProcessor extends AbstractProcessor {

    @Override
    public Set<String> getSupportedAnnotationTypes() {
        return Set.of(Magic.class.getName());
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return SourceVersion.latestSupported();
    }

    @Override
    public boolean process(final Set<? extends TypeElement> annotations,
                           final RoundEnvironment roundEnv) {
        final var messager = processingEnv.getMessager();
        final var filer = processingEnv.getFiler();

        final var elements = roundEnv.getElementsAnnotatedWith(Magic.class);
        for (final var element : elements) {
            checkConstrains(element);

            messager.printMessage(Diagnostic.Kind.NOTE, "Processing " + element.getSimpleName());

            final var pullOut = MethodSpec.methodBuilder("pullOut")
                    .addModifiers(Modifier.PUBLIC)
                    .returns(String.class)
                    .addStatement("return $S", "Rabbit!")
                    .build();

            final ClassName className = getClassName(element);

            final var magicMoja = TypeSpec.classBuilder("MagicMoja")
                    .addModifiers(Modifier.PUBLIC)
                    .addSuperinterface(className)
                    .addMethod(pullOut)
                    .build();

            final var packageName = className.packageName();
            final var javaFile = JavaFile.builder(packageName, magicMoja)
                    .build();

            write(javaFile);
        }

        return true;
    }

    private void checkConstrains(final Element element) {
        final var messager = processingEnv.getMessager();

        if (!(element.getKind() == ElementKind.INTERFACE)) {
            messager.printMessage(Diagnostic.Kind.ERROR,
                    "Magic annotation can not be used on" + element.getSimpleName());
        }
    }

    private ClassName getClassName(final Element element) {
        return ClassName.get((TypeElement) element);
    }

    private void write(final JavaFile javaFile) {
        final var messager = processingEnv.getMessager();
        final var filer = processingEnv.getFiler();

        try {
            javaFile.writeTo(filer);
        } catch (final IOException e) {
            messager.printMessage(Diagnostic.Kind.ERROR, "Fatal ERROR: " + e);
        }
    }
}
