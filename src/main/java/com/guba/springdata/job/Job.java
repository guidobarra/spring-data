package com.guba.springdata.job;

import com.guba.springdata.domain.Subject;
import com.guba.springdata.domain.Teacher;
import com.guba.springdata.repository.SubjectRepository;
import com.guba.springdata.repository.TeacherRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Job {

    private final SubjectRepository subjectRepository;
    private final TeacherRepository teacherRepository;

    @Scheduled(fixedDelay = 5000)
    public void getData() {

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
        log.info("#####################################\n");
    }
}
