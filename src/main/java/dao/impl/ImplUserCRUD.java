package dao.impl;

import dao.CRUDRepository;
import entity.Role;
import entity.User;
import dao.pool.ConnectionPool;
import service.UserService;

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
            "last_name, email, phone_number,role_id) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String DELETE = "DELETE FROM app_user WHERE id=?";
    private static final String UPDATE = "UPDATE app_user SET login=?, password=?, first_name=?," +
            "last_name=?, phone_number=?, email=?, role_id=? WHERE id=?";
    private static final String FIND_BY_LOGIN = "SELECT * FROM app_user where LOGIN=?";
    private static final String AMOUNT_ID = "SELECT COUNT(id) FROM app_user";
    private static Connection connection = null;

    static {
        try {
            connection = ConnectionPool.getInstance().getConnection();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static ImplUserCRUD instance;

    public static ImplUserCRUD getInstance() {
        if (instance == null) {
            instance = new ImplUserCRUD();
        }
        return instance;
    }

    public User findById(int id) {

        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);
            preparedStatement.setInt(1, id);
            ResultSet resultStatement = preparedStatement.executeQuery();
            if (resultStatement.next()) {
                user = getUserFromRS(resultStatement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;
    }

    public User findByLogin(String login) {
        User user = null;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_LOGIN);
            preparedStatement.setString(1, login);
            ResultSet resultStatement = preparedStatement.executeQuery();
            if (resultStatement.next()) {
                user = getUserFromRS(resultStatement);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return user;

    }

    @Override
    public List<User> findAll() {
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
        user.setEmail(resultStatement.getString("email"));
        user.setBanned(resultStatement.getBoolean("is_banned"));
        user.setPhoneNumber(resultStatement.getLong("phone_number"));
        user.setRole(Role.resolveRoleById(resultStatement.getInt("role_id")));
        return user;
    }

    public Integer numberOfUsers() {
        Integer number = 0;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(AMOUNT_ID);
            ResultSet resultStatement = preparedStatement.executeQuery();
            while (resultStatement.next()) {
                return resultStatement.getInt(1);
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return number;
    }


    @Override
    public boolean create(Object obj) {
        User user = (User) obj;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CREATE);
            preparedStatement.setInt(1, user.getId());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, UserService.getMd5Hash(user.getPassword()));
            preparedStatement.setString(4, user.getFirstName());
            preparedStatement.setString(5, user.getLastName());
            preparedStatement.setString(6, user.getEmail());
            preparedStatement.setLong(7, user.getPhoneNumber());
            preparedStatement.setInt(8, Role.resolveIdByRole(user.getRole()));
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
        User user = (User) obj;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE);

            preparedStatement.setString(1, user.getLogin());
            preparedStatement.setString(2, UserService.getMd5Hash(user.getPassword()));
            preparedStatement.setString(3, user.getFirstName());
            preparedStatement.setString(4, user.getLastName());
            preparedStatement.setString(5, user.getEmail());
            preparedStatement.setLong(6, user.getPhoneNumber());
            preparedStatement.setInt(7, Role.resolveIdByRole(user.getRole()));
            preparedStatement.setInt(8, user.getId());
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
