/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mayab.calidad.clase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author andriuxboy
 */
public class TestAlumno {
    private Alumno a;
    private AlumnoService service;
    private AlumnoDAO dao;
    private Connection hsqlConnection;
    
    
    public TestAlumno() throws SQLException, ClassNotFoundException{
        dao = new AlumnoDAO();
        service = new AlumnoService(dao);
    }
    
    
    
    
    @Before
    public void setUp(){


    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void hello() {
          a = new Alumno(1,"a","aa",18,"a@a.com");
        try {
            dao.delete(a);
            
            
            
        } catch (SQLException ex) {
            Logger.getLogger(TestAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
          
          
     
     }
}
