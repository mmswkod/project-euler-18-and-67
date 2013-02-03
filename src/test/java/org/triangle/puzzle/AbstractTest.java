package org.triangle.puzzle;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;

public class AbstractTest {
	
	protected File getFromPath(String path) throws Exception{
		assert path != null && path.length() > 0;
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        URL url = classLoader.getResource(path);
        File datasource = new File(url.toURI());
        if(datasource == null || !datasource.isFile()){
        	throw new FileNotFoundException("Datasource file not found. No way to process rows..");
        }
        
        return datasource;
	}

}
