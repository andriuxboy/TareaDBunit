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
			System.out.println("HSQLDB JDBCDriver Loaded");
			con = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/Alumnos", "SA", "");
			System.out.println("HSQLDB Connection Created");
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
    }
    public void updateEmail(Alumno alumno, String email1){
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "UPDATE \"PUBLIC\".\"ALUMNOS\" SET \"EMAIL\" = ?  WHERE ID = ?"
                            + " AND EMAIL = ? AND NAME = ? AND LASTNAME = ? AND AGE = ?");
            ps.setString(1, email1);
            ps.setInt(2, alumno.getId());
            ps.setString(3, alumno.getEmail());
            ps.setString(4, alumno.getName());
            ps.setString(5, alumno.getLastName());
            ps.setInt(6, alumno.getAge());
            connection.close();
        } catch (SQLException e1) {
            e1.printStackTrace();
        }        
    }

    public void readAlumno(Alumno alumno){
        try {
            PreparedStatement ps = connection.prepareStatement(
                    "select id, name, lastname, age, email from ALUMNOS WHERE ID = ? "
                            + "AND EMAIL = ? AND NAME = ? AND LASTNAME = ? AND AGE = ?");
            ps.setInt(1, alumno.getId());
            ps.setString(2, alumno.getEmail());
            ps.setString(3, alumno.getName());
            ps.setString(4, alumno.getLastName());
            ps.setInt(5, alumno.getAge());
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
    
    
    
    
    
    public void updateEmail(Alumno alumno){
    }    
    
    
    
    
}
