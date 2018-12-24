package TracteJSON;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class TracteJSON {

    public Object[] readJSON(String path){
        Gson gson = new GsonBuilder().create();
        Object[] usuaris = null;
        try{
            usuaris = gson.fromJson(new BufferedReader(new FileReader(path)),Object[].class);
        }catch (FileNotFoundException e){
            System.out.println("No s'ha llegit correctament el JSON");
        }
        return usuaris;
    }
}
