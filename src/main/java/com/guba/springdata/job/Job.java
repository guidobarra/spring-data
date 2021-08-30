package com.guba.springdata.job;

import com.guba.springdata.component.TaskDataBase;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class Job {

    private final List<TaskDataBase> tasksDataBase;

    @Scheduled(fixedDelay = 5000)
    public void getData() {
        tasksDataBase.forEach(TaskDataBase::printValuesDataBase);
    }

}
