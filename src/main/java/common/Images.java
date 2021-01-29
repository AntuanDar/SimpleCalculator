package common;

import javafx.scene.image.Image;

import java.net.URL;

/**
 * Created by taranenko on 29.01.2021
 * Описание:
 */
public class Images {

    private static final String imagesBasePath = '/' + Images.class.getPackage().getName().replace('.', '/') + '/';

    public static Image createImage(String path) {
        if (path.charAt(0) != '/')
            path = imagesBasePath + path;
        URL url = Images.class.getResource(path);
        if (url == null)
            return null;
        return new Image(url.toString());
    }

}
