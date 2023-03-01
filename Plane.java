import java.util.Scanner;

/*
https://contest.yandex.ru/contest/28412/problems/B/
*/
public class Plane {
    public static void main(String[] args) {

        final String WORDS = "ABC_DEF";
        Scanner scanner = new Scanner(System.in);

        // Ввод количества рядов и изначальной рассадки на этих рядах.
        String n = scanner.nextLine();
        String[] rowsInfo = new String[Integer.parseInt(n)];
        for (int i = 0; i < rowsInfo.length; i++) {
            rowsInfo[i] = scanner.nextLine();
        }

        // Ввод количества групп пассажиров и их пожеланий.
        String m = scanner.nextLine();
        String[] groupsInfo = new String[Integer.parseInt(m)];
        for (int i = 0; i < groupsInfo.length; i++) {
            groupsInfo[i] = scanner.nextLine();
        }

        // Работа с данными. Посадка пассажиров под их нужды. Последующий вывод на экран.
        for (String groupInfo : groupsInfo) {

            String[] splitGroupInfo = groupInfo.split(" ");
            int num = Integer.parseInt(splitGroupInfo[0]);
            String side = splitGroupInfo[1];
            String position = splitGroupInfo[2];

            boolean isGroupCanSeat = false;
            int savedRow = -1;

            for (int i = 0; i < rowsInfo.length; i++) {
                if (isGroupCanSeat) break;
                StringBuilder sb = new StringBuilder(rowsInfo[i]);
                switch (side) {
                    case "left": {
                        if (rowsInfo[i].lastIndexOf(".".repeat(num), 2) != -1) {
                            switch (position) {
                                case "window": {
                                    if (rowsInfo[i].charAt(0) == '.') {
                                        sb.replace(0, num, "X".repeat(num));
                                        rowsInfo[i] = new String(sb);
                                        savedRow = i;
                                        isGroupCanSeat = true;
                                    }
                                    break;
                                }
                                case "aisle": {
                                    if (rowsInfo[i].charAt(2) == '.') {
                                        sb.replace(3 - num, 3, "X".repeat(num));
                                        rowsInfo[i] = new String(sb);
                                        savedRow = i;
                                        isGroupCanSeat = true;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                    break;
                    case "right": {
                        if (rowsInfo[i].indexOf(".".repeat(num), 4) != -1) {
                            switch (position) {
                                case "window": {
                                    if (rowsInfo[i].charAt(6) == '.') {
                                        sb.replace(7 - num, 7, "X".repeat(num));
                                        rowsInfo[i] = new String(sb);
                                        savedRow = i;
                                        isGroupCanSeat = true;
                                    }
                                    break;
                                }
                                case "aisle": {
                                    if (rowsInfo[i].charAt(4) == '.') {
                                        sb.replace(4, 4 + num, "X".repeat(num));
                                        rowsInfo[i] = new String(sb);
                                        savedRow = i;
                                        isGroupCanSeat = true;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                }
            }
            if (isGroupCanSeat) {
                String strResult = "";
                for (int i = 0; i < 7; i++) {
                    if (rowsInfo[savedRow].charAt(i) == 'X') {
                        strResult += " " + (savedRow + 1) + "" + WORDS.charAt(i);
                    }
                }
                System.out.println("Passengers can take seats:" + strResult);
                for (String rowInfo : rowsInfo) {
                    System.out.println(rowInfo);
                }
                rowsInfo[savedRow] = rowsInfo[savedRow].replace("X", "#");
            } else System.out.println("Cannot fulfill passengers requirements");

        }
    }
}

// Старый код //
/*
            for (int i = 0; i < rowsInfo.length; i++) {
                StringBuilder stringBuilder = new StringBuilder(rowsInfo[i]);
                Indexer<String> indexer = side.equals("left") ? rowsInfo[i]::lastIndexOf : rowsInfo[i]::indexOf;
                if (indexer.returnIndex(".".repeat(num), 3) != -1) {
                    if (side.equals("left")) {
                        if ((position.equals("aisle")) && (rowsInfo[i].toCharArray()[2] == '.')) {
                            isGroupCanSeat = true;
                            for (int j = 2; num > 0; j--, num--) {
                                stringBuilder.replace(j, j + 1, "X");
                                rowsInfo[i] = new String(stringBuilder);
                                savedRow = i;
                            }
                            break;
                        }
                        if ((position.equals("window")) && (rowsInfo[i].toCharArray()[0] == '.')) {
                            isGroupCanSeat = true;
                            for (int j = 0; num > 0; j++, num--) {
                                stringBuilder.replace(j, j + 1, "X");
                                rowsInfo[i] = new String(stringBuilder);
                                savedRow = i;
                            }
                            break;
                        }
                    }
                    if (side.equals("right")) {
                        if ((position.equals("window")) && (rowsInfo[i].toCharArray()[6] == '.')) {
                            isGroupCanSeat = true;
                            for (int j = 6; num > 0; j--, num--) {
                                stringBuilder.replace(j, j + 1, "X");
                                rowsInfo[i] = new String(stringBuilder);
                                savedRow = i;
                            }
                            break;
                        }
                        if ((position.equals("aisle")) && (rowsInfo[i].toCharArray()[4] == '.')) {
                            isGroupCanSeat = true;
                            for (int j = 4; num > 0; j++, num--) {
                                stringBuilder.replace(j, j + 1, "X");
                                rowsInfo[i] = new String(stringBuilder);
                                savedRow = i;
                            }
                            break;
                        }
                    }
                }
            }

interface Indexer<T> {
    int returnIndex(T t, int fromIndex);
}*/
