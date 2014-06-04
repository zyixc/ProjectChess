package projectchessserverv2.Request;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by zyixc on 4-6-2014.
 */
public class RequestResult<T> {
    Object result;
    String path;

    public RequestResult(T objecttype){
        this.result = objecttype;
    }
    public RequestResult(T[] objecttype){
        this.result = new ArrayList<T>(Arrays.asList(objecttype));
    }

    public Path getJSONPath(){
        ObjectMapper mapper = new ObjectMapper();
        String filepath = System.getProperty("user.dir") + File.separator + "JSON_files" + File.separator + result.toString();
        try {
            mapper.writeValue(new File(filepath), result);
        }catch(Exception e){
            e.printStackTrace();
        }
        return Paths.get(filepath);
    }

    public String getJSONString(){
        ObjectMapper mapper = new ObjectMapper();
        String filestring = null;
        try {
            filestring = mapper.writeValueAsString(result);
        }catch(Exception e){
            e.printStackTrace();
        }
        return filestring;
    }
}
