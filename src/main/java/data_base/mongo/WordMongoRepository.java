package data_base.mongo;

import entities.Sentence;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WordMongoRepository extends MongoRepository<Sentence, String> {
    @Query("{'engword': ?0}")
    Optional<Sentence> getWordByEngWord(String engWord);

    @Query("{ 'users': { $in: [?0] } }")
    List<Sentence> getWordsByUser(String userEmail);
}
