/* BrailleBlaster Braille Transcription Application
  *
  * Copyright (C) 2010, 2012
  * ViewPlus Technologies, Inc. www.viewplus.com
  * and
  * Abilitiessoft, Inc. www.abilitiessoft.com
  * All rights reserved
  *
  * This file may contain code borrowed from files produced by various 
  * Java development teams. These are gratefully acknoledged.
  *
  * This file is free software; you can redistribute it and/or modify it
  * under the terms of the Apache 2.0 License, as given at
  * http://www.apache.org/licenses/
  *
  * This file is distributed in the hope that it will be useful, but
  * WITHOUT ANY WARRANTY; without even the implied warranty of
  * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE
  * See the Apache 2.0 License for more details.
  *
  * You should have received a copy of the Apache 2.0 License along with 
  * this program; see the file LICENSE.txt
  * If not, see
  * http://www.apache.org/licenses/
  *
  * Maintained by John J. Boyer john.boyer@abilitiessoft.com
*/

package org.brailleblaster.settings;

import org.eclipse.swt.*;
import org.brailleblaster.util.Notify;
import org.brailleblaster.localization.LocaleHandler;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.MessageBox;
import org.brailleblaster.BBIni;

/**
This class displays the welcome screen on startup and gives some 
 * capability to change settings.
 */

public class Welcome {
private String message = "Welcome to BrailleBlaster\n\n"
+ "A trumpet blast for Braille and tactile graphics\n"
+ "A blast of fresh air for those who are suffocating for lack of good "
+ "tactile material\n"
+ "\n\n"
+ "In this release you can type in the left-hand pane (DAISY view)\n"
+ "Then use Translate, followed by SaveAs or EmbossNow\n";

public Welcome() {
Display display = BBIni.getDisplay();
Shell settingsShell = new Shell(display, SWT.DIALOG_TRIM);
MessageBox mb = new MessageBox(settingsShell, SWT.OK);
mb.setText ("WELCOMEE");
mb.setMessage (message);
mb.open();
new SettingsDialog (settingsShell);
settingsShell.dispose();
}

}
