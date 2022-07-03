package Classes;

import javax.sound.midi.Soundbank;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.*;
import java.util.Date;

public class Admin {

    private final String login = "admin";
    private final String password = "admin";


    public String getPassword() {
        return password;
    }


    public String getLogin() {
        return login;
    }

    public Doctor createDoctor() {
        Scanner scan = new Scanner(System.in);
        Doctor doctor = new Doctor();
        System.out.println("Введите имя доктора:");
        doctor.setName(scan.nextLine());
        System.out.println("Введите фамилию доктора:");
        doctor.setSurname(scan.nextLine());
        System.out.println("Введите отчество доктора:");
        doctor.setMiddleName(scan.nextLine());
        System.out.println("Вы успешно добавили нового доктора в ветклинику \"MediaSoft\"!");
        System.out.println("");
        return doctor;
    }

    public Patient createPatient() throws ParseException {
        Scanner scan = new Scanner(System.in);
        Patient patient = new Patient();
        System.out.println("Введите имя пациента:");
        patient.setFirstName(scan.nextLine());
        System.out.println("Введите фамилию пациента:");
        patient.setLastName(scan.nextLine());
        System.out.println("Введите отчество пациента:");
        patient.setMiddleName(scan.nextLine());

        while (true) {
            System.out.println("Введите дату регистрации (год-месяц-день):");
            String data = scan.nextLine();
            if ((data.matches("\\d{4}\\S\\d{2}\\S\\d{2}")) == true) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                patient.setRegistrationDate((Date) formatter.parse(data));
                break;
            } else {
                System.out.println("Неверная дата регистрации!");
                System.out.println("Попробуйте ещё раз;)");
                System.out.println("");
            }
        }
        System.out.println("Пациент успешно добавлен в систему ветклиники \"MediaSoft\"!");
        System.out.println("");
        return patient;
    }

    public Recept createRecept(List<Patient> patients, List<Doctor> doctors) throws ParseException {
        Scanner scan = new Scanner(System.in);
        Recept recept = new Recept();

        while (true) {
            System.out.println("Введите дату приема (год-месяц-день):");
            String data = scan.nextLine();
            if ((data.matches("\\d{4}\\S\\d{2}\\S\\d{2}")) == true) {
                SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                recept.setDay((Date) formatter.parse(data));
                break;
            } else {
                System.out.println("Неверная дата приема!");
                System.out.println("Попробуйте ещё раз;)");
                System.out.println("");
            }
        }

        while (true) {
            System.out.println("Введите время приема(чч:мм:сс):");
            String time = scan.nextLine();
            if ((time.matches("\\d{2}\\S\\d{2}\\S\\d{2}")) == true) {
                SimpleDateFormat formatter = new SimpleDateFormat("HH:mm:ss", Locale.ENGLISH);
                Time tmp = Time.valueOf(LocalTime.parse(time));
                recept.setTime(tmp);
                System.out.println();
                break;
            } else {
                System.out.println("Неверное время приема!");
                System.out.println("Попробуйте ещё раз;)");
                System.out.println("");
            }

        }

        System.out.println("Выберите статус приема:");
        System.out.println("1) Новый");
        System.out.println("2) В процессе");
        System.out.println("3) Отменен");
        System.out.println("4) Ожидает оплаты");
        System.out.println("5) Завершен");

        switch (scan.nextInt()) {
            case 1:
                recept.setReceptStatus(ReceptStatus.RECEPT_STATUS_NEW);
                break;
            case 2:
                recept.setReceptStatus(ReceptStatus.RECEPT_STATUS_IN_PROCESS);
                break;
            case 3:
                recept.setReceptStatus(ReceptStatus.RECEPT_STATUS_CANCELED);
                break;
            case 4:
                recept.setReceptStatus(ReceptStatus.RECEPT_STATUS_AWAITING_PAYMENT);
                break;
            case 5:
                recept.setReceptStatus(ReceptStatus.RECEPT_STATUS_COMPLETE);
                break;
            default:
                System.out.println("Sorry!");
        }

        while (true) {
            System.out.println("Введите ID пациента:");
            int patientId = scan.nextInt();
            if (patients.isEmpty()) {
                System.out.println("Извните, вы не можете создать прием так как в ветклинике \"MediaSoft\" нет ни одного пациента!");
                System.out.println("Пожалуйста сперва добавьте пациента!");
                System.out.println("");
                return null;
            } else {
                if (patientId <= patients.size()) {
                    if (patientId == 0) {
                        System.out.println("Нумерация пациентов начинается с единицы!");
                    } else {
                        recept.setPatientId(patientId - 1);
                        break;
                    }
                }
            }
        }

        while (true) {
            System.out.println("Введите ID доктора:");
            int doctorId = scan.nextInt();
            if (doctors.isEmpty()) {
                System.out.println("Извните, вы не можете создать прием так как в ветклинике \"MediaSoft\" нет ни одного доктора!");
                System.out.println("Пожалуйста сперва добавьте доктора!");
                System.out.println("");
                return null;
            } else {
                if (doctorId <= doctors.size()) {
                    if (doctorId == 0) {
                        System.out.println("Нумерация докторов начинается с единицы!");
                    } else {
                        recept.setDoctorId(doctorId - 1);
                        break;
                    }
                }
            }
        }

        System.out.println("Прием пациента к доктору успешно создан!");
        System.out.println("");
        return recept;
    }

    public void changeReceptStatus(List<Recept> recepts) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Введите ID пациента:");
            int patientId = scan.nextInt();
            if (recepts.isEmpty()) {
                System.out.println("Извините, в ветклинике \"MediaSoft\" нет ни одного приема пациента к доктору!");
                System.out.println("Пожалуйста, сперва создайте прием пациента к доктору!");
                System.out.println("");
                return;
            } else {
                if (patientId <= recepts.size()) {
                    if (patientId == 0) {
                        System.out.println("Нумерация пациентов начинается с единицы!");
                    } else {

                        System.out.println("Выберите статус пациента:");
                        System.out.println("1) Новый");
                        System.out.println("2) В процессе");
                        System.out.println("3) Отменен");
                        System.out.println("4) Ожидает оплаты");
                        System.out.println("5) Завершен");

                        switch (scan.nextInt()) {
                            case 1:
                                recepts.get(patientId - 1).setReceptStatus(ReceptStatus.RECEPT_STATUS_NEW);
                                break;
                            case 2:
                                recepts.get(patientId - 1).setReceptStatus(ReceptStatus.RECEPT_STATUS_IN_PROCESS);
                                break;
                            case 3:
                                recepts.get(patientId - 1).setReceptStatus(ReceptStatus.RECEPT_STATUS_CANCELED);
                                break;
                            case 4:
                                recepts.get(patientId - 1).setReceptStatus(ReceptStatus.RECEPT_STATUS_AWAITING_PAYMENT);
                                break;
                            case 5:
                                recepts.get(patientId - 1).setReceptStatus(ReceptStatus.RECEPT_STATUS_COMPLETE);
                                break;
                            default:
                                System.out.println("Извините, но такого статуса не существует!");
                                System.out.println("");
                        }
                        System.out.println("Статус приема пациента успешно изменен!");
                        System.out.println("");
                        return;
                    }
                } else {
                    System.out.println("Такого пациента нет в системе!");
                    System.out.println("");
                }
            }
        }

    }

    public void showPatientRecept(List<Recept> recepts) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Введите ID пациента:");
            int patientId = scan.nextInt();
            if (recepts.isEmpty()) {
                System.out.println("Не было создано ни одного приема пациента к доктору!");
                System.out.println("Пожалуйста, сперва создайте прием пациента к доктору!");
                System.out.println("");
                return;
            } else {
                if (patientId <= recepts.size()) {
                    if (patientId == 0) {
                        System.out.println("Нумерация пациентов начинается с единицы!");
                    } else {
                        System.out.println("Дата приема : " + recepts.get(patientId - 1).getDay());
                        System.out.println("Время приема : " + recepts.get(patientId - 1).getTime());
                        System.out.println("Статус приема :" + recepts.get(patientId - 1).getReceptStatus());
                        System.out.println("ID Доктора : " + recepts.get(patientId - 1).getDoctorId());
                        System.out.println("");
                        break;
                    }
                } else {
                    System.out.println("Такого пациента нет в системе!");
                    System.out.println("");
                }
            }
        }

    }

    public void showAllThePatients(List<Patient> patient) {
        System.out.println("");
        System.out.println("Список пациентов ↓");
        for (int i = 0; i < patient.size(); i++) {
            System.out.println((i + 1) + ") " + patient.get(i).getFirstName() + " | " + patient.get(i).getLastName() + " | " + patient.get(i).getMiddleName() + " | " + patient.get(i).getRegistrationDate() + " |");
        }
    }

    public void changePatientFio(List<Patient> patients) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Введите ID пациента:");
            int patientId = scan.nextInt();
            if (patients.isEmpty()) {
                System.out.println("Не было создано ни одного пациента в ветклинике \"MediaSoft\"!");
                System.out.println("Пожалуйста, сперва создайте пациента в системе;)");
                System.out.println("");
                return;
            } else {
                if (patientId <= patients.size()) {
                    if (patientId == 0) {
                        System.out.println("Нумерация пациентов начинается с единицы!");
                    } else {
                        System.out.println("Выберите измениния в ФИО пациента:");
                        System.out.println("1) Изменить имя пациента");
                        System.out.println("2) Изменить фамилию пациента");
                        System.out.println("3) Изменить отчество пациента");
                        int choice =scan.nextInt();
                        scan.nextLine();

                        switch (choice) {
                            case 1:
                                System.out.println("Введите новое имя пациента:");
                                String name = scan.nextLine();
                                patients.get(patientId - 1).setFirstName(name);
                                break;
                            case 2:
                                System.out.println("Введите новую фамилию пациента:");
                                String surname = scan.nextLine();
                                patients.get(patientId - 1).setLastName(surname);
                                break;
                            case 3:
                                System.out.println("Введите новое отчество пациента:");
                                String middleName = scan.nextLine();
                                patients.get(patientId - 1).setMiddleName(middleName);
                                break;
                            default:
                                System.out.println("Некорректное действие!");
                        }
                        break;
                    }
                } else {
                    System.out.println("Такого пациента нет в системе!");
                    System.out.println("");
                }
            }
        }
        System.out.println("Изменения успешно внесены!");
        System.out.println("");
    }

    public void deletePatient(List<Patient> patients) {
        Scanner scan = new Scanner(System.in);
        while (true) {
            System.out.println("Введите ID пациента:");
            int patientId = scan.nextInt();
            if (patients.isEmpty()) {
                System.out.println("Не было создано ни одного пациента в ветклинике \"MediaSoft\"!");
                System.out.println("Пожалуйста, сперва создайте пациента в системе;)");
                System.out.println("");
                return;
            } else {
                if (patientId <= patients.size()) {
                    if (patientId == 0) {
                        System.out.println("Нумерация пациентов начинается с единицы!");
                    } else {
                        patients.remove(patientId - 1);
                        break;
                    }
                } else {
                    System.out.println("Такого пациента нет в системе!");
                    System.out.println("");
                }
            }
        }
        System.out.println("Пациент успешно удален из системы!");
        System.out.println("");
    }

    public void saveToDatabase(List<Doctor> doctors,List<Patient> patients,List<Recept> recepts) throws SQLException {

        final String DB_URL = "jdbc:postgresql://127.0.0.1:5432/VetClinic";
        final String USER = "user";
        final String PASS = "user";
        System.out.println("Проверяется соединение с PostgreSQL JDBC");

        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("PostgreSQL JDBC Driver не был найден. Включите её в свою библиотеку! ");
            e.printStackTrace();
            return;
        }

        System.out.println("PostgreSQL JDBC Driver успешно подключен");
        Connection connection = null;

        try {
            connection = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Сбой соединения");
            e.printStackTrace();
            return;
        }

        if (connection != null) {
            System.out.println("Вы успешно подключились к базе данных!");
            if(doctors.isEmpty()){

            }else{
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Doctors(doctorId,doctorName,doctorSurname,doctorMiddleName) VALUES ((?),(?),(?),(?))");
                for (int i=0;i<doctors.size();i++) {
                    preparedStatement.setInt(1,i+1);
                    preparedStatement.setString(2,doctors.get(i).getName());
                    preparedStatement.setString(3,doctors.get(i).getSurname());
                    preparedStatement.setString(4,doctors.get(i).getMiddleName());
                    preparedStatement.executeUpdate();
                }
                preparedStatement.close();
            }

            if(patients.isEmpty()){

            }else{
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Patients(patientId,patientName,patientSurname,patientMiddleName,registrationDate) VALUES ((?),(?),(?),(?),(?))");
                for (int i=0;i<patients.size();i++) {
                    preparedStatement.setInt(1,i+1);
                    preparedStatement.setString(2,patients.get(i).getFirstName());
                    preparedStatement.setString(3,patients.get(i).getLastName());
                    preparedStatement.setString(4,patients.get(i).getMiddleName());
                    preparedStatement.setDate(5, new java.sql.Date(patients.get(i).getRegistrationDate().getTime()));
                    preparedStatement.executeUpdate();

                }
                preparedStatement.close();
            }

            if(recepts.isEmpty()){

            }else{
                PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO Recepts(receptDay,receptTime,doctorId,patientId) VALUES ((?),(?),(?),(?))");
                for (int i=0;i<recepts.size();i++) {
                    preparedStatement.setDate(1, new java.sql.Date(recepts.get(i).getDay().getTime()));
                    preparedStatement.setTime(2, recepts.get(i).getTime());
                    preparedStatement.setInt(3, recepts.get(i).getDoctorId());
                    preparedStatement.setInt(4, recepts.get(i).getPatientId());
                    preparedStatement.executeUpdate();
                }
                preparedStatement.close();
            }


        } else {
            System.out.println("Failed to make connection to database");
        }
        System.out.println("Данные успешно внесены в базу данных!");
        System.out.println("");
        connection.close();
    }



}




