package animals.node;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Statement extends Node {
    private final Fact fact;

    @JsonCreator
    public Statement(@JsonProperty("fact") Fact fact) {
        this.fact = fact;
    }

    public Fact getFact() {
        return fact;
    }

    @JsonIgnore
    public String getFactQuestion() {
        return fact.toQuestionString();
    }

    @Override
    public String getContent() {
        return fact.toSentenceString();
    }
}
