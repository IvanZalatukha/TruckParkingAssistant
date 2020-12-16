
import dao.impl.ImplParkingCRUD;
import dao.impl.ImplUserCRUD;
import domain.Parking;
import domain.Role;
import domain.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        Parking park = new Parking("SLAVA IVANY",100,99, 64.2, 72.3, null);
        Parking park1 = new Parking("1",1,1, 2.2, 2.3, null);
        Parking park2 = new Parking("1999999",1,1, 2.2, 2.3, null);
        Parking park3 = new Parking("1",1,1, 2.2, 2.3, null);
        Parking park4 = new Parking("1892894489",100,1, 92.2, 92.3, null);
        System.out.println(park4.getId());
        User user = new User("PETYA", "1234", "Fimanys", "Ragylya",3141324, Role.WITHOUT_ROLE);
        User user1 = new User("123123123", "1234", "Artyr4ik", "petyh",3141324, Role.WITHOUT_ROLE);
        User user2 = new User("123123123", "1234", "Artyr4ik", "petyh",3141324, Role.WITHOUT_ROLE);
        User user3 = new User("sadsadasdas", "1234", "petywyr4ik", "petyh",3141324, Role.WITHOUT_ROLE);


        ImplParkingCRUD implParkingCRUD = new ImplParkingCRUD();

        ImplUserCRUD implUserCRUD = new ImplUserCRUD();

       implUserCRUD.update(4, user3);
        System.out.println(22);



    }

    public static void connectionToDatabase() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/tpa?useUnicode=true&serverTimezone=UTC", "root", "root");
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * from app_user");
            while (resultSet.next()) {
                System.out.println(resultSet.getString("first_name"));
            }
            statement.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }


}
