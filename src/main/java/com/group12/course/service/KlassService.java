package com.group12.course.service;

import com.group12.course.dao.KlassDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Klass Service 层
 * @author Tan Xue
 * @date 2018/12/12
 */

@Service
public class KlassService {

    @Autowired
    KlassDao klassDao;


}
