package animals.util;

import animals.node.Node;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;

import java.io.File;
import java.io.IOException;
import java.util.Locale;

public class PersistenceUtils {

    public static void saveTreeToFile(String fileName, String fileFormat, Node rootNode) {
        String lang = Locale.getDefault().getLanguage();
        String file;
        if (!lang.equals("en")) {
            file = String.format("%s_%s.%s",fileName, lang, fileFormat);
        } else {
            file = String.format("%s.%s",fileName, fileFormat);
        }
        ObjectMapper mapper = mapperOf(fileFormat);
        try {
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(file), rootNode);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static Node loadTreeFromFile(String fileName, String fileFormat) {
        String lang = Locale.getDefault().getLanguage();
        String file;
        if (!lang.equals("en")) {
            file = String.format("%s_%s.%s",fileName, lang, fileFormat);
        } else {
            file = String.format("%s.%s",fileName, fileFormat);
        }
        ObjectMapper mapper = mapperOf(fileFormat);
        try {
            return mapper.readValue(new File(file), Node.class);
        } catch (IOException e) {
            return null;
        }
    }

    private static ObjectMapper mapperOf(String format) {
        switch (format) {
            case "json":
                return new JsonMapper();
            case "xml":
                return new XmlMapper();
            case "yaml":
                return new YAMLMapper();
            default:
                throw new RuntimeException("Invalid file format");
        }
    }

}
