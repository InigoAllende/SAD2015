package sad;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.Randomize;
 
public class CargarDatos {
     
    private static CargarDatos miCargaDatos = new CargarDatos();
     
    private CargarDatos()
    {
         
    }
     
    public static CargarDatos getCargaDatos()
    {
        return miCargaDatos;
    }
 
    public static Instances cargarDatos(String path) throws Exception
    {
        
         //leemos el fichero
        FileReader fi=null;
        try {
            fi= new FileReader(path);
        } catch (FileNotFoundException e) {
            System.out.println("ERROR: Revisar path del fichero de datos: "+path);
            System.exit(1);
        }
        //cargamos las instancias del fichero
        Instances data=null;
        try {
            data = new Instances(fi);
        } catch (IOException e) {
            System.out.println("ERROR: Revisar contenido del fichero de datos: "+path);
            System.exit(1);
        }
        
        try {
            fi.close();
        } catch (IOException e) {
           
        }
        
            randomize(data);
 
       
            setClass(data);
             
            return data;
    }
     

    private static void setClass(Instances data) {
        data.setClassIndex(data.numAttributes()-1);
       
    }
    
    private static void randomize(Instances data) throws Exception {
        Random rand = new Random(42);
        data.randomize(rand);
    }
 
}

