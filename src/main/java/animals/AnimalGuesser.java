package animals;

import animals.node.Animal;
import animals.node.Fact;
import animals.node.Node;
import animals.node.Statement;
import animals.util.*;

import java.util.*;

import static animals.ResourceManager.stringResource;
import static animals.util.AnswerType.POSITIVE;
import static animals.util.AnswerType.UNDEFINED;

public class AnimalGuesser {

    private static final String FILE_NAME = "animals";
    private static final String DEFAULT_FILE_FORMAT = "json";
    private final Scanner scanner;
    private Node rootNode;
    private Node parentNode;
    private Node currentNode;
    private boolean gameOver;
    private String fileFormat;

    public AnimalGuesser() {
        fileFormat = DEFAULT_FILE_FORMAT;
        scanner = new Scanner(System.in);
        gameOver = false;
    }

    public void start() {
        greeting();
        if (!loadKnowledgeFromFile()) {
            askFavoriteAnimal();
        }
        showMenu();
        goodbye();
    }

    private void showMenu() {
        boolean exit = false;
        System.out.println(stringResource("welcome"));
        while (!exit) {
            System.out.println();
            System.out.println(stringResource("menu.property.title"));
            System.out.println();
            System.out.printf("%d. %s%n", 1, stringResource("menu.entry.play"));
            System.out.printf("%d. %s%n", 2, stringResource("menu.entry.list"));
            System.out.printf("%d. %s%n", 3, stringResource("menu.entry.search"));
            System.out.printf("%d. %s%n", 4, stringResource("menu.entry.statistics"));
            System.out.printf("%d. %s%n", 5, stringResource("menu.entry.print"));
            System.out.printf("%d. %s%n", 0, stringResource("menu.property.exit"));
            System.out.println(stringResource("menu.choice"));
            switch (askTheUser()) {
                case "1":
                    playGame();
                    break;
                case "2":
                    showAnimalList();
                    break;
                case "3":
                    searchAnimals();
                    break;
                case "4":
                    showStatistics();
                    break;
                case "5":
                    showKnowledgeTree();
                    break;
                case "0":
                    exit = true;
                    break;
                default:
                    System.out.printf(stringResource("menu.property.error"), 5);
                    break;
            }
        }
    }

    private void searchAnimals(){
        List<String> facts = new ArrayList<>();
        System.out.println(stringResource("animal.prompt"));
        String animal = createAnimalNode().getName();
        if (searchAnimal(rootNode, animal, facts)) {
            Collections.reverse(facts);
            System.out.printf(stringResource("tree.search.facts"), animal);
            facts.forEach(fact -> System.out.printf(" - %s.%n",fact));
        } else {
            System.out.printf(stringResource("tree.search.noFacts"), animal);
        }
    }

    private boolean searchAnimal(Node node, String animal, List<String> facts) {
        if (node != null) {
            if (node instanceof Animal) {
                return animal.equals(node.getContent());
            } else {
                if (searchAnimal(node.getLeftNode(), animal, facts)) {
                    facts.add(GrammarUtils.negateVerbOfSentence(node.getContent()));
                    return true;
                }
                if(searchAnimal(node.getRightNode(), animal, facts)) {
                    facts.add(node.getContent());
                    return true;
                }
            }
        }
        return false;
    }

    private void showStatistics(){
        TreeStatistic stats = new TreeStatistic(rootNode);
        System.out.println(stringResource("tree.stats.title"));
        System.out.printf(stringResource("tree.stats.root"), stats.getRootContent());
        System.out.printf(stringResource("tree.stats.nodes"), stats.getNodes());
        System.out.printf(stringResource("tree.stats.animals"), stats.getAnimals());
        System.out.printf(stringResource("tree.stats.statements"), stats.getStatements());
        System.out.printf(stringResource("tree.stats.height"), stats.getHeight());
        System.out.printf(stringResource("tree.stats.minimum"), stats.getMinAnimalDepth());
        System.out.printf(stringResource("tree.stats.average"), stats.getAvgAnimalDepth());
    }

    private void showKnowledgeTree() {
        printTree(rootNode, 1);
    }

    private void printTree(Node node, int depth) {
        if (node != null) {
            if (node instanceof Animal) {
                Animal animal = (Animal)node;
                printNode(animal.toString(), depth);
            } else {
                Statement statement = (Statement)node;
                printNode(statement.getFactQuestion(), depth);
            }
            printTree(node.getLeftNode(), depth + 1);
            printTree(node.getRightNode(), depth + 1);
        }
    }

    private void printNode(String content, int depth) {
        System.out.printf("%s %s%n"," ".repeat(depth), content);
    }

    private void showAnimalList() {
        List<String> animals = new ArrayList<>();
        searchAllAnimals(animals, rootNode);
        Collections.sort(animals);
        System.out.println(stringResource("tree.list.animals"));
        animals.forEach(animal -> System.out.printf(" - %s%n", animal));
    }

    private void searchAllAnimals(List<String> animals, Node node) {
        if (node != null) {
            if (node instanceof Animal) {
                animals.add(node.getContent());
            } else {
                searchAllAnimals(animals, node.getLeftNode());
                searchAllAnimals(animals, node.getRightNode());
            }
        }
    }

    private void saveKnowledgeToFile() {
        PersistenceUtils.saveTreeToFile(FILE_NAME, fileFormat, rootNode);
    }

    private boolean loadKnowledgeFromFile() {
        rootNode = PersistenceUtils.loadTreeFromFile(FILE_NAME, fileFormat);
        return rootNode != null;
    }

    private void resetGame() {
        gameOver = false;
        parentNode = rootNode;
        currentNode = rootNode;
    }

    private void playGame() {
        resetGame();
        System.out.println(stringResource("game.play"));
        while (!gameOver) {
            System.out.println(stringResource("game.think"));
            System.out.println(stringResource("game.enter"));
            askTheUser();
            while (!gameOver) {
                tryToGuessOrAskQuestion();
            }
            askToPlayAgain();
        }
        saveKnowledgeToFile();
    }

    private void askToPlayAgain() {
        AnswerType answer = askYesOrNoQuestion(PhraseGenerator.playAgain());
        if (answer.equals(POSITIVE)) {
            resetGame();
        } else {
            System.out.println(PhraseGenerator.thanks());
        }
    }

    private void askFavoriteAnimal() {
        System.out.println(stringResource("animal.wantLearn"));
        System.out.println(stringResource("animal.askFavorite"));
        rootNode = createAnimalNode();
    }

    private void tryToGuessOrAskQuestion() {
        if (currentNode instanceof Animal) {
            tryToGuess();
        } else {
            askQuestion();
        }
    }

    private void askQuestion() {
        Statement statement = (Statement) currentNode;
        AnswerType answerType = askYesOrNoQuestion(statement.getFactQuestion());
        parentNode = currentNode;
        if (answerType.equals(POSITIVE)) {
            currentNode = currentNode.getRightNode();
        } else {
            currentNode = currentNode.getLeftNode();
        }
    }

    private void tryToGuess() {
        Animal animal = (Animal) currentNode;
        AnswerType answer = askYesOrNoQuestion(String.format(stringResource("game.guess"), animal.toString()));
        if (answer.equals(POSITIVE)) {
            System.out.println(stringResource("game.win"));
        } else {
            giveUpAndLearn(animal);
        }
        gameOver = true;
    }

    private void giveUpAndLearn(Animal wrongAnimal) {
        System.out.println(stringResource("game.giveUp"));
        Animal correctAnimal = createAnimalNode();
        askFactAboutAnimals(wrongAnimal, correctAnimal);
    }

    private void askFactAboutAnimals(Animal wrongAnimal, Animal correctAnimal) {
        System.out.printf((stringResource("statement.prompt")), wrongAnimal.toString(), correctAnimal.toString());
        Fact fact = getPlayerFact();
        AnswerType answer = askYesOrNoQuestion(String.format(stringResource("game.isCorrect"), correctAnimal));
        if (answer.equals(POSITIVE)) {
            createStatementNode(wrongAnimal, correctAnimal, fact);
            showLearnedFacts(correctAnimal, wrongAnimal, fact);
        } else {
            createStatementNode(correctAnimal, wrongAnimal, fact);
            showLearnedFacts(wrongAnimal, correctAnimal, fact);
        }

    }

    private Fact getPlayerFact() {
        while (true) {
            Fact fact = GrammarUtils.parseFact(askTheUser());
            if (fact != null) {
                return fact;
            } else {
                System.out.println(stringResource("statement.error"));
            }
        }
    }

    private void showLearnedFacts(Animal correctAnimal, Animal wrongAnimal, Fact fact) {
        System.out.printf("%s %s%n", stringResource("animal.nice"), stringResource("animal.learnedMuch"));
        System.out.println(stringResource("game.learned"));
        System.out.println(GrammarUtils.makeSentence(stringResource("definite.article") + correctAnimal.getName(), fact.getVerb(), fact.getPredicate()));
        System.out.println(GrammarUtils.makeSentence(stringResource("definite.article") + wrongAnimal.getName(), fact.getNegativeVerbForm(), fact.getPredicate()));
        System.out.println(stringResource("game.distinguish"));
        System.out.println(fact.toQuestionString());
    }

    private void createStatementNode(Node leftNode, Node rightNode, Fact fact) {
        Statement statement = new Statement(fact);
        statement.setLeftNode(leftNode);
        statement.setRightNode(rightNode);
        if (currentNode.equals(rootNode)) {
            rootNode = statement;
        } else {
            if (parentNode.getLeftNode().equals(currentNode)) {
                parentNode.setLeftNode(statement);
            } else {
                parentNode.setRightNode(statement);
            }
        }
        parentNode = statement;
    }

    private Animal createAnimalNode() {
        while (true) {
            Animal animal = GrammarUtils.parseAnimal(askTheUser());
            if (animal != null) {
                return animal;
            } else {
                System.out.println(stringResource("animal.error"));
            }
        }
    }

    public AnswerType askYesOrNoQuestion(String question) {
        AnswerType answer = UNDEFINED;
        System.out.println(question);
        while (answer.equals(UNDEFINED)) {
            answer = GrammarUtils.parseUserYesOrNoAnswer(askTheUser());
            if (answer.equals(UNDEFINED)) {
                System.out.println(PhraseGenerator.misperception());
            }
        }
        return answer;
    }

    private void goodbye() {
        System.out.println(PhraseGenerator.farewell());
    }

    private void greeting() {
        System.out.println(PhraseGenerator.timeOfDayGreeting());
    }

    private String askTheUser() {
        return scanner.nextLine().toLowerCase(Locale.ROOT);
    }

    public void setFileFormat(String fileFormat) {
        if (fileFormat == null || fileFormat.isEmpty()) {
            this.fileFormat = DEFAULT_FILE_FORMAT;
        } else {
            this.fileFormat = fileFormat;
        }
    }


}
