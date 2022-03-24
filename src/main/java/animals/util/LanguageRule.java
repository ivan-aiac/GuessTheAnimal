package animals.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LanguageRule {
    private final Pattern rule;
    private final String replacement;

    public LanguageRule(Pattern rule, String replacement) {
        this.rule = rule;
        this.replacement = replacement;
    }

    public Matcher getMatcher(String sentence){
        return rule.matcher(sentence);
    }

    public String getReplacement() {
        return replacement;
    }
}
