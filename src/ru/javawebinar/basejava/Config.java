package ru.javawebinar.basejava;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Config {
    private static final Config INSTANCE = new Config();
    private static final File PROPS = new File("config\\resumes.properties");
    private Properties props = new Properties();
    private  File testResume;

    public static Config get() {
        return INSTANCE;
    }

    public File getTestResume() {
        return testResume;
    }

    private Config() {
        try (InputStream stream = new FileInputStream("config\\resumes.properties")) {
            props.load(stream);
            testResume = new File(props.getProperty("testResume.dir"));
        } catch (IOException e) {
            throw new IllegalStateException("Invalid config file" + PROPS.getAbsolutePath() );
        }
    }
}
