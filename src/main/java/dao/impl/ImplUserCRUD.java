package dao.impl;

import dao.CRUDRepository;
import dao.Connector;
import domain.Parking;
import domain.Role;
import domain.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ImplUserCRUD implements CRUDRepository {
    private static final String FIND_BY_ID = "SELECT * FROM app_user WHERE id=?";
    private static final String FIND_ALL = "SELECT * FROM app_user ORDER BY id";
    private static final String CREATE = "INSERT INTO app_user (id, login, password, first_name, " +
            "last_name, phone_number,role_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM app_user WHERE id=?";
    private static final String UPDATE = "UPDATE app_user SET login=?, password=?, first_name=?," +
            "last_name=?, phone_number=?, role_id=? WHERE id=?";
    private static final Connection connection = Connector.getConnection();

    public User findById(int id) {

        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultStatement = preparedStatement.executeQuery();
            if (resultStatement.next()) {
                user = getUserFromRS(resultStatement);
            }
            resultStatement.close();
            preparedStatement.close();
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    @Override
    public List findAll() {
        List<User> list = new ArrayList<>();
        User user;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL);
            ResultSet resultStatement = preparedStatement.executeQuery();
            while (resultStatement.next()) {
                user = getUserFromRS(resultStatement);
                list.add(user);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return list;
    }
    private User getUserFromRS(ResultSet resultStatement) throws SQLException {
        User user = new User();
        user.setId(resultStatement.getInt("id"));
        user.setLogin(resultStatement.getString("login"));
        user.setPassword(resultStatement.getString("password"));
        user.setFirstName(resultStatement.getString("first_name"));
        user.setLastName(resultStatement.getString("last_name"));
        user.setBanned(resultStatement.getBoolean("is_banned"));
        user.setPhoneNumber(resultStatement.getLong("phone_number"));
        user.setRole(Role.resolveRoleById(resultStatement.getInt("role_id")));
        return user;
    }


    @Override
    public boolean create(Object obj) {
        User user = (User) obj;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setLong(6, user.getPhoneNumber());
            preparedStatement.setInt(7, Role.resolveIdByRole(user.getRole()));
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
            preparedStatement.close();
            if(i == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

    @Override
    public boolean update(int id, Object obj) {
        User user = (User) obj;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setLong(5, user.getPhoneNumber());
            preparedStatement.setInt(6, Role.resolveIdByRole(user.getRole()));
            preparedStatement.setInt(7, user.getId());
            int i = preparedStatement.executeUpdate();
            preparedStatement.close();
            if (i == 1) {
                return true;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }

}
