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
import testsql.insert.Diagnosis;
import testsql.insert.Doctors;
import testsql.insert.Patient;
import testsql.insert.Person;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class ControllerJDBC {
    private static String url  =
            "jdbc:mysql://localhost:3306/jdbs_hospital?useSSL=false";//Подключение БД
    private static String user = "root";
    private static String pass = "root";
    Object val =null;


    private boolean main = false;


    //департамент
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


    //доктор
    @FXML
    private TableView<Doctors> doctorsTable;

    @FXML
    private TableColumn<Doctors,Integer> idDoctors;
    @FXML
    private TableColumn<Doctors,String> nameDoctors;
    @FXML
    private TableColumn<Doctors,String> surenameDoctors;
    @FXML
    private TableColumn<Doctors,String> positionDoctors;
    @FXML
    private TableColumn<Doctors,String> separationDoctors;
    @FXML
    private TableColumn<Doctors,Integer> departamentDoctors;

    //пациент
    @FXML
    private TableView<Patient> patientTable;

    @FXML
    private TableColumn<Patient,String> namePatient;
    @FXML
    private TableColumn<Patient,String> surenamePatient;
    @FXML
    private TableColumn<Patient,String> diagnosisPatient;
    @FXML
    private TableColumn<Patient,String> idDoctorsPatient;


    //диагноз
    @FXML
    private TableView<Diagnosis> doagnosisTable;

    @FXML
    private TableColumn<Diagnosis,String> nameDiagnosis;
    @FXML
    private TableColumn<Diagnosis,String> descriptionDiagnosis;




    @FXML//добавление доктора \\ГОТОВО\\
    private void NewLineDoctors(){
        Locale.setDefault(Locale.ENGLISH);
        Stage newWindow = new Stage();
        Button button = new Button("Cоздать");
        javafx.scene.control.TextField ID = new javafx.scene.control.TextField();
        ID.setText("ID");
        javafx.scene.control.TextField NAME = new javafx.scene.control.TextField();
        NAME.setText("NAME");
        javafx.scene.control.TextField SURENAME = new javafx.scene.control.TextField();
        SURENAME.setText("SURENAME");
        javafx.scene.control.TextField POSITION = new javafx.scene.control.TextField();
        POSITION.setText("POSITION");
        javafx.scene.control.TextField SEPARATION = new javafx.scene.control.TextField();
        SEPARATION.setText("SEPARATION");
        javafx.scene.control.TextField DEPARTMENT = new javafx.scene.control.TextField();
        DEPARTMENT.setText("DEPARTMENT");
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
                String surename = SURENAME.getText();
                String position = POSITION.getText();
                String separation = SEPARATION.getText();
                Integer department = Integer.valueOf(DEPARTMENT.getText());

                try (Connection c =
                             DriverManager.getConnection(url, user, pass)) {
                    PreparedStatement statement =
                            c.prepareStatement(
                                    "INSERT INTO DOKTORS" +
                                            "(ID, NAME, SURENAME, POSITION, SEPARATION, DEPARTMENT)" +
                                            "VALUES (?, ?, ?, ?, ?, ?)"
                            );
//INSERT INTO `jdbs_hospital`.`doktors` (`ID`, `NAME`, `SURENAME`, `POSITION`, `SEPARATION`, `DEPARTMENT`) VALUES ('3', 'lfjlkdf', 'nlnln', 'mnlknl', 'mnlbln', '11');
                    Doctors doctors = new Doctors(id, name, surename, position,separation,department);
                    System.out.println(doctors.toString());
                    statement.setInt   (1, doctors.getId());
                    statement.setString(2, doctors.getName());
                    statement.setString(3, doctors.getSurename());
                    statement.setString(4, doctors.getPosition());
                    statement.setString(5, doctors.getSeparation());
                    statement.setInt   (6, doctors.getDepartament());

                    statement.execute();

                    System.out.println("Создана запись: " + doctors);
                    System.out.println();
                    statement.clearParameters();

                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                UpdateDoctors();
                newWindow.close();
            }
        });


        root.getChildren().addAll(secondLabel, ID,NAME,SURENAME,SEPARATION,POSITION,DEPARTMENT, button);
        Scene secondScene = new Scene(root, 230, 300);
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.show();


    }

    @FXML//ОБНОВЛЕНИЕ ДОКТОРА \\ГОТОВО\\
    private void UpdateDoctors(){




        doctorsTable.getSelectionModel().setCellSelectionEnabled(true);
        ObservableList selectedCells1 = doctorsTable.getSelectionModel().getSelectedCells();


        selectedCells1.addListener(new ListChangeListener() {//пол выделеного элемента
            @Override
            public void onChanged(Change c) {
                try {
                    System.out.println();
                    TablePosition tablePosition = (TablePosition) selectedCells1.get(0);
                    val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
                    System.out.println("Selected Value " + val);
                } catch (Exception e) {
                    System.out.println("Ну бывает..");
                }

            }
        });



        ObservableList<Doctors> usersData = FXCollections.observableArrayList();
        if(main == false) {
            main();
        }
        idDoctors.setCellValueFactory(new PropertyValueFactory<Doctors, Integer>("id"));
        nameDoctors.setCellValueFactory(new PropertyValueFactory<Doctors, String>("name"));
        surenameDoctors.setCellValueFactory(new PropertyValueFactory<Doctors, String>("surename"));
        positionDoctors.setCellValueFactory(new PropertyValueFactory<Doctors, String>("position"));
        separationDoctors.setCellValueFactory(new PropertyValueFactory<Doctors, String>("separation"));
        departamentDoctors.setCellValueFactory(new PropertyValueFactory<Doctors, Integer>("departament"));

        Locale.setDefault(Locale.ENGLISH);
        try (Connection c =
                     DriverManager.getConnection(url, user, pass)) {
            Statement statement = c.createStatement();
            /* ResultSet - множество записей которые мы получим
            из БД, (не обработанные)*/
            ResultSet set = statement.executeQuery(
                    "SELECT * FROM DOKTORS"
            );

            List<Doctors> doctors = new ArrayList<>();
            while (set.next()) {
                doctors.add(new Doctors(
                        set.getInt(1),
                        set.getString(2),
                        set.getString(3),
                        set.getString(4),
                        set.getString(5),
                        set.getInt(6)
                ));
            }
            statement.close();
            ObservableList<Doctors> list = FXCollections.observableArrayList(doctors);
            System.out.println(list);
            try {
                usersData.addAll(doctors);
                for (Doctors p : doctors) {
                    // usersData.add(p);
                    System.out.println("usersData\n" + usersData);
                    System.out.println("Получена запись: " + p);
                }
                doctorsTable.setItems(usersData);


            }
            catch (Exception e){
                System.out.println("Обновлений в таблице нет");
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
    @FXML
    private void DeleteDoctors(){
        Object delete = val;


        //  val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
        try (Connection c =
                     DriverManager.getConnection(url,user,pass)) {
            PreparedStatement statement =
                    c.prepareStatement(
                            "DELETE FROM DOKTORS WHERE ID = ?"
                    );
            System.out.println();


            statement.setInt(1, (int)delete);
            statement.execute();

            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        UpdateDoctors();
    }




    @FXML
    private void EditDoctors(){//потом допишу

    }





    @FXML
    private void NewLinePatient(){
        Locale.setDefault(Locale.ENGLISH);
        Stage newWindow = new Stage();
        Button button = new Button("Cоздать");
        javafx.scene.control.TextField NAME = new javafx.scene.control.TextField();
        NAME.setText("NAME");
        javafx.scene.control.TextField SURENAME = new javafx.scene.control.TextField();
        SURENAME.setText("SURENAME");
        javafx.scene.control.TextField DIAGNOSIS = new javafx.scene.control.TextField();
        DIAGNOSIS.setText("DIAGNOSIS");
        javafx.scene.control.TextField IDIDOCTOR = new javafx.scene.control.TextField();
        IDIDOCTOR.setText("IDIDOCTOR");
        Label secondLabel = new Label("Создать новую запись?");
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String name = NAME.getText();
                String surename = SURENAME.getText();
                String diagnosis = DIAGNOSIS.getText();
                Integer idIDoctor = Integer.valueOf(IDIDOCTOR.getText());

                try (Connection c =
                             DriverManager.getConnection(url, user, pass)) {
                    PreparedStatement statement =
                            c.prepareStatement(
                                    "INSERT INTO patient" +
                                            "(NAME, SURENAME, DIAGNOSIS, IDIDOCTOR)" +
                                            "VALUES (?, ?, ?, ?)"
                            );
                    Patient patient = new Patient(name, surename, diagnosis,idIDoctor);
                    System.out.println(patient.toString());

                    statement.setString(1, patient.getName());
                    statement.setString(2, patient.getSurename());
                    statement.setString(3, patient.getDiagnosis());
                    statement.setInt   (4, patient.getIdDoctors());

                    statement.execute();

                    System.out.println("Создана запись: " + patient);
                    System.out.println();
                    statement.clearParameters();

                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                UpdateDoctors();
                newWindow.close();
            }
        });

        root.getChildren().addAll(secondLabel,NAME,SURENAME,DIAGNOSIS,IDIDOCTOR, button);
        Scene secondScene = new Scene(root, 230, 300);
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.show();

    }

    @FXML
    private void UpdatePatient(){




    }
    @FXML
    private void DeletePatient(){

    }
    @FXML
    private void EditPatient(){

    }







    @FXML
    private void NewLineDiagnosis(){

        Locale.setDefault(Locale.ENGLISH);
        Stage newWindow = new Stage();
        Button button = new Button("Cоздать");
        javafx.scene.control.TextField NAME = new javafx.scene.control.TextField();
        NAME.setText("NAME");
        javafx.scene.control.TextField SURENAME = new javafx.scene.control.TextField();
        SURENAME.setText("SURENAME");
        Label secondLabel = new Label("Создать новую запись?");
        FlowPane root = new FlowPane();
        root.setPadding(new Insets(10));
        root.setHgap(10);
        root.setVgap(10);

        button.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {

                String name = NAME.getText();
                String surename = SURENAME.getText();


                try (Connection c =
                             DriverManager.getConnection(url, user, pass)) {
                    PreparedStatement statement =
                            c.prepareStatement(
                                    "INSERT INTO dignosis" +
                                            "(DIAGNOSIS, DESCRIPTION)" +
                                            "VALUES (?,)"
                            );
//INSERT INTO `jdbs_hospital`.`doktors` (`ID`, `NAME`, `SURENAME`, `POSITION`, `SEPARATION`, `DEPARTMENT`) VALUES ('3', 'lfjlkdf', 'nlnln', 'mnlknl', 'mnlbln', '11');
                    Diagnosis doctors = new Diagnosis(name, surename);
                    System.out.println(doctors.toString());
                    statement.setString(2, doctors.getDescription());
                    statement.setString(1, doctors.getName());

                    statement.execute();

                    System.out.println("Создана запись: " + doctors);
                    System.out.println();
                    statement.clearParameters();

                    statement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                UpdateDoctors();
                newWindow.close();
            }
        });


        root.getChildren().addAll(secondLabel,NAME,SURENAME, button);
        Scene secondScene = new Scene(root, 230, 300);
        newWindow.setScene(secondScene);
        newWindow.initModality(Modality.WINDOW_MODAL);
        newWindow.show();


    }

    @FXML
    private void UpdateDiagnosis(){

    }
    @FXML
    private void DeleteDiagnosis(){

    }
    @FXML
    private void EditDiagnosis(){

    }








    @FXML
    private void Delete(){
        Object delete = val;


      //  val = tablePosition.getTableColumn().getCellData(tablePosition.getRow());
        try (Connection c =
                     DriverManager.getConnection(url,user,pass)) {
            PreparedStatement statement =
                    c.prepareStatement(
                            "DELETE FROM HOSPITAL WHERE ID = ?"
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
                            "SELECT * FROM HOSPITAL WHERE ID = ?");
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
                            "UPDATE HOSPITAL SET NAME = ?, PLACE = ?, PRICE = ? WHERE ID = ?");
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
                    "SELECT * FROM HOSPITAL"
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
                                    "INSERT INTO HOSPITAL" +
                                            "(ID, NAME, PLACE, PRICE)" +
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
