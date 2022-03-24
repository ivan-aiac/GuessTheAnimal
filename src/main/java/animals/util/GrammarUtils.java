package animals.util;

import animals.node.Animal;
import animals.node.Fact;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static animals.ResourceManager.patternResource;
import static animals.ResourceManager.stringResource;
import static animals.util.AnswerType.*;

public class GrammarUtils {

    public static final Pattern ALL_PATTERN = Pattern.compile(".*");
    private static final Pattern POSITIVE_ANSWER_PATTERN = patternResource("positiveAnswerPattern");
    private static final Pattern NEGATIVE_ANS_PATTERN = patternResource("negativeAnswerPattern");
    private static final List<LanguageRule> INDEFINITE_RULES = loadRules("rule.indefinite");
    private static final List<LanguageRule> NEGATE_RULES = loadRules("rule.negate");
    private static final List<LanguageRule> QUESTION_RULES = loadRules("rule.question");

    public static Animal parseAnimal(String sentence) {
        Pattern correct = patternResource("rule.animal.isCorrect");
        Matcher matcher = correct.matcher(sentence);
        if (matcher.matches()) {
            String animal = matcher.replaceFirst(stringResource("rule.animal.animalPart"));
            String article = matcher.replaceFirst(stringResource("rule.animal.articlePart"));
            if (article == null || "".equals(article)) {
                article = applyRules(INDEFINITE_RULES, animal);
            }
            return new Animal(article, animal);
        }
        return null;
    }

    public static Fact parseFact(String sentence) {
        Pattern correct = patternResource("rule.statement.isCorrect");
        Matcher matcher = correct.matcher(sentence);
        if (matcher.matches()) {
            String subject = matcher.replaceFirst(stringResource("rule.statement.subject"));
            String verb = matcher.replaceFirst(stringResource("rule.statement.verb"));
            String predicate = matcher.replaceFirst(stringResource("rule.statement.predicate"));
            return new Fact(subject, verb, predicate);
        }
        return null;
    }

    private static List<LanguageRule> loadRules(String name) {
        List<LanguageRule> rules = new ArrayList<>();
        int i = 1;
        while (true) {
            try {
                Pattern resourcePattern = patternResource(String.format("%s.%d.%s",name, i, "pattern"));
                String replacement = stringResource(String.format("%s.%d.%s",name, i, "replace"));
                rules.add(new LanguageRule(resourcePattern, replacement));
                i++;
            } catch (Exception e) {
                break;
            }
        }
        return rules;
    }

    private static String applyRules(List<LanguageRule> rules, String sentence) {
        for (LanguageRule rule : rules) {
            Matcher matcher = rule.getMatcher(sentence);
            if (matcher.matches()) {
                return matcher.replaceFirst(rule.getReplacement());
            }
        }
        return "";
    }

    public static String negateVerb(String verb) {
        return applyRules(NEGATE_RULES, verb);
    }


    public static String makeThirdPersonQuestion(String thirdPersonSubject, String verb,  String predicate) {
        return String.format(verbQuestionForm(verb), thirdPersonSubject, predicate);
    }

    private static String verbQuestionForm(String verb) {
        return applyRules(QUESTION_RULES, verb);
    }

    public static String makeSentence(String subject, String verb, String predicate) {
        return String.format("%s %s %s",subject, verb, predicate);
    }

    public static String negateVerbOfSentence(String sentence) {
        Fact fact = parseFact(sentence);
        if (fact != null) {
            return makeSentence(fact.getSubject(), negateVerb(fact.getVerb()), fact.getPredicate());
        }
        return sentence;
    }

    public static AnswerType parseUserYesOrNoAnswer(String userInput) {
        if (POSITIVE_ANSWER_PATTERN.matcher(userInput).matches()) {
            return POSITIVE;
        } else if (NEGATIVE_ANS_PATTERN.matcher(userInput).matches()) {
            return NEGATIVE;
        } else {
            return UNDEFINED;
        }
    }

}
