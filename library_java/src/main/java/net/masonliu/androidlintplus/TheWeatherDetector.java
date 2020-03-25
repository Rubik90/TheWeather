package net.masonliu.androidlintplus;


import com.android.annotations.NonNull;
import com.android.tools.lint.detector.api.Category;
import com.android.tools.lint.detector.api.Detector;
import com.android.tools.lint.detector.api.Implementation;
import com.android.tools.lint.detector.api.Issue;
import com.android.tools.lint.detector.api.Scope;
import com.android.tools.lint.detector.api.Severity;
import com.android.tools.lint.detector.api.XmlContext;
import java.util.Arrays;
import java.util.Collection;
import org.w3c.dom.Attr;
import static com.android.SdkConstants.ATTR_LABEL;
import static com.android.SdkConstants.TAG_APPLICATION;

public class TheWeatherDetector extends Detector
        implements Detector.XmlScanner {

        public static final Issue ISSUE = Issue.create(
                "TheWeatherCheck",                                        //ID
                "Titolo inaspettato",                      //brief description
                "Il titolo dell'applicazione dovrebbe essere 'TheWeather'",  //explanation
                Category.CORRECTNESS,                                //category
                5,                                                   //priority
                Severity.INFORMATIONAL,                              //severity
                new Implementation(                                  //implementation
                        TheWeatherDetector.class,                        //detector
                        Scope.MANIFEST_SCOPE                             //scope
                )
        );

        private static final String TITLE = "TheWeather";

        @Override public Collection<String> getApplicableAttributes() {
                return Arrays.asList(ATTR_LABEL);
        }

       @Override public void visitAttribute(@NonNull XmlContext context,
                                             @NonNull Attr attribute) {

                String tagName = attribute.getOwnerElement().getTagName();
                if (!TAG_APPLICATION.equals(tagName)) {
                        return;
                }

                String value = attribute.getValue();

                if (!TITLE.equals(value)) {
                        context.report(ISSUE, attribute, context.getLocation(attribute),
                                String.format(
                                        "TItolo inaspettato \"%1$s\". Dovrebbe essere \"TheWeather.\".", value));
                }
        }
}