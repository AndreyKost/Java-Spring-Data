import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

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

        PreparedStatement statement=connection.prepareStatement("select m.name,m.age,v.name\n" +
                "from minions  m\n" +
                "JOIN minions_villains mv\n" +
                "ON m.id=mv.minion_id\n" +
                "JOIN villains v\n" +
                "ON mv.villain_id=v.id\n" +
                "where v.id=?;");

        System.out.println("Please enter the given id you want to derive information for: ");
        int id=Integer.parseInt(scanner.nextLine());
        statement.setInt(1,id);
        ResultSet resultSet=statement.executeQuery();

        int count=0;

        if(resultSet.next()){
            System.out.println("Villain: "+resultSet.getString("v.name"));
            System.out.printf("%d. %s %d %n",
                    ++count,
                    resultSet.getString("m.name"),
                    resultSet.getInt("m.age"));
            while (resultSet.next()){
                System.out.printf("%d. %s %d %n",
                        ++count,
                        resultSet.getString("m.name"),
                        resultSet.getInt("m.age"));

            }
        } else {
            System.out.printf("No villain with ID %d exists in the database.%n",id);
        }

    }


}
