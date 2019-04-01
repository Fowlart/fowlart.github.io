import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.assertTrue;


public class SpringConfigFinder
{

	private String PATH_TO_FILE = "dic_mover-spring.xml";

	@Test
	public void testExist()
	{
		File configuration = new File(PATH_TO_FILE);
		if (!configuration.exists())
		{
			try
			{
				configuration.createNewFile();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		assertTrue(configuration.exists());
		assertTrue(configuration.isFile());
	}

	@Test
	public void contexTest()
	{
		ApplicationContext context = new ClassPathXmlApplicationContext(PATH_TO_FILE);
	}

}
