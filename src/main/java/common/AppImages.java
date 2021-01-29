package common;

import javafx.scene.image.Image;

import java.util.Arrays;
import java.util.List;

/**
 * Created by taranenko on 29.01.2021
 * Описание:
 */
public class AppImages {

    private static final String imagesBasePath = '/' + AppImages.class.getPackage().getName().replace('.', '/') + '/';

    public static Image createImage(String path) {
        return createImage(path, false);
    }

    public static Image createImage(String path, boolean isNullable) {
        if (path.charAt(0) != '/')
            path = imagesBasePath + path;
        if (isNullable && Images.class.getResource(path) == null)
            return null;
        return Images.createImage(path);
    }

    public static List<Image> getAppImages() {
        return Arrays.asList(
                createImage("app/app_icon.png")
        );
    }
}
