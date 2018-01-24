import dataset.Dataset;
import dataset.model.Root;
import dataset.model.Word;
import matrix.CoocuranceMatrix;
import matrix.Ctx;
import matrix.PPMI;
import org.apache.commons.lang3.StringUtils;
import qa.Questions;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static matrix.CoocuranceMatrix.findPosition;

public class PMI {


    public static void main(String[] args) throws IOException {
        Dataset dataset = new Dataset();

        Root wackypediaSections = dataset.getWackypediaSectionsWithoutXml();
//      //dataset.showAllSections(wackypediaSections);

        System.out.println("------SHOW SECOND COLUMN WITH FILTERING------------");
        List<Word> wordList = dataset.getAllWords(wackypediaSections);
//      //wordList.forEach(System.out::println);


        System.out.println("------------ GET QUESTIONS AND ANSWERS FROM FILE------------------");
        Questions questions = new Questions();
        questions.getAllQuestions().forEach(System.out::println);


        System.out.println(questions.getAllQuestions().get(0).getContent());
        System.out.println(questions.getAllQuestions().get(0).getAnswers().get(0).getContent());
        System.out.println(questions.getAllQuestions().get(0).getCorrectAnswer());

        List<String> questionV = new ArrayList<String>();
        List<String> correctAnswer = new ArrayList<String>();
        List<String[]> answers = new ArrayList<String[]>();

        for (int i = 0; i < questions.getAllQuestions().size(); i++) {
            questionV.add(questions.getAllQuestions().get(i).getContent());
            String[] tabAnswers = new String[4];
            tabAnswers[0] = questions.getAllQuestions().get(i).getAnswers().get(0).getContent();
            tabAnswers[1] = questions.getAllQuestions().get(i).getAnswers().get(1).getContent();
            tabAnswers[2] = questions.getAllQuestions().get(i).getAnswers().get(2).getContent();
            tabAnswers[3] = questions.getAllQuestions().get(i).getAnswers().get(3).getContent();
            answers.add(tabAnswers);
            correctAnswer.add(questions.getAllQuestions().get(i).getCorrectAnswer());

        }


        System.out.println("------------ ADD Question to Initial Dataset [LIBRARY] ------------");
        wordList = dataset.getAllWordsWithQuestionWord(wordList, questions); // all words with question words // to check algorithm


        System.out.println("------------ GET OCCURENCE MATRIX------------------");
        //TODO: sentencje trzeba zrobic bo raczej są zle - w senise zdania bo robienie matrixa dziala ale mam zle sentencje jakby
        // potrzebowal bym liste zdan tych logicznych - chyba je wyswietlasz ale niewiem czy to te
        CoocuranceMatrix coocuranceMatrix = new CoocuranceMatrix();
        //coocuranceMatrix.createCoocurance(wordList);

        PPMI ppmi = new PPMI();
        calculatePPMI(wordList, coocuranceMatrix, ppmi, questionV, correctAnswer, answers);


    }

    private static void calculatePPMI(List<Word> wordList, CoocuranceMatrix coocuranceMatrix, PPMI ppmi, List<String> questionV, List<String> goodAnswer, List<String[]> answers) throws FileNotFoundException {
        List<Word> wordListwithNoRep = coocuranceMatrix.createLiblary(wordList);
        short[][] coocurnaceMatrixxx = coocuranceMatrix.createCoocurance(wordList);
        coocuranceMatrix.printMatrix(coocurnaceMatrixxx, wordListwithNoRep.size());

        double[][] ppmiMatrix = new double[wordListwithNoRep.size()][wordListwithNoRep.size()];

        long ppmiMatrixLength = ppmiMatrix.length;

        for (int i = 0; i < ppmiMatrixLength; i++) {
            for (int j = 0; j < ppmiMatrixLength; j++) {
                if (coocurnaceMatrixxx[i][j] > 0) {
                    //System.out.print(coocurnaceMatrixxx[i][j] + " ");
                    ppmiMatrix[i][j] = ppmi.calculatePPMI(wordListwithNoRep.get(i).getWord(), wordListwithNoRep.get(j).getWord(), wordList, coocurnaceMatrixxx, wordListwithNoRep);


                }
//                if( (ppmiMatrix[i][j])>(float)0)
//                {
//                     System.out.println(ppmiMatrix[i][j]);
//                }
            }
            //System.out.println("");
        }

        calculateAnswers(ppmiMatrix, questionV, goodAnswer, answers, wordListwithNoRep);
    }

    private static void calculateAnswers(double[][] tab, List<String> questionV, List<String> goodAnswer, List<String[]> answers, List<Word> alfabet) throws FileNotFoundException {
        Ctx ctx = new Ctx();

        long correctAnswersSum = 0;
        double efficiency = 0;

        try (PrintWriter out = new PrintWriter("texts.txt")) {
            for (int i = 0; i < questionV.size(); i++) {
                int Q = findPosition(questionV.get(i), alfabet);
                int A1 = findPosition(answers.get(i)[0], alfabet);
                int A2 = findPosition(answers.get(i)[1], alfabet);
                int A3 = findPosition(answers.get(i)[2], alfabet);
                int A4 = findPosition(answers.get(i)[3], alfabet);
                double cos1 = ctx.cosinusSimilarity(tab, Q, A1);
                double cos2 = ctx.cosinusSimilarity(tab, Q, A2);
                double cos3 = ctx.cosinusSimilarity(tab, Q, A3);
                double cos4 = ctx.cosinusSimilarity(tab, Q, A4);
                int wyn = max(cos1, cos2, cos3, cos4);

                System.out.println("wynik obliczen : " + answers.get(i)[wyn] + ",  dla pytania '" + questionV.get(i) + "'  Poprawna odpowedz: " + goodAnswer.get(i));

                String text = ("wynik obliczen : " + answers.get(i)[wyn] + ",  dla pytania '" + questionV.get(i) + "'  Poprawna odpowedz: " + goodAnswer.get(i) + "\n");

                if (answers.get(i)[wyn].equals(goodAnswer.get(i))) {
                    correctAnswersSum++;
                }

                out.println(text);
            }
            efficiency = (double) correctAnswersSum / (double) questionV.size();
            System.out.println("Skuteczność algorytmu wynosi: " + efficiency);
            out.println("Skuteczność algorytmu wynosi: " + efficiency);
        }
    }

    private static int max(double a, double b, double c, double d) {

        double wynik = Math.max(a, Math.max(b, Math.max(c, d)));
        if (wynik == a) {
            return 0;
        }
        if (wynik == b) {
            return 1;
        }
        if (wynik == c) {
            return 2;
        } else return 3;

    }
}
