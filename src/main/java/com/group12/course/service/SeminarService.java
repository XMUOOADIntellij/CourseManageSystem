package com.group12.course.service;

import com.group12.course.dao.KlassSeminarDao;
import com.group12.course.dao.SeminarDao;
import com.group12.course.entity.KlassSeminar;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SeminarService {
    @Autowired
    SeminarDao seminarDao;

}
