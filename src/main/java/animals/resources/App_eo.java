package animals.resources;

import animals.util.GrammarUtils;

import java.util.ListResourceBundle;
import java.util.regex.Pattern;

public class App_eo extends ListResourceBundle {
    @Override
    protected Object[][] getContents() {
        return new Object[][]{
                {"welcome", "Bonvenon al la sperta sistemo de la besto!"},
                {"greeting.morning", "Bonan matenon!"},
                {"greeting.afternoon", "Bonan tagon!"},
                {"greeting.evening", "Bonan vesperon!"},

                {"definite.article", "La "},

                {"farewell", new String[]{"Ĝis!", "Ĝis revido!", "Estis agrable vidi vin!"}},
                {"misperception", new String[]{"Bonvolu enigi jes aŭ ne.", "Amuze, mi ankoraŭ ne komprenas, ĉu jes aŭ ne?",
                        "Pardonu, devas esti jes aŭ ne.", "Ni provu denove: ĉu jes aŭ ne?",
                        "Pardonu, ĉu mi rajtas demandi vin denove: ĉu tio estis jes aŭ ne?"}
                },

                {"animal.wantLearn", "Mi volas lerni pri bestoj."},
                {"animal.askFavorite", "Kiun beston vi plej ŝatas?"},
                {"animal.nice", "Bonege!"},
                {"animal.learnedMuch", "Mi lernis tiom multe pri bestoj!"},
                {"animal.prompt", "Enigu la nomon de besto:"},
                {"animal.error", "La besto devas esti enmetita en la jenan formaton:\nnomo de la besto, ekzemple, \"elefanto\"."},

                {"statement.prompt", "Indiku fakton, kiu distingas%s de%s.%nLa frazo devas esti de la formato: 'Ĝi ...'.%n"},
                {"statement.error", "La ekzemploj de aserto:\n - Ĝi povas flugi\n - Ĝi havas kornojn\n - Ĝi estas mamulo\n"},

                {"game.play", "Ni ludu!"},
                {"game.think", "Vi pensu pri besto, kaj mi divenos ĝin."},
                {"game.enter", "Premu enen kiam vi pretas."},
                {"game.win", "Bonege, ke mi trafis ĝin ĝuste!"},
                {"game.giveUp", "Mi rezignas. Kiun beston vi havas en la kapo?"},
                {"game.isCorrect", "Ĉu la aserto ĝustas por la%s?%n"},
                {"game.learned", "Mi lernis la jenajn faktojn pri bestoj:"},
                {"game.distinguish", "Mi povas distingi ĉi tiujn bestojn per la demando:"},
                {"game.thanks", new String[]{"Tio estis amuza!", "Estis agrable ludi kun vi!", "Dankon pro ludado!",
                        "Dankon! Mi tro amuziĝis!"}
                },
                {"game.again", new String[]{"Ĉu vi volas provi denove?", "Ĉu vi ŝatas ludi denove?", "Ĉu vi volas ripeti?",
                        "Ĉu vi volas ludi denove?", "Ankoraŭ unu ludo?", "Ĉu vi volas ludi denove?"}
                },
                {"game.guess", "Ĉu ĝi estas%s?%n"},

                {"menu.property.title", "Kion vi volas fari:"},
                {"menu.property.exit", "Eliri"},
                {"menu.property.error", "Bonvolu enigi numeron de 0 ĝis %d%n"},

                {"menu.entry.play", "Ludi la divenludon"},
                {"menu.entry.list", "Listo de ĉiuj bestoj"},
                {"menu.entry.search", "Serĉi beston"},
                {"menu.entry.statistics", "Kalkuli statistikojn"},
                {"menu.entry.print", "Printi la Sciarbon"},
                {"menu.choice", "Via elekto:"},

                {"tree.list.animals", "Jen la bestoj, kiujn mi konas:"},
                {"tree.search.facts", "Faktoj pri la %s:%n"},
                {"tree.search.noFacts", "Neniuj faktoj pri la %s.%n"},

                {"tree.stats.title", "La statistiko de la Scio-Arbo"},
                {"tree.stats.root", "radika nodo                  %s%n"},
                {"tree.stats.nodes", "totala nombro de nodoj       %d%n"},
                {"tree.stats.animals", "totala nombro de bestoj      %d%n"},
                {"tree.stats.statements", "totala nombro de deklaroj    %d%n"},
                {"tree.stats.height", "alteco de la arbo            %d%n"},
                {"tree.stats.minimum", "minimuma profundo            %d%n"},
                {"tree.stats.average", "averaĝa profundo             %.1f%n"},

                {"positiveAnswerPattern", Pattern.compile("^\\s*(?:j|jes|certe)[!.]?\\s*$", Pattern.CASE_INSENSITIVE)},
                {"negativeAnswerPattern", Pattern.compile("^\\s*(?:n|ne)[!.]?\\s*$", Pattern.CASE_INSENSITIVE)},

                {"rule.animal.isCorrect", Pattern.compile("(?:la\\s+)?(.+)$", Pattern.CASE_INSENSITIVE)},
                {"rule.animal.animalPart", "$1"},
                {"rule.animal.articlePart", ""},

                {"rule.indefinite.1.pattern", GrammarUtils.ALL_PATTERN},
                {"rule.indefinite.1.replace", ""},

                {"rule.statement.isCorrect", Pattern.compile("(ĝi)\\s+(.+)\\s+(.+)", Pattern.CASE_INSENSITIVE)},
                {"rule.statement.subject", "$1"},
                {"rule.statement.verb", "$2"},
                {"rule.statement.predicate", "$3"},

                {"rule.negate.1.pattern", Pattern.compile("(.+)", Pattern.CASE_INSENSITIVE)},
                {"rule.negate.1.replace", "ne $1"},

                {"rule.question.1.pattern", Pattern.compile("(.+)")},
                {"rule.question.1.replace", "Ĉu %s $1 %s?"},
        };
    }
}
