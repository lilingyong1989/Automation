package e2e.testData;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {

	public List<HashMap<Object,Object>> getJsonDataToMap() throws IOException {
		 String jsonContent = FileUtils.readFileToString(
	                new File(System.getProperty("user.dir") + "/src/test/java/e2e/testData/orderTestData.json"),
	                StandardCharsets.UTF_8
	        );
		//string to hashmap using jackson databind
		ObjectMapper mapper=new ObjectMapper();
		List<HashMap<Object,Object>> data =mapper.readValue(jsonContent,new TypeReference<List<HashMap<Object,Object>>>(){});

		return data;
	}
}
