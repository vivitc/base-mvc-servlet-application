package com.springapp.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.IOException;
import java.io.InputStream;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;

@Controller
public class HelloController {
    @Autowired
    ServletContext servletContext;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String printWelcome(ModelMap model) {
		model.addAttribute("message", "Hello world!");
		return "hello";
	}

    @RequestMapping(value = "/hello", method = RequestMethod.GET, headers={"Accept=application/xml"})
    @ResponseBody
    public String printWelcomeXml() {
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                "<message>Hello world</message>";
        return xml;
    }
    @RequestMapping( value = "/picture", method = RequestMethod.GET , produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] renderPicture() throws IOException {
        String picturePath = "WEB-INF/pages/image/oso.jpeg";

        return getPicture(picturePath);
    }

    public byte[] getPicture(String path) throws IOException {
        InputStream resourceAsStream;
        resourceAsStream = servletContext.getResourceAsStream(path);

        return IOUtils.toByteArray(resourceAsStream);
    }
}