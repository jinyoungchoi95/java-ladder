package step4.view;

import java.util.List;
import step4.domain.ladder.LadderLine;
import step4.domain.ladder.LadderResult;
import step4.domain.ladder.Point;
import step4.domain.user.User;

public class ResultView {

    private static final String RESULT_TITLE_PRINT_MESSAGE = "사다리 결과\n";
    private static final String LADDER_RESULT_TITLE_PRINT_MESSAGE = "실행결과\n";

    private static final String DEFAULT_USER_PRINT_FORMAT = "%-6s";
    private static final String CONNECTED_LINE_PRINT_FORMAT = "|-----";
    private static final String DISCONNECTED_LINE_PRINT_FORMAT = "|     ";

    private static final String LINE_BREAK = "\n";
    private static final String PRINT_USERNAME_BETWEEN_RESULT = " : ";

    private static final String PRINT_ALL_USER_RESULT_COMMAND = "all";

    private ResultView() {
    }

    public static void printResult(List<User> users, List<LadderLine> ladders, List<String> endPoints) {
        pirntResultTitle();
        printUsers(users);
        printLadder(ladders);
        printResultPoint(endPoints);
    }

    private static void pirntResultTitle() {
        System.out.println(RESULT_TITLE_PRINT_MESSAGE);
    }

    private static void printUsers(List<User> users) {
        StringBuilder sb = new StringBuilder();
        users.stream()
            .map(User::nameToString)
            .map(ResultView::stringFormatByDefaultUserPrintFromat)
            .forEach(sb::append);
        System.out.println(sb);
    }

    private static String stringFormatByDefaultUserPrintFromat(String name) {
        return String.format(DEFAULT_USER_PRINT_FORMAT, name);
    }

    private static void printLadder(List<LadderLine> ladder) {
        ladder.forEach(ResultView::printLine);
    }

    private static void printLine(LadderLine line) {
        StringBuilder sb = new StringBuilder();
        line.values()
            .forEach(point -> sb.append(getConnected(point)));
        System.out.println(sb);
    }

    private static String getConnected(Point point) {
        if (point.isNextConnect()) {
            return CONNECTED_LINE_PRINT_FORMAT;
        }
        return DISCONNECTED_LINE_PRINT_FORMAT;
    }

    public static void printResultPoint(List<String> endPoints) {
        StringBuilder sb = new StringBuilder();
        endPoints.stream()
            .map(ResultView::stringFormatByDefaultUserPrintFromat)
            .forEach(sb::append);
        System.out.println(sb);
    }

    public static void printLadderResult(String username, List<User> users, LadderResult ladderResult) {
        StringBuilder sb = new StringBuilder();
        sb.append(LADDER_RESULT_TITLE_PRINT_MESSAGE);

        if (username.equals(PRINT_ALL_USER_RESULT_COMMAND)) {
            users.forEach(user -> appendResult(ladderResult, sb, user));
            System.out.println(sb);
            return;
        }

        sb.append(ladderResult.getResultByUser(User.of(username)));
        System.out.println(sb);
    }

    private static void appendResult(LadderResult ladderResult, StringBuilder sb, User user) {
        String userResult = ladderResult.getResultByUser(user);
        sb.append(user.nameToString() + PRINT_USERNAME_BETWEEN_RESULT + userResult);
        sb.append(LINE_BREAK);
    }

}