package Andrey.Task1;

import java.util.ArrayList;
import java.util.Collections;

public class NumberToText {

    public static String number2text(long num) {

        String[][] str1 = {
                {"", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
                {"", "одна", "две", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять"},
        };

        String[] str11 =
                {"","десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
                        "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать", "двадцать"};

        String[] str10 =
                {"", "десять", "двадцать", "тридцать", "сорок", "пятьдесят",
                        "шестьдесят", "семьдесят", "восемьдесят", "девяносто"};

        String[] str100 =
                {"", "сто", "двести", "триста", "четыреста", "пятьсот",
                        "шестьсот", "семьсот", "восемьсот", "девятьсот"};

        String[][] forms = {
                {"","",""},
                {"тысяча", "тысячи", "тысяч"},
                {"миллион", "миллиона", "миллионов"},
                {"миллиард", "миллиарда", "миллиардов"},
                {"триллион", "триллиона", "триллионов"},
                {"квадриллион", "квадриллиона", "квадриллионов"},
                {"квинтиллион", "квинтиллиона", "квинтиллионов"}

        };

        String o = "";

        ArrayList segment = new ArrayList();

        //Если число отрицательное

        if (num < 0){
            num = num * -1;
            o += "минус ";
        }

        //Если число 0

        if (num == 0) {
            o = "ноль";
            return o;
        }

        //Разбиваем на сегменты

        long num_for_arr = num;
        while (num_for_arr > 999) {
            long seg = num_for_arr % 1000;
            segment.add(seg);
            num_for_arr = num_for_arr / 1000;
        }
        segment.add(num_for_arr);
        Collections.reverse(segment);

        for (int i = 0; i < segment.size(); i++) {// перебираем сегменты
            int lev = segment.size() - i - 1;
            int ri = (int) Integer.valueOf(segment.get(i).toString());// текущий сегмент
            int k = 0;
            if (ri % 10 == 1) k = 0;
            if (ri % 10 > 1 && ri % 10 < 5) k = 1;
            if (ri % 10 >= 5 || ri % 10 == 0) k = 2;
            if (num > 99999){
                if ( ri >= 110 && ri <= 120){
                    k = 2;
                }
            }
            if (num > 9999){
                if ( ri >= 10 && ri <= 20){
                    k = 2;
                }
            }

            int sexi = 0;
            if (lev == 1) sexi = 1;
            String rs = String.valueOf(ri); // число в строку

            // получаем числа для анализа
            int r1, r2 = 0, r3 = 0, r22 = 0;
            r1 = (int) Integer.valueOf(rs.substring(0, 1)); //первая цифра
            if (ri >= 10) {
                r2 = (int) Integer.valueOf(rs.substring(1, 2)); //вторая
            }
            if (ri >= 100) {
                r3 = (int) Integer.valueOf(rs.substring(2, 3)); //третья
                r22 = (int) Integer.valueOf(rs.substring(1, 3)); //вторая и третья
            }
            // Анализируем числа
            if (ri > 99) o += str100[r1] + " "; // Сотни

            if (r22 == 0) {
                if (r2 != 0 && r3 == 0) {
                    if (ri > 19){
                        o += str10[r1] + " " + str1[sexi][r2] +  " " + forms[lev][k] + " ";
                    } else o+= str11[r2+1] + " " + forms[lev][k] + " ";
                }
                if (r2 == 0 && r3 != 0) {
                    o += str1[sexi][r3] + " " + forms[lev][k] + " ";
                }
                if (r2 == 0 && r3 == 0){
                    if (ri >= 100 && ri < 1000){
                        o += "" + forms[lev][k] + " ";
                    }
                    if (ri < 100 && ri > 9) {
                        o += str10[r1] + " " + forms[lev][k] + " ";
                    }
                    if (ri < 10 && ri > 0 ){
                        o += str1[sexi][r1] + " " + forms[lev][k] + " ";
                    }

                    else o += "";
                }
            }

            if (r22 > 20) {// >20
                o += str10[r2] + " ";
                o += str1[sexi][r3] + " " + forms[lev][k] + " ";
            }
            if (r22 <=20 && r22 > 9) { // <=20
                o += str11[r22 - 9] + " " + forms[lev][k] + " "; // 10-20
            }
            if (r22 <=9 && r22 > 0) { // <=9
                o += str1[sexi][r3] + " " + forms[lev][k] + " "; // 0-9
            }
        }

        return o;

    }


    public static void main(String[] args) {
        String num = NumberToText.number2text(Long.parseLong(args[0]));
        System.out.println(num);
    }
}
