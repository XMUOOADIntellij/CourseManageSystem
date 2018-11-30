package com.group12.course.exception;

/**
 * @author  Tan Xue
 */
public class ClassesNotFoundException extends RuntimeException {
    private int classId;

    public ClassesNotFoundException(int classId) {
        this.classId = classId;
    }

    public int getId() {
        return classId;
    }
}