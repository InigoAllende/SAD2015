package sad;

import java.util.Enumeration;

import weka.core.Attribute;
import weka.core.Instance;
import weka.core.Instances;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Discretize;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
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
	
	public Instances superPreProcess(Instances data) throws Exception {
		
		 	Discretize m_DiscretizeFilter = null;
		    ReplaceMissingValues m_MissingValuesFilter = null;

		    boolean bHasNonNominal = false;
		    boolean bHasMissingValues = false;

		    Enumeration enu = data.enumerateAttributes();            
		    while (enu.hasMoreElements()) {
				      Attribute attribute = (Attribute) enu.nextElement();
				      if (attribute.type() != Attribute.NOMINAL) {
				     int m_nNonDiscreteAttribute = attribute.index();
				      bHasNonNominal = true;
			      //throw new UnsupportedAttributeTypeException("BayesNet handles nominal variables only. Non-nominal variable in dataset detected.");
			      }
			      Enumeration enum2 = data.enumerateInstances();
				      while (enum2.hasMoreElements()) {
					      if (((Instance) enum2.nextElement()).isMissing(attribute)) {
					        bHasMissingValues = true;
					        // throw new NoSupportForMissingValuesException("BayesNet: no missing values, please.");
					      }	
				      }
		    }

		    if (bHasNonNominal) {
		      System.err.println("Warning: discretizing data set");
		      m_DiscretizeFilter = new Discretize();
		      m_DiscretizeFilter.setInputFormat(data);
		      data = Filter.useFilter(data, m_DiscretizeFilter);
		    }

		    /*if (bHasMissingValues) {
		      System.err.println("Warning: filling in missing values in data set");
		      m_MissingValuesFilter = new ReplaceMissingValues();
		      m_MissingValuesFilter.setInputFormat(data);
		      data = Filter.useFilter(data, m_MissingValuesFilter);
		    }*/
		    return data;
		  } // normalizeDataSet
		
		
		
	}


