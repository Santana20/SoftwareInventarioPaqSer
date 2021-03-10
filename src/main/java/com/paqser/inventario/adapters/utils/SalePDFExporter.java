package com.paqser.inventario.adapters.utils;

import com.lowagie.text.Document;
import com.lowagie.text.PageSize;
import com.lowagie.text.Paragraph;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import com.paqser.inventario.adapters.utils.DTOClass.DetailSalePDF;
import com.paqser.inventario.domain.models.DetailSale;
import com.paqser.inventario.domain.models.Sale;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.stream.Stream;

public class SalePDFExporter {

    private final DateFormat dateFormat;

    public SalePDFExporter() {
        this.dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    }
    public void exportSale(HttpServletResponse response, Sale sale) throws IOException {

        Document documentPDF = new Document(PageSize.A4);
        PdfWriter.getInstance(documentPDF, response.getOutputStream());

        documentPDF.open();

        writeSale(documentPDF, sale);

        documentPDF.close();
    }
    public void exportListSales(HttpServletResponse response, Stream<Sale> listSales) throws IOException {

        Document documentPDF = new Document(PageSize.A4);
        PdfWriter.getInstance(documentPDF, response.getOutputStream());

        documentPDF.open();

        documentPDF.add(new Paragraph("Lista de todas las ventas"));
        if (listSales != null) {
            listSales.forEach(sale -> writeSale(documentPDF, sale));
        }
        documentPDF.close();

    }
    private void writeTableHeader(PdfPTable table) {
        PdfPCell cell = new PdfPCell();
        cell.setPadding(5);

        cell.setPhrase(new Phrase("Codigo"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Descripcion"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Precio Unitario"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("Cantidad"));
        table.addCell(cell);

        cell.setPhrase(new Phrase("SubTotal"));
        table.addCell(cell);
    }
    private void writeSale(Document documentPDF, Sale sale) {
        documentPDF.add(new Paragraph("Venta: #" + sale.getIdSale().toString()));
        documentPDF.add(new Paragraph("Dia: " + dateFormat.format(sale.getDateSale())));
        documentPDF.add(new Paragraph("Total: " + sale.getTotal().toString()));

        PdfPTable table = new PdfPTable(5);
        table.setWidthPercentage(100);
        table.setSpacingBefore(10);

        writeTableHeader(table);
        writeTableData(table, sale.getDetailSaleList());
        documentPDF.add(table);
    }
    private void writeTableData(PdfPTable table, List<DetailSale> detailSaleList) {
        if (detailSaleList == null) return;
        for (DetailSale detailSale : detailSaleList) {
            DetailSalePDF detailSalePDF = new DetailSalePDF(detailSale);

            table.addCell(detailSalePDF.getCodProduct());
            table.addCell(detailSalePDF.getDescription());
            table.addCell(String.valueOf(detailSalePDF.getUnitPrice()));
            table.addCell(String.valueOf(detailSalePDF.getCount()));
            table.addCell(String.valueOf(detailSalePDF.getSubTotal()));
        }
    }

}
