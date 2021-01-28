import dao.impl.ImplParkingsServicesCRUD;
import dao.impl.ImplUserCRUD;
import domain.ServicesProvidedByParking;
import validation.EmailValidation;
import validation.NameValidation;
import validation.PhoneNumberValidation;
import validation.SpotsValidate;

import java.sql.*;


public class Test {

    public static void main(String[] args) {

        System.out.println(ImplUserCRUD.getInstance().findByLogin("1111"));
        SpotsValidate spotsValidate = new SpotsValidate();
        NameValidation nameValidation = new NameValidation();
        ServicesProvidedByParking servicesProvidedByParking = new ServicesProvidedByParking(true,true,true,true,true,true,true,true,true,true,true,true,true,
                true,true);
        servicesProvidedByParking.setId(4);
        System.out.println(servicesProvidedByParking);
      ImplParkingsServicesCRUD.getInstance().create(servicesProvidedByParking);
    }
//    private static final Logger LOG = LogManager.getLogger(EmailValidation.class);



}
