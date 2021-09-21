package br.com.bb.dfe.integration.foregon;

public class ForegonProposalException extends Exception {
    ForegonProposalResponseError errorDetail = null;

    public ForegonProposalException(ForegonProposalResponseError response) {
        super(response.getError());
        this.errorDetail = response;
    }
}
