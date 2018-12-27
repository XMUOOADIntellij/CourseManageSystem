package com.group12.course.entity.application;

import java.io.Serializable;

/**
 * application 类
 * 所有的申请都继承自它
 *
 * @author Xu Gang
 * @date 2018年12月26日
 */
public class Application implements Serializable {

    private Long id;

    /**
     * 申请的状态有三种
     * 0为不合法
     * 1为合法
     * 2为审核中
     * */
    private Integer status;

    public Application() {
    }

    public Application(Integer status) {
        this.status = status;
    }

    public Application(Long id, Integer status) {
        this.id = id;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Application{" +
                "id=" + id +
                ", status=" + status +
                '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
