package br.com.scd.demo.associated;

public class AssociatedFactory {

	private AssociatedFactory() {
		// prevents instantiation
	}

	public static Associated getInstance(AssociatedEntity associatedEntity) {
		return new Associated(associatedEntity.getId(), associatedEntity.getCpf());
	}
}
