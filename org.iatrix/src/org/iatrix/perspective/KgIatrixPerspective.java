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
import org.iatrix.help.wiki.views.WikiView;
import org.iatrix.messwerte.views.MesswerteView;
import org.iatrix.views.JournalView;
import org.iatrix.views.KonsListView;

import ch.elexis.core.ui.constants.UiResourceConstants;
import ch.elexis.core.ui.contacts.views.KontaktDetailView;
import ch.elexis.core.ui.contacts.views.KontakteView;
import ch.elexis.core.ui.contacts.views.PatientDetailView2;
import ch.elexis.core.ui.laboratory.views.LabNotSeenView;
import ch.elexis.core.ui.laboratory.views.LaborView;
import ch.elexis.core.ui.medication.views.DauerMediView;
import ch.elexis.core.ui.medication.views.MedicationView;
import ch.elexis.core.ui.views.AUF2;
import ch.elexis.core.ui.views.BriefAuswahl;
import ch.elexis.core.ui.views.FaelleView;
import ch.elexis.core.ui.views.FallDetailView;
import ch.elexis.core.ui.views.FallListeView;
import ch.elexis.core.ui.views.KompendiumView;
import ch.elexis.core.ui.views.KonsDetailView;
import ch.elexis.core.ui.views.PatHeuteView;
import ch.elexis.core.ui.views.ReminderView;
import ch.elexis.core.ui.views.RezeptBlatt;
import ch.elexis.core.ui.views.RezepteView;
import ch.elexis.core.ui.views.ScriptView;
import ch.elexis.core.ui.views.SearchView;
import ch.elexis.core.ui.views.TextView;
import ch.elexis.core.ui.views.codesystems.DiagnosenView;
import ch.elexis.core.ui.views.codesystems.LeistungenView;
import ch.elexis.core.ui.views.rechnung.AccountView;
import ch.elexis.core.ui.views.rechnung.BillSummary;
import ch.elexis.core.ui.views.rechnung.RechnungsListeView;
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
		IFolderLayout left_top =
			layout.createFolder("Left.folder", IPageLayout.LEFT, 0.2f, editorArea); //$NON-NLS-1$
		IFolderLayout main =
			layout.createFolder("Main.Folder", IPageLayout.RIGHT, 0.2f, "Left.folder"); //$NON-NLS-1$
		IFolderLayout right =
			layout.createFolder("Right.folder", IPageLayout.RIGHT, 0.8f, "Main.Folder");

		left_top.addView(UiResourceConstants.PatientenListeView_ID); // Patienten
		left_top.addView(KontakteView.ID);
		// left_top.addView(ContactSelectorView.ID); // Kontakte aber Icon mit 2 Personen
		// left_top.addView(KontaktDetailView.ID); // Detail zum
		left_top.addView("ch.medshare.directories.views.WeisseSeitenSearchView");
		IFolderLayout left_second =
				layout.createFolder("Left.second", IPageLayout.BOTTOM, 0.25f, "Left.folder");
		left_second.addView("ch.elexis.agenda.largeview"); // or directly?? // Agenda Praxis AgendaGross.ID
		left_second.addView(PatHeuteView.ID);  // Kons. nach Datum
		IFolderLayout left_third =
				layout.createFolder("Left.third", IPageLayout.BOTTOM, 0.33f, "Left.second");
		// TODO:
		// left_second.addView(UiResourceConstants.LaborView_ID);  // Labor neu ??? TODO
		// left_third.addView(LabOrderView.ID);  // Ergibt Labor Verordnungen
		// Labor neu ??? TODO
		left_third.addView(LabNotSeenView.ID); // Labor neu
		// left_third.addView(LaborView.ID); // Labor
		// left_third.addView(LaborblattView.ID); // Laborblatt
		// left_third.addView(UiResourceConstants.LaborView_ID);  // Labor

		IFolderLayout left_fourth =
				layout.createFolder("Left.fourth", IPageLayout.BOTTOM, 0.5f, "Left.third");
		left_fourth.addView(ReminderView.ID);  // Pendenzen

		/* wir sollten haben KG Iatrix, Konsultationen Iatrix, Konsultion, Messwerte Iatrix,
		 * Labor, Script, Interaktionen, Agenda Praxis
		 */
		main.addView(JournalView.ID); // KG Iatrix v2
		main.addView(KonsDetailView.ID);// Konsultation
		main.addView("org.iatrix.v2.views.KonsListView"); // KonsultationenIatrix v2
		// TODO: Labor Roche // non-free software
		main.addView("org.iatrix.messwerte.v2.views.MesswerteView"); // Messwerte Iatrix v2
		// main.addView(MesswerteView.ID);
		main.addView(LaborView.ID); // Labor
		main.addView("ch.elexis.agenda.parallelview"); // TODO: Parallelanzeige
		main.addPlaceholder("ch.elexis.agenda.largeview"); // Agenda Praxis AgendaGross.ID is not exported!

		// TODO: Agenda Praxis
		// main.addView(UiResourceConstants.LaborView_ID);
		main.addPlaceholder(ScriptView.ID); // Script, falls vorhanden
		// Interaktionen. Welche View ID??
		main.addPlaceholder(FallDetailView.ID);
		main.addPlaceholder(TextView.ID);
		main.addPlaceholder(KompendiumView.ID);


		/* 3 Fenster, Fälle, dann
		 * AUF, Rezepte,
		 * mindestens 4 Reiter mit externen Dokumenten?
		 */
		right.addView(FaelleView.ID); // Fälle
		right.addView("ch.elexis.ebanking_ch.ESRView"); // ESRView2 is the old one
		// right.addView(BillSummary.ID); // Falsch: War Rechnungsübersicht
		right.addView(RechnungsListeView.ID);

		IFolderLayout rightcenter =
				layout.createFolder("Right.mitte", IPageLayout.BOTTOM, 0.25f, "Right.folder");
		rightcenter.addView(AUF2.ID);  // AUF
		rightcenter.addView(RezepteView.ID); // Rezepte
		// rightcenter.addView(FixMediDisplay.ID); // falsch: gab keinen Eintrag
		rightcenter.addView(DauerMediView.ID);

		IFolderLayout rightbottom =
				layout.createFolder("Right.unten", IPageLayout.BOTTOM, 0.5f, "Right.mitte");

		rightbottom.addView(SearchView.ID);// TODO: Suche
		rightbottom.addView(ExterneDokumente.ID); // Externe Dokumente v2
		rightbottom.addView(BriefAuswahl.ID);
		rightbottom.addView(TextView.ID); // ch.elexis.TextView"
		rightbottom.addView(FallListeView.ID); // Fälle und kons
		rightbottom.addView(FallDetailView.ID);
		rightbottom.addView(MedicationView.PART_ID);

		/**
		 * Ganz unten kommt der Balken mit den Knöpfen
		 *   "Show View as Fast View", Leistungen und Diagnose
		 */
		layout.addFastView(LeistungenView.ID, 0.5f);
		layout.addFastView(DiagnosenView.ID, 0.5f);
		layout.addFastView(WikiView.ID);
		layout.addFastView(AccountView.ID);
		layout.addFastView(PatientDetailView2.ID);
		layout.addFastView(RechnungsListeView.ID);
		layout.addFastView(BillSummary.ID);
		layout.addFastView(KontaktDetailView.ID);
		// layout.addFastView(BestellView.ID);
		layout.addFastView(RezeptBlatt.ID);
		layout.addFastView(RezepteView.ID);

		/**
		 * Add some shortcuts for Iatrix-Views
		 */
		layout.addShowViewShortcut(KonsListView.ID);
		layout.addShowViewShortcut(FallListeView.ID);
		layout.addShowViewShortcut(ExterneDokumente.ID);
		layout.addShowViewShortcut(MesswerteView.ID);
	}
}
