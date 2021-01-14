import dao.DatabaseSettings;
import dao.impl.ImplParkingCRUD;
import dao.impl.ImplParkingsServicesCRUD;
import dao.impl.ImplUserCRUD;
import domain.Parking;
import domain.Role;
import domain.ServicesProvidedByParking;
import domain.User;
import service.UserService;
import validation.EmailValidation;
import validation.LoginValidation;
import validation.PasswordValidation;
import validation.PhoneNumberValidation;

import java.util.ArrayList;
import java.util.List;

public class Test {

    public static void main(String[] args) {

        EmailValidation emailValidation = new EmailValidation();
        String test = "ivan.zala@gmail.com";

        System.out.println(emailValidation.validate(test));
    }




}
