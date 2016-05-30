package Utilitaire;

import Enum.TypeCouleur;

public enum MotifsReconnus {

	estDeuxFoisDeuxLibreLibre {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estDeuxFoisDeuxLibreLibre(couleur);
		}
	},
	
	estTroisFoisTrois {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estTroisFoisTrois(couleur);
		}
	},
	
	estTroisFoisDeux {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estTroisFoisDeux(couleur);
		}
	},
	
	estTroisLibre {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estTroisLibre(couleur);
		}
	},	
	
	estDeuxFoisUnLibreLibre {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estDeuxFoisUnLibreLibre(couleur);
		}
	},
	
	estDeuxFoisUnLibre {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estDeuxFoisUnLibre(couleur);
		}
	},
	
	estUnFoisUnLibreLibre {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estUnFoisUnLibreLibre(couleur);
		}
	},
	
	estDeuxLibreLibre {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estDeuxLibreLibre(couleur);
		}
	},
	
	estDeuxFoisUn {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estDeuxFoisUn(couleur);
		}
	},
	
	estUnFoisUnLibre {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estUnFoisUnLibre(couleur);
		}
	},
	
	estUnLibreLibre {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estUnLibreLibre(couleur);
		}
	},
	
	estUnFoisUn {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estUnFoisUn(couleur);
		}
	},
	
	estDeux {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estDeux(couleur);
		}
	},
	
	estDeuxLibre {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estDeuxLibre(couleur);
		}
	}, 
	estTroisFoisTroisLibre {
		@Override
		public boolean test(Motif motif, TypeCouleur couleur) {
			return motif.estTroisFoisTroisLibre(couleur);
		}
	};
	

	
	
	
	public abstract boolean test(Motif motif, TypeCouleur couleur);
}
