package animals.util;

import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;

import static animals.ResourceManager.stringArrayResource;
import static animals.ResourceManager.stringResource;

public class PhraseGenerator {

    public static String timeOfDayGreeting() {
        int currentHour = LocalTime.now().getHour();
        if (currentHour >= 5 && currentHour < 12) {
            return stringResource("greeting.morning");
        } else if (currentHour >= 12 && currentHour < 18) {
            return  stringResource("greeting.afternoon");
        } else {
            return  stringResource("greeting.evening");
        }
    }

    public static String playAgain() {
        return randomStringFromArray(stringArrayResource("game.again"));
    }

    public static String thanks() {
        return randomStringFromArray(stringArrayResource("game.thanks"));
    }

    public static String farewell() {
        return randomStringFromArray(stringArrayResource("farewell"));
    }

    public static String misperception() {
        return randomStringFromArray(stringArrayResource("misperception"));
    }

    private static String randomStringFromArray(String[] array){
        return array[ThreadLocalRandom.current().nextInt(array.length)];
    }

}
