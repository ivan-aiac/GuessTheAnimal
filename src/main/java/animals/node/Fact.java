package animals.node;

import animals.util.GrammarUtils;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Locale;

public class Fact {
    private final String subject;
    private final String verb;
    private final String predicate;

    @JsonCreator
    public Fact(@JsonProperty("subject") String subject, @JsonProperty("verb") String verb,@JsonProperty("predicate") String predicate) {
        this.subject = subject.substring(0, 1).toUpperCase(Locale.getDefault()) + subject.substring(1);
        this.verb = verb;
        this.predicate = predicate;
    }

    public String getSubject() {
        return subject;
    }

    public String getPredicate() {
        return predicate;
    }

    public String getVerb() {
        return verb;
    }

    @JsonIgnore
    public String getNegativeVerbForm() {
        return GrammarUtils.negateVerb(verb);
    }

    public String toQuestionString() {
        return GrammarUtils.makeThirdPersonQuestion(subject.toLowerCase(Locale.getDefault()), verb, predicate);
    }

    public String toSentenceString() {
        return GrammarUtils.makeSentence(subject, verb, predicate);
    }

}
