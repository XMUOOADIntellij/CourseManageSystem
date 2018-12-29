package com.group12.course.dao;

import com.group12.course.entity.Klass;
import com.group12.course.entity.KlassRound;
import com.group12.course.mapper.KlassRoundMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author Tan Xue
 */
@Component
public class KlassRoundDao {

    @Autowired
    KlassRoundMapper klassRoundMapper;

    public KlassRound getKlassRound(Long id){
        return klassRoundMapper.selectKlassRoundById(id);
    }

    public int deleteKlassRound(Long id){
        return klassRoundMapper.deleteKlassRound(id);
    }

    public int addKlassRound(KlassRound klassRound){
        return klassRoundMapper.addKlassRound(klassRound);
    }

    public int updateKlassRound(KlassRound klass){
        return klassRoundMapper.updateKlassRound(klass);
    }

    public KlassRound getKlassRoundByKlassIdAndRoundId(Long klassId,Long roundId){
        return klassRoundMapper.selectKlassRoundByKlassIdAndRoundId(klassId,roundId);
    }

}
