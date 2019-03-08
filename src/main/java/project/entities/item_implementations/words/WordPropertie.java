package project.entities.item_implementations.words;

import project.entities.ItemProperty;


public class WordPropertie implements ItemProperty<String>
{

	private String word;
	private WordsProperieType type;

	public WordPropertie (String word) {
		setWord(word);
	}

	private void setWord(String word)
	{
		this.word = word.trim();
		if (this.word.matches("^[A-Za-z]+"))
			type = WordsProperieType.ENG;
		else if (this.word.matches("^[А-Яа-яёЁЇїІіЄєҐґ]+"))
			type = WordsProperieType.UKR;
		else
			type = WordsProperieType.UNDEFINED;
	}

	@Override
	public String getPropertieName()
	{
		return this.getClass().getName();
	}

	@Override
	public String getPropertieValue()
	{
		return word;
	}

	public WordsProperieType getType()
	{
		return type;
	}
}
