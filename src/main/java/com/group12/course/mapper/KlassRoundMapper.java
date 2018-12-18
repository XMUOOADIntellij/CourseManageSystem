package com.group12.course.mapper;

import com.group12.course.entity.KlassRound;
import com.group12.course.entity.Round;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

/**
 * KlassRound Mapper层
 * @author Tan Xue
 * @date 2018/12/17
 */
@Mapper
@Component
public interface KlassRoundMapper {

    int deleteKlassRound(Long id);

    int addKlassRound(KlassRound record);

    KlassRound selectKlassRoundById(Long id);

    int updateKlassRound(KlassRound record);

}
