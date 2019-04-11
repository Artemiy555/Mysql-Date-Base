package testsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Locale;

public class JDBCConectionUtil {
    public static void main(String[] args){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Драйвер обнаружен!");

            Locale.setDefault(Locale.ENGLISH);

            String url = "jdbc:mysql://localhost:3306?useSSL=false";

            String user ="root";
            String pass ="root";

            Connection connection =
                    DriverManager.getConnection(url,user,pass);

            if(connection != null){
                System.out.println("Соединение установленно");

                connection.close();
            }


        }catch (SQLException e){
            System.out.println("Соединение не установленно");
        }catch (ClassNotFoundException e){
            System.out.println("Драйвер не найден");
        }



    }

}
