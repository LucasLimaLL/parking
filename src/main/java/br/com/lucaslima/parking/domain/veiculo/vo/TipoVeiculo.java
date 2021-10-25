package br.com.lucaslima.parking.domain.veiculo.vo;

/**
 * <p>
 * Enumeration <b>TipoVeiculo</b> responsável por representar o tipo de veículo
 * </p>
 *
 * @author Lucas Lima
 * @since 17/09/2021
 **/
public enum TipoVeiculo {

	CAMINHAO {
		@Override
		public Integer getOrdinal() {
			return 0;
		}
	},
	CARRO {
		@Override
		public Integer getOrdinal() {
			return 1;
		}
	},
	MOTO {
		@Override
		public Integer getOrdinal() {
			return 2;
		}
	};

	public static TipoVeiculo getByOridinal(Integer tipoVeiculo) {
		for (TipoVeiculo tipo : values()) {
			if (tipo.getOrdinal() == tipoVeiculo) {
				return tipo;
			}
		}

		return null;
	}

	public abstract Integer getOrdinal();

	public TipoVeiculo getTipo(Integer ordinal) {
		for (TipoVeiculo tipoVeiculo : values()) {
			if (tipoVeiculo.getOrdinal() == ordinal) {
				return tipoVeiculo;
			}
		}

		return null;
	}
}
