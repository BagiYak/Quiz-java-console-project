/*
*   Bagdat Yakushev -> development author
*   BagiYa -> my nickname
*   @BagiYa -> Instagram
*   t.me/BagiYak or @BagiYak -> Telegram
*
* This is my first Java App
* that I decided to develop myself without copy pasting from any other projects.
* Just using OCA, OCP and Head First books for java.
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

import java.util.*;

/**
 * Quiz class
 * main and need to start quiz game
 * use this code for terminal / console playing
 */
public class Quiz {

    // List to store all new gamers object to show scores anf names for statistic
    public static List<Gamer> listGamers = new ArrayList<>();

    public static void main(String[] args) {

        // boolean flag to stop first loop While if need to choose quit from the quiz game at all
        boolean quit = true;

        // boolean flag to stop second loop While if need to change gamer to new user
        boolean changeGamer;

        // First loop While to quit from the quiz game at all
        while (quit) {

            // create new Gamer instance
            Gamer gamer = new Gamer();

            // create new Question instance
            Question question = new Question();

            // change flag to true to start second loop While again with new gamer
            changeGamer = true;

            System.out.println("**********************************");
            System.out.println("* Hello in Quiz App by '@BagiYa' *");
            System.out.println("**********************************");
            System.out.println();
            System.out.println();

            // show list of gamer's names and scores
            if(listGamers.size() != 0) {

                // method from Quiz class
                showGamersScore(listGamers);
            }

            // method for scanning user typing in console to get his name and set it to gamer instance
            // this method from Quiz class
            getSetGamerName(gamer);

            System.out.println("******************************");
            System.out.println("Glad to see you " + gamer.getName() + "!");
            System.out.println();
            System.out.println("Now you need to answer quiz questions to get the highest score.");
            System.out.println("******************************");
            System.out.println();

            // 2 loop to change to new gamer
            while (changeGamer) {

                /** Start - this code need to get question, right answer, explanation
                and variants of answers from DataBase or DataFile to change questions after it is used **/

                // save new question, answer and explanation to question object of Question class
                question.setQuestion("What is the capital of Kazakhstan ?");
                question.setAnswer("Nur-Sultan");
                question.setExplanation("Nur-Sultan is the capital of Kazakhstan country. Before it was Astana!");

                // create Collection to save answer variants for quiz question
                ArrayList<String> answersVariants = new ArrayList<String>();
                answersVariants.add(0, "Aktau");
                answersVariants.add(1, "Nur-Sultan");
                answersVariants.add(2, "Oral");

                // show question to gamer
                System.out.println(question.getQuestion());
                System.out.println("******************************");
                System.out.println();

                // show all variant of answers to gamer
                for (int i = 0; i < answersVariants.size(); i++) {
                    System.out.println(i+1 + ") " + answersVariants.get(i));
                }
                System.out.println("******************************");
                System.out.println();

                // check gamer answer from console typing while he type acceptable number for answer
                int i = 0;
                while (i < 1 || i > answersVariants.size()) {

                    // scan char numbers from console typing to get gamer answer
                    Scanner inAnswerNumber = new Scanner(System.in);
                    System.out.print("Choose the right number of answer: ");

                    // catch exceptions if typed letters or symbols instead of numbers
                    try {
                        i = inAnswerNumber.nextInt();
                    } catch (InputMismatchException e) {
                        e.fillInStackTrace();
                    }
                    System.out.println();
                }

                // save number of answer minus 1 for index position in answerVariants list
                int answerNumber = --i;

                // check for right answer to congratulate gamer and store score +1
                if (answersVariants.get(answerNumber) == question.getAnswer()) {

                    System.out.println("Congratulations!!! Good job :-)");
                    System.out.println();

                    // show explanation of the answer to gamer
                    System.out.println(question.getExplanation());
                    System.out.println();

                    // store gamer's score (each answer = +1 score)
                    gamer.setScore();

                    // show gamer's scores
                    System.out.println("Now your score: " + gamer.getScore());
                    System.out.println("******************************");
                    System.out.println();
                }
                /** End - this code need to get question, right answer, explanation
                and variants of answers from DataBase or DataFile to change questions after it is used **/

                else {
                    // scan char letters from console typing to get gamer answer
                    Scanner inQuitChangeNext = new Scanner(System.in);

                    System.out.println("Wrong answer...");
                    System.out.println("******************************");
                    System.out.println("Type 'no' to quit from the quiz game;");
                    System.out.println("Type 'new' to change to new gamer;");
                    System.out.println("Type any keyboard key to try Next Question.");
                    System.out.println("******************************");
                    System.out.print("What do you choose: ");

                    // save answer as variable string quitChangeNext
                    String quitChangeNext = inQuitChangeNext.nextLine();

                    // check quitChangeNext answer to choose either gamer wanna quit, change user or get next question
                    // quit from game at all
                    if (quitChangeNext.equals("no")) {

                        // method to quit from quiz game at all
                        // this method from Quiz class
                        quitFromGame(listGamers, gamer);

                        // boolean flags to out from first and second loops
                        changeGamer = false;
                        quit = false;
                    }
                    // change to new user (gamer)
                    else if (quitChangeNext.equals("new")) {

                        // add new gamer object to list from this gamer instance using Gamer class parameterised constructor
                        listGamers.add(new Gamer(gamer));
                        
                        System.out.println("******************************");
                        System.out.println("Waiting for next gamer......");
                        System.out.println();

                        // boolean flag to out from second loop (inner)
                        changeGamer = false;
                    }
                    System.out.println();
                }
            }
        }
    }


    /**
     * Method to scanning user typing in console to get his name and set it to gamer instance
     *
     * @param newGamer
     */
    private static void getSetGamerName(Gamer newGamer) {

        // scan user typing in console
        Scanner inAnswerName = new Scanner(System.in);
        System.out.println("Please, fill your name for score statistic and enjoy playing Quiz. ");
        System.out.println("******************************");
        System.out.print("Your name is: ");
        String gamerName = inAnswerName.nextLine();
        System.out.println();

        // sets new gamer name in Gamer class instance
        newGamer.setName(gamerName);
    }

    /**
     * Method to quit from quiz game at all
     *
     * @param listOfGamers
     * @param gamer
     */
    private static void quitFromGame(List<Gamer> listOfGamers, Gamer gamer) {

        System.out.println("******************************");
        System.out.println("Your highest score: " + gamer.getScore());
        System.out.println("Good luck, see you soon !!!");
        System.out.println("******************************");
        System.out.println();

        // this code line where I use Gamer class parameterised constructor
        // to store all gamers objects to list for statistic info
        listOfGamers.add(new Gamer(gamer));

        // show list of gamer's score statistic info
        showGamersScore(listOfGamers);
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

        System.out.println("______________________________");
        System.out.println("List of the best Quiz gamers by Score");
        System.out.println("______________________________");

        // show list of gamers by score
        int i = 0;
        for(Object objGamer : o) {
            System.out.println(i+1 + ". " + objGamer.toString());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            i++;
        }
        System.out.println();

        /*
        * 2 variant of sorting array by score by implementing Comparator interface in Gamer class
        * usefull when need to sort by variant variable states of instance
        */
        Object[] objectArray = listOfGamers.toArray();

        // another way to sort implementing Comparator interface in ComparatorByScore class from Gamer class
        //Arrays.sort(objectArray, new ComparatorByScore());

        // lambda statement to sort array by score
        //Arrays.sort(objectArray, (o1, o2) -> ((Gamer)o1).getScore() - ((Gamer)o2).getScore());

        // another variant code instead of lambda statement to sort array by score
        Arrays.sort(objectArray, new Comparator<Object>(){
            @Override
            public int compare(Object o1, Object o2) {
                //return ((Gamer)o1).getScore() - ((Gamer)o2).getScore();
                return ((Gamer)o1).getName().compareTo(((Gamer)o2).getName());
            }
        });


        System.out.println("______________________________");
        System.out.println("List of the gamers by Name");
        System.out.println("______________________________");
        int k = 0;
        for(Object objGamer : objectArray) {
            System.out.println(k+1 + ". " + objGamer.toString());
            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            k++;
        }
        System.out.println();
    }
}
