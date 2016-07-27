package com.cairone.sdlpocjpa.ctrls;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sdl.odata.controller.AbstractODataController;

@RestController @RequestMapping("/odata/PoCService.svc/**")
public class ODataController extends AbstractODataController {

}
