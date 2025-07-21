package com.cricMaster.fantasyICL_backend.etl.pdf;

import org.apache.pdfbox.pdmodel.PDDocument;
import technology.tabula.ObjectExtractor;
import technology.tabula.Page;
import technology.tabula.RectangularTextContainer;
import technology.tabula.Table;
import technology.tabula.extractors.SpreadsheetExtractionAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableExtractor {

    public static List<Table> extractTables(File pdfFile) throws IOException {
        try (PDDocument doc = PDDocument.load(pdfFile)) {
            ObjectExtractor oe = new ObjectExtractor(doc);
            Page page = oe.extract(1); // first page
            SpreadsheetExtractionAlgorithm sea = new SpreadsheetExtractionAlgorithm();
            return sea.extract(page);
        }
    }

    /**
     * Converts a Tabula Table into a row-by-row List of String cells.
     */
    public static List<List<String>> asStringRows(Table t) {
        List<List<String>> out = new ArrayList<>();
        for (List<RectangularTextContainer> row : t.getRows()) {
            List<String> cells = new ArrayList<>();
            for (RectangularTextContainer cell : row) {
                cells.add(cell.getText().trim());
            }
            out.add(cells);
        }
        return out;
    }
}
