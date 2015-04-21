package sad;

import weka.attributeSelection.BestFirst; 
import weka.attributeSelection.CfsSubsetEval; 
import weka.core.Instances; 
import weka.filters.Filter; 
import weka.filters.supervised.attribute.AttributeSelection;
import weka.filters.unsupervised.attribute.InterquartileRange;
import weka.filters.unsupervised.attribute.RemoveUseless;
import weka.filters.unsupervised.attribute.StringToWordVector;
import weka.filters.unsupervised.instance.Normalize;


public class Preprocess {
	private static Preprocess miPreprocesar = new Preprocess();
	
	private Preprocess(){
		
	}
	public static Preprocess getPreprocesar(){
		return miPreprocesar;
	}
	
	public Instances preprocess(Instances data) throws Exception{
		
		Normalize filter= new Normalize();
		filter.setInputFormat(data);
		Instances newData = Filter.useFilter(data, filter);
		return newData;
	}
	
	public Instances preprocess2(Instances data) throws Exception{
		//En este preprocesado se aplican filtros que eliminan atributos
		//si se usa a la hora de clasificar las instancias de test hay que usar FilteredClassifier
		//RemoveUseless filter = new RemoveUseless();
		StringToWordVector filter = new StringToWordVector();
		filter.setInputFormat(data);
		Instances newData = Filter.useFilter(data, filter);
		return newData;
	}
}

