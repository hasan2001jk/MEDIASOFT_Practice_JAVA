import Classes.Admin;
import Classes.Doctor;
import Classes.Patient;
import Classes.Recept;
import Classes.ReceptStatus;


import java.sql.SQLException;
import java.sql.SQLOutput;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String args[]) throws ParseException, SQLException {
        System.out.println("Добро пожаловать в Ветклинику \"MediaSoft\"");
        Scanner scan = new Scanner(System.in);
        Admin admin =new Admin();
        List<Doctor> doctors = new ArrayList<Doctor>();
        List<Patient> patients = new ArrayList<Patient>();
        List<Recept> recepts = new ArrayList<Recept>();

        exit:

        while(true){
            System.out.println("Введите логин:");
            String login=scan.nextLine();
            System.out.println("Введите пароль:");
            String password=scan.nextLine();

            if(login.equals(admin.getLogin()) && password.equals(admin.getPassword())){
                while (true){
                    System.out.println("");
                    System.out.println("Выберите дальнейшее действие:");
                    System.out.println("1) Создать доктора");
                    System.out.println("2) Создать пациента");
                    System.out.println("3) Создать прием пациента к доктору");
                    System.out.println("4) Изменить статус приема пациента");
                    System.out.println("5) Вывод всех приемов пациента");
                    System.out.println("6) Вывод всех пациентов");
                    System.out.println("7) Редактриование ФИО пациента");
                    System.out.println("8) Удаление пациентов");
                    System.out.println("9) Сохранить в БД");
                    System.out.println("10) Выход из системы");

                    short choice=scan.nextShort();


                    switch (choice) {
                        case 1:
                            //Create Doctor
                            doctors.add(admin.createDoctor());
                        break;
                        case 2:
                            //Create Patient
                            patients.add(admin.createPatient());
                        break;
                        case 3:
                            //Create Recept
                            recepts.add(admin.createRecept(patients,doctors));
                        break;
                        case 4:
                            //Change recept status of patient
                            admin.changeReceptStatus(recepts);
                        break;
                        case 5:
                            //Show all the receptions of patient
                            admin.showPatientRecept(recepts);
                        break;
                        case 6:
                            //Show all the patients
                            admin.showAllThePatients(patients);
                        break;
                        case 7:
                            //Change patient's FIO
                            admin.changePatientFio(patients);
                        break;
                        case 8:
                            //Delete patient
                            admin.deletePatient(patients);
                        break;
                        case 9:
                            //Save to Database
                            admin.saveToDatabase(doctors,patients,recepts);
                        break;
                        case 10:
                            //Exit
                            System.out.println("До свидания!");
                        break exit;
                        default:
                            System.out.println("Выберите корректное действие!");
                            System.out.println("");
                    }
                }
            }else{
                System.out.println("Введен неправильный логин или пароль!");
                System.out.println("Попробуйте еще раз:)");
                System.out.println("");
            }

        }
    }

}
