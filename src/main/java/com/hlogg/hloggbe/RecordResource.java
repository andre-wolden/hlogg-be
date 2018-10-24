package com.hlogg.hloggbe;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;

@RestController
public class RecordResource {

    @CrossOrigin
    @RequestMapping(value = "/records/all", method = RequestMethod.GET)
    public ArrayList<Record> getAllRecords(){

        RecordRepo recordRepo = new RecordRepo();

        return recordRepo.getAllRecords();
    }

    @RequestMapping(value = "/records/{record_id}", method = RequestMethod.GET)
    public Record getRecordById(@PathVariable int record_id){

        Record record = new Record();
        record.setId(record_id);
        record.setDate(LocalDate.now());

        return record;
    }

    @RequestMapping(value = "/records/new", method = RequestMethod.POST)
    public Record saveNewRecord(@RequestBody JsonRecord jsonRecord){

        ActivityRepo activityRepo = new ActivityRepo();
        Activity activity = activityRepo.getActivityById(jsonRecord.getActivity_id());

        Record record = new Record();
        record.setActivity(activity);
        record.setDate(LocalDate.now());

        RecordRepo recordRepo = new RecordRepo();
        Record savedRecord = recordRepo.saveNewRecord(record);


        return savedRecord;
    }


}
