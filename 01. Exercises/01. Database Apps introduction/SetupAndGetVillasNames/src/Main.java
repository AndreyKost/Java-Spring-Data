import java.sql.*;
import java.util.Properties;
import java.util.Scanner;
import java.lang.*;



public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner scanner=new Scanner(System.in);
        System.out.print("Enter username default (root): ");
        String user=scanner.nextLine();
        user=user.equals("") ? "root":user;

        System.out.print("Enter password default(empty): ");
        String password=scanner.nextLine().trim();
        password=password.equals("") ? "empty":password;

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
        Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db?allowPublicKeyRetrieval=true&useSSL=false",props);
        System.out.println("DB Connected Successfully");

        PreparedStatement statement=connection.prepareStatement("select v1.name,count(*) from villains  v1\n" +
                "JOIN minions_villains  mv\n" +
                "ON v1.id=mv.villain_id\n" +
                "group by v1.id having count(*)>15\n" +
                "order by count(*) desc");

        ResultSet resultSet=statement.executeQuery();

        while (resultSet.next()){
            System.out.printf("| %s | %d |%n",
                    resultSet.getString("v1.name"),
                    resultSet.getInt("count(*)"));

        }

    }
}
