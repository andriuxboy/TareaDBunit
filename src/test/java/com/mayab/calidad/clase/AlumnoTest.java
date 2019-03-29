/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mayab.calidad.clase;

import java.io.File;
import org.dbunit.DBTestCase;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.dbunit.database.QueryDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.dbunit.Assertion;
import org.dbunit.dataset.ITable;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.samePropertyValuesAs;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


/**
 *
 * @author andriuxboy
 */
public class AlumnoTest{
    private JdbcDatabaseTester databaseTester;
    public AlumnoTest() {
        
    }
    
    @Before
    public void setUp() throws Exception{
        databaseTester = new JdbcDatabaseTester("org.hsqldb.jdbc.JDBCDriver","jdbc:hsqldb:hsql://localhost/Alumnos", "SA", "");
        IDatabaseConnection connection = databaseTester.getConnection();
        
        //Real Database
        //QueryDataSet dataSet = new QueryDataSet(connection); 
        //dataSet.addTable("FOO", "SELECT * FROM TABLE WHERE COL='VALUE'");
        
        FlatXmlDataSetBuilder builder = new  FlatXmlDataSetBuilder();
        IDataSet dataSetXML = builder.build(this.getClass().getResourceAsStream("/alumnos-input.xml"));
        
        databaseTester.setDataSet(dataSetXML);
        databaseTester.onSetup();
    }
    
    @After
    public void tearDown() throws Exception {
        databaseTester.onTearDown();
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void CountTest() throws Exception {
            IDatabaseConnection connection = databaseTester.getConnection();
            assertThat(4,equalTo(connection.getRowCount("ALUMNOS")));
     }
     @Test
     public void InsertAlumno() throws Exception {
            IDatabaseConnection connection = databaseTester.getConnection();
            Alumno alumno = new Alumno(5,"Luis","Hernandez",31,"luis@hotmail.com");
            AlumnoDAO dao = new AlumnoDAO();
            AlumnoService service = new AlumnoService(dao);
            service.insertNewAlumno(alumno);
            IDataSet databaseDataSet = connection.createDataSet();
            ITable actualTable = databaseDataSet.getTable("ALUMNOS");
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("alumno-expected-newalumno.xml"));
            ITable expectedTable = databaseDataSet.getTable("ALUMNOS");               
            Assertion.assertEquals(expectedDataSet, databaseDataSet);
     }     
//      @Test
//     public void ReadAlumno() throws Exception {
//            IDatabaseConnection connection = databaseTester.getConnection();
//            Alumno alumno = new Alumno(1,"Andres","Ruiz",18,"andres@hotmail.com");
//            AlumnoDAO dao = new AlumnoDAO();
//            AlumnoService service = new AlumnoService(dao);
//            Alumno a = service.readAlumno(alumno);
//            System.out.print(a.getAge().toString());
//            assertThat(alumno, samePropertyValuesAs(a));
//     } 
     @Test
     public void DeleteAlumno() throws Exception {
            IDatabaseConnection connection = databaseTester.getConnection();
            Alumno alumno = new Alumno(1,"Andres","Ruiz",18,"andres@hotmail.com");
            AlumnoDAO dao = new AlumnoDAO();
            AlumnoService service = new AlumnoService(dao);
            service.deleteAlumno(alumno);
            IDataSet databaseDataSet = connection.createDataSet();
            ITable actualTable = databaseDataSet.getTable("ALUMNOS");
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("alumno-expected-deletealumno.xml"));
            ITable expectedTable = databaseDataSet.getTable("ALUMNOS");               
            Assertion.assertEquals(expectedDataSet, databaseDataSet);
     }
     @Test
     public void UpdateEmail() throws Exception {
            IDatabaseConnection connection = databaseTester.getConnection();
            Alumno alumno = new Alumno(1,"Andres","Ruiz",18,"andres@hotmail.com");
            AlumnoDAO dao = new AlumnoDAO();
            AlumnoService service = new AlumnoService(dao);
            service.updateEmail(alumno,"exito@hotmail.com");
            IDataSet databaseDataSet = connection.createDataSet();
            ITable actualTable = databaseDataSet.getTable("ALUMNOS");
            IDataSet expectedDataSet = new FlatXmlDataSetBuilder().build(new File("alumno-expected-updateemail.xml"));
            ITable expectedTable = databaseDataSet.getTable("ALUMNOS");               
            Assertion.assertEquals(expectedDataSet, databaseDataSet);
     }         
}
