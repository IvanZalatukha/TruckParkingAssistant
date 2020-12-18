package dao.pool;

import dao.DatabaseSettings;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static ConnectionPool instance;
    private static final DatabaseSettings settings = DatabaseSettings.getInstance();
    private BlockingQueue<Connection> connections;

    private ConnectionPool() {
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            instance = init();
        }
        return instance;
    }

    private static ConnectionPool init() {
        ConnectionPool connectionPool = new ConnectionPool();
        try {
            Class.forName(settings.getDriver());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Database driver connection failed", e);
        }
        connectionPool.connections = new ArrayBlockingQueue<>(settings.getPoolSize());
        for (int i = 0; i < settings.getPoolSize(); i++) {
            connectionPool.connections.add(createConnection());
        }
        return connectionPool;
    }

    private static Connection createConnection() {
        try {
            return DriverManager.getConnection(settings.getURL(), settings.getUser(),
                    settings.getPassword());
        } catch (SQLException e) {
            throw new RuntimeException("Database connection failed", e);
        }
    }

    public Connection getConnection() throws InterruptedException {
        return connections.take();
    }

    public void releaseConnection(Connection connection) {
        connections.add(connection);
    }

    public void destroyPool() {
        try {
            for (Connection connection : connections) {
                connection.close();
            }
            instance = null;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
