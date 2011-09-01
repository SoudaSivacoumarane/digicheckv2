package com.cpi.sterling.lot.helper;

import java.applet.Applet;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;

import com.cpi.sterling.check.view.CheckView;
import com.cpi.sterling.document.view.DocumentView;
import com.cpi.sterling.lot.exception.LotException;
import com.cpi.sterling.lot.view.LotView;
import com.cs.common.utils.NumberUtil;

public class LotHelper {
	private static final String PARAM_AMOUNT = "importe";
	private static final String PARAM_DATE = "fecha";
	private static final String PARAM_DIVID = "divisa";
	private static final String PARAM_NODOCS = "docs";
	private static final String PARAM_REFERENCE = "referencia";
	private static final String PARAM_SUCID = "sucursal";
	private static final String PARAM_USER = "usuario";

	public LotView createLotViewFromApplet(Applet applet)throws LotException{
		LotView lotView = null;
		try{
			lotView = new LotView();
			lotView.setAmount(NumberUtil.parseDouble(applet.getParameter(PARAM_AMOUNT)));
			lotView.setDate(applet.getParameter(PARAM_DATE));
			lotView.setDivId(NumberUtil.parseInt(applet.getParameter(PARAM_DIVID)));
			lotView.setNoDocs(NumberUtil.parseInt(applet.getParameter(PARAM_NODOCS)));
			lotView.setReference(applet.getParameter(PARAM_REFERENCE));
			lotView.setSucId(NumberUtil.parseInt(applet.getParameter(PARAM_SUCID)));
			lotView.setUser(applet.getParameter(PARAM_USER));
		}catch(Exception exception){
			LotException lotException = new LotException(exception, LotException.LAYER_ACTION, LotException.ACTION_INSERT);
			lotException.printStackTrace(System.out);
			throw lotException;
		}
		return lotView;
	}
	
	public void setChecks(LotView lotView, List<CheckView> arrayChecks, List<Image> arrayImages)throws LotException{
		Iterator<CheckView> checksIterator = null;
		CheckView checkView = null;
		List<DocumentView> documentList = null;
		DocumentView documentView = null;
		int index = 0;
		try{
			lotView.setChekViewList(arrayChecks);
			checksIterator = arrayChecks.iterator();
			while(checksIterator.hasNext()){
				documentList = new ArrayList<DocumentView>();
				checkView = checksIterator.next();
				documentView = convertDocumentView( arrayImages.get(index*2), DocumentView.TYPE_FRONT );
				documentList.add(documentView);
				documentView = convertDocumentView( arrayImages.get(index*2+1), DocumentView.TYPE_BACK );
				documentList.add(documentView);
				checkView.setDocumentList(documentList);
				index++;
			}
		}catch(LotException lotException){
			throw lotException;
		}catch(Exception exception){
			LotException lotException = new LotException(exception, LotException.LAYER_ACTION, LotException.ACTION_INSERT);
			lotException.printStackTrace(System.out);
			throw lotException;
		}
	}

	public DocumentView convertDocumentView(Image image,int documentType) throws LotException{
		ByteArrayOutputStream byteArrayOutputStream = null;
		BufferedImage bufferedImage = null;
		DocumentView documentView = null;
		try{
			byteArrayOutputStream = new ByteArrayOutputStream();
			bufferedImage = new BufferedImage(image.getWidth(null), image.getHeight(null),BufferedImage.TYPE_INT_RGB);
			bufferedImage.createGraphics().drawImage(image, 0, 0, null);
			ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
			byteArrayOutputStream.close();
			documentView = new DocumentView();
			documentView.setDocTypeId(documentType);
			documentView.setFile(byteArrayOutputStream.toByteArray());
		}catch(Exception exception){
			LotException lotException = new LotException(exception, LotException.LAYER_ACTION, LotException.ACTION_INSERT);
			lotException.printStackTrace(System.out);
			throw lotException;
		}
		return documentView;
	}
}