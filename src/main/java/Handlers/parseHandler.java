package Handlers;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.ling.CoreLabel;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.util.CoreMap;

import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.ArrayList;

public class parseHandler{

    public static ArrayList<String> getNames(String text){
        Properties props = new Properties();
        props.setProperty("annotators", "tokenize, ssplit, pos, lemma, ner");
        StanfordCoreNLP pipeline = new StanfordCoreNLP(props);

        // Create an Annotation with the text
        Annotation annotation = new Annotation(text);

        // Annotate the text
        pipeline.annotate(annotation);

        ArrayList<String> names = new ArrayList<>();
        // Iterate through the annotated sentences and find people's names
        List<CoreMap> sentences = annotation.get(CoreAnnotations.SentencesAnnotation.class);
        for (CoreMap sentence : sentences) {
            for (CoreLabel token : sentence.get(CoreAnnotations.TokensAnnotation.class)) {
                String namedEntity = token.get(CoreAnnotations.NamedEntityTagAnnotation.class);
                if (namedEntity.equals("PERSON")) {
                    String name = token.originalText();
                    boolean exists = false;
                    for(String existingName : names){
                        if (name.equals(existingName)) {
                            exists = true;
                            break;
                        }
                    }
                    if(!exists){
                        String[] array = text.split(" ");
                        try {
                            for(int i = 0; i < array.length; i++){
                                if(array[i].equals(name)){
                                    if(array[i+1].charAt(0) >= 'A' && array[i+1].charAt(0) <= 'Z' && array[i].split(" ").length < 2){
                                        name = name.concat(" "+array[i+1]);
                                    }
                                }
                            }
                            for(int i = 1; i < names.size(); i++){
                                if(names.get(i).split(" ").length == 1 && names.get(i).split(" ")[0].equals(names.get(i-1).split(" ")[1])){
                                    names.set(i, names.get(i).split(" ")[1]);
                                }
                            }
                        } catch(Exception e){
                            System.out.print("Out of bounds exception here eh?\n");
                        }
                        names.add(name);
                    }
                }
            }
        }
        ArrayList<String> fullNames = new ArrayList<>();
        return names;
    }


}