package animals;

public class Main {

    public static void main(String[] args) {
        String format = "";
        if (args.length > 1 && "-type".equals(args[0])) {
            format = args[1];
        }
        AnimalGuesser animalGuesser = new AnimalGuesser();
        animalGuesser.setFileFormat(format);
        animalGuesser.start();
    }
}
