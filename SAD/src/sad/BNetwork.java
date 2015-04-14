package sad;

import java.util.ArrayList;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.BayesNet;
import weka.classifiers.bayes.net.estimate.BMAEstimator;
import weka.classifiers.bayes.net.estimate.BayesNetEstimator;
import weka.classifiers.bayes.net.estimate.MultiNomialBMAEstimator;
import weka.classifiers.bayes.net.estimate.SimpleEstimator;
import weka.classifiers.bayes.net.search.SearchAlgorithm;
import weka.core.Instances;
import weka.estimators.Estimator;

public class BNetwork {
	
private static BNetwork miBNetwork = new BNetwork();
	
	private BNetwork(){
		
	}
	
	public static BNetwork getBayesNet(){
		return miBNetwork;
	}
	
	public void noHonesto(Instances data)
	{
		//Tiene 3 opciones importantes
		//estimator elige el algoritmo estimador, se pone con setEstimator(BayesNetEstimator)
		//searchAlgorithm elige el metodo de busqueda de red estructural (aprendizaje estructural), 
		//se pone con setSearchAlgorithm(SearchAlgorithm)
		//ADTree reduce el tiempo que tarda en hacer sus cosas pero consume mas memoria y puede dar problemas
		//se pone con setUseADTree(boolean)
		ArrayList<Object> estimators = new ArrayList<>();
		
		BayesNet classifier;
		Evaluation evaluator;
		/*
		estimators.add(new SimpleEstimator());
		estimators.add(new BayesNetEstimator());	
		estimators.add(new BMAEstimator());
		estimators.add(new MultiNomialBMAEstimator());
		*/
		classifier = new BayesNet();
		classifier.setEstimator(new BayesNetEstimator());
		SearchAlgorithm as = new SearchAlgorithm();

		classifier.setSearchAlgorithm(new SearchAlgorithm());

	}

}
