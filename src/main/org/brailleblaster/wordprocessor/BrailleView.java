package org.brailleblaster.wordprocessor;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormAttachment;

class BrailleView {

StyledText view;

BrailleView (Shell documentWindow) {
view = new StyledText (documentWindow, SWT.BORDER | SWT.H_SCROLL | SWT.V_SCROLL | SWT.WRAP);
FormData location = new FormData();
location.left = new FormAttachment(56);
location.right = new FormAttachment(100);
location.top = new FormAttachment (12);
location.bottom = new FormAttachment(92);
view.setLayoutData (location);
view.setText ("Braille view");
}

}

