import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

import opennlp.tools.doccat.*;
import opennlp.tools.util.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DocumentCategorizer {

    public static void main(String[] args) {
        ArrayList<String> testList = new ArrayList();
        try (BufferedWriter spamBufferedWriter = new BufferedWriter(new FileWriter(new File("documentcategorizer.txt")))) {

            // THE LOCATION OF YOUR TRAINING DATA
            String rootDirectoryName = "../../../texts";
            File rootDirectory = new File(rootDirectoryName);
            for (String directoryName : Objects.requireNonNull(rootDirectory.list())) {
                File file = new File(rootDirectoryName + "/" + directoryName);

                String fileNames[] = file.list();
                if (fileNames != null) {
                    for (String fileName : fileNames) {
                        String filePath = rootDirectoryName + "/" + directoryName + "/" + fileName;
                        StringBuilder lineStringBuilder = new StringBuilder();

                        BufferedReader br = new BufferedReader(new FileReader(new File(filePath)));
                        String line = null;
                        if (fileName.contains("spms")) {
                            lineStringBuilder.append("spam\t");
                        } else {
                            lineStringBuilder.append("ham\t");
                        }
                        while ((line = br.readLine()) != null) {
                            lineStringBuilder.append(line);
                        }
                        if (directoryName.equals("test")) {
                            testList.add(lineStringBuilder.toString());
                        } else {
                            spamBufferedWriter.write(lineStringBuilder.toString() + "\n");
                        }
                        lineStringBuilder.setLength(0);
                    }
                }

            }

        } catch (IOException ex) {
            // Handle exceptions
        }

//        InputStreamFactory isf = new InputStreamFactory() {
//            public InputStream createInputStream() throws IOException {
//                return new FileInputStream("spamtraining.train");
//            }
//        };

//        try {
//            TrainingParameters params = TrainingParameters.defaultParams();
//            DoccatFactory factory = new DoccatFactory();
//            InputStreamFactory isf = new MarkableFileInputStreamFactory(new File("spamtraining.train"));
//            ObjectStream<String> objectStream = new PlainTextByLineStream(isf, "UTF-8");
//            ObjectStream<DocumentSample> documentSampleStream = new DocumentSampleStream(objectStream);
//            DoccatModel documentCategorizationModel = DocumentCategorizerME.train("en", documentSampleStream, params, factory);
//            DocumentCategorizerME documentCategorizer = new DocumentCategorizerME(documentCategorizationModel);
//            for (String testItem : testList) {
//                double[] probabilities = documentCategorizer.categorize(new String[]{testItem});
//                String bestCategory = documentCategorizer.getBestCategory(probabilities);
//                System.out.println("The best fit for: [" + testItem.subSequence(0, 32) + "...] is: " + bestCategory);
//            }
//        } catch (IOException ex) {
//            // Handle exceptions
//        }

//        String text = "Congratualtions! You have won! Click here...";
//
//        try (BufferedReader br = new BufferedReader(new FileReader(new File("spam.txt")))) {
//
//            String line = null;
//            while ((line = br.readLine()) != null) {
//                Pattern pattern = Pattern.compile(line);
//                Matcher matcher = pattern.matcher(text);
//                if (matcher.find()) {
//                    System.out.println("Spam detected");
//                    break;
//                }
//            }
//
//            System.out.println(text.contains("Click here"));
//        } catch (IOException e) {
//            // Handle exceptions
//        }

    }

}
