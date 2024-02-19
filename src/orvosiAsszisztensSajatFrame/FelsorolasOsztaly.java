package orvosiAsszisztensSajatFrame;

public class FelsorolasOsztaly {

	public enum BetegsegLefolyas {

		ENYHE("enyhe"), HEVENY("heveny"), VESZELYES("veszelyes");

		private final String lefolyasMegnevezes;

		private BetegsegLefolyas(String lefolyasMegnevezes) {
			this.lefolyasMegnevezes = lefolyasMegnevezes;
		}

		public String toString() {
			return lefolyasMegnevezes;
		}

		public static BetegsegLefolyas megnevezesKonvert(String megnevezes) {

			for (BetegsegLefolyas lefolyas : BetegsegLefolyas.values()) {

				if (lefolyas.lefolyasMegnevezes.equals(megnevezes)) {

					return lefolyas;

				}

			}
			throw new IllegalArgumentException("Nem letezo tipus!");
		}

	}

	public enum BetegsegTipus {

		VIRUS("virus"), BAKTERIUM("bakterium");

		public final String tipusMegnevezes;

		private BetegsegTipus(String tipusMegnevezes) {
			this.tipusMegnevezes = tipusMegnevezes;
		}

		public String toString() {
			return tipusMegnevezes;
		}

		public static BetegsegTipus megnevezesKonvert(String megnevezes) {

			for (BetegsegTipus tipus : BetegsegTipus.values()) {

				if (tipus.tipusMegnevezes.equals(megnevezes)) {

					return tipus;

				}

			}
			throw new IllegalArgumentException("Nem letezo tipus!");
		}

	}

}
