/**
 *
 */
package br.com.gc.utils;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

/**
 * @author Administrador
 *
 */
public class Componentes {

       /*
        *
        */
	   public String openFiles(){

		   String fileWay = "";

		   JFileChooser fc = new JFileChooser(System
                   .getProperty("user.dir"));
           fc.setMultiSelectionEnabled(false);
           javax.swing.filechooser.FileFilter filter = new javax.swing.filechooser.FileFilter() {
               public boolean accept(File f) {
                   return f.isDirectory()
                           || f.getName().toLowerCase().endsWith(
                                   ".txt");
               }

               public String getDescription() {
                   return "(*.txt)";
               }
           };
           fc.addChoosableFileFilter(filter);
           int returnVal = fc.showDialog(null,"Open");

           if (returnVal == JFileChooser.APPROVE_OPTION) {
        	   fileWay = fc.getSelectedFile().getAbsolutePath();

           } else if (returnVal == JFileChooser.CANCEL_OPTION) {

           } else if (returnVal == JFileChooser.UNDEFINED_CONDITION) {

           }
           return fileWay;
	   }

		/**
		 *
		 */
		public String saveRecordXLS() {
			File file = null;
			JFileChooser fc = null;
			try {
				fc = new JFileChooser(System
	                    .getProperty("user.dir"));
	            fc.setMultiSelectionEnabled(false);
	            FileFilter filter = new FileFilter() {
	                public boolean accept(File f) {
	                    return f.isDirectory()
	                            || f.getName().toLowerCase().endsWith(
	                                    ".xls");
	                }

	                public String getDescription() {
	                    return "(*.xls)";
	                }
	            };

				fc.addChoosableFileFilter(filter);

				int ok = fc.showDialog(null,"Open");

				if (ok == JFileChooser.APPROVE_OPTION) {
					file = fc.getCurrentDirectory();
				} else if (ok == JFileChooser.CANCEL_OPTION) {

		        }

			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro salvando arquivo");
			}
			return fc.getSelectedFile().getAbsolutePath() +".xls";
		}

		/**
		 *
		 */
		public String saveRecordTXT() {
			File file = null;
			JFileChooser fc = null;
			try {
				fc = new JFileChooser(System
	                    .getProperty("user.dir"));
	            fc.setMultiSelectionEnabled(false);
	            FileFilter filter = new FileFilter() {
	                public boolean accept(File f) {
	                    return f.isDirectory()
	                            || f.getName().toLowerCase().endsWith(
	                                    ".txt");
	                }

	                public String getDescription() {
	                    return "(*.txt)";
	                }
	            };

				fc.addChoosableFileFilter(filter);

				int ok = fc.showDialog(null,"Open");

				if (ok == JFileChooser.APPROVE_OPTION) {
					file = fc.getCurrentDirectory();
				} else if (ok == JFileChooser.CANCEL_OPTION) {

		        }
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Erro salvando arquivo");
			}
			return fc.getSelectedFile().getAbsolutePath() +".txt";
		}
}
