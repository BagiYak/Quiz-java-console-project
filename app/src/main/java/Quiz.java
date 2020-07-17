/*
*   Bagdat Yakushev -> development author
*   BagiYa -> my nickname
*   @BagiYa -> Instagram
*   t.me/BagiYak or @BagiYak -> Telegram
*
* This is my first Java App
* that I decided to develop myself without copy pasting from any other projects.
* Just using OCA and Head First books for java.
*/

/*
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

    // path to the file where we get questions
    private static File source = new File(SOURCE_FILE);

    // string array for storing each string data from source file
    private static List<String> data;

    // List to store all Question object from file source data
    private static List<Question> listQuestions = new ArrayList<>();

    // List to store all new gamers object to show scores anf names for statistic
    private static List<Gamer> listGamers = new ArrayList<>();

    // create new Gamer instance
    private static Gamer gamer = new Gamer();

    // boolean flag to stop first loop While if need to choose quit from the quiz game at all
    private static boolean quit = true;

    // interacting with gamers by console
    public static Console console = System.console();


    public static void main(String[] args) throws IOException {

        // TODO: method to save a list of gamers with score to a file
        // TODO: method to get and show a list of gamers with score to a file

        // return string array data using my readingFile() method
        data = readFile(source);

        // add Question objects to list using my addQuestionsToList() method from Quiz class
        addQuestionsToList(data, listQuestions);



        // check if console is available in user machine
        if(console != null) {

            // Welcome words for gamer
            console.writer().println("****************************************************");
            console.writer().println("Hello in Quiz App by '@BagiYa'");
            console.writer().println("****************************************************");

            console.writer().println("Your score at start: " + gamer.getScore());

            // get user name from console and set it to gamer instance using my getSetGamerName() method from Quiz class
            getSetGamerName(gamer);

            // Say hello to gamer and start quiz game
            console.writer().println("Glad to see you, " + gamer.getName() + "!");
            console.writer().println("****************************************************");
            console.writer().println("Now you need to answer quiz questions to get the highest score.");
            console.writer().println("****************************************************");

            // start Quiz game using my startQuiz() method from Quiz class
            startQuiz(console);
        }

        else {
            console.writer().println("****************************************************");
            console.writer().println("Console is not available. Check if it is available on your machine...");
        }

        // show all questions for testing code using my method showQuestions() from Quiz class
        //showQuestions(listQuestions);

    }




    /**
     * Method to start Quiz game in console
     *
     * @param console
     */
    private static void startQuiz(Console console) {

        // index to step next question in showQuestion() method in main while loop
        int indexNextQuestion = 0;

        // main while loop to start quiz game, show questions with scores until gamer answer to all, prefer to quit or change gamer
        while (quit == true && indexNextQuestion < listQuestions.size()) {

            // show question per one using my showQuestion() method from Quiz class
            showQuestion(listQuestions, indexNextQuestion);

            // printing instructions for gamer how to quit from game or change to new gamer
            console.writer().println("****************************************************");
            console.writer().println("If you wanna quit from the quiz game, just enter 'q' when answering!");
            console.writer().println("If you wanna change to new gamer, just enter 'g' when answering!");
            console.writer().println("****************************************************");

            // console to ask gamer choose a number of answer for given question
            String stringConsoleAnswer = console.readLine("Please, enter your answer: ");

            // index number of entered answer, used to check matches between strings in answer and answers[answerInt-1]
            int answerInt = -1;

            // catching NumberFormatException when trying to convert gamer answer string from console to int number
            try {

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

                console.writer().println("****************************************************");
                console.writer().println("You chose to quit from Quiz game, thank you for trying!");

                // save gamers score to list and show all gamers scores
                saveGamerScore(listGamers, gamer);

                // show list of gamer's score statistic info
                showGamersScore(listGamers);

                // boolean flag to quit from main while loop and stop the Quiz app in console
                quit = false;
            }

            // "g" to change gamer
            else if(stringConsoleAnswer.matches("g")) {

                console.writer().println("****************************************************");
                console.writer().println("You chose to change to new gamer, thank you for trying!");

                // save gamers score to list and show all gamers scores
                saveGamerScore(listGamers, gamer);

                // show list of gamer's score statistic info
                showGamersScore(listGamers);

                // start the main() again with new gamer
                String[] stringsVarargs = {"New gamer is launching..."};

                // create new gamer for quiz game
                gamer = new Gamer();

                try {
                    Quiz.main(stringsVarargs);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // right answer to congratulate, set and show gamer's score
            else if (answerInt > 0 && answerInt <= answers.length && answer.matches(answers[answerInt-1])) {

                // set gamer's score
                gamer.setScore();

                console.writer().println("****************************************************");

                // show gamer's score
                console.writer().println("Good job!!! Your score is: " + gamer.getScore());

                // show explanation of answer
                console.writer().println("Explanation: " + listQuestions.get(indexNextQuestion).getExplanation());

                // increase index to step next question in showQuestion() method in main while loop
                indexNextQuestion++;
            }

            // wrong answer to say try again
            else {

                console.writer().println("*** No answers is matched ***");
                console.writer().println("Your answer is wrong.");
                console.writer().println("Should be some from 1 to : " + listQuestions.get(indexNextQuestion).getAnswers().length);
            }
        }

    }


    /**
     * Method to read data from source file and return it
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

            // add each Question object to list
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
    private static void getSetGamerName(Gamer newGamer) {

        String gamerName = console.readLine("Please, enter your name: ");

        // sets new gamer name in Gamer class instance
        newGamer.setName(gamerName);

    }


    // TODO: add method to retrieve gamers from file and put them to list of gamers

    /**
     * Method to quit from quiz game at all
     *
     * @param listOfGamers
     * @param gamer
     */
    private static void saveGamerScore(List<Gamer> listOfGamers, Gamer gamer) {

        console.writer().println("Your highest score: " + gamer.getScore());
        console.writer().println("Good luck, see you next time !!!");
        console.writer().println("******************************");

        // this code line where I use Gamer class parameterised constructor
        // to store all gamers objects to list for statistic info
        listOfGamers.add(new Gamer(gamer));

    }


    /**
     * Method to show list of gamers by score
     *
     * @param listOfGamers
     */
    private static void showGamersScore(List<Gamer> listOfGamers) {

        /*
         * 1 variant (default) of sorting array by score
         */

        // save list of gamer to array
        Object[] o = listOfGamers.toArray();

        // default array sorting and reverse to show winners by highest score
        Arrays.sort(o, Collections.reverseOrder());

        console.writer().println("List of the best Quiz gamers by Score:");
        console.writer().println("******************************");

        // show list of gamers by score
        int indexGamer = 0;

        for(Object objGamer : o) {

            console.writer().println(indexGamer+1 + ") " + objGamer.toString());
            console.writer().println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            indexGamer++;
        }

        /*
        * 2 variant of sorting array by score by implementing Comparator interface in Gamer class
        * usefull when need to sort by variant variable states of instance
        */
        Object[] objectArray = listOfGamers.toArray();

        // another way to sort implementing Comparator interface in ComparatorByScore class from Gamer class
        //Arrays.sort(objectArray, new ComparatorByScore());

        // lambda statement to sort array by score
        //Arrays.sort(objectArray, (o1, o2) -> ((Gamer)o1).getScore() - ((Gamer)o2).getScore());

        // another variant code instead of lambda statement to sort array by name
        Arrays.sort(objectArray, new Comparator<Object>(){

            @Override
            public int compare(Object o1, Object o2) {
                //return ((Gamer)o1).getScore() - ((Gamer)o2).getScore();
                return ((Gamer)o1).getName().compareTo(((Gamer)o2).getName());
            }

        });

        console.writer().println("List of Quiz gamers by Name:");
        console.writer().println("______________________________");

        int k = 0;

        for(Object objGamer : objectArray) {

            console.writer().println(k+1 + ") " + objGamer.toString());
            console.writer().println("******************************");
            k++;
        }

        console.writer().println("______________________________");
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
