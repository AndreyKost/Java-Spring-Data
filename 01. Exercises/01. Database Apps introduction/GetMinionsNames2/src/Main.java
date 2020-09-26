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
        // getVillainsNamesAndCountOfMinions();
        getMinionNameEx();

    }

    private static void getMinionNameEx() throws IOException, SQLException {
        System.out.println("Enter villain_id: ");
        int villain_id = Integer.parseInt(reader.readLine());

        if (!checkIfEntityExists(villain_id, "villains")) {
            System.out.printf("No villain with ID %d exists in the database.%n", villain_id);
            return;
        }

        System.out.printf("Villain: %s%n", getEntityNameById(villain_id, "villains"));
        getMinionsNameAndAgeByVillainId(villain_id);

    }

    private static void getMinionsNameAndAgeByVillainId(int villain_id) throws SQLException {
        query = "select m.name,m.age,v.name\n" +
                "from minions  m\n" +
                "JOIN minions_villains mv\n" +
                "ON m.id=mv.minion_id\n" +
                "JOIN villains v\n" +
                "ON mv.villain_id=v.id\n" +
                "where v.id=?;";


        statement = connection.prepareStatement(query);
        statement.setInt(1, villain_id);
        ResultSet resultSet = statement.executeQuery();
        int count = 0;

        while (resultSet.next()) {
            System.out.printf("%d. %s %d %n",
                    ++count,
                    resultSet.getString("m.name"),
                    resultSet.getInt("m.age"));

        }
    }

    private static String getEntityNameById(int entity_id, String tableName) throws SQLException {
        query = "SELECT name from " + tableName + " WHERE id=?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, entity_id);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next() ? resultSet.getString("name") : null;

    }

    private static boolean checkIfEntityExists(int villain_id, String villains) throws SQLException {
        query = "SELECT * FROM " + villains + " WHERE ID=?";
        statement = connection.prepareStatement(query);
        statement.setInt(1, villain_id);
        ResultSet resultSet = statement.executeQuery();

        return resultSet.next();
    }

    private static void getVillainsNamesAndCountOfMinions() throws SQLException {
        query = "select v1.name,count(*) from villains  v1\n" +
                "JOIN minions_villains  mv\n" +
                "ON v1.id=mv.villain_id\n" +
                "group by v1.id having count(*)>15\n" +
                "order by count(*) desc;";

        statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("| %s | %d |%n",
                    resultSet.getString("v1.name"),
                    resultSet.getInt("count(*)"));

        }
    }


}
