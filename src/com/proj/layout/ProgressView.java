package com.proj.layout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;

public class ProgressView extends View {

	private ShapeDrawable mDrawable;

	public ProgressView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub

		int x = 10;
		int y = 10;
		int width = 300;
		int height = 50;

		mDrawable = new ShapeDrawable(new RectShape());
		mDrawable.getPaint().setColor(0xff74AC23);
		mDrawable.setBounds(x, y, x + width, y + height);
	}

	protected void onDraw(Canvas canvas) {
		mDrawable.draw(canvas);
	}

}
