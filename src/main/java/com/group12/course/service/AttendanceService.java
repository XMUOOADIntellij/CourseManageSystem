package com.group12.course.service;

import com.group12.course.dao.AttendanceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttendanceService {
    @Autowired
    AttendanceDao attendanceDao;
    //TODO
}
