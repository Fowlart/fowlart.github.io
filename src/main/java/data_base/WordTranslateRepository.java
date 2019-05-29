package data_base;

import entities.WordTranslate;

public interface WordTranslateRepository {

    Iterable<WordTranslate> findAll();

    WordTranslate findById(Long id);

    Long save(WordTranslate word);

}