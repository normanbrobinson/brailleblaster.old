/* BrailleBlaster Braille Transcription Application
  *
  * Copyright (C) 2010, 2012
  * ViewPlus Technologies, Inc. www.viewplus.com
  * and
  * Abilitiessoft, Inc. www.abilitiessoft.com
  * and
  * American Printing House for the Blind, Inc. www.aph.org
  *
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
  * this program; see the file LICENSE.
  * If not, see
  * http://www.apache.org/licenses/
  *
  * Maintained by John J. Boyer john.boyer@abilitiessoft.com
*/

package org.brailleblaster.printers;

import java.awt.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;

import org.brailleblaster.BBIni;
import org.brailleblaster.localization.LocaleHandler;
import org.brailleblaster.util.Notify;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PrintersManager implements Printable {

    int[] pageBreaks;  // array of page break line positions.

    String[] textLines;
    StringBuffer textToPrint = new StringBuffer();
    final int MAX_CHAR = 90;
    
    LocaleHandler lh = new LocaleHandler();
    
    static Logger logger;
    
    public PrintersManager (String text) {
    	
    	logger = BBIni.getLogger();
    	
        char c = 0;
    	for (int i = 0; i < text.length(); i++) {
        	if ( (c = text.charAt(i)) == 0x0d) {
    			this.textToPrint.append(" ");
        	} else {
        		this.textToPrint.append(c);
        	}
    	}
    }
    
    private void initTextLines() {
    	int numLines = 0;
    	int numChar = 0;
    	int j = 0;
        char c = 0;
        if (textLines == null) {
        	for (j = 0; j < textToPrint.length(); j++) { 
        		if ((++numChar > MAX_CHAR) || (textToPrint.charAt(j) == 0x0a) ){
        			numLines++;
        			numChar = 0;
        		} 
        	}

        	if (c != 0x0a) {
        		textToPrint.append("\n");
        		numLines++;
        	}
        	textLines = new String[numLines];
        	int end = 0;
        	int start = 0;
        	numChar = 0;
        	int i = 0;
            while (i < numLines) {
            	for (end = start; end < textToPrint.length(); end++) {  
            		if (((c=textToPrint.charAt(end)) == 0x0a) || (++numChar > MAX_CHAR)) {
                    	textLines[i++] = textToPrint.substring(start, end);
                    	numChar = 0;
                        start = end + 1;
            		}
            	}
            }
        }
    }

    public int print(Graphics g, PageFormat pf, int pageIndex)
             throws PrinterException {

        Font font = new Font("Serif", Font.PLAIN, 10);
        FontMetrics metrics = g.getFontMetrics(font);
        int lineHeight = metrics.getHeight();

        if (pageBreaks == null) {
            initTextLines();
            int linesPerPage = ((int)(pf.getImageableHeight()/lineHeight)) - 2;
            int numBreaks = (textLines.length-1)/linesPerPage;
            pageBreaks = new int[numBreaks];
            for (int b=0; b<numBreaks; b++) {
                pageBreaks[b] = (b+1)*linesPerPage; 
            }
        }

        if (pageIndex > pageBreaks.length) {
            return NO_SUCH_PAGE;
        }

        /* User (0,0) is typically outside the imageable area, so we must
         * translate by the X and Y values in the PageFormat to avoid clipping
         * Since we are drawing text we
         */
        Graphics2D g2d = (Graphics2D)g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        /* Draw each line that is on this page.
         * Increment 'y' position by lineHeight for each line.
         */
        
        int y = lineHeight; 
        int start = (pageIndex == 0) ? 0 : pageBreaks[pageIndex-1];
        int end   = (pageIndex == pageBreaks.length)
                         ? textLines.length : pageBreaks[pageIndex];
        for (int line=start; line<end; line++) {
            y += lineHeight;
            g.drawString(textLines[line], 36, y);
        }

        /* tell the caller that this page is part of the printed document */
        return PAGE_EXISTS;
    }

    public void printText() {
       	 
         PrinterJob job = PrinterJob.getPrinterJob();
         job.setPrintable(this);
         boolean ok = job.printDialog();
         if (ok) {
             try {
                  job.print();
             } catch (PrinterException ex) {
            	 logger.log(Level.SEVERE, lh.localValue("cannotPrint") + " " + job.getPrintService().getName());
//            	 System.err.println(ex.getMessage());
            	 new Notify(lh.localValue("cannotPrint") + " " + job.getPrintService().getName()) ;
             }
         }
    }
}

