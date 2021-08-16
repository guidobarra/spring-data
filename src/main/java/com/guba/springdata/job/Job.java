package com.guba.springdata.job;

import com.guba.springdata.domain.Domicile;
import com.guba.springdata.domain.Student;
import com.guba.springdata.repository.DomicileRepository;
import com.guba.springdata.repository.StudentRepository;
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

    @Scheduled(fixedDelay = 5000)
    public void getData() {

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
}
