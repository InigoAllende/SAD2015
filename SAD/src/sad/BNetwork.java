package sad;

import java.util.ArrayList;
import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.net.estimate.BMAEstimator;
import weka.classifiers.bayes.net.estimate.BayesNetEstimator;
import weka.classifiers.bayes.net.estimate.MultiNomialBMAEstimator;
import weka.classifiers.bayes.net.estimate.SimpleEstimator;
import weka.classifiers.bayes.net.search.SearchAlgorithm;
import weka.classifiers.bayes.net.search.ci.CISearchAlgorithm;
import weka.classifiers.bayes.net.search.fixed.NaiveBayes;
import weka.classifiers.bayes.net.search.global.HillClimber;
import weka.classifiers.bayes.net.search.local.K2;
import weka.classifiers.meta.FilteredClassifier;
import weka.core.Instances;
import weka.estimators.Estimator;

public class BNetwork {
	
private static BNetwork miBNetwork = new BNetwork();
	
	private BNetwork(){
		
	}
	
	public static BNetwork getBayesNet(){
		return miBNetwork;
	}
	
	public void evaluar(Instances train, Instances dev, boolean honesto) throws Exception
	{
		//Tiene 3 opciones importantes
		//estimator elige el algoritmo estimador, se pone con setEstimator(BayesNetEstimator)
		//searchAlgorithm elige el metodo de busqueda de red estructural (aprendizaje estructural), 
		//se pone con setSearchAlgorithm(SearchAlgorithm)
		//ADTree reduce el tiempo que tarda en hacer sus cosas pero consume mas memoria y puede dar problemas
		//se pone con setUseADTree(boolean)
		
		BayesNet classifier;
		BayesNet mejorClassifier = null;
		Evaluation evaluator;
		double mejorFM = 0.0;
		String mejorEst = "";
		String mejorSA = "";
		Evaluation mejorEvaluator = null;
		
		ArrayList<BayesNetEstimator> estimadores = new ArrayList<>();
		
		estimadores.add(new SimpleEstimator());
		//estimadores.add(new BMAEstimator());
		//estimadores.add(new MultiNomialBMAEstimator());
		//Al usar este me sale un error de incorrect estimator use subclass
		//estimadores.add(new BayesNetEstimator());
		
		ArrayList<String> lista = new ArrayList<>();
		lista.add("Simple Estimator");
		//lista.add("BMA Estimator");
		//lista.add("MultiNomialBMA Estimator");
		//lista.add("BayesNetEstimator");
		
		ArrayList<SearchAlgorithm> listSearch = new ArrayList<>();
		  listSearch.add(new K2());
		  listSearch.add(new NaiveBayes());
		  listSearch.add(new CISearchAlgorithm());
		  
		  ArrayList<String> listSearchNombres= new ArrayList<>();
		  listSearchNombres.add("K2");
		  listSearchNombres.add("NaiveBayes");
		  listSearchNombres.add("CISearchAlgortihm");
		
		Instances total= new Instances(train);

		for (int i = 0; i < dev.numInstances(); i++) {
			
			total.add(dev.instance(i));
			
		}
		//Por cara estimador que vayamos a usar
		for(int i = 0; i < estimadores.size(); i++)
		{
			classifier = new BayesNet();
			evaluator = new Evaluation(total);
			classifier.setEstimator(estimadores.get(i));
			
			for (int k=0;k<listSearch.size();k++) {
				
				classifier.setSearchAlgorithm(listSearch.get(k));	
			
			if (!honesto) {	
				classifier.buildClassifier(total);
				evaluator.evaluateModel(classifier, total);			
			}
			else {
				classifier.buildClassifier(total);
				evaluator.crossValidateModel(classifier, total, 2, new Random(1));
			}
			
			double j = evaluator.weightedFMeasure();
			if(j > mejorFM)
			{
				mejorFM = j;
				mejorEst = lista.get(i);
				mejorSA = listSearchNombres.get(k);
				mejorEvaluator = evaluator;
			}
			}
		}
		
		
		
		System.out.println("");
		if (!honesto) {
			System.out.println("Evaluación no Honesta");
		}
		
		else {
			System.out.println("Evaluación Honesta");		
		}
		System.out.println("---------------------");
		System.out.println("");
		System.out.println("La mejor F-measure es: "+mejorFM+" con el estimador: "+mejorEst+" y el algoritmo de búsqueda: "+mejorSA);
		System.out.println("");
		ImprimirDatos.getImprimirDatos().imprimir(mejorEvaluator);
		
	}

}
