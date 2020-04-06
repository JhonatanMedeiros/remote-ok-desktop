package remoteokdesktop.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RemoteOkJob {
    @JsonProperty("id")
    private Long id;
    @JsonProperty("epoch")
    private Long epoch;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("company")
    private String company;
    @JsonProperty("company_logo")
    private String companyLogoUrl;
    @JsonProperty("tags")
    private List<String> tags;
    @JsonProperty("slug")
    private String slug;
    @JsonProperty("logo")
    private String logoUrl;
    @JsonProperty("description")
    private String description;
    @JsonProperty("url")
    private String url;
    @JsonProperty("position")
    private String position;
}
