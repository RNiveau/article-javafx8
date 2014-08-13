package fr.xebia.blog.service.dtos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by romainn on 12/08/2014.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Results implements Serializable {

    @JsonProperty
    private List<HistoricQuote> quote;


    public List<HistoricQuote> getQuote() {
        return quote;
    }

    public void setQuote(List<HistoricQuote> quote) {
        this.quote = quote;
    }
}
