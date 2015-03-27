package sad;

import weka.attributeSelection.BestFirst; 
import weka.attributeSelection.CfsSubsetEval; 
import weka.core.Instances; 
import weka.filters.Filter; 
import weka.filters.supervised.attribute.AttributeSelection;
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
}

