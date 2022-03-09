package ru.itis.services.old;

import ru.itis.models.Subscription;
import ru.itis.models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DataBaseConnection {

    private static DataBaseConnection instance;
    private Connection connection;

    public DataBaseConnection() {
        try{
            Class.forName("org.postgresql.Driver");//регистрация драйвера
            String url = "jdbc:postgresql://localhost:5432/elephant-db";
            String password = "karolina2001";
            String username = "postgres";
            this.connection = DriverManager.getConnection(url, username, password);
        }
        catch (ClassNotFoundException | SQLException e){
            System.out.println("Something wrong with database connection");
        }
    }

    private Connection getConnection() { return connection; }

    // methods for work with table "users"

    public void saveUser(User user) {
        try{
            String sql = "INSERT INTO users(name,surname,email,password) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,user.getEmail().trim());
            preparedStatement.setString(2,user.getName().trim());
            preparedStatement.setString(3,user.getSurname().trim());
            preparedStatement.setString(4, user.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Failed to save user");
        }
    }
    public User findUserByEmail(String email) {
        try{
            String sql = "SELECT * FROM users WHERE email = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,email.trim());
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return new User(resultSet.getLong("id"), resultSet.getString("email"),resultSet.getString("name"),resultSet.getString("surname"),
                        resultSet.getString("password"));
            }
        } catch (SQLException ex) {
            System.out.println("Failed to find user!");
        }
        return null;
    }

    public User findUserById(Long id) {
        try{
            String sql = "SELECT * FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()){
                return new User(resultSet.getLong("id"), resultSet.getString("email"),resultSet.getString("name"),resultSet.getString("surname"),
                        resultSet.getString("password"));
            }
        } catch (SQLException ex) {
            System.out.println("Failed to find user!");
        }
        return null;
    }

    public void updateUserPassword(Long id, String newPassword) {
        try{
            String sql = "UPDATE users SET password = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,newPassword);
            preparedStatement.setLong(2,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to update password!");
        }
    }

    public void deleteUser(Long id) {
        try{
            String sql = "DELETE FROM users WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete user!");
        }
    }

    // methods for work with table "subscriptions"

    public void createNewSub(Subscription subscription) {
        try{
            String sql = "INSERT INTO subscriptions(user_id,serviceName,comment,endingDate) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,subscription.getUserID());
            preparedStatement.setString(2,subscription.getServiceName());
            preparedStatement.setString(3, subscription.getEndingDate());
            preparedStatement.setString(4, subscription.getComment());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to create a new subscription!");
        }
    }
    public List<Subscription> getSubscriptionsByUserId(Long id) {
        try{
            List<Subscription> list = new ArrayList<>();
            String sql = "SELECT * FROM subscriptions WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                list.add(new Subscription(resultSet.getInt("id"),resultSet.getInt("user_id"),resultSet.getString("serviceName"),
                        resultSet.getString("endingDate")));
            }
            return list;
        } catch (SQLException e) {
            System.out.println("Failed to get subscriptions by user id!");
        }
        return null;
    }

//    public Subscription getSubscriptionById(int id){
//        try {
//            String sql = "SELECT * FROM subscriptions WHERE id = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setInt(1, id);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//                return new Subscription(resultSet.getInt("id"), resultSet.getInt("user_id"), resultSet.getString("serviceName"),resultSet.getString("comment"),
//                        resultSet.getDate("endingDate"));
//            }
//
//        } catch (SQLException e) {
//            System.out.println("Failed to get subscriptions by id!");
//        }
//        return null;
//    }

    public void deleteSub(int sub_id, Long user_id) {
        try{
            String sql = "DELETE FROM subscriptions WHERE id = ? AND user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,sub_id);
            preparedStatement.setLong(2,user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete subscription!");
        }
    }

    public void deleteSubsByUserId(Long user_id){
        try{
            String sql = "DELETE FROM subscriptions WHERE user_id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1,user_id);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Failed to delete subscriptions!");
        }
    }

    // methods for work with table "date_email"

//    public void create(DateEmail date, String email){
//        try{
//            String sql = "INSERT INTO date_email(date,email) VALUES (?,?)";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setDate(1,date);
//            preparedStatement.setString(2,email);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Failed to insert data in table \"data_email\"!");
//        }
//    }

//    public void delete(DateEmail date, String email){
//        try{
//            String sql = "DELETE FROM date_email WHERE date = ? AND email = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setDate(1,date);
//            preparedStatement.setString(2,email);
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            System.out.println("Failed to delete data in table \"data_email\"!");
//        }
//    }

//    public List<String> getEmailsByDate(DateEmail date){
//        try {
//            List<String> listForEmails = new ArrayList<>();
//            String sql = "SELECT email FROM date_email WHERE date = ?";
//            PreparedStatement preparedStatement = connection.prepareStatement(sql);
//            preparedStatement.setDate(1, date);
//            ResultSet resultSet = preparedStatement.executeQuery();
//
//            while (resultSet.next()){
//                listForEmails.add(resultSet.getString("email"));
//            }
//
//            return listForEmails;
//
//        } catch (SQLException e) {
//            System.out.println("Failed to get subscriptions by id!");
//        }
//        return null;
//    }
}
