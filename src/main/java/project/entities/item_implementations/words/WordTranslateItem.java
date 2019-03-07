package project.entities.item_implementations.words;

import project.entities.Item;
import project.entities.ItemProperty;

import java.util.Arrays;


public class WordTranslateItem extends Item
{
	private String engword;
	private String ukrword;

	public WordTranslateItem(WordPropertie word1, WordPropertie word2)
	{
		if (word1.getType() == WordsProperieType.UKR)
		{
			ukrword = word1.getPropertieValue();
			engword = word2.getPropertieValue();
		}
		else
		{
            ukrword = word2.getPropertieValue();
            engword = word1.getPropertieValue();
		}


	}

    public String getEngword() {
        return engword;
    }

    public String getUkrword() {
        return ukrword;
    }
}
