/**
 * 
 */
package fr.xebia.blog.service.dtos;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Kiva
 * 
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HistoricQuote implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3333460860168856346L;

	@JsonProperty(value = "Date")
	private Date date;

	@JsonProperty
	private Float Open;

	@JsonProperty
	private Float High;

	@JsonProperty
	private Float Low;

	@JsonProperty
	private Float Close;

	@JsonProperty
	private Long Volume;

	@JsonProperty
	private Float Adj_Close;

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the open
	 */
	public Float getOpen() {
		return Open;
	}

	/**
	 * @param open
	 *            the open to set
	 */
	public void setOpen(Float open) {
		Open = open;
	}

	/**
	 * @return the high
	 */
	public Float getHigh() {
		return High;
	}

	/**
	 * @param high
	 *            the high to set
	 */
	public void setHigh(Float high) {
		High = high;
	}

	/**
	 * @return the low
	 */
	public Float getLow() {
		return Low;
	}

	/**
	 * @param low
	 *            the low to set
	 */
	public void setLow(Float low) {
		Low = low;
	}

	/**
	 * @return the close
	 */
	public Float getClose() {
		return Close;
	}

	/**
	 * @param close
	 *            the close to set
	 */
	public void setClose(Float close) {
		Close = close;
	}

	/**
	 * @return the volume
	 */
	public Long getVolume() {
		return Volume;
	}

	/**
	 * @param volume
	 *            the volume to set
	 */
	public void setVolume(Long volume) {
		Volume = volume;
	}

	/**
	 * @return the adj_Close
	 */
	public Float getAdj_Close() {
		return Adj_Close;
	}

	/**
	 * @param adj_Close
	 *            the adj_Close to set
	 */
	public void setAdj_Close(Float adj_Close) {
		Adj_Close = adj_Close;
	}
}
