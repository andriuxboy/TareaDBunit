/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mayab.calidad.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


/**
 *
 * @author andriuxboy
 */
public class AlumnoDAO {
    
    private String query;
    private Statement statement;
    private ResultSet result;
    
    public Connection connection;
    
    public AlumnoDAO() throws SQLException, ClassNotFoundException {
        Class.forName("org.hsqldb.jdbc.JDBCDriver");
        connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/Alumnos", "SA", "");
    }
    
    
	public static Connection getConnection() {
		Connection con = null;

		try {
			Class.forName("org.hsqldb.jdbc.JDBCDriver");
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/Alumnos", "SA", "");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
    
    public void save(Alumno alumno) throws SQLException{
        statement = connection.createStatement();
        query = "INSERT INTO \"PUBLIC\".\"ALUMNOS\"\n" +
        "( \"ID\", \"NAME\", \"LASTNAME\", \"AGE\", \"EMAIL\" )\n" +
        "VALUES ( "+alumno.getId().toString()+", '"+alumno.getName()+"', '"+alumno.getLastName()
                +"',"+alumno.getAge().toString()+" , '"+alumno.getEmail()+"')";
        statement.executeUpdate(query);
    }
    public void delete(Alumno alumno) throws SQLException{
        statement = connection.createStatement();
        query = "DELETE FROM \"PUBLIC\".\"ALUMNOS\"\n " +
        " WHERE ID="+alumno.getId().toString();
        statement.executeUpdate(query);        
    }
    public void updateEmail(Alumno alumno, String email) throws SQLException {
        statement = connection.createStatement();
        query = "UPDATE ALUMNOS SET EMAIL = '"+email+"' WHERE id = "+alumno.getId();
        statement.executeUpdate(query);    
    }

public Alumno readAlumno(Alumno alumno) throws SQLException{
    ArrayList array;
    array = new ArrayList();
    try {
        PreparedStatement ps = connection.prepareStatement(
                "select id, name, lastname, age, email from ALUMNOS WHERE ID= ?");
        ps.setInt(1, alumno.getId());
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            array.add(0, rs.getString("id"));
            array.add(1, rs.getString("name"));
            array.add(2, rs.getString("lastname"));
            array.add(3, rs.getInt("age"));
            array.add(4, rs.getString("email"));
                    }
        rs.close();
        connection.close();
    } catch (SQLException e1) {
        e1.printStackTrace();
    }
    Alumno temp;
//    temp = new Alumno(array.get(0), array.get(1), array.get(2), array.get(3), array.get(4));
    return null;
}


    public void readAll(){
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select id, name, lastname, age, email from ALUMNOS");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("ID = " + rs.getInt("id") + 
                        ", Name = " + rs.getString("name") + ", LastName = "
                        + rs.getString("lastName") + ", Age "+rs.getString("age")+
                        ", Email = "+rs.getString("email"));
			}
            rs.close();
            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }
    }
    
   
    
    
    
    
}
