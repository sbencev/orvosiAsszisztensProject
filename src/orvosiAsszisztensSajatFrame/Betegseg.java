package orvosiAsszisztensSajatFrame;

import orvosiAsszisztensSajatFrame.FelsorolasOsztaly.BetegsegLefolyas;
import orvosiAsszisztensSajatFrame.FelsorolasOsztaly.BetegsegTipus;

public class Betegseg {

	private String megnevezes;
	private BetegsegLefolyas lefolyas;
	private BetegsegTipus tipus;

	public Betegseg(String megnevezes, BetegsegLefolyas lefolyas, BetegsegTipus tipus) {
		this.megnevezes = megnevezes;
		this.lefolyas = lefolyas;
		this.tipus = tipus;
	}

	public String getMegnevezes() {
		return megnevezes;
	}

	public void setMegnevezes(String megnevezes) {
		this.megnevezes = megnevezes;
	}

	public BetegsegLefolyas getLefolyas() {
		return lefolyas;
	}

	public void setLefolyas(BetegsegLefolyas lefolyas) {
		this.lefolyas = lefolyas;
	}

	public BetegsegTipus getTipus() {
		return tipus;
	}

	public String toString() {

		return megnevezes + ": lefolyasa: " + lefolyas.toString() + " tipusa: " + tipus.toString();

	}

}
