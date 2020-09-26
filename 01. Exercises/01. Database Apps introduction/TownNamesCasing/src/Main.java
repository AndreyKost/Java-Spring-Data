import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class Main {
    private static final String CONNECTION_STRING = "jdbc:mysql://localhost:3306/";
    private static final String DATABASE_NAME = "minions_db?allowPublicKeyRetrieval=true&useSSL=false";
    private static Connection connection;
    private static String query;
    private static PreparedStatement statement;
    private static BufferedReader reader;

    public static void main(String[] args) throws SQLException, IOException {
        reader = new BufferedReader(new InputStreamReader(System.in));

        Properties props = new Properties();
        props.setProperty("user", "root");
        props.setProperty("password", "1234");

        connection = DriverManager.getConnection(CONNECTION_STRING + DATABASE_NAME, props);
        changeTownNamesCasing();


    }

    private static void changeTownNamesCasing() throws SQLException, IOException {
        query="update towns set name=upper(name) where country=?;";

        statement=connection.prepareStatement(query);
        String country=reader.readLine();
        statement.setString(1,country);
        statement.execute();
        if(findIfTownExists(country)){
            townNamesAffected(country);
        } else {
            System.out.println("No town names were affected.");
        }

    }

    private static void townNamesAffected(String country) throws SQLException {
        int numberOfTowns = countTheNumberOfTownsAffected(country);
        query="SELECT name from towns where country='"+country+"'";
        statement=connection.prepareStatement(query);
        ResultSet resultSet=statement.executeQuery();
        List<String> townNames=new ArrayList<>();

        System.out.printf("%d town names were affected.%n",numberOfTowns);
        while (resultSet.next()){
            townNames.add(resultSet.getString("name"));
        }
        System.out.println(townNames.toString());

    }

    private static int countTheNumberOfTownsAffected(String country) throws SQLException {
        query="SELECT count(name) from towns where country='"+country+"'";
        statement=connection.prepareStatement(query);
        ResultSet resultSet=statement.executeQuery();
        resultSet.next();

        return resultSet.getInt(1);
    }

    private static boolean findIfTownExists(String country) throws SQLException {
        query="SELECT * FROM towns where country='"+country+"'";
        statement=connection.prepareStatement(query);
        ResultSet resultSet=statement.executeQuery();

        return resultSet.next();
    }


}
