import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;
import java.util.Properties;
import java.util.Scanner;

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
        addMinionExercise4();


    }

    private static void addMinionExercise4() throws IOException, SQLException {
        String minion[] = reader.readLine().split(" ");
        String villain[] = reader.readLine().split(" ");
        String minionName = minion[1];
        int minionAge = Integer.parseInt(minion[2]);
        String minionTown = minion[3];
        String villainName = villain[1];

        if (!checkIfMinionTownExists(minionTown)) {
            query="insert into towns (name) values (?);";
            statement=connection.prepareStatement(query);
            statement.setString(1,minionTown);
            statement.executeUpdate();
            System.out.printf("Town %s was added to the database.%n",minionTown);
        }

        if (!checkIfVillainExists(villainName)) {
            query = "insert into villains (name, evilness_factor) values (?, 'evil');";
            statement = connection.prepareStatement(query);
            statement.setString(1, villainName);
            statement.executeUpdate();
            System.out.printf("Villain %s was added to the database.%n", villainName);
        }

        int townIdByName = findTownIdByName(minionTown);
        insertMinion(minionName,minionAge,townIdByName);

        int minionIdByName = findMinionIdByName(minionName);
        insertVillainOfTheMinion(minionIdByName,townIdByName);

        System.out.printf("Successfully added %s to be minion of %s.",minionName,villainName);
    }

    private static void insertVillainOfTheMinion(int minionIdByName, int townIdByName) throws SQLException {
        query="insert into minions_villains (minion_id, villain_id) values (?, ?);";
        statement=connection.prepareStatement(query);
        statement.setInt(1,minionIdByName);
        statement.setInt(2,townIdByName);
        statement.executeUpdate();

    }

    private static void insertMinion(String minionName, int minionAge, int townIdByName) throws SQLException {
        query="insert into minions ( name, age, town_id) values (?, ?, ?);";
        statement=connection.prepareStatement(query);
        statement.setString(1,minionName);
        statement.setInt(2,minionAge);
        statement.setInt(3,townIdByName);
        statement.executeUpdate();
    }

    private static int findMinionIdByName(String minionName) throws SQLException {
        query="SELECT id from minions where name=?";
        statement=connection.prepareStatement(query);
        statement.setString(1,minionName);
        ResultSet resultSet=statement.executeQuery();

        return resultSet.next() ? resultSet.getInt("id"):0;
    }

    private static int findTownIdByName(String minionTown) throws SQLException {
        query="SELECT id from towns where name=?";
        statement=connection.prepareStatement(query);
        statement.setString(1,minionTown);
        ResultSet resultSet=statement.executeQuery();

        return resultSet.next() ? resultSet.getInt("id"): 0;
    }

    private static boolean checkIfVillainExists(String villainName) throws SQLException {
        query = "SELECT * FROM villains where name=?";
        statement = connection.prepareStatement(query);
        statement.setString(1, villainName);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    private static boolean checkIfMinionTownExists(String minionTown) throws SQLException {
        query = "SELECT * FROM towns where name=?";
        statement = connection.prepareStatement(query);
        statement.setString(1, minionTown);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();

    }
}
