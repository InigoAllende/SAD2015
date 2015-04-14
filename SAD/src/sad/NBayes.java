package sad;

import java.util.Random;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.core.Instances;

public class NBayes {
	
private static NBayes miNBayes = new NBayes();
	
	private NBayes(){
		
	}
	
	public static NBayes getNBayes(){
		return miNBayes;
	}
	public void honesto(Instances data) throws Exception
	{
		NaiveBayes classifier;
		Evaluation evaluator;
		double fMea = 0;
		double mejor = 0;
		classifier = new NaiveBayes();
		evaluator = new Evaluation(data);
		evaluator.crossValidateModel(classifier, data, 10, new Random(1));
		mejor = evaluator.weightedFMeasure();
		
		//Solo puede haber 1 o ninguna opcion activada a la vez, por lo que al hacer set se desactivan
		//las que hubieran antes
		classifier.setUseKernelEstimator(true);
		Evaluation evaluator2 = new Evaluation(data);
		evaluator2.crossValidateModel(classifier, data, 10, new Random(1));
		
		fMea = evaluator2.weightedFMeasure();
		
		if(fMea>mejor)
		{
			mejor = fMea;
		}

		classifier.setUseSupervisedDiscretization(true);
		Evaluation evaluator3 = new Evaluation(data);
		evaluator3.crossValidateModel(classifier, data, 10, new Random(1));
		fMea = evaluator3.weightedFMeasure();

		if(fMea>mejor)
		{
			mejor = fMea;
		}
		//Hay dos opciones mas setdebug y setdisplay, pero creo que esas no afectan a este aspecto
		//Necesito mas informacion de todas formas
		System.out.println(mejor);
	}
	
	public void noHonesto(Instances data) throws Exception
	{
		//da una excepcion cuando hace el evaluatemodel, no se por que es
		NaiveBayes classifier;
		Evaluation evaluator;
		double fMea = 0;
		double mejor = 0;
		classifier = new NaiveBayes();
		evaluator = new Evaluation(data);
		evaluator.evaluateModel(classifier, data, (Object) null);
		mejor = evaluator.weightedFMeasure();
		
		//Solo puede haber 1 o ninguna opcion activada a la vez, por lo que al hacer set se desactivan
		//las que hubieran antes
		classifier.setUseKernelEstimator(true);
		Evaluation evaluator2 = new Evaluation(data);
		evaluator2.evaluateModel(classifier, data, (Object) null);
		
		fMea = evaluator2.weightedFMeasure();
		
		if(fMea>mejor)
		{
			mejor = fMea;
		}

		classifier.setUseSupervisedDiscretization(true);
		Evaluation evaluator3 = new Evaluation(data);
		evaluator3.evaluateModel(classifier, data, (Object) null);
		fMea = evaluator3.weightedFMeasure();

		if(fMea>mejor)
		{
			mejor = fMea;
		}
		//Hay dos opciones mas setdebug y setdisplay, pero creo que esas no afectan a este aspecto
		//Necesito mas informacion de todas formas
		System.out.println(mejor);
	}

}
