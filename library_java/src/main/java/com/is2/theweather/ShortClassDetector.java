package com.is2.theweather;

import com.android.tools.lint.client.api.UElementHandler;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Detector.UastScanner;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.JavaContext;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;

import org.jetbrains.uast.UClass;
import org.jetbrains.uast.UElement;

import java.lang.String;
import java.util.Collections;
import java.util.List;

public class ShortClassDetector extends Detector implements UastScanner {
    //Issue décrivant le problème et pointant vers l'implementation du détecteur

    public static final Issue ISSUE = Issue.create(
            // ID: utilisé dans les avertissements "warning" @SuppressLint etc
            "Class2Short",

            //Titre -- montré dans le dialogue de préférences de l'IDE, comme en-tête de catégorie
            // dans la fenêtre de résultats de l'analyse, etc
            "Class Declaration Too Short",

            //Description complète de l'issue
            "This check highlights classes declaration which name is shorter" +
                    " or equal than three character\n" +
                    "Class name must be named in a way to identify them properly.\n" +
                    "A name too short can't communicate the class purpose properly.\n",

            Category.CORRECTNESS,
            6,
            Severity.WARNING,
            new Implementation(
                    ShortClassDetector.class,
                    Scope.JAVA_FILE_SCOPE
            )
    );

    @Override
    public List<Class<? extends UElement>> getApplicableUastTypes() {
        return Collections.singletonList(UClass.class);
    }

    @Override
    public UElementHandler createUastHandler(JavaContext context) {
        return new UElementHandler() {
            @Override
            public void visitClass(UClass node) {
                String string = node.getName();
                try {
                    if (string.length() <= 3){
                        context.report(ISSUE, node, context.getNameLocation(node),
                                "This class is too short, please give it a proper description**");
                    }
                }
                catch (Exception e) {
                    System.out.println("Exception occurred about string length");
                }
            }
        };
    }
}