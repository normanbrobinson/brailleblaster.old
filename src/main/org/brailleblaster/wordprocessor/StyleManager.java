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

package org.brailleblaster.wordprocessor;

class StyleManager{
	
    StylePanel sp;
    DocumentManager dm;
	
	public StyleManager(DocumentManager dm){
		this.dm = dm;
        sp = new StylePanel(this);
	}

    void createStyle(String styleName){
    	EditStyle es = new EditStyle(this);
    	es.create(styleName);
    }
    
    void modifyStyle(Style style){
    	EditStyle es = new EditStyle(this);
    	es.modify(style);
    }
    
    void stylePanel(){
    	sp.open();
    }
    
    void readStyleFiles(String styleName){
    	sp.readStyleFiles(styleName);
    }
}
