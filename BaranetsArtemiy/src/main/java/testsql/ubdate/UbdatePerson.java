package testsql.ubdate;

import testsql.insert.Person;

import java.sql.*;
import java.util.Locale;

public class UbdatePerson {

    private static String url  =
            "jdbc:mysql://localhost:3306/test?useSSL=false";
    private static String user = "root";
    private static String pass = "root";

    public static void main(String[] args) {
        Locale.setDefault(Locale.ENGLISH);
        try (Connection c =
                     DriverManager.getConnection(url, user, pass)) {
            PreparedStatement statement =
                    c.prepareStatement(
                            "SELECT * FROM REGISTRATION WHERE ID = ?");
            statement.setInt(1, 24);
            ResultSet set = statement.executeQuery();

            Person person = new Person();
            while (set.next()) {
                person.setId(set.getInt(1));
                person.setName(set.getString(2));
                person.setSurename(set.getString(3));
                person.setNumber(set.getInt(4));
            }
            statement.close();

            System.out.println("Получена запись : " + person);

            person.setSurename("Petrov");
            person.setNumber(47);

            System.out.println("Обновленная запись: " + person);

            PreparedStatement upd = c.
                    prepareStatement(
                            "UPDATE REGISTRATION SET SURNAME = ?, AGE = ? WHERE ID = ?");
            upd.setString(1, person.getSurename());
            upd.setInt(2, person.getNumber());
            upd.setInt(3, person.getId());
            upd.execute();

            upd.close();

            System.out.println("Обновление прошло успешно!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

