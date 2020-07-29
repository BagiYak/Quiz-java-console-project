# Quiz-java-console-project
Quiz app in console - with very simple logic!

/*
*   Development author -> Bagdat Yakushev
*   GitHub -> BagiYak
*   Instagram -> @Bagdat.Yakushev
*   Telegram -> t.me/BagiYak or @BagiYak
*
* This is my first Java App
* that I decided to develop myself without copy pasting from any other projects.
* Just using OCA and OCP Java SE 8, Head First books for java.
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
 
 
 This is simple console Java App.
 You can use it just write your questions to file in resources/questions.txt.

 In file use pattern:
 Each line is a context for creating Question class object,
 that has values for each object's fields - and they separate by ':'.
 First in line is question, then right answer, then explanation and
 the rest are variants of answers that separates by '~'.
 
 Example:
 question?:answer:explanation:1_answerVariant~2_answerVariant~3_answerVariant
 
 Also all new user gamers are added to file as list of gamers with their name and score.
