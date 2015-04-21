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
	
	
	public void evaluar(Instances train, Instances dev, Boolean honesto) throws Exception
	
	{
		
		NaiveBayes classifier;
		Evaluation evaluator;
		double fMea = 0;
		double mejor = 0;
		String opciones = "";
		String mejorOpciones = "";
		Instances total= new Instances(train);
		// Instances total = Instances.mergeInstances(train, dev);
		
		for (int i = 0; i < dev.numInstances(); i++) {
			
			total.add(dev.instance(i));
			
		}
		
		System.out.println("Evaluando..... Espere Porfavor");
		
		String prueba = "2";

		for (int i = 0; i < 3; i++) {

			classifier = new NaiveBayes();
			evaluator = new Evaluation(train);

			// Solo puede haber 1 o ninguna opcion activada a la vez, por lo que
			// al hacer set se desactivan
			// las que hubieran antes

			if (i == 0) {

				opciones = "SetUseKernelEstimator: false, SetUseSupervisedDiscretization: false";

			} else if (i == 1) {

				classifier.setUseKernelEstimator(true);
				opciones = "SetUseKernelEstimator: true, SetUseSupervisedDiscretization: false";

			} else {

				classifier.setUseSupervisedDiscretization(true);
				opciones = "SetUseKernelEstimator: false, SetUseSupervisedDiscretization: true";

			}

			// Hay dos opciones mas setdebug y setdisplay, pero creo que esas no
			// afectan a este aspecto
			// Necesito mas informacion de todas formas

			if (!honesto) {
				
				classifier.buildClassifier(total);
				evaluator.evaluateModel(classifier, total);
				
			}

			else {

				//Con Hold Out nos da mejor rendimiento que con no honesto :S:S:S:S
				
				//classifier.buildClassifier(train);
				//evaluator.evaluateModel(classifier, dev);

				//Asi que hemos decidido usar cross
				evaluator.crossValidateModel(classifier, total, 10,
						new Random(3));
			}

			fMea = evaluator.weightedFMeasure();
			//System.out.println("F-Measure: " + fMea + " Opciones: " + opciones);

			if (fMea > mejor) {

				mejor = fMea;
				mejorOpciones = opciones;
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
		System.out.println("La mejor F-Measure es: "+mejor+" con las siguientes opciones:");
		System.out.println(mejorOpciones);
		System.out.println("");
	
	}

}
