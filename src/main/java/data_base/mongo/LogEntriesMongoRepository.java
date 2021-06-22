package data_base.mongo;

import entities.LogEntry;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface LogEntriesMongoRepository extends MongoRepository<LogEntry, Date> {
}
