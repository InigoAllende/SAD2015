package sad;

import weka.classifiers.bayes.BayesNet;
import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.rules.OneR;
import weka.core.Instances;


public class main {

	public static void main(String[] args) throws Exception {
		//Esto funciona con un fichero de train y otro de test
		//El uso del test todavia no está implementado
		
		if( args.length < 4 ){
			System.out.println("ERROR DE ARGUMENTOS");
			System.out.println("\n");
			System.out.println("El programa necesita 4 parámetros, la ruta de los conjuntos de entrenamiento, la ruta del conjunto de test y el destino de las predicciones. Recuerda que si no hay conjunto de entrenamiento tendras que poner el conjunto de test 2 veces.");
			System.out.println(" \n");
			System.out.println("Ejemplo:  java -jar DSSP3.jar [C:/weine_train.arff] [C:/weine_dev.arff] [C:/weine_test.arff] [C:/resultados.txt]");
			System.out.println(" \n");
			System.out.println(" \n");
			return;
		}
		// cargamos los ficheros del test y de entrenamiento
		System.out.println("Cargando los ficheros...");
		
		Instances train = ManejoDatos.cargarDatos(args[0]);
		Instances dev = ManejoDatos.cargarDatos(args[1]);
		Instances test = ManejoDatos.cargarDatos(args[2]);
		ManejoDatos.getManejoDatos().setRuta(args[3]);
		
		System.out.println("Ficheros cargados");
		System.out.println("");
		//preproceso, filtro normalize
		train=Preprocess.getPreprocesar().preprocess(train);
		dev =Preprocess.getPreprocesar().preprocess(dev);
		train=Preprocess.getPreprocesar().preprocess2(train);
		dev =Preprocess.getPreprocesar().preprocess2(dev);
		train=Preprocess.getPreprocesar().superPreProcess(train);
		dev =Preprocess.getPreprocesar().superPreProcess(dev);
		test = Preprocess.getPreprocesar().superPreProcess(test);
		
		//prepro con removeuseless y outliers....

		//Baseline
		NBayes.getNBayes().evaluar(train, dev, false);
		NBayes.getNBayes().evaluar(train, dev, true);

		//BayesNet
		BNetwork.getBayesNet().evaluar(train, dev, false);
		BNetwork.getBayesNet().evaluar(train, dev, true);
		
		Predicciones.getMiPredicciones().predecir(train, dev, test);	
		
		
	}	

}

