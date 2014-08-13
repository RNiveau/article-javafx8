package fr.xebia.blog.service.dtos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created by romainn on 12/08/2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class YahooResponse implements Serializable {

    @JsonProperty
    private Query query;

    public Query getQuery() {
        return query;
    }

    public void setQuery(Query query) {
        this.query = query;
    }
}
