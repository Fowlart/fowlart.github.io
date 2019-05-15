package data_base;

import entities.WordTranslate;

public interface WordTranslateRepository {

    Iterable<WordTranslate> findAll();

    WordTranslate findById(Long id);

    WordTranslate save(WordTranslate word);

}