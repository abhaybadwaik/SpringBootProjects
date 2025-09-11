package com.exampleObjectMapper.ObjectMapperPractice;

import ch.qos.logback.core.model.INamedModel;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.IOException;

@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)

class Eidiko{
	int id;
	String name;
	String password;
	int Salary;
}
@Slf4j
@SpringBootApplication
public class ObjectMapperPracticeApplication {

    public static void main(String[] args) throws Exception {
		SpringApplication.run(ObjectMapperPracticeApplication.class, args);
		ObjectMapper objectMapper = new ObjectMapper();
		Eidiko obj1 = new Eidiko(1,"Abhay","Abhay@123",32000);
		String result = objectMapper.writeValueAsString(obj1);
		log.info("Json String is "+result);

		ObjectMapperPracticeApplication obj = new ObjectMapperPracticeApplication();
		obj.jsontoJava();
		obj.javatoJson();
		obj.javaToFile();
		obj.fileToJava();
		obj.eidikoToDto();
		obj.checkJson();

	}
	public void javatoJson() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		Eidiko eidi = new Eidiko(5,"raj","errr2e",78878);
		String java = objectMapper.writeValueAsString(eidi);
		log.info("java to json h bro"+ java);
	}
	public void jsontoJava() throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		String json ="{\"id\":1,\"name\":\"Abhay\",\"password\":\"Abhay@123\",\"salary\":32000}";
		Eidiko eidiko = objectMapper.readValue(json,Eidiko.class);
		log.info("json to java it is " + eidiko);

	}
	public void javaToFile() throws IOException {
		ObjectMapper objectMapper=new ObjectMapper();
		Eidiko eidiko=new Eidiko(1,"Pratik","sarasu10",20200);
		File file=new File("C:\\Users\\ABHAY\\Downloads\\ObjectMapperPractice\\ObjectMapperPractice\\src\\main\\resources\\file1.txt");
		objectMapper.writeValue(file,eidiko);
	}

	public void fileToJava() throws IOException {
		ObjectMapper mapper = new ObjectMapper();
		String file = "C:\\Users\\ABHAY\\Downloads\\ObjectMapperPractice\\ObjectMapperPractice\\src\\main\\resources\\file1.txt";
		Eidiko eidiko = mapper.readValue(new File(file), Eidiko.class);
		System.out.println(eidiko);
	}

	public void eidikoToDto(){
		ObjectMapper mapper = new ObjectMapper();
		Eidiko eidi = new Eidiko(786,"Nothing","random",879564);
//		mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
		Dto dt = mapper.convertValue(eidi, Dto.class);
		System.out.println(dt);
	}
	public void checkJson() throws JsonProcessingException {
		String json = "{\"id\":1,\"address\":\"Hyderabad\",\"vehicle\":{\"color\":\"red\",\"name\":\"Honda\",\"vehicleType\":{\"vehicleType\":\"Two\"}}}";
		ObjectMapper mapper = new ObjectMapper();
		JsonNode jsonNode = mapper.readTree(json);
		log.info("this is how jsonnode look like" + jsonNode);
		String id = jsonNode.get("id").asText();
		log.info(id);
		JsonNode vehicleType = jsonNode.get("vehicle").get("color");
		JsonNode vehicle = jsonNode.get("vehicle");
		System.out.println(vehicleType);
		System.out.println(vehicle);

		((ObjectNode) vehicle).put("Engine","Bs5");
		System.out.println(vehicle);

	}
}
