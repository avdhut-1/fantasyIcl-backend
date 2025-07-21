package com.cricMaster.fantasyICL_backend.etl.pdf;

import com.cricMaster.fantasyICL_backend.etl.pdf.model.BattingPerf;
import com.cricMaster.fantasyICL_backend.etl.pdf.model.BowlingPerf;
import com.cricMaster.fantasyICL_backend.etl.pdf.model.MatchDetails;
import technology.tabula.Table;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class PdfScorecardParser {

    private static final DateTimeFormatter DATE_FMT = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private static final DateTimeFormatter TIME_FMT = DateTimeFormatter.ofPattern("hh:mm a", Locale.ENGLISH);

    /**
     * Parses *all* the key sections from the scorecard PDF.
     */
    public static ParsedScorecard parse(File pdf) throws IOException {
        List<Table> tables = TableExtractor.extractTables(pdf);
        List<List<String>> rows;

        ParsedScorecard out = new ParsedScorecard();
        int inningNo = 0;

        for (Table t : tables) {
            rows = TableExtractor.asStringRows(t);
            if (looksLikeMatchDetails(rows.get(0))) {
                out.matchDetails = parseMatchDetails(rows);
            }
            else if (looksLikeBattingHeader(rows.get(0))) {
                inningNo++;
                out.batting.addAll(parseBatting(rows, inningNo));
            }
            else if (looksLikeBowlingHeader(rows.get(0))) {
                out.bowling.addAll(parseBowling(rows, inningNo));
            }
        }
        return out;
    }

    private static boolean looksLikeMatchDetails(List<String> header) {
        return header.stream().anyMatch(h -> h.contains("Match"));
    }
    private static boolean looksLikeBattingHeader(List<String> header) {
        return header.contains("B") && header.contains("4s") && header.contains("6s") && header.contains("SR");
    }
    private static boolean looksLikeBowlingHeader(List<String> header) {
        return header.contains("O") && header.contains("M") && header.contains("W") && header.contains("Eco");
    }

    private static MatchDetails parseMatchDetails(List<List<String>> rows) {
        MatchDetails d = new MatchDetails();
        // assume rows like:
        // ["Match","The Golden Warriors vs Super Strikers"]
        // ["Ground","Sportsage…","Pune"]
        // ["Date","2025-07-18,","01:24","AM","UTC"]
        for (var r : rows) {
            switch (r.get(0)) {
                case "Match":
                    var teams = r.get(1).split(" vs ");
                    d.teamA = teams[0];
                    d.teamB = teams[1];
                    break;
                case "Ground":
                    d.venue = String.join(" ", r.subList(1, r.size()));
                    break;
                case "Date":
                    // r = ["Date","2025-07-18,","01:24","AM","UTC"]
                    String date = r.get(1).replace(",", "");
                    String time = r.get(2) + " " + r.get(3);
                    d.date = LocalDate.parse(date, DATE_FMT);
                    d.time = LocalTime.parse(time, TIME_FMT);
                    break;
                case "Toss":
                    // if present
                    break;
                case "Result":
                    // etc…
                    break;
            }
        }
        return d;
    }

    private static List<BattingPerf> parseBatting(List<List<String>> rows, int inningNo) {
        var data = new ArrayList<BattingPerf>();
        // skip header row (idx=0)
        for (int i = 1; i < rows.size(); i++) {
            var r = rows.get(i);
            if (r.size()<7) continue; // guard
            BattingPerf bp = new BattingPerf();
            bp.inningNo   = inningNo;
            bp.position   = Integer.parseInt(r.get(0));
            bp.playerName = r.get(1);
            bp.dismissal  = r.get(2);
            bp.runs       = Integer.parseInt(r.get(3));
            bp.balls      = Integer.parseInt(r.get(4));
            bp.fours      = Integer.parseInt(r.get(5));
            bp.sixes      = Integer.parseInt(r.get(6));
            bp.strikeRate = new BigDecimal(r.get(7));
            data.add(bp);
        }
        return data;
    }

    private static List<BowlingPerf> parseBowling(List<List<String>> rows, int inningNo) {
        var data = new ArrayList<BowlingPerf>();
        for (int i = 1; i < rows.size(); i++) {
            var r = rows.get(i);
            if (r.size()<10) continue;
            BowlingPerf bw = new BowlingPerf();
            bw.inningNo      = inningNo;
            bw.position      = Integer.parseInt(r.get(0));
            bw.playerName    = r.get(1);
            bw.overs         = new BigDecimal(r.get(2));
            bw.maidens       = Integer.parseInt(r.get(3));
            bw.runsConceded  = Integer.parseInt(r.get(4));
            bw.wickets       = Integer.parseInt(r.get(5));
            bw.zeros         = Integer.parseInt(r.get(6));
            bw.fours         = Integer.parseInt(r.get(7));
            bw.sixes         = Integer.parseInt(r.get(8));
            bw.wides         = Integer.parseInt(r.get(9));
            bw.noballs       = Integer.parseInt(r.get(10));
            bw.economy       = new BigDecimal(r.get(11));
            data.add(bw);
        }
        return data;
    }

    /** Aggregates everything. */
    public static class ParsedScorecard {
        public MatchDetails matchDetails;
        public List<BattingPerf> batting = new ArrayList<>();
        public List<BowlingPerf> bowling = new ArrayList<>();
    }
}
