package demo;

import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user=scanner.nextLine();
        user =user.equals("") ? "root" : user;

        System.out.print("Enter password default (empty): ");
        String password=scanner.nextLine().trim();
        password=password.equals("") ? "empty" : password;

        Properties props=new Properties();
            props.setProperty("user",user);
            props.setProperty("password",password);

            //1. Load jdbc driver (optional)
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.exit(0);
        }

        System.out.println("Driver Loaded Successfully");

        //2. Connect to DB
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni?allowPublicKeyRetrieval=true&useSSL=false", props);
        System.out.println("Connected Successfully");

        PreparedStatement stmt=connection.prepareStatement("SELECT * FROM employees where salary > ?");
        System.out.println("Enter minimal salary (default 2000): ");
       /* String salary=scanner.nextLine().trim();
        stmt.setDouble(1, Double.parseDouble(salary));*/
       String strSalary=scanner.nextLine().trim();
       Double salary= strSalary.equals("") ? 20000:Double.parseDouble(strSalary);
       stmt.setDouble(1,salary);
        ResultSet rs=stmt.executeQuery();


        while (rs.next()){
            System.out.printf("| %-15.15s | %-15.15s | %-10.2f |%n",
                    rs.getString("first_name"),
                    rs.getString("last_name"),
                    rs.getDouble("salary"));

        }

        connection.close();

    }
}
