package animals;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class ResourceManager {

    private static final ResourceBundle RESOURCES = ResourceBundle.getBundle("animals.resources.App", Locale.getDefault());

    public static String stringResource(String name) {
        return RESOURCES.getString(name);
    }

    public static String[] stringArrayResource(String name) {
        return RESOURCES.getStringArray(name);
    }

    public static Pattern patternResource(String name) {
        return (Pattern) RESOURCES.getObject(name);
    }

}
