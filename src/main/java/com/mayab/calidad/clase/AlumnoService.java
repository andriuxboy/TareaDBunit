/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mayab.calidad.clase;

import java.sql.SQLException;

/**
 *
 * @author andriuxboy
 */
public class AlumnoService {
    private AlumnoDAO dao;

    public AlumnoService(AlumnoDAO dao) {
        this.dao = dao;
    }
    
    
    public void insertNewAlumno(Alumno a) throws SQLException{
        dao.save(a);   
    }
    public void deleteAlumno(Alumno a) throws SQLException{
        dao.delete(a);   
    }
    public void updateEmail(Alumno a, String email){
        dao.updateEmail(a, email);
    }
    public void readAll(){
        dao.readAll();
    }
    public void readAlumno(Alumno a){
        dao.readAlumno(a);
    }    
    
    
}
