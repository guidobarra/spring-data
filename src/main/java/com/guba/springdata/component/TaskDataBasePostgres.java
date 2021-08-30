package com.guba.springdata.component;

import com.guba.springdata.domain.postgres.Career;
import com.guba.springdata.domain.postgres.Department;
import com.guba.springdata.repository.postgres.CareerRepository;
import com.guba.springdata.repository.postgres.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Component
public class TaskDataBasePostgres implements TaskDataBase {

    private final DepartmentRepository departmentRepository;
    private final CareerRepository careerRepository;

    @Override
    public void printValuesDataBase() {
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
        log.info("#################DATABASE POSTGRES####################");
        log.info("all departments {}: {}", departments.size(), departments);
        log.info("all careers {}: {}", careers.size(), careers);
        log.info("#################DATABASE POSTGRES####################\n");
    }
}
