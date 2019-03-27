/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mayab.calidad.clase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author andriuxboy
 */
public class main {
public static void main(String[] args) throws SQLException, ClassNotFoundException {
                Alumno alumno = new Alumno(1,"a","aa",18,"what@the.com");
                AlumnoDAO dao = new AlumnoDAO();
                AlumnoService service = new AlumnoService(dao);
                //service.readAll();
                String word = ".com";

                service.updateEmail(alumno,word);


	}
}
