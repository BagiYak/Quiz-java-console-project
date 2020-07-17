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

import java.util.Comparator;

/**
 * Gamer class
 * need to create an instance of new quiz gamer
 */
public class Gamer implements Comparable {

    private String name;

    // to store gamer's scores (each answer = +1 score)
    private int score = 0;

    // default constructor for extends and polymorphism rules
    public Gamer() {}

    // this constructor for saving as new object to list in Quiz class to prevent reference mismatch and data errors
    public Gamer(Gamer gamer) {
        this.name = gamer.getName();
        this.score = gamer.getScore();
    }

    public void setName(String n){
        this.name = n;
    }
    public String getName(){
        return name;
    }

    public void setScore(){
        this.score++;
    }
    public int getScore(){
        return score;
    }

    @Override
    public String toString() {
        return "Gamer: " + name + ", score=" + score;
    }

    @Override
    public int compareTo(Object o) {
        //return this.score - ((Gamer)o).score;
        //return this.name.compareTo(((Gamer)o).name);

        if (this.score > ((Gamer)o).score) {
            return 1;
        } else if (this.score < ((Gamer)o).score) {
            return -1;
        } else {
            return 0;
        }
    }
}

class ComparatorByScore implements Comparator {
    public int compare(Object o1, Object o2) {
        return ((Gamer)o1).getScore() - ((Gamer)o2).getScore();
    }
}
