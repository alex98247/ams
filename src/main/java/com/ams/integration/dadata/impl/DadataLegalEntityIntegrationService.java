package com.ams.integration.dadata.impl;

import com.ams.integration.LegalEntityContext;
import com.ams.integration.LegalEntityIntegrationService;
import com.ams.integration.dadata.impl.mapper.DadataMapper;
import com.ams.integration.dadata.impl.ro.request.DadataLegalEntityByInnRequest;
import com.ams.integration.dadata.impl.ro.request.DadataLegalEntitySuggestRequest;
import com.ams.integration.dadata.impl.ro.response.DadataLegalEntityByInnResponse;
import com.ams.integration.dadata.impl.ro.response.DadataLegalEntitySuggestResponse;
import com.ams.integration.dto.LegalEntityDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Responsible for working with https://dadata.ru/ service.
 *
 * @author Alexey Mironov
 */
@Service
public class DadataLegalEntityIntegrationService implements LegalEntityIntegrationService {
    /**
     * The api key.
     */
    @Value("${integration.dadata.api.key}")
    private String apiKey;
    /**
     * Search by inn url endpoint.
     */
    @Value("${integration.dadata.url.inn}")
    private String findByInnUrl;
    /**
     * Search by query url endpoint.
     */
    @Value("${integration.dadata.url.suggest}")
    private String suggestUrl;
    /**
     * The spring rest template.
     */
    private final RestTemplate restTemplate;

    public DadataLegalEntityIntegrationService(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public LegalEntityDetails searchLegalEntityByInn(LegalEntityContext ctx) {

        DadataLegalEntityByInnRequest dadataRequest = new DadataLegalEntityByInnRequest();
        dadataRequest.setKpp(ctx.getKpp());
        dadataRequest.setCount(ctx.getCount());
        dadataRequest.setQuery(ctx.getQuery());
        dadataRequest.setBranchType(DadataMapper.toDadataBranchType(ctx.getBranchType()));
        dadataRequest.setType(DadataMapper.toDadataLegalEntityType(ctx.getLegalEntityType()));

        ResponseEntity<DadataLegalEntityByInnResponse> response = sendRequest(findByInnUrl, dadataRequest, DadataLegalEntityByInnResponse.class);
        if (response.getStatusCode() != HttpStatus.OK) {
            return null;
        }

        DadataLegalEntityByInnResponse responseBody = response.getBody();
        if (responseBody == null || responseBody.getLegalEntityInfo().isEmpty()) {
            return null;
        }

        return DadataMapper.toLegalEntityDetails(responseBody.getLegalEntityInfo().get(0));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<LegalEntityDetails> suggestLegalEntity(LegalEntityContext ctx) {

        DadataLegalEntitySuggestRequest dadataRequest = new DadataLegalEntitySuggestRequest();
        dadataRequest.setKpp(ctx.getKpp());
        dadataRequest.setCount(ctx.getCount());
        dadataRequest.setQuery(ctx.getQuery());
        dadataRequest.setBranchType(DadataMapper.toDadataBranchType(ctx.getBranchType()));
        dadataRequest.setType(DadataMapper.toDadataLegalEntityType(ctx.getLegalEntityType()));

        ResponseEntity<DadataLegalEntitySuggestResponse> response = sendRequest(suggestUrl, dadataRequest, DadataLegalEntitySuggestResponse.class);
        if (response.getStatusCode() != HttpStatus.OK || response.getBody() == null) {
            return null;
        }
        DadataLegalEntitySuggestResponse responseBody = response.getBody();

        return responseBody.getLegalEntityInfo().stream().map(DadataMapper::toLegalEntityDetails).collect(Collectors.toList());
    }

    private <T, V> ResponseEntity<T> sendRequest(String url, V dadataRequest, Class<T> responseType) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(HttpHeaders.AUTHORIZATION, "Token " + apiKey);

        HttpEntity<V> request = new HttpEntity<>(dadataRequest, headers);
        return restTemplate.exchange(url, HttpMethod.POST, request, responseType, 1);
    }
}
