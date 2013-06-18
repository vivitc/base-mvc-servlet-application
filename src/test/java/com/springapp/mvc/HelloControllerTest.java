package com.springapp.mvc;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.ModelMap;

import javax.servlet.ServletContext;

import java.io.IOException;
import java.io.InputStream;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.spy;

public class HelloControllerTest {
    private ModelMap model;
    @InjectMocks
    HelloController helloController;

    @Mock
    ServletContext servletContext;
    @Mock
    InputStream inputStream;

    @Before
    public void setup() {
        helloController = new HelloController();
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void should_render_to_hello_world(){
        model = new ModelMap();
        String expectedView = "hello";

        String actualView = helloController.printWelcome(model);

        assertThat(actualView, is(expectedView));
    }

    @Test
    public void should_render_to_xml_hello_world(){
        String expectedXml = "<?xml version=\"1.0\" encoding=\"utf-8\"?>" +
                            "<message>Hello world</message>";

        String actualXml = helloController.printWelcomeXml();

        assertThat(actualXml, is(expectedXml));
    }

    @Test

    public void should_render_to_a_picture() throws IOException {
        HelloController spyHelloController = spy(helloController);
        byte[] expectedBytes = "Picture".getBytes();
        String picturePath = "WEB-INF/pages/image/oso.jpeg";

        Mockito.doReturn(expectedBytes).when(spyHelloController).getPicture(eq(picturePath));

        assertThat(spyHelloController.renderPicture(),is(expectedBytes));
    }
}
