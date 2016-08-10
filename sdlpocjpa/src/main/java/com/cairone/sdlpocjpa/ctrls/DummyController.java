package com.cairone.sdlpocjpa.ctrls;

import java.security.Principal;
import java.util.Date;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cairone.sdlpocjpa.dtos.EchoDto;

@RestController 
public class DummyController {

	@RequestMapping("/odata/echo")
	public EchoDto echo(@AuthenticationPrincipal Principal principal) {
		return new EchoDto(principal.getName(), new Date().getTime());
	}
}
