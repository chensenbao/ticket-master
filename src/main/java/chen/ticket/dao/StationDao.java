package chen.ticket.dao;

import chen.ticket.entity.StationEntity;
import org.springframework.stereotype.Repository;

@Repository
public interface StationDao {

    void delete();

    void insert(StationEntity stationEntity);
}
