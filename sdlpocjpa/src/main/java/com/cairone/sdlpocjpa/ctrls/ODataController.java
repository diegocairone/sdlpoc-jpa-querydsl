package com.cairone.sdlpocjpa.ctrls;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sdl.odata.controller.AbstractODataController;

@Controller @RequestMapping("/odata/PoCService.svc/**")
public class ODataController extends AbstractODataController {

}
