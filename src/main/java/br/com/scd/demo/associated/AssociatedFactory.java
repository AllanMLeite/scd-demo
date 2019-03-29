package br.com.scd.demo.associated;

public class AssociatedFactory {

	private AssociatedFactory() {

	}

	public static Associated getInstance(AssociatedEntity associatedEntity) {
		return new Associated(associatedEntity.getId(), associatedEntity.getCpf());
	}
}
