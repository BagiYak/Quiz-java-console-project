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

/**
 * Question class
 * need to create an instance of each question for quiz game
 */
public class Question {

    private String question;
    private String answer;
    private String explanation;
    private String[] answers;

    public static final int COUNT_OF_FIELDS_IN_QUESTION_CLASS = 4;

    public void setQuestion(String question){
        this.question = question;
    }
    public String getQuestion(){
        return question;
    }

    public void setAnswer(String answer){
        this.answer = answer;
    }
    public String getAnswer(){
        return answer;
    }

    public void setExplanation(String explanation){
        this.explanation = explanation;
    }
    public String getExplanation(){
        return explanation;
    }

    public void setAnswers(String[] answers) { this.answers = answers; }
    public String[] getAnswers() { return answers; }

}
