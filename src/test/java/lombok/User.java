package lombok;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
        private String id;
        private String name;
        private String job;
        private String createdAt;
        private String updatedAt;
    }
