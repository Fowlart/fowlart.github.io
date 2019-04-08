package launch;

import project.entities.item_implementations.words.WordPropertie;
import project.entities.item_implementations.words.WordTranslate;
import project.io_data_module.CsvWordsWriter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class OnServerFileCreator
{

	private String PATH_TO_SPRING_FILE = "dic_mover-spring.xml";
	private String PATH_TO_FILE_DB = "db.csv";

	public String getPATH_TO_SPRING_FILE()
	{
		return PATH_TO_SPRING_FILE;
	}

	public String getPATH_TO_FILE_DB()
	{
		return PATH_TO_FILE_DB;
	}

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
			"<integration:service-activator input-channel=\"filesIn\" output-channel=\"filesOut\" ref=\"handler\" method=\"handleFile\"/>\n"
			+
			"\n" +
			"<file:outbound-channel-adapter id=\"filesOut\" directory=\"dictionaries/was_imported\" delete-source-files=\"true\"/>\n"
			+
			"\n" +
			"</beans>";


	public void createSpringConfig()
	{
		File configuration = new File(PATH_TO_SPRING_FILE);
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
	}

	public void createFileDB()
	{
		File fileDB = new File(PATH_TO_FILE_DB);
		if (!fileDB.exists())
		{
			try
			{
				fileDB.createNewFile();
				System.out.println(">created: " + fileDB.getAbsolutePath());
				WordTranslate a = new WordTranslate(new WordPropertie("test"), new WordPropertie("тест"), 0);
				WordTranslate b = new WordTranslate(new WordPropertie("dog"), new WordPropertie("собака"), 0);
				WordTranslate c = new WordTranslate(new WordPropertie("cat"), new WordPropertie("кіт"), 0);
				List<WordTranslate> list = new ArrayList<>();
				list.addAll(Arrays.asList(a, b, c));
				CsvWordsWriter writer = new CsvWordsWriter();
				writer.writeInFile(PATH_TO_FILE_DB, list);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}




}
