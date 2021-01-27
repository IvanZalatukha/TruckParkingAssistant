import dao.impl.ImplUserCRUD;
import validation.EmailValidation;
import validation.PhoneNumberValidation;

import java.sql.*;


public class Test {

    public static void main(String[] args) {

        System.out.println(ImplUserCRUD.getInstance().findByLogin("1111"));
    }
//    private static final Logger LOG = LogManager.getLogger(EmailValidation.class);



}
