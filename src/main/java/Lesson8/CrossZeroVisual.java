package Lesson8;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class CrossZeroVisual {
    public static String[][] field; //игровое поле
    public static int SIZE = 3;
    public static ArrayList<Boolean> horizontalLineStatus = new ArrayList<>(); //доступные линии для заполнения по горизонтали
    public static ArrayList<Boolean> verticalLineStatus = new ArrayList<>(); //доступные линии для заполнения по вертикали
    public static Random random = new Random();
    public static boolean end; //конец игры
    public static int computerPhase = 1;
    //фаза 0 - рандомно ставит куда попало (один раз и только в начале игры)
    //фаза 1 - проверка хода игрока и проверка куда поставить по горизонталям и вертикалям
    //фаза 2 - пытается заполнить диагонали, если возможности нет переходит к фазе 1
    //фаза 3 - повышается gameBalance, компьютер активно пытается помешать тебе победить. Активируется если у АИ нет больше шансов на победу
    //версия игры где для победы необходимо закрыть лишь часть клеток, работает корректно если условие победы меньше размера поля не более чем на 3
    public static boolean turnPC = true;
    public static int gameBalance = 1;
    public static String DOT_ZERO = "0", DOT_X = "X", EMPTY = ".";
    public static int winCondition;
    private static AlertWindow alert;
    public static Window visualWindow;
    private static int userCount = 0, pcCount = 0;
    private static File clipFile = new File("src/main/java/Lesson8/clip.wav");
    private static File failFile = new File("src/main/java/Lesson8/fail.wav");
    private static File winFile = new File("src/main/java/Lesson8/win.wav");
    private static AudioInputStream userClip, fail, win;

    static {
        try {
            userClip = AudioSystem.getAudioInputStream(clipFile);
            fail = AudioSystem.getAudioInputStream(failFile);
            win = AudioSystem.getAudioInputStream(winFile);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static Clip clip;
    private static Clip failClip;
    private static Clip winClip;


    static {
        try {
            clip = AudioSystem.getClip();
            clip.open(userClip);
            failClip = AudioSystem.getClip();
            failClip.open(fail);
            winClip = AudioSystem.getClip();
            winClip.open(win);
        } catch (LineUnavailableException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        field = new String[SIZE][SIZE]; //инициализируем игровое поле
        visualWindow = new Window(SIZE);
        alert = new AlertWindow();
        while (true) {
            startGame();
        }
    }

    public static void startGame() throws IOException {
        computerPhase = random.nextInt(2); //генерируем вариант хода
        field_Init(field); //заполняем игровое поле и массивы доступных побед
        winCondition = field.length;
        while (!end) {
            actionPC(field); //действует компьютер
            winnerCheck(field); //проверка победителя
        }
    }

    public static void humanTurn(int y, int x) throws IOException, LineUnavailableException {
        field[y][x] = DOT_X;
        clip.setFramePosition(0);
        clip.start();
        turnPC = true;
    }

    public static void field_Init(String[][] field) { //инициализация игрового поля
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

    public static void checkVerticalLineStatus(String[][] field) {  //проверяет вертикальные линии, заполняет массив доступных для победы
        int lineWithX = 0;
        int emptyLine = 0;
        for (int i = 0; i < field.length; i++) {
            lineWithX = lineScanner(field, i, DOT_X, "v");
            emptyLine = lineScanner(field, i, EMPTY, "v");

            if (lineWithX > 0) {
                verticalLineStatus.set(i, false);
            } else if ((emptyLine == field.length) || (lineWithX == 0)) {
                verticalLineStatus.set(i, true);
            }
        }
    }

    public static void checkHorizontalLineStatus(String[][] field) {  //проверяет горизонтальные линии, заполняет массив доступных для победы
        int lineWithX = 0;
        int emptyLine = 0;
        for (int i = 0; i < field.length; i++) {
            lineWithX = lineScanner(field, i, DOT_X, "h");
            emptyLine = lineScanner(field, i, EMPTY, "h");
            if (lineWithX > 0) {
                horizontalLineStatus.set(i, false);
            } else if ((emptyLine == field.length) || (lineWithX == 0)) {
                horizontalLineStatus.set(i, true);
            }
        }
    }

    public static int checkDiagonalLineStatus(String[][] field, String value, String side) {  //проверяет диагонали (правую и левую)
        int status = 0;
        switch (side) {
            case "l":
                for (int i = 0, j = 0; i < field.length; i++, j++) {
                    if (field[i][j] == value) {
                        status++;
                    }
                }
                break;
            case "r":
                for (int i = 0, j = field[i].length - 1; j >= 0; i++, j--) {
                    if (field[i][j] == value) {
                        status++;
                    }
                }
                break;
        }
        return status;
    }

    public static void actionPC(String[][] field) {  //действует компьютер. В данный метод можно вкладывать паттерны хода
        checkHorizontalLineStatus(field);
        checkVerticalLineStatus(field);
        switch (computerPhase) {
            case 0: {
                //если у ПК первый ход ставим рэндомно ноль
                if (turnPC) {
                    int x = 0;
                    int y = 0;
                    do {
                        x = random.nextInt(field.length - 1);
                        y = random.nextInt(field.length - 1);
                        if (field[x][y] == EMPTY) {
                            field[x][y] = DOT_ZERO;
                            Window.cells[x][y].setText("0");
                            break;
                        }
                    } while (true);
                    turnPC = false;
                    computerPhase = 1;
                }
                break;
            }
            case 1: {
                if (turnPC) {
                    winnerChance(field);
                }
                if (turnPC) {
                    ifDanger(field);
                }
                if (turnPC) {
                    normalAction(field);
                }
                break;
            }
            case 2: {
                if (turnPC) {
                    winnerChance(field);
                }
                if (turnPC) {
                    ifDanger(field);
                }
                if (turnPC) {
                    if (checkDiagonalLineStatus(field, EMPTY, "l") <= field.length && checkDiagonalLineStatus(field, DOT_X, "l") == 0) {
                        setZero(field, 0, "l");
                        turnPC = false;
                    } else if (checkDiagonalLineStatus(field, EMPTY, "r") <= field.length && checkDiagonalLineStatus(field, DOT_X, "r") == 0) {
                        setZero(field, 0, "r");
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

    public static int lineScanner(String[][] field, int arrayPosition, String value, String line) { //проверяет линии с определенными параметрами
        //v - вертикальная линия
        //h - горизонтальная линия
        int status = 0;
        switch (line) {
            case "h":
                for (int i = 0; i < field[arrayPosition].length; i++) {
                    if (field[arrayPosition][i] == value) {
                        status++;
                    }
                }
                break;
            case "v":
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

    public static void ifDanger(String[][] field) { //проверяет сколько шагов осталось сопернику для победы, если соперник близок к победе - начинает мешать
        for (int i = 0; i < field.length; i++) {
            if (lineScanner(field, i, EMPTY, "h") != 0 && lineScanner(field, i, DOT_X, "h") >= winCondition - gameBalance) {
                setZero(field, i, "h");
                turnPC = false;
                //найти пустую ячейку и поставить 0
                break;
            } else if (lineScanner(field, i, EMPTY, "v") != 0 && lineScanner(field, i, DOT_X, "v") >= winCondition - gameBalance) {
                setZero(field, i, "v");
                turnPC = false;
                //найти пустую ячейку и поставить 0
                break;
            } else if ((checkDiagonalLineStatus(field, EMPTY, "l") != 0) && (checkDiagonalLineStatus(field, DOT_X, "l") >= winCondition - gameBalance)) {
                setZero(field, i, "l");
                turnPC = false;
                //найти пустую ячейку и поставить 0
                break;
            } else if ((checkDiagonalLineStatus(field, EMPTY, "r") != 0) && (checkDiagonalLineStatus(field, DOT_X, "r") >= winCondition - gameBalance)) {
                setZero(field, i, "r");
                turnPC = false;
                //найти пустую ячейку и поставить 0
                break;
            }
        }
    }

    public static void normalAction(String[][] field) { //обычное действие, ищет наиболее подходящую линию на которой доступна победа компьютера
        int verticalChanceWin = 0, horizontalChanceWin = 0, verticalChanceMAX = 0, horizontalChanceMAX = 0, verticalMaxChancePosition = 0, horizontalMaxChancePosition = 0;
        for (int i = 0; i < field.length; i++) {
            verticalChanceWin = lineScanner(field, i, DOT_ZERO, "v");
            horizontalChanceWin = lineScanner(field, i, DOT_ZERO, "h");
            if ((verticalChanceWin >= verticalChanceMAX) && verticalLineStatus.get(i) == true) {
                verticalChanceMAX = verticalChanceWin;
                verticalMaxChancePosition = i;
            }
            if ((horizontalChanceWin >= horizontalChanceMAX) && (horizontalLineStatus.get(i) == true)) {
                horizontalChanceMAX = horizontalChanceWin;
                horizontalMaxChancePosition = i;
            }
        }
        if (horizontalChanceMAX >= verticalChanceMAX) {
            setZero(field, horizontalMaxChancePosition, "h");
            turnPC = false;
        } else if (horizontalChanceMAX <= verticalChanceMAX) {
            setZero(field, verticalMaxChancePosition, "v");
            turnPC = false;
        }
    }

    public static void setZero(String[][] field, int arrayPosition, String line) { //ставит 0 только там где это можно и нужно
        //v - вертикальная линия
        //h - горизонтальная линия
        //l - левая диагональ
        //r - правая диагональ
        switch (line) {
            case "h":
                for (int i = 0; i < field.length; i++) {
                    if (field[arrayPosition][i] == EMPTY) {
                        field[arrayPosition][i] = DOT_ZERO;
                        Window.cells[arrayPosition][i].setText(DOT_ZERO);
                        break;
                    }
                }
                break;
            case "v":
                for (int i = 0; i < field.length; i++) {
                    if (field[i][arrayPosition] == EMPTY) {
                        field[i][arrayPosition] = DOT_ZERO;
                        Window.cells[i][arrayPosition].setText(DOT_ZERO);
                        break;
                    }
                }
                break;
            case "l":
                for (int i = 0, j = 0; i < field.length; i++, j++) {
                    if (field[i][j] == EMPTY) {
                        field[i][j] = DOT_ZERO;
                        Window.cells[i][j].setText(DOT_ZERO);
                        break;
                    }
                }
                break;
            case "r":
                for (int i = 0, j = field.length; j > 0; i++, j--) {
                    if (field[i][j - 1] == EMPTY) {
                        field[i][j - 1] = DOT_ZERO;
                        Window.cells[i][j - 1].setText(DOT_ZERO);
                        break;
                    }
                }
                break;
            default:
                break;
        }
    }

    public static void winnerChance(String[][] field) { //проверка ситуации, когда у человека и компьютера одинаковые шансы на победу. Компьютер должен победить, а не мешать в этом случае
        for (int i = 0; i < field.length; i++) {
            if (lineScanner(field, i, DOT_ZERO, "v") == winCondition - 1 && lineScanner(field, i, DOT_X, "v") == 0) {
                setZero(field, i, "v");
                turnPC = false;
                break;
            }
            if (lineScanner(field, i, DOT_ZERO, "h") == winCondition - 1 && lineScanner(field, i, DOT_X, "h") == 0) {
                setZero(field, i, "h");
                turnPC = false;
                break;
            }
            if (checkDiagonalLineStatus(field, DOT_ZERO, "l") == winCondition - 1 && checkDiagonalLineStatus(field, DOT_X, "l") == 0) {
                setZero(field, 0, "l");
                turnPC = false;
                break;
            }
            if (checkDiagonalLineStatus(field, DOT_ZERO, "r") == winCondition && checkDiagonalLineStatus(field, DOT_X, "r") == 0) {
                setZero(field, 0, "r");
                turnPC = false;
                break;
            }
        }
    }

    public static void winnerCheck(String[][] field) { //проверка победителя
        for (int i = 0; i < field.length; i++) {
            if (lineScanner(field, i, DOT_X, "v") >= winCondition) {
                winAlert(true);
                Window.lineWinnerBacklight(i, "v", true);
                break;
            }
            if (lineScanner(field, i, DOT_ZERO, "v") >= winCondition) {
                winAlert(false);
                Window.lineWinnerBacklight(i, "v", false);
                break;
            }
            if (lineScanner(field, i, DOT_X, "h") >= winCondition) {
                winAlert(true);
                Window.lineWinnerBacklight(i, "h", true);
                break;
            }
            if (lineScanner(field, i, DOT_ZERO, "h") >= winCondition) {
                winAlert(false);
                Window.lineWinnerBacklight(i, "h", false);
                break;
            }
            if (checkDiagonalLineStatus(field, DOT_X, "l") >= winCondition) {
                winAlert(true);
                Window.lineWinnerBacklight(i, "l", true);
                break;
            }
            if (checkDiagonalLineStatus(field, DOT_ZERO, "l") >= winCondition) {
                winAlert(false);
                Window.lineWinnerBacklight(i, "l", false);
                break;
            }
            if (checkDiagonalLineStatus(field, DOT_X, "r") >= winCondition) {
                winAlert(true);
                Window.lineWinnerBacklight(i, "r", true);
                break;
            }
            if (checkDiagonalLineStatus(field, DOT_ZERO, "r") >= winCondition) {
                winAlert(false);
                Window.lineWinnerBacklight(i, "r", false);
                break;
            }
        }
        if (isDraw(field) && !end) {
            alert.setLabelText("  Простите, но у нас ничья!:(");
            alert.setVisible(true);
            end = true;
        }
        if (!end && !horizontalLineStatus.contains(true) && !verticalLineStatus.contains(true) && computerPhase != 3) { //если у компьютера не остается шанса на победу, он начинает активно мешать
            computerPhase = 3;
            gameBalance = 2;
        }

    }

    public static boolean isDraw(String[][] field) { //проверка на ничью
        int emptyElements = 0;
        for (String[] chars : field) {
            for (String aChar : chars) {
                if (aChar == EMPTY) {
                    emptyElements++;
                }
            }
        }
        return emptyElements == 0;
    }

    public static void winAlert(boolean player) { //вывод сообщения о победе или проигрше
        if (!player) {
            pcCount++;
            failClip.setFramePosition(0);
            failClip.start();
            alert.setLabelText("  Увы, но вы проиграли!! Хотите повторить?");
            alert.setTitle("Score: " + "Вы " + userCount + " vs " + "PC " + pcCount);
            alert.setVisible(true);
            end = true;
        } else {
            userCount++;
            winClip.setFramePosition(0);
            winClip.start();
            alert.setLabelText("  Вы победили!! Хотите повторить?");
            alert.setTitle("Score: " + "Вы " + userCount + " vs " + "PC " + pcCount);
            alert.setVisible(true);
            end = true;
        }
    }
}
