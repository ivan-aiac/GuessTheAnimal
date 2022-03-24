package animals.resources;

import animals.util.GrammarUtils;

import java.util.ListResourceBundle;
import java.util.regex.Pattern;

public class App extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"welcome", "Welcome to the animal expert system!"},
                {"greeting.morning", "Good Morning!"},
                {"greeting.afternoon", "Good Afternoon!"},
                {"greeting.evening", "Good Evening!"},

                {"definite.article", "The "},

                {"farewell", new String[]{"Bye!", "See you next time!", "See you later!", "See ya!", "Later!", "Laters!", "Catch you later!",
                        "Catch you on the flip side!", "Have a good day!", "Have a nice day!", "Have a good one!", "Peace!",
                        "Peace out!", "Adios, amigo!", "Adieu", "Farewell!", "Until next time!"}
                },
                {"misperception", new String[]{"I'm not sure I caught you: was it yes or no?", "Funny, I still don't understand, is it yes or no?",
                        "Oh, it's too complicated for me: just tell me yes or no.", "Could you please simply say yes or no?",
                        "Oh, no, don't try to confuse me: say yes or no.", "Oh, it''s too complicated for me: just say me yes or no.",
                        "I'm filling a bit intrigued: just say yes or no.", "I am a bit confused, give me a simple answer: yes or no",
                        "Oh, no, don’t try to confuse me: say yes or no.", "Could you please simply say yes or no?",
                        "Sorry, may I ask you again: was it yes or no"}
                },

                {"animal.wantLearn", "I want to learn about animals."},
                {"animal.askFavorite", "Which animal do you like most?"},
                {"animal.nice", "Awesome!"},
                {"animal.learnedMuch", "I've learned so much about animals!"},
                {"animal.prompt", "Enter animal:"},
                {"animal.error", "The animal should be entered in the following format:\na/an + name of the animal, for example, \"an elephant\"."},

                {"statement.prompt", "Specify a fact that distinguishes %s from %s.%nThe sentence should be of the format: 'It can/has/is ...'.%n"},
                {"statement.error", "The examples of a statement:\n - It can fly\n - It has horns\n - It is a mammal\n"},

                {"game.play", "Let's play a game!"},
                {"game.think", "You think of an animal, and I guess it."},
                {"game.enter", "Press enter when you're ready."},
                {"game.win", "It's great that I got it right!"},
                {"game.giveUp", "I give up. What animal do you have in mind?"},
                {"game.isCorrect", "Is the statement correct for %s?"},
                {"game.learned", "I have learned the following facts about animals:"},
                {"game.distinguish", "I can distinguish these animals by asking the question:"},
                {"game.thanks", new String[]{"That was funny!", "It was nice to play with you!", "It was a pleasure for me to know you better!",
                        "Thank you for playing!", "Thank you! I had too much fun!"}
                },
                {"game.again", new String[]{"Want to try again?", "Would you like to play again?", "Do you want to repeat?",
                        "Want to play again?", "One more game?", "Do you want to play again?"}
                },
                {"game.guess", "Is it %s?"},

                {"menu.property.title", "What do you want to do:"},
                {"menu.property.exit", "Exit"},
                {"menu.property.error", "Please enter the number from 0 up to %d%n"},

                {"menu.entry.play", "Play the guessing game"},
                {"menu.entry.list", "List of all animals"},
                {"menu.entry.search", "Search for an animal"},
                {"menu.entry.statistics", "Calculate statistics"},
                {"menu.entry.print", "Print the Knowledge Tree"},
                {"menu.choice", "Your choice:"},

                {"tree.list.animals", "Here are the animals I know:"},
                {"tree.search.facts", "Facts about the %s:%n"},
                {"tree.search.noFacts", "No facts about the %s.%n"},

                {"tree.stats.title", "The Knowledge Tree stats"},
                {"tree.stats.root", "- root node                    %s%n"},
                {"tree.stats.nodes", "- total number of nodes        %d%n"},
                {"tree.stats.animals", "- total number of animals      %d%n"},
                {"tree.stats.statements", "- total number of statements   %d%n"},
                {"tree.stats.height", "- height of the tree           %d%n"},
                {"tree.stats.minimum", "- minimum depth                %d%n"},
                {"tree.stats.average", "- average depth                %.1f%n"},

                {"positiveAnswerPattern", Pattern.compile("^\\s*(?:y|yes|yeah|yep|sure|right|affirmative|correct|indeed|you bet|exactly|you said it)[!.]?\\s*$", Pattern.CASE_INSENSITIVE)},
                {"negativeAnswerPattern", Pattern.compile("^\\s*(?:n|no(?: way)?|nah|nope|negative|I don't think so|yeah no)[!.]?\\s*$", Pattern.CASE_INSENSITIVE)},

                {"rule.animal.isCorrect", Pattern.compile("(?:(?:the|(an|a))\\s+)?(.+)$", Pattern.CASE_INSENSITIVE)},
                {"rule.animal.animalPart", "$2"},
                {"rule.animal.articlePart", "$1"},

                {"rule.indefinite.1.pattern", Pattern.compile("^[aeiou].+", Pattern.CASE_INSENSITIVE)},
                {"rule.indefinite.1.replace", "an"},
                {"rule.indefinite.2.pattern", GrammarUtils.ALL_PATTERN},
                {"rule.indefinite.2.replace", "a"},

                {"rule.statement.isCorrect", Pattern.compile("(It)\\s+(can|has|is)\\s+(.+)", Pattern.CASE_INSENSITIVE)},
                {"rule.statement.subject", "$1"},
                {"rule.statement.verb", "$2"},
                {"rule.statement.predicate", "$3"},

                {"rule.negate.1.pattern", Pattern.compile("(is)", Pattern.CASE_INSENSITIVE)},
                {"rule.negate.1.replace", "isn't"},
                {"rule.negate.2.pattern", Pattern.compile("(have)", Pattern.CASE_INSENSITIVE)},
                {"rule.negate.2.replace", "doesn't have"},
                {"rule.negate.3.pattern", Pattern.compile("(can)", Pattern.CASE_INSENSITIVE)},
                {"rule.negate.3.replace", "can't"},

                {"rule.question.1.pattern", Pattern.compile("(is)", Pattern.CASE_INSENSITIVE)},
                {"rule.question.1.replace", "Is %s %s?"},
                {"rule.question.2.pattern", Pattern.compile("(have)", Pattern.CASE_INSENSITIVE)},
                {"rule.question.2.replace", "Does %s have %s?"},
                {"rule.question.3.pattern", Pattern.compile("(can)", Pattern.CASE_INSENSITIVE)},
                {"rule.question.3.replace", "Can %s %s?"},

        };
    }
}
