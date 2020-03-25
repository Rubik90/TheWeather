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

    public static final Issue ISSUE = Issue.create(

            "Class2Short",

            "Dichiarazione della classe troppo corta",

            "Questo controllo evidenzia le classi che hanno un nome piu' breve o uguale a 4 caratteri.\n" +
                       "Il nome della classe deve essere abbastanza lungo da identificarla appropriatamente.\n" +
                    "Un nome troppo corto non indica bene il proposito della classe.\n",

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
                    if (string.length() <= 4){
                        context.report(ISSUE, node, context.getNameLocation(node),
                                "Questa classe ha un nome troppo corto, inseriscine uno piu' descrittivo");
                    }
                }
                catch (Exception e) {
                    System.out.println("Eccezione Java catturata.");
                }
            }
        };
    }
}