package org.acme;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Base64;

import javax.inject.Inject;

import org.apache.commons.io.FileUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;

import br.com.bb.dfe.integration.foregon.ForegonProposal;
import br.com.bb.dfe.integration.foregon.ForegonProposalException;
import br.com.bb.dfe.integration.foregon.ForegonProposalResponseError;
import br.com.bb.dfe.integration.foregon.ForegonRestApiClient;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class ForegonTest {

    @Inject
    @RestClient
    ForegonRestApiClient api;

    @Test
    public void testHelloEndpoint() throws IOException, ForegonProposalException {
        ClassLoader classLoader = getClass().getClassLoader();
        // InputStream inputStream = classLoader.getResourceAsStream("proposal.json");
        // String data = readFromInputStream(inputStream);
        var prop = makeProposal();
        var token = api.sendProposal(prop);

        System.out.println("\n\nResponse: "+token.getUuid());
    }

    private String readFromInputStream(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }

    private ForegonProposal makeProposal() throws IOException {
        ForegonProposal prpo = new ForegonProposal();
        // prpo.setAcceptSRC("2018-09-29T18:46:19Z");
        prpo.setName("Maria");
        prpo.setCpf("94060102083");
        prpo.setAddress("00000000");
        prpo.setBirthday("14/11/1989");
        // prpo.setCategoryJob("Tecnologia");
        // prpo.setCellphone("61992314383");
        prpo.setCity("São Paulo");
        prpo.setCodepostal("00000000");
        prpo.setComplement("Bloco A - 3 Andar");
        // prpo.setContact("contato");
        // prpo.setContactByEmail("email@teste.com.br");
        // prpo.setContactBySMS("61992314383");
        // prpo.setCpf("abc");
        // prpo.setDeviceFingerPrintID("abc");
        // prpo.setDistrict("DF");
        // // prpo.setDocument("192168");
        // prpo.setDocumentEmitter("SSP/SPF");
        prpo.setDocumentImage(base64("passport.jpg"));
        // prpo.setDocumentNumber("2482360");
        // prpo.setDocumentType("RG");
        // prpo.setDocumentValidForm("00/00/0000");
        prpo.setEmail("email@teste.com.br");
        // prpo.setFathers_name("fulano da silva");
        prpo.setGender("M");
        // prpo.setInvoice_day("01/01/2001");
        // prpo.setJobTitle("Analista");
        // prpo.setMarital_status("Single");
        prpo.setMothers_name("Siclana pereira");
        // prpo.setName("Fulano filho pereira da silva");
        prpo.setNeighborhood("abc");
        prpo.setNumber("3200");
        // prpo.setPhone("61992314383");
        prpo.setScholarity("Pós Graduado");
        prpo.setSelfieImage(base64("selfie.jpg"));
        prpo.setState("SP");
        // prpo.setTimeWorked("700");
        prpo.setType("ALL");
        return prpo;
    }

    private String base64(String resoursePath) throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File inputFile = new File(classLoader.getResource(resoursePath).getFile());

        byte[] fileContent = FileUtils.readFileToByteArray(inputFile);
        String encodedString = Base64.getEncoder().encodeToString(fileContent);

        return "" + encodedString;
    }
}
