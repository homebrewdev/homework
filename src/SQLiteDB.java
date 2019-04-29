import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;


public class SQLiteDB {
    public static void dbAddNewPart (int id, String name, String color,
                                     String material, Double weight, int amount) throws Exception {

        String driver   = "org.sqlite.JDBC";
        String url      = "jdbc:sqlite:/users/tiger/AndroidStudioProjects/homework/home/db/storage.db";
        /* для других БД можно будет использовать строки ниже, SQLite не нужна аутентификация
        String dbName   = "...";
        String userName = "...";
        String password = "...";
        */
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url);
            if (conn != null)
                System.out.println("Приложение подключилось к БД !");
            else {
                System.out.println("Приложение НЕ подключилось к БД ?");
                conn.close();
            }
            // SQL запрос к БД на вставку новой записи (детали)
            PreparedStatement prep = conn.prepareStatement(
                    "insert into parts values (?, ?, ?, ?, ?, ?);");

            prep.setInt(1, id);
            prep.setString(2, name);
            prep.setString(3, color);
            prep.setString(4, material);
            prep.setDouble(5, weight);
            prep.setInt(6, amount);

            conn.setAutoCommit(false);
            prep.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<String> printAllParts() {
        String driver   = "org.sqlite.JDBC";
        String url      = "jdbc:sqlite:/users/tiger/AndroidStudioProjects/homework/home/db/storage.db";
        /* для других БД можно будет использовать строки ниже, SQLite не нужна аутентификация
        String dbName   = "...";
        String userName = "...";
        String password = "...";
        */

        String finalString = "";
        ArrayList<String> AllRows = new ArrayList<String>();

        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url);
            if (conn != null)
                System.out.println("Приложение подключилось к БД !");
            else {
                System.out.println("Приложение НЕ подключилось к БД ?");
                conn.close();
            }

            Statement stat = conn.createStatement();
            ResultSet rs = stat.executeQuery("select * from parts;");

            AllRows.add("id | Наименование | Цвет | Материал | Вес | Количество\n");

            while (rs.next()) {
                finalString = rs.getString("id") + '|' + rs.getString("name") +
                        '|' + rs.getString("color") + '|' + rs.getString("material") +
                        '|' + rs.getString("weight") + '|' + rs.getString("amount") + '\n';
                AllRows.add(finalString);
            }
            rs.close();
            stat.close();

            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return AllRows;
    }

    public static void delPartFromDB (int id) {
        String driver   = "org.sqlite.JDBC";
        String url      = "jdbc:sqlite:/users/tiger/AndroidStudioProjects/homework/home/db/storage.db";
        /* для других БД можно будет использовать строки ниже, SQLite не нужна аутентификация
        String dbName   = "...";
        String userName = "...";
        String password = "...";
        */
        try {
            Class.forName(driver).newInstance();
            Connection conn = DriverManager.getConnection(url);
            if (conn != null)
                System.out.println("Приложение подключилось к БД !");
            else {
                System.out.println("Приложение НЕ подключилось к БД ?");
                conn.close();
            }

            PreparedStatement prep = conn.prepareStatement(
                    "DELETE FROM parts WHERE id = ?;");
            prep.setInt(1, id);

            conn.setAutoCommit(false);
            prep.executeUpdate();
            conn.commit();
            conn.setAutoCommit(true);

            conn.close();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
