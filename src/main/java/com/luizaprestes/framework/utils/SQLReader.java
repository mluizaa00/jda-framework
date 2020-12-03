package com.luizaprestes.framework.utils;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

/**
 * @author SaiintBrisson
 */

public class SQLReader {

    private final Map<String, String> sqlQueries = new HashMap<>();

    public String getSql(String name) {
        return sqlQueries.get(name.toLowerCase());
    }

    /**
     * Loads a set of SQL files from the sql/ resource folder
     *
     * @throws IOException if an I/O error has occurred
     */
    public void loadFromResources() throws IOException {
        loadFromResources("sql/");
    }

    /**
     * Loads a set of SQL files from a specified resource folder
     *
     * @param path the resource folder to load from
     * @throws IOException if an I/O error has occurred
     */
    public void loadFromResources(String path) throws IOException {
        Class<?> callerClass;
        try {
            callerClass = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
        } catch (ClassNotFoundException exception) {
            throw new IOException(exception);
        }

        final ClassLoader classLoader = callerClass.getClassLoader();

        URI uri;
        try {
            uri = callerClass.getProtectionDomain().getCodeSource().getLocation().toURI();
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }

        File file = new File(uri);
        if(!file.exists()) {
            throw new IllegalArgumentException("Could not find specified file");
        }
        if(file.isDirectory()) {
            throw new IllegalArgumentException("The specified folder must be a file");
        }

        JarFile jarFile = new JarFile(file);
        Enumeration<JarEntry> entries = jarFile.entries();

        while (entries.hasMoreElements()) {
            JarEntry entry = entries.nextElement();
            String name = entry.getName();

            if(name.length() < path.length() + 5 || !name.startsWith(path) || !name.endsWith(".sql")) {
                continue;
            }

            InputStream stream = classLoader.getResourceAsStream(name);
            if(stream == null) {
                return;
            }

            InputStreamReader reader = new InputStreamReader(stream);
            name = name.substring(path.length(), name.length() - 4);

            read(name, reader);
        }
    }

    /**
     * Loads all SQL queries from specified directory and adds them to a map
     */
    public void loadFromDir(File dir) {
        if(!dir.exists()) {
            throw new IllegalArgumentException("Could not find specified dir");
        }
        if(!dir.isDirectory()) {
            throw new IllegalArgumentException("The specified file must be a folder");
        }

        for (File listFile : Objects.requireNonNull(dir.listFiles())) {
            readFile(listFile);
        }
    }

    /**
     * Loads all SQL queries from specified URL and adds them to a map
     */
    public void loadFromUrl(URL url) {
        try {
            loadFromUri(url.toURI());
        } catch (URISyntaxException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * Loads all SQL queries from specified URI and adds them to a map
     */
    public void loadFromUri(URI uri) {
        File file = new File(uri);

        if(!file.exists()) {
            throw new IllegalArgumentException("Could not find specified file");
        }

        if (file.isDirectory()) {
            loadFromDir(file);
        } else {
            loadFromFile(file);
        }
    }

    /**
     * Loads a single SQL query and adds it to a map
     */
    public void loadFromFile(File file) {
        if(!file.exists()) {
            throw new IllegalArgumentException("Could not find specified file");
        }
        if(file.isDirectory()) {
            throw new IllegalArgumentException("The specified folder must be a file");
        }

        readFile(file);
    }

    private void readFile(File file) {
        if(file.isDirectory()) {
            return;
        }

        String name = file.getName();
        if (name.length() < 5 || !name.endsWith(".sql")) {
            return;
        }
        name = name.substring(0, name.length() - 4);

        try(FileReader reader = new FileReader(file)) {
            read(name, reader);
        } catch (IOException e) {
            System.err.printf("Could not read \"%s\", skipping...", name);
            e.printStackTrace();
        }
    }

    private void read(String name, Reader reader) {
        StringBuilder stringBuilder = new StringBuilder();
        try(BufferedReader stream = new BufferedReader(reader)) {
            String line;

            while((line = stream.readLine()) != null) {
                stringBuilder.append(line)
                    .append("\n");
            }
        } catch (IOException e) {
            System.err.printf("Could not read \"%s\", skipping...", name);
            e.printStackTrace();
        }

        sqlQueries.put(name.toLowerCase(), stringBuilder.toString());
    }

}
