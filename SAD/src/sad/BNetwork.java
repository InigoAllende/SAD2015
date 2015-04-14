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
	
	public void noHonesto(Instances data) throws Exception
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

		classifier = new BayesNet();
		evaluator = new Evaluation(data);
		evaluator.crossValidateModel(classifier, data, 10, new Random(1));
		classifier.setEstimator(new MultiNomialBMAEstimator());
		classifier.getEstimator().setAlpha(0.3);
		//SearchAlgorithm as = new SearchAlgorithm();
		HillClimber est = new HillClimber();
		//est.setMaxNrOfParents(1);
		classifier.setSearchAlgorithm(est);
		classifier.setUseADTree(true);
		double j = evaluator.weightedFMeasure();
		System.out.println(j);
	}
		//0.7038798522052109
		//0.7038798522052109
		//0.7038798522052109
		
}
