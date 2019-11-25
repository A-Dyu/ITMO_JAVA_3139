package md2html;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ToHTMLText {
    static private final Map<String, String> md2HtmlMarks = Map.of(
            "*", "em",
            "_", "em",
            "**", "strong",
            "__", "strong",
            "--", "s",
            "`", "code"
    );

    static private final Map<Character, String> specialSymbols = Map.of(
            '<', "&lt;",
            '>', "&gt;",
            '&', "&amp;"
    );

    static private final Map<String, String> closeToOpen = Map.of(
            ")", "(",
            "]", "["
    );

    public static String toHtml(List<String> lines) {
        StringBuilder textBuilder = new StringBuilder();
        for (int i = 0; i < lines.size(); i++) {
            StringBuilder stringBuilder = new StringBuilder();
            while (i < lines.size() && !lines.get(i).isEmpty()) {
                stringBuilder.append(lines.get(i)).append(System.lineSeparator());
                i++;
            }
            if (stringBuilder.length() == 0) {
                continue;
            }
            stringBuilder.setLength(stringBuilder.length() - System.lineSeparator().length());
            parseParagraph(textBuilder, stringBuilder.toString());
        }
        return textBuilder.toString();
    }

    private static void parseParagraph(StringBuilder stringBuilder, String markString) {
        int headerLevel = headerLevelCounter(markString);
        Map<Integer, Integer> markers = new HashMap<>();
        stringBuilder.append(paragraphMarker(headerLevel, false));
        findMarkers(markers, markString);
        parseString(markers, stringBuilder, markString, headerLevel == 0 ? 0 : headerLevel + 1, markString.length());
        stringBuilder.append(paragraphMarker(headerLevel, true));
        stringBuilder.append(System.lineSeparator());
    }

    private static int headerLevelCounter(String string) {
        int headerLevel = 0;
        while (headerLevel < string.length() && string.charAt(headerLevel) == '#') {
            headerLevel++;
        }
        if (headerLevel < string.length() && string.charAt(headerLevel) == ' ') {
            return headerLevel;
        }
        return 0;
    }

    private static String paragraphMarker(int headerLevel, boolean isClose) {
        return "<" + (isClose ? "/" : "") + (headerLevel == 0 ? "p" : "h" + headerLevel)  + ">";
    }

    private static boolean checkSlash(String string, int position) {
        return 0 <= position && string.charAt(position) == '\\';
    }

    private static boolean isMarker(String string) {
        return md2HtmlMarks.containsKey(string) || closeToOpen.containsKey(string) || closeToOpen.containsValue(string);
    }

    private static boolean checkMarker(String string, int position) {
        for (int markSz = 1; markSz <= 2; markSz++) {
            if (position + markSz <= string.length() && isMarker(string.substring(position, position + markSz))) {
                return true;
            }
        }
        return false;
    }

    private static String getMarker(String string, int position) {
        for (int markSz = 2; ; markSz--) {
            if (position + markSz <= string.length()) {
                final String marker = string.substring(position, position + markSz);
                if (isMarker(marker)) {
                    return closeToOpen.getOrDefault(marker, marker);
                }
            }
        }
    }

    private static void pushMarker(Map<Integer, Integer> markers, Map<String, Integer> lastMarker, String marker, int position) {
        if (lastMarker.containsKey(marker)) {
            markers.put(lastMarker.get(marker), position);
            lastMarker.remove(marker);
        } else {
            lastMarker.put(marker, position);
        }
    }

    private static boolean checkLink(Map<Integer, Integer> markers, String markString, int position) {
        return markString.charAt(position) == '[' &&
                markString.charAt(markers.get(position) + 1) == '(' &&
                    markers.containsKey(markers.get(position) + 1);
    }

    private static int putMarkers(Map<Integer, Integer> markers, StringBuilder stringBuilder, String markString, int position) {
        String marker = getMarker(markString, position);
        int closePosition = markers.get(position);
        if (md2HtmlMarks.containsKey(marker)) {
            String htmlMarker = md2HtmlMarks.get(marker);
            stringBuilder.append("<").append(htmlMarker).append(">");
            parseString(markers, stringBuilder, markString, position + marker.length(), closePosition);
            stringBuilder.append("</").append(htmlMarker).append(">");
            position = closePosition + marker.length() - 1;
            return position;
        } else if (!checkLink(markers, markString, position)) {
            stringBuilder.append(markString.charAt(position));
            return position;
        } else {
            stringBuilder.append("<a href='");
            stringBuilder.append(markString, closePosition + 2, markers.get(closePosition + 1));
            stringBuilder.append("'>");
            parseString(markers, stringBuilder, markString, position + 1, closePosition);
            stringBuilder.append("</a>");
            return markers.get(closePosition + 1);
        }
    }

    private static void parseString(Map<Integer, Integer> markers, StringBuilder stringBuilder, String markString, int l, int r) {
        for (int i = l; i < r; i++) {
            if (markers.containsKey(i)) {
                i = putMarkers(markers, stringBuilder, markString, i);
            } else if (specialSymbols.containsKey(markString.charAt(i))) {
                stringBuilder.append(specialSymbols.get(markString.charAt(i)));
            } else if (!checkSlash(markString, i)) {
                stringBuilder.append(markString.charAt(i));
            }
        }
    }

    private static void findMarkers(Map<Integer, Integer> markers, String markString) {
        Map<String, Integer> lastMarker = new HashMap<>();
        for (int i = 0; i < markString.length(); i++) {
            while (checkSlash(markString, i)) {
                i += 2;
            }
            if (checkMarker(markString, i)) {
                final String marker = getMarker(markString, i);
                pushMarker(markers, lastMarker, marker, i);
                i += marker.length() - 1;
            }
        }
    }

}
