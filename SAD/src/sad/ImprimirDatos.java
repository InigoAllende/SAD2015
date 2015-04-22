package sad;

import weka.classifiers.Evaluation;

public class ImprimirDatos {
	
	private static ImprimirDatos miImprimirDatos = new ImprimirDatos();
	
	private ImprimirDatos(){
		
	}
	
	public static ImprimirDatos getImprimirDatos(){
		return miImprimirDatos;
	}
	
	public void imprimir(Evaluation eval) throws Exception
	{
		double acc=eval.pctCorrect();
		double inc=eval.pctIncorrect();
		double kappa=eval.kappa();
		double mae=eval.meanAbsoluteError();    
		double rmse=eval.rootMeanSquaredError();
		double rae=eval.relativeAbsoluteError();
		double rrse=eval.rootRelativeSquaredError();
		double confMatrix[][]= eval.confusionMatrix();
		double auRoc=eval.areaUnderROC(0);
		double recall = eval.recall(0);
		
		System.out.println("Correctly Classified Instances  " + acc);
		System.out.println("Incorrectly Classified Instances  " + inc);
		System.out.println("Kappa statistic  " + kappa);
		System.out.println("Mean absolute error  " + mae);
		System.out.println("Root mean squared error  " + rmse);
		System.out.println("Relative absolute error  " + rae);
		System.out.println("Root relative squared error  " + rrse);
		System.out.println("Area under the ROC:  " + auRoc);
		System.out.println("Recall: " + recall);
		System.out.println("Confusion Matrix: ");
		System.out.println("\n");
		
		for (int i= 0; i < confMatrix.length; i++ ) {
			
			for (int j=0; j < confMatrix.length;j++) {
				
				System.out.print(confMatrix[i][j]);
				System.out.print("	");
			}
			System.out.println("\n");
			
		}
	}

}
