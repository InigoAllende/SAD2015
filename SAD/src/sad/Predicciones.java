package sad;

import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;


public class Predicciones {
	
	private static Predicciones miPredicciones = new Predicciones();

	private Predicciones() {


	}

	public static Predicciones getMiPredicciones() {
		return miPredicciones;
	}
	
	
	public void predecir(Instances train, Instances dev, Instances test, Classifier estimador) throws Exception {
		
		Instances total= new Instances(train);

		for (int i = 0; i < dev.numInstances(); i++) {
			
			total.add(dev.instance(i));
			
		}
		
		Evaluation evaluator = new Evaluation(total);
		double predictions[] = new double[test.numInstances()];
		//Guardamos por cada instancia del test la predicción en un array
		for (int i = 0; i < test.numInstances(); i++) {
			predictions[i] = evaluator.evaluateModelOnceAndRecordPrediction((Classifier)estimador, test.instance(i));
			
		}
		
		//Imprimimos por pantalla
		for (int i=0; i < test.numInstances(); i++) {
			
			System.out.println("Instancia:  "+i+"  Clasificada como:  "+predictions[i]);
		}
	}

}

