import org.junit.Test;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertTrue;


public class SpringConfigFinder
{

	private String PATH_TO_FILE = "dic_mover-spring.xml";

	private File created_spring_config;

	private String init_congig = "<beans xmlns=\"http://www.springframework.org/schema/beans\"\n" +
			"               xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
			"               xmlns:integration=\"http://www.springframework.org/schema/integration\"\n" +
			"               xmlns:file=\"http://www.springframework.org/schema/integration/file\"\n" +
			"               xmlns:p=\"http://www.springframework.org/schema/p\"\n" +
			"               xsi:schemaLocation=\"http://www.springframework.org/schema/beans\n" +
			"        http://www.springframework.org/schema/beans/spring-beans.xsd\n" +
			"        http://www.springframework.org/schema/integration\n" +
			"        http://www.springframework.org/schema/integration/spring-integration.xsd\n" +
			"        http://www.springframework.org/schema/integration/file\n" +
			"        http://www.springframework.org/schema/integration/file/spring-integration-file.xsd\n" +
			"        http://www.springframework.org/schema/context\n" +
			"        http://www.springframework.org/schema/context/spring-context.xsd\">\n" +
			"\n" +
			"<bean id=\"handler\" class=\"spring_utils.Handler\"/>\n" +
			"\n" +
			"<file:inbound-channel-adapter id=\"filesIn\" directory=\"dictionaries/import\">\n" +
			"    <integration:poller id=\"poller\" fixed-delay=\"5000\"/>\n" +
			"</file:inbound-channel-adapter>\n" +
			"\n" +
			"<integration:service-activator input-channel=\"filesIn\" output-channel=\"filesOut\" ref=\"handler\" method=\"handleFile\"/>\n" +
			"\n" +
			"<file:outbound-channel-adapter id=\"filesOut\" directory=\"dictionaries/was_imported\" delete-source-files=\"true\"/>\n" +
			"\n" +
			"</beans>";


	public void testExist()
	{
		File configuration = new File(PATH_TO_FILE);
		if (!configuration.exists())
		{
			try
			{
				configuration.createNewFile();
				System.out.println(">created: " + configuration.getAbsolutePath());
				FileWriter fileWriter = new FileWriter(configuration);
				fileWriter.append(this.init_congig);
				fileWriter.flush();
				fileWriter.close();
				System.out.println(">initial configuration was written into " + configuration.getAbsolutePath());
				created_spring_config = configuration;
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		assertTrue(configuration.exists());
		assertTrue(configuration.isFile());
	}


	public void contexTest()
	{
		created_spring_config = new File(PATH_TO_FILE);

		if (created_spring_config.exists())
		{
			Thread thread = new Thread(()->new FileSystemXmlApplicationContext(this.created_spring_config.getAbsolutePath()));
			thread.setDaemon(true);
			thread.run();
			try
			{
				System.out.println(">begin process");
				TimeUnit.SECONDS.sleep(5);
				System.out.println(">end process");
			}
			catch (InterruptedException e)
			{
				e.printStackTrace();
			}
		}
		else
			System.out.println(">configuration file was not created");

	}
}
