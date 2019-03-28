package testsql;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.FlowPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import testsql.insert.Person;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ControllerJDBC {
    private static String url  =
            "jdbc:mysql://localhost:3306/test?useSSL=false";//Подключение БД
    private static String user = "root";
    private static String pass = "root";
    Object val =null;


    private boolean main = false;
    @FXML
    private TableView<Person> tableView;

    @FXML
    private TableColumn<Person,Integer> userIDCol;
    @FXML
    private TableColumn<Person,String> userNameCol;
    @FXML
    private TableColumn<Person,String> userFNameCol;
    @FXML
    private TableColumn<Person,Integer> userAgeCol;


    @FXML
    private void Delete(){
        Object delete = val;


      //  val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
        try (Connection c =
                     DriverManager.getConnection(url,user,pass)) {
            PreparedStatement statement =
                    c.prepareStatement(
                            "DELETE FROM REGISTRATION WHERE ID = ?"
                    );
            System.out.println();


            statement.setInt(1, (int)delete);
            statement.execute();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Updete();
    }

    private Person Pol(int i){
        Person person = new Person();
        try (Connection c =
                     DriverManager.getConnection(url, user, pass)) {
            PreparedStatement statement =
                    c.prepareStatement(
                            "SELECT * FROM REGISTRATION WHERE ID = ?");
            statement.setInt(1, i);
            ResultSet set = statement.executeQuery();
            while (set.next()) {
                person.setId(set.getInt(1));
                person.setName(set.getString(2));
                person.setSurename(set.getString(3));
                person.setNumber(set.getInt(4));
            }
            statement.close();

            System.out.println("Получена запись : " + person);
        }catch (Exception e){
            e.printStackTrace();
        }
        return person;
    }

    private void Redactor(int id,String name,String surename,int are,Person person){
        try (Connection c =
                     DriverManager.getConnection(url, user, pass)) {

            System.out.println();

            person.setId(id);
            person.setName(name);
            person.setSurename(surename);
            person.setNumber(are);

            System.out.println("Обновленная запись: " + person);

            PreparedStatement upd = c.
                    prepareStatement(
                            "UPDATE REGISTRATION SET NAME = ?, SURNAME = ?, AGE = ? WHERE ID = ?");
            upd.setString(1,person.getName());
            upd.setString(2, person.getSurename());
            upd.setInt(3, person.getNumber());
            upd.setInt(4, person.getId());
            upd.execute();

            upd.close();

            System.out.println("Обновление прошло успешно!");
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    @FXML
    private void Edit(){
        Person person = Pol((int)val);
        System.out.println(person.getId());
        System.out.println(person.getName());
        System.out.println(person.getSurename());
        System.out.println(person.getNumber());

        Stage newWindow = new Stage();
        Button button = new Button("Обновить");
        javafx.scene.control.TextField ID = new javafx.scene.control.TextField();
        ID.setText(String.valueOf(person.getId()));
        javafx.scene.control.TextField NAME = new javafx.scene.control.TextField();
        NAME.setText(person.getName());
        javafx.scene.control.TextField FNAME = new javafx.scene.control.TextField();
        FNAME.setText(person.getSurename());
        javafx.scene.control.TextField ARE = new javafx.scene.control.TextField();
        ARE.setText(String.valueOf(person.getNumber()));
        Label secondLabel = new Label("Создать новую запись?");
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                int id = Integer.parseInt(ID.getText());
                String name = NAME.getText();
                String surename = FNAME.getText();
                int are = Integer.parseInt(ARE.getText());

                Redactor(id,name,surename,are,person);
                Updete();
                newWindow.close();
            }
        });


        root.getChildren().addAll(secondLabel, ID,NAME,FNAME,ARE, button);
        Scene secondScene = new Scene(root, 230, 250);
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.show();



    }

    @FXML
    private void Updete(){
        ObservableList<Person> usersData = FXCollections.observableArrayList();
        if(main == false) {
            main();
        }
        userIDCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("id"));
        userNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("name"));
        userFNameCol.setCellValueFactory(new PropertyValueFactory<Person, String>("surename"));
        userAgeCol.setCellValueFactory(new PropertyValueFactory<Person, Integer>("number"));
        Locale.setDefault(Locale.ENGLISH);
        try (Connection c =
                     DriverManager.getConnection(url, user, pass)) {
            Statement statement = c.createStatement();
            /* ResultSet - множество записей которые мы получим
            из БД, (не обработанные)*/
            ResultSet set = statement.executeQuery(
                    "SELECT * FROM REGISTRATION"
            );

            List<Person> people = new ArrayList<>();
            while (set.next()) {
                people.add(new Person(
                        set.getInt(1),
                        set.getString(2),
                        set.getString(3),
                        set.getInt(4)
                ));
            }
            statement.close();
            ObservableList<Person> list = FXCollections.observableArrayList(people);
            System.out.println(list);
            try {
            usersData.addAll(people);
            for (Person p : people) {
              // usersData.add(p);
               System.out.println("usersData\n" + usersData);
                System.out.println("Получена запись: " + p);
            }
            tableView.setItems(usersData);


            }
            catch (Exception e){
                System.out.println("Обновлений в таблице нет");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    @FXML
    private void NewLine(){

        Locale.setDefault(Locale.ENGLISH);
        Stage newWindow = new Stage();
        Button button = new Button("Cоздать");
        javafx.scene.control.TextField ID = new javafx.scene.control.TextField();
        ID.setText("ID");
        javafx.scene.control.TextField NAME = new javafx.scene.control.TextField();
        NAME.setText("NAME");
        javafx.scene.control.TextField FNAME = new javafx.scene.control.TextField();
        FNAME.setText("FNAME");
        javafx.scene.control.TextField ARE = new javafx.scene.control.TextField();
        ARE.setText("ARE");
        Label secondLabel = new Label("Создать новую запись?");
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                Integer id = Integer.valueOf(ID.getText());
                String name = NAME.getText();
                String fname = FNAME.getText();
                Integer are = Integer.valueOf(ARE.getText());

                try (Connection c =
                             DriverManager.getConnection(url, user, pass)) {
                    PreparedStatement statement =
                            c.prepareStatement(
                                    "INSERT INTO REGISTRATION" +
                                            "(ID, NAME, SURNAME, AGE)" +
                                            "VALUES (?, ?, ?, ?)"
                            );

                    Person person = new Person(id, name, fname, are);
                    statement.setInt(1, person.getId());
                    statement.setString(2, person.getName());
                    statement.setString(3, person.getSurename());
                    statement.setInt(4, person.getNumber());
                    statement.execute();

                    System.out.println("Создана запись: " + person);
                    System.out.println();
                    statement.clearParameters();

                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                Updete();
                newWindow.close();
            }
        });


        root.getChildren().addAll(secondLabel, ID,NAME,FNAME,ARE, button);
        Scene secondScene = new Scene(root, 230, 250);
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.show();



        /*PreparedStatement класс который позволяет созтать
         * параметоризированный запрос для работы с БД*/


    }



    @FXML
    private void DriverTest(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Драйвер обнаружен!");

            Locale.setDefault(Locale.ENGLISH);

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



    private void main() {
        tableView.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells = tableView.getSelectionModel().getSelectedCells();


        selectedCells.addListener(new ListChangeListener() {//пол выделеного элемента
            @Override
            public void onChanged(Change c) {
                try {
                    TablePosition tablePosition = (TablePosition) selectedCells.get(0);
                    val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                    System.out.println("Selected Value " + val);
                } catch (Exception e) {
                    System.out.println("Ну бывает..");
                }

            }
        });
        main = true;
    }
}
