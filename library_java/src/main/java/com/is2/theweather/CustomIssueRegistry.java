package com.is2.theweather;

import com.android.tools.lint.client.api.IssueRegistry;
import com.android.tools.lint.detector.api.Issue;

import java.util.Arrays;
import java.util.List;

public class CustomIssueRegistry extends IssueRegistry {

    @Override
    public List<Issue> getIssues() {
        return Arrays.asList(TheWeatherDetector.ISSUE,LintDetector.ISSUE,PrefixDetector.ISSUE);
    }

    @Override public int getApi() { return com.android.tools.lint.detector.api.ApiKt.CURRENT_API; }
}