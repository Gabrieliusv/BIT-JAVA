/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lt.bit;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

/**
 *
 * @author Gabrielius
 */
public class Run {

    /**
     * @param args the command line arguments
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     */
    public static void main(String[] args) throws ClassNotFoundException, SQLException, ParseException {
        Class.forName("com.mysql.cj.jdbc.Driver");

        System.out.println("Options");
        System.out.println("1. Persons list");
        System.out.println("2. Address list");
        System.out.println("3. Contact list");
        System.out.println("4. Add a person");
        System.out.println("5. Delete person");
        System.out.println("0. Exit");

        try (Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/addressbook?serverTimezone=UTC&characterEncoding=UTF-8", "root", "root");) {
            boolean exit = false;

            while (!exit) {
                Scanner sc = new Scanner(System.in);
                System.out.print("Choose an option:");

                int option;
                int personId;
                try {
                    option = sc.nextInt();
                } catch (Exception e) {
                    System.out.println("No such option found");
                    return;
                }

                switch (option) {
                    case 0:
                        System.out.println("Bye");
                        exit = true;
                        break;

                    case 1:
                    try (Statement st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select * from person");) {
                        while (rs.next()) {
                            System.out.print(rs.getInt("id"));
                            System.out.print(" ");
                            System.out.print(rs.getString("first_name"));
                            System.out.print(" ");
                            System.out.print(rs.getString("last_name"));
                            System.out.print(" ");
                            System.out.print(rs.getDate("birth_date"));
                            System.out.print(" ");
                            System.out.print(rs.getBigDecimal("salary"));
                            System.out.println("");
                        }
                    }
                    break;

                    case 2:
                        System.out.println("Write person's id you want to see addresses from");
                        try {
                            personId = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("Person's id must be a number");
                            return;
                        }

                        try (Statement st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select * from address where person_id = " + personId);) {

                            int numOfResults = 0;

                            while (rs.next()) {
                                System.out.print(rs.getInt("id"));
                                System.out.print(" ");
                                System.out.print(rs.getInt("person_id"));
                                System.out.print(" ");
                                System.out.print(rs.getString("address"));
                                System.out.print(" ");
                                System.out.print(rs.getString("city"));
                                System.out.print(" ");
                                System.out.print(rs.getString("postal_code"));
                                System.out.println(" ");
                                numOfResults++;
                            }

                            if (numOfResults == 0) {
                                System.out.println("There is no addresses for this person");
                            }
                        }
                        break;

                    case 3:
                        System.out.println("Write person's id you want to see contacts from");
                        try {
                            personId = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("Person's id must be a number");
                            return;
                        }

                        try (Statement st = conn.createStatement();
                                ResultSet rs = st.executeQuery("select * from contact where person_id = " + personId);) {

                            int numOfResults = 0;

                            while (rs.next()) {
                                System.out.print(rs.getInt("id"));
                                System.out.print(" ");
                                System.out.print(rs.getInt("person_id"));
                                System.out.print(" ");
                                System.out.print(rs.getString("contact_type"));
                                System.out.print(" ");
                                System.out.print(rs.getString("contact"));
                                System.out.println("");
                                numOfResults++;
                            }

                            if (numOfResults == 0) {
                                System.out.println("There is no contacts for this person");
                            }
                        }
                        break;

                    case 4:
                        Scanner sc1 = new Scanner(System.in);
                        System.out.println("First name:");
                        String firstName = sc1.nextLine();
                        System.out.println("Last name:");
                        String lastName = sc1.nextLine();
                        System.out.println("Birthdate:");
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                        Date birthdate = sdf.parse(sc1.nextLine());
                        Calendar cal = Calendar.getInstance();
                        cal.setTime(birthdate);
                        cal.set(Calendar.HOUR, 12);
                        cal.set(Calendar.MINUTE, 0);
                        cal.set(Calendar.SECOND, 0);
                        cal.set(Calendar.MILLISECOND, 0);
                        java.sql.Date sqlBirthdate = new java.sql.Date(cal.getTime().getTime());
                        System.out.println("Salary:");
                        BigDecimal salary = sc1.nextBigDecimal();

                        try (PreparedStatement st = conn.prepareStatement("insert into person (first_name, last_name, birth_date, salary) values(? , ? , ? , ?)");) {
                            st.setString(1, firstName);
                            st.setString(2, lastName);
                            st.setDate(3, sqlBirthdate);
                            st.setBigDecimal(4, salary);
                            st.execute();
                        }
                        break;
                    case 5:
                        System.out.println("Write person's id you want to delete");
                        try {
                            personId = sc.nextInt();
                        } catch (Exception e) {
                            System.out.println("Person's id must be a number");
                            return;
                        }

                        try (PreparedStatement st = conn.prepareStatement("delete from person where id = ?");) {
                            st.setInt(1, personId);
                            st.execute();
                        }
                        break;
                    default:
                        System.out.println("No such option found");
                }
            }
        }
    }
}
