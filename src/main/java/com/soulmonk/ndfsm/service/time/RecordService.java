package com.soulmonk.ndfsm.service.time;

import com.soulmonk.ndfsm.domain.time.Record;

import java.util.List;

/**
 * Company: PolecatSoft
 * User: soulmonk
 * Date: 05.06.14
 * Time: 9:22
 */
public interface RecordService {
    public List<Record> findAll();

    public Record findById(Long id);

    public Record save(Record record);

    public void delete(Long id);
}
