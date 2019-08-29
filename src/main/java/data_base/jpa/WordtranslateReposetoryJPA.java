package data_base.jpa;

import entities.WordTranslate;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WordtranslateReposetoryJPA extends CrudRepository<WordTranslate, Long> {

}
