package com.guba.springdata.job;

import com.guba.springdata.domain.mysql.Domicile;
import com.guba.springdata.domain.mysql.Student;
import com.guba.springdata.repository.mysql.DomicileRepository;
import com.guba.springdata.repository.mysql.StudentRepository;
import com.guba.springdata.domain.mariadb.Subject;
import com.guba.springdata.domain.mariadb.Teacher;
import com.guba.springdata.repository.mariadb.SubjectRepository;
import com.guba.springdata.repository.mariadb.TeacherRepository;
import com.guba.springdata.domain.postgres.Department;
import com.guba.springdata.domain.postgres.Career;
import com.guba.springdata.repository.postgres.DepartmentRepository;
import com.guba.springdata.repository.postgres.CareerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Job {

    private final DomicileRepository domicileRepository;
    private final StudentRepository studentRepository;
    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;
    private final DepartmentRepository departmentRepository;
    private final CareerRepository careerRepository;

    @Scheduled(fixedDelay = 5000)
    public void getData() {

        printDataMySQL();
        printDataMariaDB();
        printDataPostgres();
    }

    private void printDataMySQL() {
        List<Student> students = studentRepository.findAll();
        List<Domicile> domiciles = domicileRepository.findAll();
        if (students.size() < 2 && domiciles.size() < 2) {
            var domicile1 = new Domicile();
            var domicile2 = new Domicile();
            domicile1.setStreet("Las Calas");
            domicile1.setStreetNum(100);
            domicile2.setStreet("Av. Roca");
            domicile2.setStreetNum(1000);
            domicile1.setDepartNumFloor(2);
            domicile1.setDepartNumRoom(10);

            var student1 = new Student();
            var student2 = new Student();
            student1.setName("Lucas");
            student1.setLastName("Fernadez");
            student1.setPhone(1111112222);
            student1.setDomiciles(List.of(domicile1, domicile2));
            student2.setName("Pepe");
            student2.setLastName("Fernadez");
            student2.setPhone(1111113333);
            student2.setDomiciles(List.of(domicile1));
            studentRepository.saveAll(List.of(student1, student2));
        }
        log.info("#####################################");
        log.info("all domiciles {}: {}", domiciles.size(), domiciles);
        log.info("all students {}: {}", students.size(), students);
        log.info("#####################################\n");
    }

    private void printDataMariaDB() {
        List<Teacher> teachers = teacherRepository.findAll();
        List<Subject> subjects = subjectRepository.findAll();
        if (teachers.size() < 2 && subjects.size() < 2) {
            var subject1 = new Subject();
            var subject2 = new Subject();
            subject1.setCode("MA1L001");
            subject1.setName("Mathematics – I");
            subject1.setDescription("Calculus, Calculus of Several Variables and Vector Calculus");
            subject2.setCode("PH1L001");
            subject2.setName("Physics / Chemistry");
            subject2.setDescription("Classical Physics and Action Principle and Lagrange's equations");

            var teacher1 = new Teacher();
            var teacher2 = new Teacher();
            teacher1.setName("Crawford F.S.");
            teacher1.setLastName("Teich");
            teacher1.setTitle("Engineer Mechanics");
            teacher2.setName("Zweibach");
            teacher2.setLastName("Resnick");
            teacher2.setTitle("doctorate mathematics");
            teacher2.setSubjects(List.of(subject1));
            teacher1.setSubjects(List.of(subject1, subject2));
            teacherRepository.saveAll(List.of(teacher1, teacher2));
        }
        log.info("#####################################");
        log.info("all domiciles {}: {}", subjects.size(), subjects);
        log.info("all students {}: {}", teachers.size(), teachers);
    }

    private void printDataPostgres() {
        List<Career> careers = careerRepository.findAll();
        List<Department> departments = departmentRepository.findAll();
        if (careers.size() < 2 && departments.size() < 2) {
            var department1 = new Department();
            var department2 = new Department();
            department1.setTitle("Economic");
            department1.setName("Economic Sciences");
            department1.setDescription("Since 1989, academic excellence and commitment to society mark the prestigious trajectory of this Department in pedagogical and disciplinary aspects. The professional teaching body is commendable for its academic development and research training. Regarding management, the interest in strengthening the bond between students, teachers and authorities stands out.");
            department2.setTitle("Engineering");
            department2.setName("Engineering and Technological Research");
            department2.setDescription("The Department of Engineering and Technological Research has the task of providing technological education aiming at the development of ICTs.");

            var career1 = new Career();
            var career2 = new Career();
            var career3 = new Career();
            career1.setName("COMPUTER ENGINEERING");
            career1.setYearStudyPlan(2009L);
            career1.setDescription("Able to contribute to a technology-intensive society.");
            career1.setDepartment(department2);

            career2.setName("ELECTRONIC ENGINEERING");
            career2.setYearStudyPlan(2009L);
            career2.setDescription("Basic notions of planning, administration and control of human, technical, economic and environmental resources Functional concept of the operational structure of the company and Engineering");
            career2.setDepartment(department2);

            career3.setName("INTERNATIONAL TRADE");
            career3.setYearStudyPlan(2020L);
            career3.setDescription("Professional capable of carrying out international business management, and of designing, executing, evaluating and monitoring micro and macro economic marketing projects in this field.");
            career3.setDepartment(department1);

            careerRepository.saveAll(List.of(career1, career2, career3));
        }
        log.info("#####################################");
        log.info("all departments {}: {}", departments.size(), departments);
        log.info("all careers {}: {}", careers.size(), careers);
        log.info("#####################################\n");
    }
}
