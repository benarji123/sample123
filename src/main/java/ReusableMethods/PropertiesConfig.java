package ReusableMethods;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class PropertiesConfig {
	private static String path=System.getProperty("user.dir")+"/src/test/resources/info.properties";
	public static Properties p;
	public static void loadPropertyFile() throws FileNotFoundException, IOException {
		p=new Properties();
		p.load(new FileInputStream(path));
		
		
	}
	

}
