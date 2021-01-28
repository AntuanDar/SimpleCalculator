package common;

import java.io.File;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by taranenko on 28.01.2021
 * Описание:
 */
public class AppStyles {

    public static String getStylesheet(String name) {
        return AppStyles.class.getResource(name).toExternalForm();
    }

    public static List<String> getAllStylesheets() {
        try {
            List<String> list = new ArrayList<>();
            File dir = new File(AppStyles.class.getResource(".").toURI());
            for (File file : dir.listFiles(new ExtensionFileFilter("css")))
                list.add(AppStyles.class.getResource(file.getName()).toExternalForm());
            return list;
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }
}
