package orvosiAsszisztensSajatFrame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.List;

import javax.swing.JOptionPane;

import orvosiAsszisztensSajatFrame.FelsorolasOsztaly.BetegsegLefolyas;
import orvosiAsszisztensSajatFrame.FelsorolasOsztaly.BetegsegTipus;

public class FajlKezeles {

	public static void fajlBeolvasas(String fajlnev, String elvalaszto, List<Betegseg> betegsegek) {

		File fajl = new File(fajlnev);
		if (fajl.exists() && !fajl.isDirectory()) {

			try {
				BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(fajlnev), "UTF-8"));

				while (br.ready()) {

					String[] csvSor = br.readLine().split(elvalaszto);

					betegsegek.add(new Betegseg(csvSor[0], BetegsegLefolyas.megnevezesKonvert(csvSor[1]),
							BetegsegTipus.megnevezesKonvert(csvSor[2])));

				}
				br.close();

			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "I/O hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);
			} catch (IllegalArgumentException e) {
				JOptionPane.showMessageDialog(null, e.getMessage(), "Hiba", JOptionPane.ERROR_MESSAGE);
			}

		}

	}

	public static void fajlKiiras(String fajlnev, String elvalaszto, List<Betegseg> betegsegek) {

		try (OutputStreamWriter out = new OutputStreamWriter(new FileOutputStream(fajlnev), "UTF-8")) {

			for (Betegseg betegseg : betegsegek) {

				out.write(betegseg.getMegnevezes() + elvalaszto);
				out.write(String.valueOf(betegseg.getLefolyas()) + elvalaszto);
				out.write(String.valueOf(betegseg.getTipus()) + "\n");

			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "I/O hiba!", "Hiba", JOptionPane.ERROR_MESSAGE);
		}

	}

}
