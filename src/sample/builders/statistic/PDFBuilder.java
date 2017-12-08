package sample.builders.statistic;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import sample.entities.AbsoluteLiquidityEntity;
import sample.entities.CurrentLiquidityEntity;
import sample.entities.Entity;
import sample.entities.ProfitabilityEntity;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.HashMap;

public class PDFBuilder {
    private Entity[] list;
    private String type;
    private final String DEST = "statistic.pdf";

    private static final HashMap<String, String[]> headers = new HashMap<>();

    static {
        headers.put("absolute", new String[] { "Funds", "Short financial investments", "Short liabilities", "Result", "Date" });
        headers.put("current", new String[] { "Revolving assets", "Short liabilities", "Result", "Date" });
        headers.put("profit", new String[] { "Profit from all activities", "Total product sales costs", "Result", "Date" });
    }

    public PDFBuilder(String type, Entity[] list) {
        this.type = type;
        this.list = list;
    }

    public void perform() {
        File file = new File(DEST);

        if (type == "absolute") handleAbsolute(list);
        if (type == "current") handleCurrent(list);
        if (type == "profit") handleProfit(list);
    }

    private void handleAbsolute(Entity[] list) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(DEST));
            document.open();
            PdfPTable table = new PdfPTable(5);

            addHeaders(table);

            for (Entity elem : list) {
                AbsoluteLiquidityEntity entity = (AbsoluteLiquidityEntity) elem;
                table.addCell(entity.getFunds());
                table.addCell(entity.getShortFinancialInvestments());
                table.addCell(entity.getShortLiabilities());
                table.addCell(entity.getResult());
                table.addCell(entity.getDate());
            }

            document.add(table);
            document.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getStackTrace());
        } catch (DocumentException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    private void handleCurrent(Entity[] list) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(DEST));
            document.open();
            PdfPTable table = new PdfPTable(4);

            addHeaders(table);

            for (Entity elem : list) {
                CurrentLiquidityEntity entity = (CurrentLiquidityEntity) elem;

                table.addCell(entity.getRevolvingAssets());
                table.addCell(entity.getShortLiabilities());
                table.addCell(entity.getResult());
                table.addCell(entity.getDate());
            }

            document.add(table);
            document.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getStackTrace());
        } catch (DocumentException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    private void handleProfit(Entity[] list) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(DEST));
            document.open();
            PdfPTable table = new PdfPTable(4);

            addHeaders(table);

            for (Entity elem : list) {
                ProfitabilityEntity entity = (ProfitabilityEntity) elem;

                table.addCell(entity.getProfitFromAllActivities());
                table.addCell(entity.getTotalProductSalesCosts());
                table.addCell(entity.getResult());
                table.addCell(entity.getDate());
            }

            document.add(table);
            document.close();

        } catch (FileNotFoundException ex) {
            System.out.println(ex.getStackTrace());
        } catch (DocumentException ex) {
            System.out.println(ex.getStackTrace());
        }
    }

    private void addHeaders(PdfPTable table) {
        for (String header : headers.get(type)) {
            table.addCell(header);
        }
    }
}
