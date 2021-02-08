package by.zalatukha.tpa.dao.impl;

import by.zalatukha.tpa.dao.CRUDRepository;
import by.zalatukha.tpa.dao.pool.ConnectionPool;
import by.zalatukha.tpa.entity.MessageFromUser;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplMessageCRUD implements CRUDRepository {
    private static final String FIND_BY_ID = "SELECT * FROM messages_from_users WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM messages_from_users ORDER BY id";
    private static final String CREATE = "INSERT INTO messages_from_users (id, name, email, topic, " +
            "text) VALUES (?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM messages_from_users WHERE id=?";
    private static final String UPDATE = "UPDATE messages_from_users SET name=?, email=?, topic=?, " +
            "text=? WHERE id=?";
    private static final String NUMBER_OF_MESSAGES = "SELECT COUNT(id) FROM messages_from_users";
    private static Connection connection = null;

    static {
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static ImplMessageCRUD instance;

    public static ImplMessageCRUD getInstance() {
        if (instance == null) {
            instance = new ImplMessageCRUD();
        }
        return instance;
    }

    public MessageFromUser findById(int id) {
        MessageFromUser message = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultStatement = preparedStatement.executeQuery();
            if (resultStatement.next()) {
                message = getMessageFromRS(resultStatement);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return message;
    }

    private MessageFromUser getMessageFromRS(ResultSet resultStatement) throws SQLException {
        MessageFromUser message = new MessageFromUser();
        message.setId(resultStatement.getInt("id"));
        message.setName(resultStatement.getString("name"));
        message.setEmail(resultStatement.getString("email"));
        message.setTopic(resultStatement.getString("topic"));
        message.setText(resultStatement.getString("text"));
        return message;
    }

    @Override
    public List<MessageFromUser> findAll() {
        List<MessageFromUser> list = new ArrayList<>();
        MessageFromUser message;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultStatement = preparedStatement.executeQuery();
            while (resultStatement.next()) {
                message = getMessageFromRS(resultStatement);
                list.add(message);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }

        return list;
    }

    public Integer numberOfMessages() {
        Integer numberOfMessages = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(NUMBER_OF_MESSAGES);
            ResultSet resultStatement = preparedStatement.executeQuery();
            while (resultStatement.next()) {
                return resultStatement.getInt(1);
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return numberOfMessages;
    }

    @Override
    public boolean create(Object obj) {
        MessageFromUser message = (MessageFromUser) obj;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setInt(1, message.getId());
            preparedStatement.setString(2, message.getName());
            preparedStatement.setString(3, message.getEmail());
            preparedStatement.setString(4, message.getTopic());
            preparedStatement.setString(5, message.getText());
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE);
            preparedStatement.setInt(1, id);
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean update(int id, Object obj) {
        MessageFromUser message = (MessageFromUser) obj;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);
            preparedStatement.setString(1, message.getName());
            preparedStatement.setString(2, message.getEmail());
            preparedStatement.setString(3, message.getTopic());
            preparedStatement.setString(4, message.getText());
            preparedStatement.setInt(5, id);
            int i = preparedStatement.executeUpdate();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}