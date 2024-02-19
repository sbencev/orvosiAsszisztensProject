package orvosiAsszisztensSajatFrame;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import orvosiAsszisztensSajatFrame.FelsorolasOsztaly.BetegsegLefolyas;
import orvosiAsszisztensSajatFrame.FelsorolasOsztaly.BetegsegTipus;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FelvitelModositoAblak extends JDialog {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final JPanel contentPanel = new JPanel();
	private JTextField textField;

	private Betegseg betegseg;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbLefolyas;
	@SuppressWarnings("rawtypes")
	private JComboBox cmbTipus;

	private DialogResult dialogresult = DialogResult.CANCEL;

	public DialogResult getDialogresult() {
		return dialogresult;
	}

	public void setDialogresult(DialogResult dialogresult) {
		this.dialogresult = dialogresult;
	}

	public Betegseg getBetegseg() {
		return betegseg;
	}

	public void setBetegseg(Betegseg betegseg) {
		this.betegseg = betegseg;
		textField.setText(betegseg.getMegnevezes());
		cmbLefolyas.setSelectedItem(betegseg.getLefolyas());
		cmbTipus.setSelectedItem(betegseg.getTipus());
		cmbTipus.setEnabled(false);

	}

	/**
	 * Create the dialog.
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public FelvitelModositoAblak() {
		setModal(true);
		setTitle("Adatlap");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 20);
		contentPanel.setLayout(new FlowLayout());
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		{
			JButton btnMentes = new JButton("Mentes");
			btnMentes.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					ellenorzes();

				}
			});
			btnMentes.setBounds(81, 206, 84, 23);
			getContentPane().add(btnMentes);
			btnMentes.setActionCommand("OK");
			getRootPane().setDefaultButton(btnMentes);
		}
		{
			JButton btnMegsem = new JButton("Megsem");
			btnMegsem.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {

					dispose();

				}
			});
			btnMegsem.setBounds(279, 206, 84, 23);
			getContentPane().add(btnMegsem);
			btnMegsem.setActionCommand("Cancel");
		}

		textField = new JTextField();
		textField.setBounds(122, 31, 236, 20);
		getContentPane().add(textField);
		textField.setColumns(10);

		JLabel lblMegnevezes = new JLabel("Megnevezes:");
		lblMegnevezes.setBounds(28, 34, 64, 14);
		getContentPane().add(lblMegnevezes);

		cmbLefolyas = new JComboBox();
		cmbLefolyas.setModel(new DefaultComboBoxModel(BetegsegLefolyas.values()));
		cmbLefolyas.setBounds(122, 77, 241, 22);
		getContentPane().add(cmbLefolyas);

		JLabel lblLefolyas = new JLabel("Lefolyas:");
		lblLefolyas.setBounds(28, 81, 64, 14);
		getContentPane().add(lblLefolyas);

		cmbTipus = new JComboBox();
		cmbTipus.setModel(new DefaultComboBoxModel(BetegsegTipus.values()));
		cmbTipus.setBounds(122, 121, 241, 22);
		getContentPane().add(cmbTipus);

		JLabel lblTipus = new JLabel("Tipus:");
		lblTipus.setBounds(28, 125, 64, 14);
		getContentPane().add(lblTipus);
	}

	@SuppressWarnings("static-access")
	private void ellenorzes() {

		if (betegseg == null) {

			if (!textField.getText().isBlank()) {
				betegseg = new Betegseg(textField.getText(), (BetegsegLefolyas) cmbLefolyas.getSelectedItem(),
						(BetegsegTipus) cmbTipus.getSelectedItem());
				dialogresult = DialogResult.OK;
				dispose();
			} else {
				JOptionPane.showMessageDialog(this, "A megnevezes mezot ki kell tolteni!", "Figyelmeztetes",
						JOptionPane.WARNING_MESSAGE);
			}

		} else {

			if (!textField.getText().isBlank()) {

				betegseg.setMegnevezes(textField.getText());
				betegseg.setLefolyas((BetegsegLefolyas) cmbLefolyas.getSelectedItem());
				dialogresult = dialogresult.OK;
				dispose();

			} else {
				JOptionPane.showMessageDialog(this, "A megnevezes mezot ki kell tolteni!", "Figyelmeztetes",
						JOptionPane.WARNING_MESSAGE);
			}

		}

	}

}
