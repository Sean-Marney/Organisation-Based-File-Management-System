package graphium.graphiumteam8.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public class FileControllerTests {

    @Autowired MockMvc mockMvc;

    @Test
    @DisplayName("File upload test")
    void shouldUploadAFile() throws Exception {

        // Creating a mock file to test
        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "test-file.txt",
                "text/plain",
                "Content"
                        .getBytes()
        );
        // MockMvc passes the mock file through the /upload endpoint
        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/upload")
                .file(mockMultipartFile))
                .andExpect(MockMvcResultMatchers.status().isOk()); // Checks status is OK (HTTP response = 200)

    }

    @Test
    @DisplayName("File download test")
    void shouldDownloadAFile(){

        MockMultipartFile mockMultipartFile = new MockMultipartFile(
                "file",
                "test-file.txt",
                "text/plain",
                "Content"
                .getBytes()
        );
        this.mockMvc.perform(MockMvcRequestBuilders.multipart("/download"))
    }
}
