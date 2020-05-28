package Andrey.Task2;

import java.util.ArrayList;
import static java.lang.Math.pow;

public class TextToNum {

    public static int text2num(String num) {

        String[][] str1 = {
                {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
                {"", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
        };

        String[] str11 =
                {"", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
                        "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"};

        String[] str10 =
                {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят",
                        "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};

        String[] str100 =
                {"", "сто", "двести", "триста", "четыреста", "пятьсот",
                        "шестьсот", "семьсот", "восемьсот", "девятьсот"};

        String[][] forms = {
                {"", "", ""},
                {"тысяча", "тысячи", "тысяч"},
                {"миллион", "миллиона", "миллионов"},
                {"миллиард", "миллиарда", "миллиардов"},
                {"триллион", "триллиона", "триллионов"},
                {"квадриллион", "квадриллиона", "квадриллионов"},
                {"квинтиллион", "квинтиллиона", "квинтиллионов"}

        };

        String[] strNum;
        String delimetr = " "; //Разделитель
        strNum = num.split(delimetr);
        ArrayList intNum = new ArrayList();

        //Нахождение отдельного числа int

        for (int i=0; i< strNum.length; i++) {
            for (int j=0; j< str1[0].length; j++){
                if (strNum[i].equals(str1[0][j])){
                    intNum.add(j);
                }
            }

            for (int j=0; j< 3; j++){
                if (strNum[i].equals(str1[1][j])){
                    intNum.add(j);
                }
            }

            for (int j=0; j< str11.length; j++){
                if (strNum[i].equals(str11[j])){
                    intNum.add(10+j);
                }
            }

            for (int j=0; j< str10.length; j++){
                if (strNum[i].equals(str10[j])){
                    intNum.add(j*10);
                }
            }

            for (int j=0; j< str100.length; j++){
                if (strNum[i].equals(str100[j])){
                    intNum.add(j*100);
                }
            }

            for (int j=0; j< forms.length; j++){
                for (int k=0; k<3; k++) {
                    if (strNum[i].equals(forms[j][k])) {
                        intNum.add((int) pow(1000,j));
                    }
                }
            }
        }

        int answerNum = 0;
        int buffer = 0;
        ArrayList numForAnswer = new ArrayList();

        //Нахождение промежуточных чисел(тысячи, миллионы,..)

        for (int i=0; i< intNum.size(); i++){
            if (Integer.parseInt(intNum.get(i).toString()) > 999){
                buffer = buffer * Integer.parseInt(intNum.get(i).toString());
                numForAnswer.add(buffer);
                buffer = 0;
            }
            else buffer = buffer + Integer.parseInt(intNum.get(i).toString());
        }
        numForAnswer.add(buffer);

        //Сложение чисел и получение конечного

        for (int i=0; i< numForAnswer.size(); i++){
            answerNum = answerNum + Integer.parseInt(numForAnswer.get(i).toString());
        }

        return answerNum;


    }

    public static void main(String[] args) {
        int num = TextToNum.text2num(args[0]);
        System.out.println(num);
    }
}
