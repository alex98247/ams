package com.ams.integration;

import com.ams.integration.dto.LegalEntityDetails;

import java.util.List;

/**
 * Responsible for search legal entity information.
 *
 * @author Alexey Mironov
 */
public interface LegalEntityIntegrationService {

    /**
     * Get legal entity info by inn.
     *
     * @param ctx - context includes parameters of legal entity
     * @return legal entiti
     */
    LegalEntityDetails searchLegalEntityByInn(LegalEntityContext ctx);

    /**
     * Find legal entities for full text query.
     *
     * @param ctx - context includes parameters of legal entity
     * @return legal entities
     */
    List<LegalEntityDetails> suggestLegalEntity(LegalEntityContext ctx);
}
