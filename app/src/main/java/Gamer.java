/*
 *   Development author -> Bagdat Yakushev
 *   GitHub -> BagiYak
 *   Instagram -> @Bagdat.Yakushev
 *   Telegram -> t.me/BagiYak or @BagiYak
 *
 * This is my first Java App
 * that I decided to develop myself without copy pasting from any other projects.
 * Just using books: OCA and OCP Java SE 8, Head First Java.
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

import java.io.Serializable;
import java.util.Comparator;

/**
 * Gamer class
 * need to create an instance of new quiz gamer
 */
public class Gamer implements Comparable, Serializable {

    private String name;

    // to store gamer's scores (each answer = +1 score)
    private int score = 0;

    // private constructor
    // Singleton pattern to create an object only once in memory within an App and have it shared by multiple class
    private Gamer() {}

    // create only one instance of Gamer class within App
    private static final Gamer instance = new Gamer();

    public static Gamer getInstance() {
        return instance;
    }

    public synchronized void setName(String n){
        this.name = n;
    }
    public synchronized String getName(){
        return name;
    }

    public synchronized void setScore(){
        this.score++;
    }
    public synchronized int getScore(){
        return score;
    }

    @Override
    public synchronized String toString() {
        return "Gamer: " + name + ", score=" + score;
    }

    @Override
    public synchronized int compareTo(Object o) {
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
    public synchronized int compare(Object o1, Object o2) {
        return ((Gamer)o1).getScore() - ((Gamer)o2).getScore();
    }
}
