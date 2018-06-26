package io.pivotal.pal.tracker;

import org.springframework.stereotype.Component;

import java.sql.Time;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class InMemoryTimeEntryRepository implements TimeEntryRepository {

    private Map<Long, TimeEntry> timeEntries = new HashMap<>();

    public TimeEntry create(final TimeEntry timeEntry) {
        Long nextId = timeEntries.size() + 1L;
        timeEntry.setId(nextId);
        timeEntries.put(nextId, timeEntry);
        return timeEntry;
    }

    public TimeEntry find(final long id) {

        return timeEntries.get(id);
    }

    public List<TimeEntry> list() {
        return new ArrayList<>(timeEntries.values());
    }

    public TimeEntry update(final long id, final TimeEntry timeEntry) {
        if (timeEntries.get(id) == null) {
            return null;
        }

        timeEntry.setId(id);
        timeEntries.put(id, timeEntry);
        return timeEntry;
    }

    public TimeEntry delete(final long id) {
        TimeEntry timeEntry = timeEntries.get(id);
        if (timeEntry != null) {
            timeEntries.remove(id);
        }
        return timeEntry;
    }
}
