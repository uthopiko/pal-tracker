package io.pivotal.pal.tracker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TimeEntryController {

    private TimeEntryRepository timeEntryRepository;

    @Autowired
    public TimeEntryController(@Qualifier("timeEntryRepository") final TimeEntryRepository timeEntryRepository) {
        this.timeEntryRepository = timeEntryRepository;
    }

    @PostMapping("/time-entries")
    public ResponseEntity<TimeEntry> create(final TimeEntry timeEntry) {
        TimeEntry timeEntryCreated = timeEntryRepository.create(timeEntry);
        return new ResponseEntity<>(timeEntryCreated, HttpStatus.CREATED);
    }

    public ResponseEntity<TimeEntry> read(final long id) {
        TimeEntry timeEntry = timeEntryRepository.find(id);

        if (timeEntry == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(timeEntry, HttpStatus.OK);
    }

    public ResponseEntity<List<TimeEntry>> list() {
        List<TimeEntry> list = timeEntryRepository.list();

        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    public ResponseEntity<TimeEntry> update(final long id, final TimeEntry timeEntry) {
        TimeEntry timeEntryUpdated = timeEntryRepository.update(id, timeEntry);
        if (timeEntryUpdated == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(timeEntryUpdated, HttpStatus.OK);
    }

    public ResponseEntity<TimeEntry> delete(final long id) {
        TimeEntry timeEntryDeleted = timeEntryRepository.delete(id);

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);    }
}
