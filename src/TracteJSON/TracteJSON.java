package TracteJSON;

import Element.Servidor;
import Element.Usuari;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;


public class TracteJSON {

    public Servidor[] readJSONServers(String path){
        Gson gson = new GsonBuilder().create();
        Servidor[] servidors = null;
        try{
            servidors = gson.fromJson(new BufferedReader(new FileReader(path)),Servidor[].class);
        }catch (FileNotFoundException e){
            System.out.println("No s'ha llegit correctament el JSON");
        }
        return servidors;
    }

    public Usuari[] readJSONUsers(String path){
        Gson gson = new GsonBuilder().create();
        Usuari[] usuaris = null;
        try{
            usuaris = gson.fromJson(new BufferedReader(new FileReader(path)),Usuari[].class);
        }catch (FileNotFoundException e){
            System.out.println("No s'ha llegit correctament el JSON");
        }
        return usuaris;
    }

}
