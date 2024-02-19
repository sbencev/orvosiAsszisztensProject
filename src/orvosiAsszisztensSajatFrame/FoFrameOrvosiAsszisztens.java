package orvosiAsszisztensSajatFrame;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import java.awt.Font;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;

public class FoFrameOrvosiAsszisztens {

	private JFrame frmFoAblak;
	private List<Betegseg> betegsegek;
	private DefaultListModel<Betegseg> listModel;
	private JList<Betegseg> lstBetegsegek;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FoFrameOrvosiAsszisztens window = new FoFrameOrvosiAsszisztens();
					window.frmFoAblak.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FoFrameOrvosiAsszisztens() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFoAblak = new JFrame();
		frmFoAblak.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				Object[] opciok = { "Igen", "Nem" };
				int kilepes = JOptionPane.showOptionDialog(frmFoAblak, "Biztos ki akar lepni?", "Kilepes",
						JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciok, opciok[1]);

				if (kilepes == JOptionPane.YES_OPTION) {

					FajlKezeles.fajlKiiras("betegsegek.csv", ";", betegsegek);
					System.exit(0);

				}

			}
		});
		frmFoAblak.setTitle("Orvosi asszisztens");
		frmFoAblak.setBounds(100, 100, 450, 508);
		frmFoAblak.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frmFoAblak.getContentPane().setLayout(null);

		betegsegek = new ArrayList<Betegseg>();
		FajlKezeles.fajlBeolvasas("betegsegek.csv", ";", betegsegek);

		listModel = new DefaultListModel<>();
		listaModellhezRendelese();

		lstBetegsegek = new JList<Betegseg>();
		lstBetegsegek.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lstBetegsegek.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		lstBetegsegek.setBounds(10, 11, 307, 292);
		frmFoAblak.getContentPane().add(lstBetegsegek);

		lstBetegsegek.setModel(listModel);

		JButton btnFelvitel = new JButton("Új adat");
		btnFelvitel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				adatfelvitel();

			}
		});
		btnFelvitel.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnFelvitel.setBounds(10, 314, 307, 40);
		frmFoAblak.getContentPane().add(btnFelvitel);

		JButton btnModosit = new JButton("Módosítás");
		btnModosit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				adatmodositas();

			}
		});
		btnModosit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnModosit.setBounds(10, 365, 307, 40);
		frmFoAblak.getContentPane().add(btnModosit);

		JButton btnTorles = new JButton("Törlés");
		btnTorles.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				adattorles();

			}
		});
		btnTorles.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnTorles.setBounds(10, 416, 307, 40);
		frmFoAblak.getContentPane().add(btnTorles);

		JButton btnNewButton = new JButton("Kilépés");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				frmFoAblak.dispatchEvent(new WindowEvent(frmFoAblak, WindowEvent.WINDOW_CLOSING));

			}
		});
		btnNewButton.setBackground(new Color(255, 102, 102));
		btnNewButton.setBounds(335, 427, 89, 23);
		frmFoAblak.getContentPane().add(btnNewButton);
	}

	private void listaModellhezRendelese() {

		for (Betegseg betegseg : betegsegek) {
			
			listModel.addElement(betegseg);
			
		}
		
	}

	private void adatfelvitel() {

		FelvitelModositoAblak felugroAblak = new FelvitelModositoAblak();
		felugroAblak.setVisible(true);

		if (felugroAblak.getDialogresult() == DialogResult.OK) {
			betegsegek.add(felugroAblak.getBetegseg());
			listModel.addElement(felugroAblak.getBetegseg());
		}

	}

	private void adatmodositas() {

		if (lstBetegsegek.getSelectedIndex() != -1) {

			FelvitelModositoAblak felugroAblak = new FelvitelModositoAblak();
			felugroAblak.setBetegseg((Betegseg) lstBetegsegek.getSelectedValue());
			felugroAblak.setVisible(true);

			if (felugroAblak.getDialogresult() == DialogResult.OK) {

				listModel.set(lstBetegsegek.getSelectedIndex(), felugroAblak.getBetegseg());

			}

		} else {
			JOptionPane.showMessageDialog(frmFoAblak, "Nincs kivalasztva elem!", "Figyelmeztetes",
					JOptionPane.WARNING_MESSAGE);
		}

	}

	private void adattorles() {

		if (lstBetegsegek.getSelectedIndex() != -1 && JOptionPane.showOptionDialog(frmFoAblak,
				"Biztosan torli a kijelolt elemet?", "Torles", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, null, null) == JOptionPane.YES_OPTION) {

			betegsegek.remove(lstBetegsegek.getSelectedIndex());
			listModel.remove(lstBetegsegek.getSelectedIndex());

		} else if (lstBetegsegek.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(frmFoAblak, "Nincs kivalasztva elem!", "Figyelmeztetes",
					JOptionPane.WARNING_MESSAGE);
		}
	}

}
