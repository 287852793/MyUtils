package com.xiaoer.work;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.PDFRenderer;

import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfDocument;
import com.itextpdf.text.pdf.PdfPage;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.parser.Matrix;


/**
 * pbfbox、itext、pdf2image 横向对比测试
 * 
 * @author pyf
 * @time 2023-09-22 11:02:02
 */
public class Pbf2jpgTtest {
	// test pbfbox
//	public static void main(String[] args) {
//
//		long t1 = System.currentTimeMillis();
//		try {
//			File file = new File("E:\\00.工作\\03.内部合作\\24.表格OCR还原比对\\观湖花园16号楼（报告书扫描件）.pdf");
//			// 加载PDF文档
//			PDDocument document = PDDocument.load(file);
//			// 渲染PDF文档为BufferedImage
//			PDFRenderer pdfRenderer = new PDFRenderer(document);
//			for (int page = 0; page < document.getNumberOfPages(); ++page) {
//				long t = System.currentTimeMillis();
//				BufferedImage image = pdfRenderer.renderImageWithDPI(page, 300); // 300 DPI 高分辨率图像
//				ImageIO.write(image, "jpg", new File("E:\\00.工作\\03.内部合作\\24.表格OCR还原比对\\testpbfbox\\pdfImage_" + (page + 1) + ".jpg")); // 写入到文件，格式为jpg
//				System.out.println("第 " + (page + 1) + "页耗时：" + (System.currentTimeMillis() - t));
//			}
//			document.close(); // 关闭文档
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		System.out.println("总耗时：" + (System.currentTimeMillis() - t1));
//	}

	// test itext
	public static void main(String[] args) {
//		String pdfPath = "input.pdf"; // 输入的PDF文件路径  
//        String outputPath = "output"; // 输出的JPG文件路径  
//        int dpi = 300; // 分辨率，dpi为dots per inch，即每英寸的点数  
//        int width = -1; // 输出图片的宽度，如果为-1，则自动计算  
//        int height = -1; // 输出图片的高度，如果为-1，则自动计算  
//        boolean useLosslessCompression = true; // 是否使用无损压缩  
//        FontProvider fontProvider = new FontProvider();  
//        FontSet fontSet = new FontSet();  
//        Font font = new Font(FontProgramCollection.GENERIC, 12);  
//        fontSet.addFont(font);  
//        fontProvider.addFontSet(fontSet);  
//        try (PdfReader reader = new PdfReader(pdfPath);  
//             PdfWriter writer = new PdfWriter(new FileOutputStream(outputPath + ".pdf"));  
//             PdfDocument pdfDoc = new PdfDocument(reader, writer)) {  
//            Document document = new Document(pdfDoc, PageSize.A4);  
//            Rectangle pageSize = document.getPdfDocument().getPageSize(1);  
//            if (width == -1) {  
//                width = (int) pageSize.getWidth() * dpi / 72;  
//            }  
//            if (height == -1) {  
//                height = (int) pageSize.getHeight() * dpi / 72;  
//            }  
//            Image image = new Image(ImageDataFactory.create(document.getPdfDocument().getPageN(1), dpi, dpi, useLosslessCompression));  
//            image.setScale(UnitValue.createPercentValue(100));  
//            document.add(image);  
//            document.close();  
//        } catch (Exception e) {  
//            e.printStackTrace();  
//        } finally {  
//            if (fontProvider != null) {  
//                fontProvider.dispose();  
//            }  
//        } 
	}
}
