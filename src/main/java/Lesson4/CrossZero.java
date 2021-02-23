package Lesson4;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class CrossZero {
    public static char[][] field; //игровое поле
    public static ArrayList<Boolean> horizontalLineStatus = new ArrayList<>(); //доступные линии для заполнения по горизонтали
    public static ArrayList<Boolean> verticalLineStatus = new ArrayList<>(); //доступные линии для заполнения по вертикали
    public static Scanner consoleReader = new Scanner(System.in);
    public static Random random = new Random();
    public static boolean end; //конец игры
    public static int computerPhase = 1;
    //фаза 0 - рандомно ставит куда попало (один раз и только в начале игры)
    //фаза 1 - проверка хода игрока и проверка куда поставить по горизонталям и вертикалям
    //фаза 2 - пытается заполнить диагонали, если возможности нет переходит к фазе 1
    //фаза 3 - повышается gameBalance, компьютер активно пытается помешать тебе победить. Активируется если у АИ нет больше шансов на победу
    //версия игры где для победы необходимо закрыть лишь часть клеток, работает более менее корректно с небольшими полями (5х5 или 6х6)
    public static boolean turnPC = true;
    public static int gameBalance = 1;
    public static char DOT_ZERO = '0', DOT_X = 'X', EMPTY = '.';
    public static int winCondition;

    public static void main(String[] args) {
        int n = -1;
        System.out.println("Привет! Я маленький компьютерный мозг!\n Давай играть в крестики-нолики! \n Введите размер поля: ");
        do {
            try {
                n = consoleReader.nextInt();
            } catch (Exception e) {
                System.out.println("Неверный ввод!");
                consoleReader.nextLine();
                continue;
            }

        } while (n < 0);
        System.out.println("Введите длину выстроенной линии для победы или введите любую букву для установки значения по умолчанию (длина ряда): ");
        field = new char[n][n]; //инициализируем игровое поле
        computerPhase = random.nextInt(3); //генерируем вариант хода
        try {
            winCondition = consoleReader.nextInt();
            if (winCondition >= field.length) throw new Exception();
            System.out.println("Установлено новое значение, для победы достаточно выставить в ряд " + winCondition + " элемента!");
        } catch (Exception e) {
            winCondition = field.length;
            consoleReader.nextLine();
            System.out.println("Установлено стандартное значение (пока полностью не заполнится ряд крестиков или ноликов)!");
        }

        System.out.println();
        field_Init(field);
        printField(field);
        System.out.println();
        int y = -1, x = -1;
        while (!end) {
            do {
                System.out.println("Введите координаты в формате x y:");
                if (consoleReader.hasNextInt()) {
                    x = consoleReader.nextInt() - 1;
                }
                if (consoleReader.hasNextInt()) {
                    y = consoleReader.nextInt() - 1;
                }
                consoleReader.nextLine();
            } while (!isValidCell(x, y));
            field[y][x] = DOT_X;
            winnerCheck(field);
            if (end) {
                printField(field);
                System.out.println();
                break;
            }
            checkHorizontalLineStatus(field);
            checkVerticalLineStatus(field);
            actionPC(field);
            winnerCheck(field);
            printField(field);
            System.out.println("\n");
        }
        consoleReader.close();
    }

    public static void field_Init(char[][] field) { //инициализация игрового поля
        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[i].length; j++) {
                field[i][j] = EMPTY;
            }
        }
        for (int i = 0; i < field.length; i++) { //заполняем массив доступной победы по горизонтали
            horizontalLineStatus.add(i, true);
        }
        for (int i = 0; i < field.length; i++) { //заполняем массив доступной победы по вертикали
            verticalLineStatus.add(i, true);
        }
    }

    public static void printField(char[][] field) { //вывод на экран текущего состояния поля
        String up = "  ";
        for (int i = 0; i < field.length; i++) { //выводим шапку
            up += i + 1 + " ";
        }
        System.out.println(up);
        for (int i = 0; i < field.length; i++) {
            System.out.print(i + 1 + " "); //выводим левую шкалу
            for (int j = 0; j < field[i].length; j++) {
                System.out.print(field[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean isValidCell(int x, int y) { //проверка ввода
        if (x < 0 || y < 0 || x > field.length - 1 || y > field.length - 1 || field[y][x] != EMPTY) {
            System.out.println("Неверный ввод");
            return false;
        }
        return true;
    }

    public static void checkVerticalLineStatus(char[][] field) {  //проверяет вертикальные линии, заполняет массив доступных для победы
        int lineWithX = 0;
        int emptyLine = 0;
        for (int i = 0; i < field.length; i++) {
            lineWithX = lineScanner(field, i, DOT_X, 'v');
            emptyLine = lineScanner(field, i, EMPTY, 'v');

            if (lineWithX > 0) {
                verticalLineStatus.set(i, false);
            } else if ((emptyLine == field.length) || (lineWithX == 0)) {
                verticalLineStatus.set(i, true);
            }
        }
    }

    public static void checkHorizontalLineStatus(char[][] field) {  //проверяет горизонтальные линии, заполняет массив доступных для победы
        int lineWithX = 0;
        int emptyLine = 0;
        for (int i = 0; i < field.length; i++) {
            lineWithX = lineScanner(field, i, DOT_X, 'h');
            emptyLine = lineScanner(field, i, EMPTY, 'h');
            if (lineWithX > 0) {
                horizontalLineStatus.set(i, false);
            } else if ((emptyLine == field.length) || (lineWithX == 0)) {
                horizontalLineStatus.set(i, true);
            }
        }
    }


    public static int checkDiagonalLineStatus(char[][] field, char value, char side) {  //проверяет диагонали (правую и левую)
        int status = 0;
        switch (side) {
            case 'l':
                for (int i = 0, j = 0; i < field.length; i++, j++) {
                    if (field[i][j] == value) {
                        status++;
                    } else if (field[i][j] != value && winCondition != field.length && status > 0) { //проверка на наличие последовательности в строке (исключает true для пустых клеток между значениями)
                        break;
                    }
                }
                break;
            case 'r':
                for (int i = 0, j = field[i].length - 1; j >= 0; i++, j--) {
                    if (field[i][j] == value) {
                        status++;
                    } else if (field[i][j] != value && winCondition != field.length && status > 0) { //проверка на наличие последовательности в строке (исключает true для пустых клеток между значениями)
                        break;
                    }
                }
                break;
        }
        return status;
    }

    public static void actionPC(char[][] field) {  //действует компьютер. В данный метод можно вкладывать паттерны хода
        turnPC = true;
        switch (computerPhase) {
            case 0: {
                //если у ПК первый ход ставим рэндомно ноль
                int x = 0;
                int y = 0;
                do {
                    x = random.nextInt(field.length - 1);
                    y = random.nextInt(field.length - 1);
                    if (field[x][y] == EMPTY) {
                        field[x][y] = DOT_ZERO;
                        break;
                    }
                } while (true);
                turnPC = false;
                computerPhase = 1;
                break;
            }
            case 1: {
                ifDanger(field);
                if (turnPC) {
                    normalAction(field);
                }
                break;
            }
            case 2: {
                ifDanger(field);
                if (turnPC) {
                    if (checkDiagonalLineStatus(field, EMPTY, 'l') <= field.length && checkDiagonalLineStatus(field, DOT_X, 'l') == 0) {
                        setZero(field, 0, 'l');
                        turnPC = false;
                    } else if (checkDiagonalLineStatus(field, EMPTY, 'r') <= field.length && checkDiagonalLineStatus(field, DOT_X, 'r') == 0) {
                        setZero(field, 0, 'r');
                        turnPC = false;
                    } else {
                        computerPhase = 1;
                        normalAction(field);
                    }
                }
                break;
            }
            case 3: {
                do {
                    ifDanger(field);
                    gameBalance++;
                } while (turnPC);
                gameBalance = 2;
            }
            break;
        }


    }

    public static int lineScanner(char[][] field, int arrayPosition, char value, char line) { //проверяет линии с определенными параметрами
        //v - вертикальная линия
        //h - горизонтальная линия
        int status = 0;
        switch (line) {
            case 'h':
                for (int i = 0; i < field[arrayPosition].length; i++) {
                    if (field[arrayPosition][i] == value) {
                        status++;
                    } else if (field[arrayPosition][i] != value && winCondition != field.length && status > 0) { //проверка на наличие последовательности в строке, необходим для случая когда для победы нужна не вся длина массива
                        break;
                    }
                }
                break;
            case 'v':
                for (int i = 0; i < field.length; i++) {
                    if (field[i][arrayPosition] == value) {
                        status++;
                    } else if (field[i][arrayPosition] != value && winCondition != field.length && status > 0) { //проверка на наличие последовательности в строке, необходим для случая когда для победы нужна не вся длина массива
                        break;
                    }
                }
                break;
        }
        return status;
    }

    public static void ifDanger(char[][] field) { //проверяет сколько шагов осталось сопернику для победы, если соперник близок к победе - начинает мешать
        for (int i = 0; i < field.length; i++) {
            if (lineScanner(field, i, EMPTY, 'h') != 0 && lineScanner(field, i, DOT_X, 'h') >= winCondition - gameBalance) {
                setZero(field, i, 'h');
                turnPC = false;
                //найти пустую ячейку и поставить 0
                break;
            } else if (lineScanner(field, i, EMPTY, 'v') != 0 && lineScanner(field, i, DOT_X, 'v') >= winCondition - gameBalance) {
                setZero(field, i, 'v');
                turnPC = false;
                //найти пустую ячейку и поставить 0
                break;
            } else if ((checkDiagonalLineStatus(field, EMPTY, 'l') != 0) && (checkDiagonalLineStatus(field, DOT_X, 'l') >= winCondition - gameBalance)) {
                setZero(field, i, 'l');
                turnPC = false;
                //найти пустую ячейку и поставить 0
                break;
            } else if ((checkDiagonalLineStatus(field, EMPTY, 'r') != 0) && (checkDiagonalLineStatus(field, DOT_X, 'r') >= winCondition - gameBalance)) {
                setZero(field, i, 'r');
                turnPC = false;
                //найти пустую ячейку и поставить 0
                break;
            }
        }
    }

    public static void normalAction(char[][] field) { //обычное действие, ищет наиболее подходящую линию на которой доступна победа компьютера
        int verticalChanceWin = 0, horizontalChanceWin = 0, verticalChanceMAX = 0, horizontalChanceMAX = 0, verticalMaxChancePosition = 0, horizontalMaxChancePosition = 0;
        for (int i = 0; i < field.length; i++) {
            verticalChanceWin = lineScanner(field, i, DOT_ZERO, 'v');
            horizontalChanceWin = lineScanner(field, i, DOT_ZERO, 'h');
            if ((verticalChanceWin >= verticalChanceMAX) && CrossZero.verticalLineStatus.get(i) == true) {
                verticalChanceMAX = verticalChanceWin;
                verticalMaxChancePosition = i;
            }
            if ((horizontalChanceWin >= horizontalChanceMAX) && (CrossZero.horizontalLineStatus.get(i) == true)) {
                horizontalChanceMAX = horizontalChanceWin;
                horizontalMaxChancePosition = i;
            }
        }
        if (horizontalChanceMAX >= verticalChanceMAX) {
            setZero(field, horizontalMaxChancePosition, 'h');
            turnPC = false;
        } else if (horizontalChanceMAX <= verticalChanceMAX) {
            setZero(field, verticalMaxChancePosition, 'v');
            turnPC = false;
        }
    }

    public static void setZero(char[][] field, int arrayPosition, char line) { //ставит 0 только там где это можно и нужно
        //v - вертикальная линия
        //h - горизонтальная линия
        //l - левая диагональ
        //r - правая диагональ
        switch (line) {
            case 'h':
                for (int i = 0; i < field.length; i++) {
                    if (field[arrayPosition][i] == EMPTY) {
                        field[arrayPosition][i] = DOT_ZERO;
                        break;
                    }
                }
                break;
            case 'v':
                for (int i = 0; i < field.length; i++) {
                    if (field[i][arrayPosition] == EMPTY) {
                        field[i][arrayPosition] = DOT_ZERO;
                        break;
                    }
                }
                break;
            case 'l':
                for (int i = 0, j = 0; i < field.length; i++, j++) {
                    if (field[i][j] == EMPTY) {
                        field[i][j] = DOT_ZERO;
                        break;
                    }
                }
                break;
            case 'r':
                for (int i = 0, j = field.length; j > 0; i++, j--) {
                    if (field[i][j - 1] == EMPTY) {
                        field[i][j - 1] = DOT_ZERO;
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }

    public static void winnerCheck(char[][] field) { //проверка победителя
        for (int i = 0; i < field.length; i++) {
            if (lineScanner(field, i, DOT_X, 'v') >= winCondition) {
                winAlert(true);
                break;
            }
            if (lineScanner(field, i, DOT_ZERO, 'v') >= winCondition) {
                winAlert(false);
                break;
            }
            if (lineScanner(field, i, DOT_X, 'h') >= winCondition) {
                winAlert(true);
                break;
            }
            if (lineScanner(field, i, DOT_ZERO, 'h') >= winCondition) {
                winAlert(false);
                break;
            }
            if (checkDiagonalLineStatus(field, DOT_X, 'l') >= winCondition) {
                winAlert(true);
                break;
            }
            if (checkDiagonalLineStatus(field, DOT_ZERO, 'r') >= winCondition) {
                winAlert(false);
                break;
            }
        }
        if (isDraw(field)) {
            System.out.println("\n");
            System.out.println("Простите, но у нас ничья!:(");
            end = true;
        }
        if (!end && !horizontalLineStatus.contains(true) && !verticalLineStatus.contains(true) && computerPhase != 3) { //если у компьютера не остается шанса на победу, он начинает активно мешать
            System.out.println("\n");
            System.out.println("Ты не оставил мне шанса на победу! Я в ярости!! \\\\0.0//");
            computerPhase = 3;
            gameBalance = 2;
        }

    }

    private static boolean isDraw(char[][] field) { //проверка на ничью
        int emptyElements = 0;
        for (char[] chars : field) {
            for (char aChar : chars) {
                if (aChar == EMPTY) {
                    emptyElements++;
                }
            }
        }
        return emptyElements == 0;
    }

    public static void winAlert(boolean player) { //вывод сообщения о победе или проигрше
        if (!player) {
            System.out.println("Увы, но вы проиграли!!");
            CrossZero.end = true;
        } else {
            System.out.println(("Поздравляю! Вы победили!! Еще увидимся ;) "));
            CrossZero.end = true;
        }
    }
}
