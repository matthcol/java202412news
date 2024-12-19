package org.example.tool;

import java.io.File;
import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;
import java.util.Objects;

public class FilePathResourceUtils {

    /**
     * create Path object from a resource file
     * @param aClass class to obtain resource directory from
     * @param resource file placed in resource directory
     * @return Path object representing resource file
     * @throws IllegalArgumentException if resource not found or cannot be converted into URI
     */
    public static Path pathFromResource(Class<?> aClass, String resource){
        try {
            var urlResource =  aClass.getResource(resource);
            if (Objects.isNull(urlResource)){
                throw new IllegalArgumentException(
                        MessageFormat.format("Resource not found <{0}>", resource)
                );
            }
            return Paths.get(urlResource.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(
                    MessageFormat.format("Resource URI Syntax incorrect <{0}>", resource)
            );
        }
    }

    /**
     * create File object from a resource file
     * @param aClass class to obtain resource directory from
     * @param resource file placed in resource directory
     * @return File object representing resource file
     * @throws IllegalArgumentException if resource is not found
     */
    public static File fileFromResource(Class<?> aClass, String resource){
        var urlResource = aClass.getResource(resource);
        if (Objects.isNull(urlResource)){
            throw new IllegalArgumentException(
                    MessageFormat.format("Resource not found <{0}>", resource)
            );
        }
        return new File(urlResource.getFile());
    }
}
