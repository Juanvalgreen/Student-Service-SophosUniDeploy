package com.Sophos.SophosUniversity.services;

import com.Sophos.SophosUniversity.entities.Student;
import com.Sophos.SophosUniversity.exceptions.InternalServerErrorException;
import com.Sophos.SophosUniversity.exceptions.StudentNotFoundException;
import com.Sophos.SophosUniversity.models.Courses;
import com.Sophos.SophosUniversity.models.Enrollments;
import com.Sophos.SophosUniversity.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class StudentService implements IStudentService{


    @Autowired
    private StudentRepository repository;

    @Autowired
    RestTemplate restTemplate;

    @Override
    public List<Student> getAllStudents() throws Exception{
        try{
            return (List<Student>) repository.findAll();
        }catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        }catch (RuntimeException ex) {
            // Manejar otras excepciones de tiempo de ejecución
            ex.printStackTrace();
            throw new InternalServerErrorException("Error interno del servidor");
        }
    }


    @Override
    public List<Student> searchAllStudentsById(Long id) throws  Exception{

        try{
            return (List<Student>) repository.searchAllStudentsById(id);
        }catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        }catch (RuntimeException ex) {
            // Manejar otras excepciones de tiempo de ejecución
            ex.printStackTrace();
            throw new InternalServerErrorException("Error interno del servidor");
        }

    }

    @Override
    public List<Student> searchAllStudentsByName(String name) throws  Exception{

        try{
            return (List<Student>) repository.searchAllStudentsByName(name);
        }catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        }catch (RuntimeException ex) {
            // Manejar otras excepciones de tiempo de ejecución
            ex.printStackTrace();
            throw new InternalServerErrorException("Error interno del servidor");
        }

    }



    @Override
    public Student getStudentById(Long id) throws Exception{

        try{
            return repository.findById(id).get();
        }catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new StudentNotFoundException("Estudiante no encontrado con ID: " + id);
        }

    }

    @Override
    public List<Student> getMultipleStudentsById(Iterable<Long> MultipleId) throws Exception{

        try{
            return (List<Student>) repository.findAllById(MultipleId);
        }catch (DataAccessException ex){
            ex.printStackTrace();
            throw new InternalServerErrorException("Error to access the database");
        } catch(RuntimeException ex){
            throw new StudentNotFoundException("Estudiantes no encontrado con esta lista de IDs");
        }

    }

    @Override
    public String updateStudent(Student student) throws Exception{
        Long studentId = student.getStudent_id();
        if(repository.existsById(studentId)){
            try{
                repository.save(student);
                return "Estudiante Actualizado";

            }catch (DataAccessException ex) {
                ex.printStackTrace();
                throw new InternalServerErrorException("Error al acceder a la base de datos");
            } catch (RuntimeException ex) {
                throw new InternalServerErrorException("Error interno del servidor");
            }

        }else{
            throw new StudentNotFoundException("Estudiante no encontrado con el ID: "+ studentId);
        }

    }

    @Override
    public String addNewStudent(Student student) throws Exception{

        try{
            repository.save(student);
            return"Estudiante Registrado";
        }catch (DataAccessException ex) {
            ex.printStackTrace();
            throw new InternalServerErrorException("Error al acceder a la base de datos");
        } catch (RuntimeException ex) {
            throw new InternalServerErrorException("Error interno del servidor");
        }

    }

    @Override
    public String deleteStudent(Long id){
        if(repository.existsById(id)){
            try{


                ResponseEntity<Enrollments[]> responseEntity= restTemplate.getForEntity("http://localhost:9000/api/v1/enrollments/"+id+"/students", Enrollments[].class);
                List<Enrollments> enrollments = Arrays.asList(responseEntity.getBody());

                for (Enrollments enroll : enrollments) {

                    Courses course = restTemplate.getForObject("http://localhost:9002/api/v1/courses/" + enroll.getCourse_id(), Courses.class);

                    if (id.equals(course.getCourse_student_monitor_id())) {
                        course.setCourse_student_monitor_id(null);
                        restTemplate.put("http://localhost:9002/api/v1/courses",course,Courses.class);
                    }


                    restTemplate.delete("http://localhost:9000/api/v1/enrollments/"+enroll.getEnrollment_id());

                }
                repository.deleteById(id);
                return "Estudiate eliminado exitosamente";
            } catch (DataAccessException ex) {
                ex.printStackTrace();
                throw new InternalServerErrorException("Error al acceder a la base de datos");
            } catch (RuntimeException ex) {
                throw new InternalServerErrorException(ex.getMessage());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

        }else{
            throw new StudentNotFoundException("Estudiante no encontrado con este ID: "+id);
        }

    }


}
