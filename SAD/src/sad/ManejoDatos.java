package sad;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import weka.core.Instances;
 
public class ManejoDatos {
     
    private static ManejoDatos miManejoDatos = new ManejoDatos();
     private String ruta = "";
     
    private ManejoDatos()
    {
         
    }
     
    public static ManejoDatos getManejoDatos()
    {
        return miManejoDatos;
    }
    
    public void setRuta(String pRuta)
    {
    	this.ruta = pRuta;
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
    
    public void guardarEnFichero(Instances data) {

		
		//String ruta = "C:/Users/Borja/Desktop/prueba1.arff";
		File f = new File(this.ruta);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.write(data.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
    
public void guardarEnFichero2(String data) {

		
		//String ruta = "C:/Users/Admin/Desktop/prueba1.txt";
		File f = new File(this.ruta);
		try {
			BufferedWriter writer = new BufferedWriter(new FileWriter(f));
			writer.write(data);
			writer.flush();
			writer.close();
			System.out.println("Fichero guardado");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
 
}

