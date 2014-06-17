package projectchessserverv2.request;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by zyixc on 4-6-2014.
 */
public class RequestResult<T> {
    Object result;
    ObjectMapper mapper = new ObjectMapper();

    public RequestResult(T objecttype){
        this.result = objecttype;
    }

    public Path getJSONPath(){
        File file = new File(System.getProperty("user.dir") + File.separator + "JSON_files");
        if (!file.exists()) {
            if (file.mkdir()) {
                System.out.println("Directory is created!");
            } else {
                System.out.println("Failed to create directory!");
            }
        }else{
            System.out.println("Something is wrong");
        }
        String filepath = System.getProperty("user.dir") + File.separator + "JSON_files" + File.separator + result.hashCode()+".json";
        try {
            mapper.writeValue(new File(filepath), result);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Paths.get(filepath);
    }

    public String getJSONString(){
        String filestring = null;
        try {
            filestring = mapper.writeValueAsString(result);
        }catch(Exception e){
            e.printStackTrace();
        }
        return filestring;
    }
}
