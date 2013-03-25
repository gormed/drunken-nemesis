package com.reactplane.gui;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

import TUIO.TuioObject;

/**
 * @author Hans
 * @version 1.0
 * @created 25-Mrz-2013 09:54:25
 */
public class TuioInputObject extends TuioObject {

	private Shape square;

	public TuioInputObject(TuioObject tobj) {
		super(tobj);
		int size = TuioInputListener.object_size;
		square = new Rectangle2D.Float(-size/2,-size/2,size,size);

		AffineTransform transform = new AffineTransform();
		transform.translate(xpos,ypos);
		transform.rotate(angle,xpos,ypos);
		square = transform.createTransformedShape(square);
	}

	public void paint(Graphics2D g, int width, int height) {

		float Xpos = xpos*width;
		float Ypos = ypos*height;
		float scale = height/(float)TuioInputListener.table_size;

		AffineTransform trans = new AffineTransform();
		trans.translate(-xpos,-ypos);
		trans.translate(Xpos,Ypos);
		trans.scale(scale,scale);
		Shape s = trans.createTransformedShape(square);

		g.setPaint(Color.black);
		g.fill(s);
		g.setPaint(Color.white);
		g.drawString(symbol_id+"",Xpos-10,Ypos);
	}

	public void update(TuioObject tobj) {

		float dx = tobj.getX() - xpos;
		float dy = tobj.getY() - ypos;
		float da = tobj.getAngle() - angle;

		if ((dx!=0) || (dy!=0)) {
			AffineTransform trans = AffineTransform.getTranslateInstance(dx,dy);
			square = trans.createTransformedShape(square);
		}

		if (da!=0) {
			AffineTransform trans = AffineTransform.getRotateInstance(da,tobj.getX(),tobj.getY());
			square = trans.createTransformedShape(square);
		}

		super.update(tobj);
	}

}