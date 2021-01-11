import java.io.File;
import java.util.HashMap;
import java.util.Map;
import weka.classifiers.Classifier;
import weka.classifiers.Evaluation;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.classifiers.trees.ADTree;

public class ADTtree {
	public String TrainingPath; // path to training data
	public String TestPath; // path to test data
	
	public String TrainingPath1; // path to training data
	public String TestPath1; // path to test data

	public Instances TrainingData;
	public Instances TestingData;

	public Map<String, String> mappings = new HashMap<String, String>();
	public Map<Integer, Double> testscores = new HashMap<Integer, Double>();

	public ADTtree(String trainingPath, String testPath) {
		super();
		this.TrainingPath = trainingPath;
		this.TestPath = testPath;

	}

	public void loadData() throws Exception {
		ArffLoader loader = new ArffLoader();
		loader.setSource(new File(this.TrainingPath));
		Instances trainingSet = loader.getDataSet();
		trainingSet.setClassIndex(trainingSet.numAttributes() - 1);
		TrainingData = trainingSet;

		loader = new ArffLoader();
		loader.setSource(new File(this.TestPath));
		Instances testSet = loader.getDataSet();
		testSet.setClassIndex(testSet.numAttributes() - 1);
		TestingData = testSet;

	}

	public void adtTree() throws Exception {
		String[] option = new String[4];
		option[0] = "-B";
		option[1] = "20";
		option[2] = "-E";
		option[3] = "-3";
		
		ADTree a = new ADTree();
		a.setOptions(option);
		Classifier cls = a;
		cls.buildClassifier(TrainingData);

		Evaluation eval = new Evaluation(TrainingData);
		eval.evaluateModel(cls, TestingData);

		// Focus on evaluating performance on identifying label "1", i.e., isReq
		System.out.println("======ADTree======");
		System.out.println("Precision: " + eval.precision(1));
		System.out.println("Recall: " + eval.recall(1));
		System.out.println("F1: " + eval.fMeasure(1));

	}

	public static void main(String[] args) throws Exception {
		String TrainingPath = "./data/training_android.arff";
		String TestPath = "./data/test_android.arff";
		
		String TrainingPath1 = "./data/training_openstack.arff";
		String TestPath1 = "./data/test_openstack.arff";
			
		System.out.println("Android:");

		ADTtree adtree = new ADTtree(TrainingPath, TestPath);
		adtree.loadData();
		adtree.adtTree();
		
		System.out.println("\nOpenstack:");
		
		ADTtree adtree1 = new ADTtree(TrainingPath1, TestPath1);
		adtree1.loadData();
		adtree1.adtTree();
		

	}

}
