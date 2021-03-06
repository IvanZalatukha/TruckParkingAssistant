package by.zalatukha.tpa.dao;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class DatabaseSettings {
    private final String user;
    private final String password;
    private final String jdbc;
    private final String host;
    private final String port;
    private final String dbName;
    private final int poolSize;
    private final boolean useSSL;
    private final boolean useUnicode;
    private final String encoding;
    private final String driver;
    private final String serverTimezone;

    public DatabaseSettings(String user, String password, String jdbc, String host, String port, String dbName, int poolSize,
                            boolean useSSL, boolean useUnicode, String encoding, String driver, String serverTimezone) {
        this.user = user;
        this.password = password;
        this.jdbc = jdbc;
        this.host = host;
        this.port = port;
        this.dbName = dbName;
        this.poolSize = poolSize;
        this.useSSL = useSSL;
        this.useUnicode = useUnicode;
        this.encoding = encoding;
        this.driver = driver;
        this.serverTimezone = serverTimezone;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public String getDriver() {
        return driver;
    }

    private static DatabaseSettings instance;

    public static DatabaseSettings getInstance() {
        if (instance == null) {
            instance = init();
        }
        return instance;
    }

    private static DatabaseSettings init() {
        Properties properties = new Properties();
        try {
            InputStream inputStream = DatabaseSettings.class.getClassLoader().getResourceAsStream("database.properties");
            properties.load(inputStream);
            String user = properties.getProperty("user");
            String password = properties.getProperty("password");
            String jdbc = properties.getProperty("jdbc");
            String host = properties.getProperty("host");
            String port = properties.getProperty("port");
            String name = properties.getProperty("name");
            int poolSize = Integer.parseInt(properties.getProperty("poolSize"));
            boolean ssl = Boolean.parseBoolean(properties.getProperty("useSSL"));
            boolean useUnicode = Boolean.parseBoolean(properties.getProperty("useUnicode"));
            String encoding = properties.getProperty("encoding");
            String driver = properties.getProperty("driver");
            String serverTimezone = properties.getProperty("serverTimezone");

            return new DatabaseSettings(user, password, jdbc, host, port, name, poolSize, ssl, useUnicode, encoding, driver, serverTimezone);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getURL() {
        return jdbc + host + ":" + port + "/" + dbName + "?useSSL=" + useSSL + "&useUnicode=" +
                useUnicode + "&serverTimezone=" + serverTimezone;
    }

}
