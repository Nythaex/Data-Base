import java.sql.*;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni","root","123");
            PreparedStatement  statement=connection.prepareStatement("SELECT * FROM addresses;");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                System.out.println(resultSet.getString(2));
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }
}
