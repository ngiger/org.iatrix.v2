/*******************************************************************************
 * Copyright (c) 2008, D. Lutz and Elexis
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *    D. Lutz - initial implementation
 *    
 *******************************************************************************/

package org.iatrix.help.wiki;

public interface Constants {
	public static final String CFG_BASE = "org.iatrix.help.wiki";
	
	public static final String CFG_BASE_URL = CFG_BASE + "/base_url";
	public static final String CFG_START_PAGE = CFG_BASE + "/start_page";
	
	public static final String DEFAULT_BASE_URL =
		"https://www.iatrix.org/pmwiki.php/Elexishilfe/Uebersicht";
	public static final String DEFAULT_START_PAGE = "Uebersicht";
}
