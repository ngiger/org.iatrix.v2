/* Copyright (c) 2016, Niklaus Giger and Elexis.
 * All rights reserved. This program and the accompanying materials
 * are made available under the terms of the Eclipse Public License v1.0
 * which accompanies this distribution, and is available at
 * http://www.eclipse.org/legal/epl-v10.html
 *
 * Contributors:
 *     Niklaus Giger - initial API and implementation
 *
 * Sponsors:
 *     Dr. Peter Schönbucher, Luzern
 ******************************************************************************/
package org.iatrix.perspective;

import org.eclipse.ui.IFolderLayout;
import org.eclipse.ui.IPageLayout;
import org.eclipse.ui.IPerspectiveFactory;
import org.iatrix.IatrixActivator;
import org.iatrix.messwerte.views.MesswerteView;
import org.iatrix.views.JournalView;
import org.iatrix.views.KonsListView;

import ch.elexis.core.ui.constants.UiResourceConstants;
import ch.elexis.core.ui.views.AUF2;
import ch.elexis.core.ui.views.FaelleView;
import ch.elexis.core.ui.views.FallDetailView;
import ch.elexis.core.ui.views.FallListeView;
import ch.elexis.core.ui.views.KompendiumView;
import ch.elexis.core.ui.views.KonsDetailView;
import ch.elexis.core.ui.views.PatHeuteView;
import ch.elexis.core.ui.views.RezepteView;
import ch.elexis.core.ui.views.TextView;
import ch.elexis.core.ui.views.codesystems.DiagnosenView;
import ch.elexis.core.ui.views.codesystems.LeistungenView;
import ch.elexis.extdoc.views.ExterneDokumente;

/**
 * <p>The KG Iatrix perspective. Here we layout the views that compose the GUI.</p>
 * <p>Derived from the PatientPerspektive</p>
 *
 * @author Niklaus Giger
 */
public class KgIatrixPerspective implements IPerspectiveFactory {

	/**
	 * ID of this perspective.
	 */
	public static final String ID = IatrixActivator.PLUGIN_ID + ".ui.perspective";

	/**
	 * Creates the initial perspective layout.
	 *
	 * @param layout
	 */
	@Override
	public void createInitialLayout(IPageLayout layout) {
		String editorArea = layout.getEditorArea();
		layout.setEditorAreaVisible(false);
		layout.setFixed(false);
		IFolderLayout left =
			layout.createFolder("Links.folder", IPageLayout.LEFT, 0.2f, editorArea); //$NON-NLS-1$
		IFolderLayout main =
			layout.createFolder("Haupt.Folder", IPageLayout.RIGHT, 0.2f, "Links.folder"); //$NON-NLS-1$
		IFolderLayout right =
			layout.createFolder("Rechts.folder", IPageLayout.RIGHT, 0.8f, "Haupt.Folder");

		left.addView(UiResourceConstants.PatientenListeView_ID); // Patienten
		left.addView(PatHeuteView.ID);  // Kons. nach Datum
		IFolderLayout leftcenter =
				layout.createFolder("Links.mitte", IPageLayout.BOTTOM, 0.5f, "Links.folder");
		leftcenter.addView(FaelleView.ID); // Fälle
		IFolderLayout leftbottom =
				layout.createFolder("Links.unten", IPageLayout.BOTTOM, 0.5f, "Links.mitte");
		leftbottom.addView(UiResourceConstants.LaborView_ID);  // Labor

		/* wir sollten haben KG Iatrix, Konsultationen Iatrix, Konsultion, Messwerte Iatrix,
		 * Labor, Script, Interaktionen, Agenda Praxis
		 */
		main.addView(JournalView.ID); // KG Iatrix v2
		main.addView(KonsListView.ID); // Konsultationen
		// main.addView(KonsDetailView.ID);// Konsultation
		//main.addView(MesswerteView.ID);
		// main.addView(UiResourceConstants.LaborView_ID);
		main.addPlaceholder("ch.elexis.scriptsView"); // Script, falls vorhanden
		// Interaktionen. Welche View ID??
		main.addPlaceholder("ch.elexis.agenda.largeview"); // Agenda gross, falls vorhanden
		main.addPlaceholder(FallDetailView.ID);
		main.addPlaceholder(TextView.ID);
		main.addPlaceholder(KompendiumView.ID);


		/* 3 Fenster, Fälle, dann
		 * AUF, Rezepte,
		 * mindestens 4 Reiter mit externen Dokumenten?
		 */
		right.addView(FallListeView.ID); // Fälle und kons
		IFolderLayout rightcenter =
				layout.createFolder("Rechts.mitte", IPageLayout.BOTTOM, 0.2f, "Rechts.folder");
		rightcenter.addView(AUF2.ID);  // UAF
		rightcenter.addView(RezepteView.ID); // Rezepte
		rightcenter.addView(ExterneDokumente.ID); // Externe Dokumente v2

		IFolderLayout rightbottom =
				layout.createFolder("Rechts.unten", IPageLayout.BOTTOM, 0.4f, "Rechts.mitte");
		rightbottom.addView(KonsDetailView.ID);// Konsultation

		/**
		 * Ganz unten kommt der Balken mit den Knöpfen
		 *   "Show View as Fast View", Leistungen und Diagnose
		 */
		layout.addFastView(LeistungenView.ID, 0.5f);
		layout.addFastView(DiagnosenView.ID, 0.5f);


		/**
		 * Add some shortcuts for Iatrix-Views
		 */
		layout.addShowViewShortcut(KonsListView.ID);
		layout.addShowViewShortcut(FallListeView.ID);
		layout.addShowViewShortcut(ExterneDokumente.ID);
		layout.addShowViewShortcut(MesswerteView.ID);
	}
}
