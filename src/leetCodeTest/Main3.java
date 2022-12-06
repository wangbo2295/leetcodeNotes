package leetCodeTest;

import java.util.Scanner;

public class Main3 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String[] pokers = scanner.nextLine().split("-");

        }


    }

    class Poker {
        int single;
        int shunzi; //min val of shunzi
        int boom;
        int triple;
        int joker;  //0-none;1-joker;2-JOKER

        public void setSingle(int single) {
            this.single = single;
        }

        public void setShunzi(int shunzi) {
            this.shunzi = shunzi;
        }

        public void setBoom(int boom) {
            this.boom = boom;
        }

        public void setTriple(int triple) {
            this.triple = triple;
        }

        public void setJoker(int joker) {
            this.joker = joker;
        }

        public int getSingle() {
            return single;
        }

        public int getShunzi() {
            return shunzi;
        }

        public int getBoom() {
            return boom;
        }

        public int getTriple() {
            return triple;
        }

        public int getJoker() {
            return joker;
        }
    }
}
