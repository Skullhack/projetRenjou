package Utilitaire;

import Enum.TypeCouleur;

public enum MotifsReconnus {

	estDeuxFoisDeuxLibreLibre {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estDeuxFoisDeuxLibreLibre(couleur);
		}
	},
	estDeuxLibreLibre {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estDeuxLibreLibre(couleur);
		}
	},
	estDeuxLibre {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estDeuxLibre(couleur);
		}
	},

	estTroisFoisTroisLibre {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estTroisFoisTroisLibre(couleur);
		}
	},
	estTroisFoisTrois {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estTroisFoisTrois(couleur);
		}
	},
	estTroisFoisDeux {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estTroisFoisDeux(couleur);
		}
	},
	estTroisLibre {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estTroisLibre(couleur);
		}
	},
	estTroisFoisTroisLibreLibre {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estTroisFoisTroisLibreLibre(couleur);
		}
	},
	estTroisLibreLibre {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estTroisLibreLibre(couleur);
		}
	},
	estQuatreFoisTrois {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estQuatreFoisTrois(couleur);
		}
	},
	estQuatreLibre {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estQuatreLibre(couleur);
		}
	},
	estTroisFoisDeuxLibreLibre {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estTroisFoisDeuxLibreLibre(couleur);
		}
	},
	estTroisFoisDeuxLibre {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estTroisFoisDeuxLibre(couleur);
		}
	},
	estDeuxFoisDeuxLibre {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estDeuxFoisDeuxLibre(couleur);
		}
	},
	estDeuxFoisDeux {
		@Override
		public boolean verif(Motif motif, TypeCouleur couleur) {
			return motif.estDeuxFoisDeux(couleur);
		}
	};

	public abstract boolean verif(Motif motif, TypeCouleur couleur);
}
