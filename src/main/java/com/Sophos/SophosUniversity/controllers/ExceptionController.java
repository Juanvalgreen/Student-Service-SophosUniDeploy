package com.Sophos.SophosUniversity.controllers;

import com.Sophos.SophosUniversity.exceptions.*;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(CourseNotfoundException.class)
    public ResponseEntity<Object> handleCourseNotFoundException(CourseNotfoundException ex) {
        // Obtén el mensaje de error de la excepción.
        String errorMessage = ex.getMessage();

        // respuesta de error con el mensaje de error.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

    }

    @ExceptionHandler(TeacherNotFoundException.class)
    public ResponseEntity<Object> handleTeacherNotFoundException(TeacherNotFoundException ex) {
        // Obtén el mensaje de error de la excepción.
        String errorMessage = ex.getMessage();

        // respuesta de error con el mensaje de error.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Object> handleStudentNotFoundException(StudentNotFoundException ex) {
        // Obtén el mensaje de error de la excepción.
        String errorMessage = ex.getMessage();

        // respuesta de error con el mensaje de error.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

    }

    @ExceptionHandler(EnrollmentNotFoundException.class)
    public ResponseEntity<Object> handleEnrollmentNotFoundException(EnrollmentNotFoundException ex) {
        // Obtén el mensaje de error de la excepción.
        String errorMessage = ex.getMessage();

        // respuesta de error con el mensaje de error.
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);

    }

    @ExceptionHandler(InternalServerErrorException.class)
    public ResponseEntity<Object> handleInternalServerError(InternalServerErrorException ex) {
        // Log the exception for debugging purposes
        ex.printStackTrace();

        // Create an error message
        String errorMessage = "Internal Server Error: " + ex.getMessage();

        // Return a 500 Internal Server Error response with a plain text message
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }

    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
        // Loguea la excepción para depuración
        ex.printStackTrace();

        // Crea un mensaje de error
        String errorMessage = "Error de acceso a datos: " + ex.getMessage();

        // Devuelve una respuesta de error HTTP 500 con un mensaje de texto
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorMessage);
    }
}
