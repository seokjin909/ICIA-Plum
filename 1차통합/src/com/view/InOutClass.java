package com.view;

import java.util.Scanner;


public class InOutClass {
    Scanner scan = new Scanner(System.in);

    // 문자열 입력 메소드
    public String inStr(String str) {
        onePrint(str);
        String s = scan.nextLine();
        return s;
    }

    // 정수 입력 메소드
    public int inNum(String str) {
        onePrint(str);
        String s = scan.nextLine();
        int num = -1;

        if (s.equals("")) { // 아무 값도 입력되지 않았을 경우
            num = -1;
            return num;
        }
        try { // 정상적으로 숫자가 입력되었을 경우
            num = Integer.parseInt(s);
        } catch (Exception e) { // 그 외에 모든 예외사항 처리
            twoPrint("잘못된 입력!");
            num = -1;
        }
        return num;
    }


    public void onePrint(String str) {
        System.out.print(str);
    }

    public void twoPrint(String str) {
        System.out.println(str);
    }
}

