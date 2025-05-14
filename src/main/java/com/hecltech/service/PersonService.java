package com.hecltech.service;

import com.hecltech.model.Person;
import com.hecltech.util.DatabaseUtil;

import javax.xml.crypto.Data;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;
import java.util.PropertyResourceBundle;

public class PersonService {
    private static final String SELECT_ALL_PERSONS_QUERY="SELECT * FROM PERSON";
    private static final String SELECT_ONE_QUERY="SELECT * FROM PERSON WHERE ID = ?";
    private static final String INSERT_ONE_QUERY="INSERT INTO PERSON(FIRST_NAME,LAST_NAME) VALUES "+"(?,?)";
    private static final String DELETE_ONE_BY_ID="DELETE FROM PERSON WHERE ID = ?";
    private static final String UPDATE_QUERY="UPDATE PERSON SET FIRST_NAME= ? , LAST_NAME=? WHERE ID = ?";



    public List<Person> getAll(){
        List<Person> result = new ArrayList<>();
        try(Connection connection = DatabaseUtil.getConnection()){
            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_ALL_PERSONS_QUERY);
            result=resultSetToPersonsList(resultSet);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
        return result;
    }

    public Person getOneByID(final int id){

        try(Connection connection = DatabaseUtil.getConnection()){
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ONE_QUERY);
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();

//            ResultSet resultSet = connection.createStatement().executeQuery(SELECT_ONE_QUERY+id);
            final List<Person> result = resultSetToPersonsList(resultSet);
            if(result.isEmpty()){
                return null;
            }
            return result.get(0);
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public Person create(final Person person) throws SQLException {
        final Person result;
        String firstName = person.getFirstName();
        String lastName = person.getLastName();
        final Connection connection = DatabaseUtil.getConnection();
        try(connection){
            final PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ONE_QUERY, Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setString(1,firstName);
            preparedStatement.setString(2,lastName);

            preparedStatement.executeUpdate();
            connection.commit();
            int generatedId = getGeneratedId(preparedStatement.getGeneratedKeys());
            if(generatedId ==-1) {
                return null;
            }
             result = getOneByID(generatedId);

        }
        catch (SQLException e){
            try {
                connection.rollback();
            }
            catch (SQLException ex){
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        return result;
    }

    public void deleteOneByID(int id) {
        final Connection connection = DatabaseUtil.getConnection();
        try (connection) {

            final PreparedStatement preparedStatement = connection.prepareStatement(DELETE_ONE_BY_ID);
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            connection.commit();

        } catch (SQLException exception) {
            try {
                connection.rollback();
            } catch (SQLException e) {
                throw new RuntimeException(e);

            }

        }
    }
    public Person update(final Person person) {
         Connection connection = null;
        try  {
            connection=DatabaseUtil.getConnection();
            final PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_QUERY);
            preparedStatement.setString(1, person.getFirstName());
            preparedStatement.setString(2, person.getLastName());
            preparedStatement.setInt(3, person.getId());


            int rowsUpdated = preparedStatement.executeUpdate();
            if (rowsUpdated==0){
                connection.rollback();
                throw new RuntimeException("No person found with id : "+person.getId());
            }

            connection.commit();
            return getOneByID(person.getId());

        } catch (SQLException e) {
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEX) {
                    throw new RuntimeException("Rollbck Failed", rollbackEX);

                }
            }
            throw new RuntimeException("Update Failed", e);
        }finally {
            if(connection!=null){
                try {
                    connection.close();
                }catch (SQLException closeEX){
                    throw new RuntimeException("Failed to close",closeEX);
                }
            }
        }

    }

    // --------------------------------------------------------------------------------------------------------------------------------
    //------------------------------------------------------------------------------------------------------------------------------------------
   private static List<Person>  resultSetToPersonsList(ResultSet resultSet) throws SQLException {
        List<Person> result =new ArrayList<>();
        if(resultSet!=null){
            while(resultSet.next()){
                result.add( new Person(resultSet.getInt("id"),
                        resultSet.getString("first_name"),
                        resultSet.getString("last_name")));

            }

        }
        return result;
   }
   private int getGeneratedId(final ResultSet generatedKeys) throws SQLException {
        if(generatedKeys.next()){
            return generatedKeys.getInt(1);

        }
        return -1;
   }

}
