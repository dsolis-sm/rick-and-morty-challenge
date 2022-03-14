package rick.and.morty.challenge.exception;

public class BadRequestException extends RuntimeException{

	private static final long serialVersionUID = -4419802414013851752L;
	
	public BadRequestException(String message) {
		super(message);
	}

}
