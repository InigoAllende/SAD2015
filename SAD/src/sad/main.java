package sad;

import weka.classifiers.functions.MultilayerPerceptron;
import weka.classifiers.rules.OneR;
import weka.core.Instances;


public class main {

	public static void main(String[] args) throws Exception {
		//Esto funciona con un fichero de train y otro de test
		//El uso del test todavia no está implementado
		
		if( args.length < 2 ){
			System.out.println("ERROR DE ARGUMENTOS");
			System.out.println("\n");
			System.out.println("El programa necesita 2 parámetros, la ruta del conjunto de entrenamiento y la ruta del conjunto de test. Recuerda que si no hay conjunto de entrenamiento tendras que poner el conjunto de test 2 veces.");
			System.out.println(" \n");
			System.out.println("Ejemplo:  java -jar DSSP3.jar [C:/weine_train.arff] [C:/weine_test.arff]");
			System.out.println(" \n");
			System.out.println(" \n");
			return;
		}
		// cargamos los ficheros del test y de entrenamiento
		System.out.println("Cargando los ficheros...");
		Instances train = CargarDatos.cargarDatos(args[0]);
		Instances test = CargarDatos.cargarDatos(args[1]);
		Instances train2 = train;
		System.out.println("\n");
		System.out.println("Ficheros cargados");
		System.out.println("\n");
		//preproceso, filtro normalize
		train=Preprocess.getPreprocesar().preprocess(train);
		//prepro con removeuseless y outliers....
		train2 = Preprocess.getPreprocesar().preprocess2(train2);
		//NBayes.getNBayes().honesto(train);
		BNetwork.getBayesNet().noHonesto(train);
		
		/*
		//buscamos el mejor clasificador para cada algoritmo
		OneR one = ScanParamsOneR.getScanParamsOneR().barrido(train);
		MultilayerPerceptron mp=ScanParamsAlgorithm.getScanParamsAlgorithm().getElMejor(train);
		
		//Imprimimos las figuras de mérito y la matriz de cofusión para el OneR
		System.out.println("OneR Optimista: \n");
		UpperBounds.getMiUpperBounds().imprimirDatos(one, train);
		System.out.println("OneR Realista: \n");
		RealisticBounds.getMiRealisticBounds().imprimirDatos(one, train);
		
		//Imprimimos las figuras de mérito y la matriz de cofusión para el MP
		
		System.out.println("MP Optimista: \n");
		UpperBounds.getMiUpperBounds().imprimirDatos(mp, train);
		System.out.println("MP Realista: \n");
		RealisticBounds.getMiRealisticBounds().imprimirDatos(mp, train);
		
	
		//Realizamos las predicciones
		System.out.println("OneR: \n");
		Predictions.getMiPredictions().predecir(train, test, one);
		System.out.println("\n\n\n");
		System.out.println("MP: \n");
		Predictions.getMiPredictions().predecir(train, test, mp);*/
	}	

}

