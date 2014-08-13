package fr.xebia.blog.service.dtos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created by romainn on 12/08/2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Query implements Serializable {

    @JsonProperty
    private Integer count;

    @JsonProperty
    private Results results;

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Results getResults() {
        return results;
    }

    public void setResults(Results results) {
        this.results = results;
    }
}
