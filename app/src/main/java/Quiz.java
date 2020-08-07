/*
 *   Development author -> Bagdat Yakushev
 *   GitHub -> BagiYak
 *   Instagram -> @Bagdat.Yakushev
 *   Telegram -> t.me/BagiYak or @BagiYak
 *
 * This is my first Java App
 * that I decided to develop myself without copy pasting from any other projects.
 * Just using books: OCA and OCP Java SE 8, Head First Java.
 
 
 * Copyright (C) 2020 Bagdat Yakushev
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

import java.io.*;
import java.util.*;
import static java.lang.Integer.parseInt;


/**
 * Quiz class
 * main and need to start quiz game
 * use this code for terminal / console playing
 */
public class Quiz {

    // path to a file where quiz questions are written as text
    private static final String SOURCE_FILE = "C:\\Users\\Bagi\\IdeaProjects\\quiz\\src\\main\\resources\\questions.txt";

    // path to a file where we save data with Gamers objects
    private static final String GAMERS_FILE = "C:\\Users\\Bagi\\IdeaProjects\\quiz\\src\\main\\resources\\gamers.txt";

    // path to the file where we get questions
    private static File sourceFile = new File(SOURCE_FILE);

    // path to the file where we save gamers objects
    private static File gamersFile = new File(GAMERS_FILE);

    // string array for storing each string data from source file
    private static List<String> data;

    // List to store all new gamers object to show scores anf names for statistic
    private static List<Gamer> listGamers = new ArrayList<>();

    // get Gamer instance
    private static final Gamer gamer = Gamer.getInstance();

    // List to store all Question object from file source data
    private static List<Question> listQuestions = new ArrayList<>();

    // boolean flag to stop first loop While if need to choose quit from the quiz game at all
    private static boolean quit = true;

    // interacting with gamers by console
    public static Console console = System.console();



    // The Main method - entry code of the App!
    public static void main(String[] args) throws Exception {

        // check if console is available in user machine
        if(console != null) {

            // Welcome words for gamer
            console.writer().println("*********************************");
            console.writer().println("* Hello in Quiz App by @BagiYak *");
            console.writer().println("*********************************");

            console.writer().println("Your score at start: " + gamer.getScore());

            // catching StreamCorruptedException when reading data from gamer's source file
            try {
                // get all Gamer's objects from file gamers.txt and add it to list using my getGamersFromFile() method from Quiz class
                listGamers = getGamersFromFile(gamersFile);

                // show list of gamer's score statistic info using my showGamersScore() method from Quiz class
                if(!listGamers.isEmpty()) {
                    showGamersScore(listGamers);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("Check gamer's list source file, some error with objects valid reading");
            }

            console.writer().println();

            // return string array data using my readingFile() method from Quiz class
            data = readFile(sourceFile);

            // add Question objects to list using my addQuestionsToList() method from Quiz class
            addQuestionsToList(data, listQuestions);

            // get user name from console and set it to gamer instance using my getSetGamerName() method from Quiz class
            getSetCheckGamerName(gamer, listGamers);

            // Say hello to gamer and start quiz game
            console.writer().println("Glad to see you, " + gamer.getName() + "!");
            console.writer().println("*****************************************************************");
            console.writer().println(" Now you need to answer quiz questions to get the highest score ");
            console.writer().println("*****************************************************************");

            // start Quiz game using my startQuiz() method from Quiz class
            startQuiz(console);
        }

        else {
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
            System.out.println("! Console is not available. Check if it is available on your Java machine... !");
            System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        }

        // show all questions for testing code using my method showQuestions() from Quiz class
        //showQuestions(listQuestions);

    }




    /**
     * Method to start Quiz game in console
     *
     * @param console
     */
    private static void startQuiz(Console console) throws IOException {

        // index to step next question in showQuestion() method in main while loop
        int indexNextQuestion = 0;

        // main while loop to start quiz game, show questions with scores until gamer answer to all, prefer to quit or change gamer
        while (quit == true && indexNextQuestion < listQuestions.size()) {

            // show question per one using my showQuestion() method from Quiz class
            showQuestion(listQuestions, indexNextQuestion);

            // printing instructions for gamer how to quit from game or change to new gamer
            console.writer().println("************************************************************************");
            console.writer().println("* If you wanna quit from the quiz game, just enter 'q' when answering! *");
            console.writer().println("************************************************************************");

            // console to ask gamer choose a number of answer for given question
            String stringConsoleAnswer = console.readLine("Please, enter your answer: ");

            // index number of entered answer, used to check matches between strings in answer and answers[answerInt-1]
            int answerInt = -1;

            // catching NumberFormatException when trying to convert gamer answer string from console to int number
            try {

                // convert String from console to int
                answerInt = parseInt(stringConsoleAnswer);
                //console.writer().println("answerInt: " + answerInt);

            } catch (NumberFormatException e) {
                //e.printStackTrace();
            }

            // getting right answer and answer's varieties from Question instance fields 'answer' and 'answers' in list
            String answer = listQuestions.get(indexNextQuestion).getAnswer();
            String[] answers = listQuestions.get(indexNextQuestion).getAnswers();

            // check gamer's answer string to process logic of next quiz steps
            // "q" to quit
            if(stringConsoleAnswer.matches("q")) {

                console.writer().println("*********************************************************");
                console.writer().println(" You chose to quit from Quiz game, thank you for trying! ");
                console.writer().println("*********************************************************");

                // boolean flag to quit from main while loop and stop the Quiz app in console
                quit = false;
            }

            // check given int answer by matching right answer with index from answers array
            else if (answerInt > 0 && answerInt <= answers.length && answer.matches(answers[answerInt-1])) {

                // set gamer's score
                gamer.setScore();

                console.writer().println("******************************");

                // show gamer's score
                console.writer().println(" Good job!!! Your score: " + gamer.getScore());

                console.writer().println("******************************");

                // show explanation of answer
                console.writer().println("Explanation: " + listQuestions.get(indexNextQuestion).getExplanation());
            }

            // wrong answer to say try again
            else {
                console.writer().println("*** Your answer is wrong ***");

                // show gamer's score
                console.writer().println(" Your score still: " + gamer.getScore());
            }

            // increase index to step next question in next time using showQuestion() method in main while loop
            indexNextQuestion++;

        }

        // save gamers score to list and show all gamers scores using my saveGamers() method from Quiz class
        saveGamers(listGamers, gamer);

        // show list of gamer's score statistic info using my showGamersScore() method from Quiz class
        showGamersScore(listGamers);

        // save list of gamers objects to destination file using my createGamersFile() method from Quiz class
        createGamersFile(listGamers, gamersFile);

    }


    /**
     * Method to read data with questions from source file and return it
     *
     * @param source
     * @return
     * @throws IOException
     */
    private static List<String> readFile(File source) throws IOException {

        List<String> data = new ArrayList<>();

        try(BufferedReader in = new BufferedReader(new FileReader(source))) {

            String s;

            while ((s = in.readLine()) != null) {
                data.add(s);
            }

        }

        return data;
    }


    /**
     * Method to add Question objects to list
     *
     * @param dataFromReadFile
     * @param listQuestions
     */
    private static void addQuestionsToList(List<String> dataFromReadFile, List<Question> listQuestions) {

        // clear list of questions, or it will add questions when gamer change to new gamer due to runtime
        // and it will cause if-else statement when checking indexNextQuestion == listQuestions.size()
        // for increasing index to step next question in showQuestion() method in main while loop
        listQuestions.clear();

        for (String s : dataFromReadFile) {

            // create new Question instance
            Question question = new Question();

            // split string with all question instance fields to string array by ':'
            String[] questionArray = s.split(":");

            // set all Question instance fields
            if(questionArray.length == Question.COUNT_OF_FIELDS_IN_QUESTION_CLASS) {

                int indexQuestionArray = questionArray.length - Question.COUNT_OF_FIELDS_IN_QUESTION_CLASS;

                question.setQuestion(questionArray[indexQuestionArray]);
                question.setAnswer(questionArray[indexQuestionArray+1]);
                question.setExplanation(questionArray[indexQuestionArray+2]);

                // to set answers field split string with all answers varieties to string array by '~'
                String[] answerVarieties = questionArray[indexQuestionArray+3].split("~");

                question.setAnswers(answerVarieties);
            }

            // add each new Question object to list

            listQuestions.add(question);
        }

        return;
    }


    /**
     * Method to show question and answer varieties from list of Question objects
     *
     * @param listQuestions
     */
    private static void showQuestion(List<Question> listQuestions, int indexNextQuestion) {

        console.writer().println("****************************************************");
        console.writer().println("Question: " + listQuestions.get(indexNextQuestion).getQuestion());

        console.writer().println("Answers varieties: ");

        int indexVariety = 1;

        for (String answerVariety : listQuestions.get(indexNextQuestion).getAnswers()) {

            console.writer().println(indexVariety + ") "+ answerVariety);
            indexVariety++;
        }
    }


    /**
     * Method to scanning user typing in console to get his name and set it to gamer instance
     *
     * @param newGamer
     */
    private static void getSetCheckGamerName(Gamer newGamer, List<Gamer> listOfGamers) {

        // flag if need to call this method again when gamer name is invalid
        boolean flagCallThisMethodAgain = false;

        String gamerName = console.readLine("Please, enter your special name: ");

        // check gamer name for null or less than 2 letters in name
        if(gamerName != null && gamerName.length() > 1) {

            // check matching name with names in file with Gamer's objects
            for (Gamer gamerInList : listOfGamers) {

                // if name is matched, ask new gamer to change name
                if(gamerName.matches(gamerInList.getName())) {

                    console.writer().println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    console.writer().println(" There is already gamer with name: '" + gamerName + "'");
                    console.writer().println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
                    flagCallThisMethodAgain = true;
                    break;
                }
            }

            // sets new gamer name in Gamer class instance
            newGamer.setName(gamerName);

        } else {
            console.writer().println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            console.writer().println(" The name may not be empty or less than 2 letters");
            console.writer().println("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
            flagCallThisMethodAgain = true;
        }

        if(flagCallThisMethodAgain == true) {
            getSetCheckGamerName(newGamer, listOfGamers);
        }

    }


    /**
     * Method to create file gamers.txt and save all gamers in it
     *
     * @param listOfGamers
     * @param dataFile
     * @throws IOException
     */
    private static void createGamersFile(List<Gamer> listOfGamers, File dataFile) throws IOException {

        // if gamer score is more than 0 - save list of gamers objects to destination file
        if(gamer.getScore() > 0) {

            try(ObjectOutputStream out = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(dataFile)))) {

                for(Gamer gamerInList : listOfGamers){
                    out.writeObject(gamerInList);
                }
            }
        }
    }


    /**
     * Method to get all gamers from file gamers.txt
     *
     * @param dataFile
     * @return
     * @throws IOException
     * @throws ClassNotFoundException
     */
    private static List<Gamer> getGamersFromFile(File dataFile) throws IOException, ClassNotFoundException {

        List<Gamer> gamersList = new ArrayList<>();

        try(ObjectInputStream in = new ObjectInputStream(new BufferedInputStream(new FileInputStream(dataFile)))) {

            while (true) {

                Object object = in.readObject();

                if(object instanceof Gamer) {
                    gamersList.add((Gamer)object);
                }
            }

        } catch (EOFException e){
            //e.printStackTrace();
        }

        return gamersList;
    }


    /**
     * Method to quit from quiz game at all
     *
     * @param listOfGamers
     * @param paramGamer
     */
    private static void saveGamers(List<Gamer> listOfGamers, Gamer paramGamer) {

        console.writer().println("************************************");
        console.writer().println("* Good luck, see you next time !!! *");
        console.writer().println("************************************");

        // to store all gamers objects to list for statistic info
        listOfGamers.add(paramGamer);

    }


    /**
     * Method to show list of gamers by score
     *
     * @param listOfGamers
     */
    private static void showGamersScore(List<Gamer> listOfGamers) {

        /* 1 variant (default) of sorting list by score */

        // sorting to show winners by highest score
        Collections.sort(listOfGamers);

        console.writer().println();
        console.writer().println("*************************************************");
        console.writer().println("* List of the best Quiz gamers by highest Score *");
        console.writer().println("*************************************************");

        // show list of gamers by highest score
        listOfGamers.forEach(c -> console.writer().println(c));


        /*
         * 2 variant of sorting list by score by implementing new Comparator interface as anonymous class
         * usefull when need to sort by variant variable states of instance
         */

        Collections.sort(listOfGamers, new Comparator<Gamer>(){

            @Override
            public int compare(Gamer g1, Gamer g2) {
                return g1.getName().compareTo(g2.getName());
            }

        });

        console.writer().println();
        console.writer().println("*******************************");
        console.writer().println("* List of Quiz gamers by Name *");
        console.writer().println("*******************************");

        // show list of gamers by sorted names
        listOfGamers.forEach(console.writer()::println);
    }


    /**
     * Method to show all questions for testing code
     *
     * @param listQuestions
     */
    private static void showQuestions(List<Question> listQuestions) {

        for (int i = 0; i < listQuestions.size(); i++) {

            console.writer().println("****************************************************");
            console.writer().println("Question: " + listQuestions.get(i).getQuestion());
            console.writer().println("Answer: " + listQuestions.get(i).getAnswer());
            console.writer().println("Explanation: " + listQuestions.get(i).getExplanation());

            int indexVariety = 1;

            for (String answerVariety : listQuestions.get(i).getAnswers()) {

                console.writer().println(indexVariety + " variety: "+ answerVariety);
                indexVariety++;
            }
        }
    }


}