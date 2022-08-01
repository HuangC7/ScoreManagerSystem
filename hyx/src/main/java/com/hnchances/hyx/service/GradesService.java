package com.hnchances.hyx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hnchances.hyx.entity.Grades;
import com.hnchances.hyx.entity.User;

/**
 * (Grades)表服务接口
 *
 */
public interface GradesService extends IService<Grades> {
    /**
     * 删除成绩信息(逻辑删除)
     *
     * @param grades
     * @return int
     */
    int deleteGradesById(Grades grades);
}

