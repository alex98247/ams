package com.ams.integration;

import com.ams.integration.dto.LegalEntityBranchType;
import com.ams.integration.dto.LegalEntityType;

/**
 * Context for legal entity search.
 *
 * @author Alexey Mironov
 */
public class LegalEntityContext {
    /**
     * Inn, kpp or query for search.
     */
    private final String query;
    /**
     * The legal entity type.
     */
    private final LegalEntityType legalEntityType;
    /**
     * The branch type (main or branch).
     */
    private final LegalEntityBranchType branchType;
    /**
     * The kpp.
     */
    private final String kpp;
    /**
     * Max count to return.
     */
    private final int count;

    private LegalEntityContext(LegalEntityContextBuilder builder) {
        this.query = builder.query;
        this.legalEntityType = builder.entityType;
        this.branchType = builder.branchType;
        this.kpp = builder.kpp;
        this.count = builder.count;
    }

    public String getQuery() {
        return query;
    }

    public LegalEntityType getLegalEntityType() {
        return legalEntityType;
    }

    public LegalEntityBranchType getBranchType() {
        return branchType;
    }

    public String getKpp() {
        return kpp;
    }

    public int getCount() {
        return count;
    }

    public static LegalEntityContextBuilder builder() {
        return new LegalEntityContextBuilder();
    }

    public static class LegalEntityContextBuilder {
        private LegalEntityType entityType;
        private LegalEntityBranchType branchType;
        private String kpp;
        private int count = 10;
        private String query;

        public LegalEntityContextBuilder entityType(LegalEntityType entityType) {
            this.entityType = entityType;
            return this;
        }

        public LegalEntityContextBuilder branchType(LegalEntityBranchType branchType) {
            this.branchType = branchType;
            return this;
        }

        public LegalEntityContextBuilder kpp(String kpp) {
            this.kpp = kpp;
            return this;
        }

        public LegalEntityContextBuilder count(int count) {
            this.count = count;
            return this;
        }

        public LegalEntityContextBuilder query(String query) {
            this.query = query;
            return this;
        }

        public LegalEntityContext build() {
            return new LegalEntityContext(this);
        }
    }

}
