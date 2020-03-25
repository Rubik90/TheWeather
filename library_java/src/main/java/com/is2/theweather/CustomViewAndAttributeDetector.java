package com.is2.theweather;

import com.android.annotations.NonNull;
import com.android.resources.ResourceFolderType;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.ResourceXmlDetector;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.TextFormat;
import com.android.tools.lint.detector.api.XmlContext;

import org.w3c.dom.Element;

import java.util.Collection;
import java.util.Collections;
import java.util.EnumSet;

/**
 * @author: bedprakash on 6/2/17.
 */

public class CustomViewAndAttributeDetector extends ResourceXmlDetector {

    private static final Class<? extends Detector> DETECTOR_CLASS =
            CustomViewAndAttributeDetector.class;
    private static final EnumSet<Scope> DETECTOR_SCOPE = EnumSet.noneOf(Scope.class);

    private static final Implementation IMPLEMENTATION = new Implementation(
            DETECTOR_CLASS,
            Scope.RESOURCE_FILE_SCOPE
    );

    private static final String CUSTOM_VIEW_TAG = "com.bpr10.customview";
    private static final String MANDATORY_ATTRIBUTE = "android:mandatoryAttribute";

    private static final String ISSUE_ID = "CustomViewIssue";
    private static final String ISSUE_DESCRIPTION = "Custom view used without mandatory attribute";
    private static final String ISSUE_EXPLANATION =
            "Please specify " + MANDATORY_ATTRIBUTE + " when using " + CUSTOM_VIEW_TAG;
    private static final Category ISSUE_CATEGORY = Category.CORRECTNESS;
    private static final int ISSUE_PRIORITY = 8;
    private static final Severity ISSUE_SEVERITY = Severity.WARNING;

    /**
     * The main issue discovered by this detector
     */
    public static final Issue ISSUE = Issue.create(
            ISSUE_ID,
            ISSUE_DESCRIPTION,
            ISSUE_EXPLANATION,
            ISSUE_CATEGORY,
            ISSUE_PRIORITY,
            ISSUE_SEVERITY,
            IMPLEMENTATION);

    @Override
    public boolean appliesTo(@NonNull ResourceFolderType folderType) {
        return folderType == ResourceFolderType.LAYOUT;
    }


    @Override
    public Collection<String> getApplicableElements() {
        return Collections.singletonList(CUSTOM_VIEW_TAG);
    }

    @Override
    public void visitElement(XmlContext context, Element element) {
        String attributeValue = element.getAttribute(MANDATORY_ATTRIBUTE);
        if (attributeValue == null || attributeValue.isEmpty()) {
            //attribute not found. Throw warning
            context.report(ISSUE, element, context.getLocation(element),
                    ISSUE.getExplanation(TextFormat.TEXT));
        }
    }
}