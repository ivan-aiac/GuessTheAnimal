package animals.node;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class Animal extends Node {
    private final String name;
    private final String indefiniteArticle;

    @JsonCreator
    public Animal(@JsonProperty("indefiniteArticle") String indefiniteArticle,@JsonProperty("name") String name) {
        this.name = name;
        this.indefiniteArticle = indefiniteArticle;
    }

    public String getName() {
        return name;
    }

    public String getIndefiniteArticle() {
        return indefiniteArticle;
    }

    public String toString() {
        return String.format("%s %s",indefiniteArticle, name);
    }

    @Override
    public String getContent() {
        return name;
    }
}
