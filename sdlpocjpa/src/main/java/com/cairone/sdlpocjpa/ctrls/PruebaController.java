package com.cairone.sdlpocjpa.ctrls;

import java.security.Principal;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController @PreAuthorize("hasRole('ADMINISTRADOR')")
public class PruebaController {

	 @RequestMapping("/pruebas/PoCService.svc/**")
	public String prueba(@AuthenticationPrincipal Principal principal) {
		return "HOLA";
	}
}
