package avitepa.foundation.bank2.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;

//@ControllerAdvice
@RestControllerAdvice
public class MyControllerAdvice {

	
	@ExceptionHandler(CustomerNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	
	MyExceptionFormat CustomerNotFoundException(CustomerNotFoundException exception, HttpServletRequest req) {
		MyExceptionFormat response = new MyExceptionFormat();
		response.setError(exception.getMessage());
		response.setUrl(req.getRequestURI());
		
		return response;
	}
	
	
	@ExceptionHandler(AccountTypeSameException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
	
	MyExceptionFormat AccountTypeSameException(AccountTypeSameException exception, HttpServletRequest req) {
		MyExceptionFormat response = new MyExceptionFormat();
		response.setError(exception.getMessage());
		response.setUrl(req.getRequestURI());
		
		return response;
	}
	
	@ExceptionHandler(AccountNotFoundException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
//	@ResponseBody
	MyExceptionFormat AccountNotFoundException(AccountNotFoundException exception, HttpServletRequest req) {
		MyExceptionFormat response = new MyExceptionFormat();
		response.setError(exception.getMessage());
		response.setUrl(req.getRequestURI());
		
		return response;
	}
	
	@ExceptionHandler(AccountException.class)
	@ResponseStatus(value = HttpStatus.NOT_FOUND)
//	@ResponseBody
	MyExceptionFormat AccountNotFoundException(AccountException exception, HttpServletRequest req) {
		MyExceptionFormat response = new MyExceptionFormat();
		response.setError(exception.getMessage());
		response.setUrl(req.getRequestURI());
		
		return response;
	}
	
	
}
