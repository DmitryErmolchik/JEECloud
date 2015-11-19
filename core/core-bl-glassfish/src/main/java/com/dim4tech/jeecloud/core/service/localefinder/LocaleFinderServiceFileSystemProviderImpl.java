package com.dim4tech.jeecloud.core.service.localefinder;

import javax.enterprise.context.Dependent;
import java.io.File;
import java.io.FileFilter;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Dependent
public class LocaleFinderServiceFileSystemProviderImpl extends AbstractLocaleFinderService {
    @Override
    public Set<Locale> getLocales(String path, String bundle) {
        Set<Locale> locales = new HashSet<>();
        try {
            Path resourcePath = Paths.get(this.getClass().getClassLoader().getResource(path).toURI());
            File[] files = resourcePath.toFile().listFiles(new FileFilter() {
                @Override
                public boolean accept(File file) {
                    return file.getName().startsWith(bundle);
                }
            });
            for (File file : files) {
                locales.add(getLocale(file.toString()));
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return locales;
    }
}
