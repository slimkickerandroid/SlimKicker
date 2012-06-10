package com.proj.actions;

import com.markupartist.*;
import com.markupartist.android.widget.ActionBar;
import com.markupartist.android.widget.ActionBar.Action;
import android.content.Intent;
import android.os.Bundle;
import com.proj.layout.*;
import android.view.View;
import android.app.*;

public class DietAction implements Action {
	private Activity parent;
	
	public DietAction(Activity parent) {
		this.parent = parent;
	}
	
    @Override
    public int getDrawable() {
        return R.drawable.action_icon;
    }

    @Override
    public void performAction(View view) {
        Intent diet = new Intent("SearchFood");
	    parent.startActivity(diet);
    }
}