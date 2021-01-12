import dao.DatabaseSettings;
import dao.impl.ImplParkingCRUD;
import dao.impl.ImplParkingsServicesCRUD;
import dao.impl.ImplUserCRUD;
import domain.Parking;
import domain.Role;
import domain.ServicesProvidedByParking;
import domain.User;
import service.UserService;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        ImplParkingCRUD implParkingCRUD = ImplParkingCRUD.getInstance();
        System.out.println();
        UserService userService = new UserService();
       User user = new User();
       user.setLogin("Mike");
       user.setPassword("123");

        System.out.println(userService.login(user));
        ImplParkingsServicesCRUD parkingsServicesCRUD = ImplParkingsServicesCRUD.getInstance();




    }




}
