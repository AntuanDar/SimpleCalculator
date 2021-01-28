package common;

import java.io.File;
import java.io.FileFilter;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by taranenko on 28.01.2021
 * Описание:
 */
public class ExtensionFileFilter implements FileFilter {

    private final Set<String> extensions = new HashSet<String>();

    public ExtensionFileFilter(Collection<String> extensions) {
        if (extensions != null && extensions.size() > 0)
            this.extensions.addAll(extensions);
    }

    public ExtensionFileFilter(String... extensions) {
        if (extensions != null && extensions.length > 0)
            Collections.addAll(this.extensions, extensions);
    }

    @Override
    public boolean accept(File file) {
        String name = file.getName();
        if (name.endsWith("."))
            return extensions.size() == 0;

        String extension = name.substring(name.lastIndexOf('.') + 1);
        return extensions.contains(extension);
    }

}
