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
		double mejorFM = 0.0;
		String mejorEst = null;
		
		
		SimpleEstimator sEst = new SimpleEstimator();
		MultiNomialBMAEstimator est = new MultiNomialBMAEstimator();
		//BMAEstimator est = new BMAEstimator();
		ArrayList<BayesNetEstimator> estimadores = new ArrayList<>();
		estimadores.add(new SimpleEstimator());
		estimadores.add(new BMAEstimator());
		estimadores.add(new MultiNomialBMAEstimator());
		//Al usar este me sale un error de incorrect estimator use subclass
		//estimadores.add(new BayesNetEstimator());
		
		ArrayList<String> lista = new ArrayList<>();
		lista.add("Simple Estimator");
		lista.add("BMA Estimator");
		lista.add("MultiNomialBMA Estimator");
		//Por cara estimador que vayamos a usar
		for(int i = 0; i < estimadores.size(); i++)
		{
			System.out.println(i);
			classifier = new BayesNet();
			evaluator = new Evaluation(data);
			classifier.setEstimator(estimadores.get(i));
			
			evaluator.crossValidateModel(classifier, data, 10, new Random(2));
			double j = evaluator.weightedFMeasure();
			if(j > mejorFM)
			{
				mejorFM = j;
				mejorEst = lista.get(i);
			}
		}
		
		
		est.setAlpha(0.5);
		//SearchAlgorithm as = new SearchAlgorithm();
		K2 search = new K2();
		//HillClimber search = new HillClimber();
		search.setMaxNrOfParents(1);
		
		//classifier.setSearchAlgorithm(search);
		//classifier.setUseADTree(false);
		
		System.out.println("El mejor estimador es: "+mejorEst+" con F-measure: "+mejorFM+".	");
	}
		//0.7038798522052109
		//0.7038798522052109
		//0.7038798522052109
		
}
