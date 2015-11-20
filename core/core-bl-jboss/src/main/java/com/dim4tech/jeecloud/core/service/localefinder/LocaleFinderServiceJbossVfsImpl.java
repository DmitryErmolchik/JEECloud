package com.dim4tech.jeecloud.core.service.localefinder;

import org.jboss.vfs.VFS;
import org.jboss.vfs.VirtualFile;

import javax.enterprise.context.Dependent;
import java.net.URISyntaxException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

@Dependent
public class LocaleFinderServiceJbossVfsImpl extends AbstractLocaleFinderService {

    @Override
    public Set<Locale> getLocales(String bundlePath) {
        Set<Locale> locales = new HashSet<>();

        String path = getPath(bundlePath);
        String bundle = getBundle(bundlePath);

        try {
            VirtualFile virtualFile = VFS.getChild(this.getClass().getClassLoader().getResource(path).toURI());
            virtualFile.getChildren().forEach(value -> {
                if (value.getName().startsWith(bundle) && value.getName().endsWith("properties")) {
                    locales.add(getLocale(value.getName()));
                }
            });
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return locales;
    }
}
