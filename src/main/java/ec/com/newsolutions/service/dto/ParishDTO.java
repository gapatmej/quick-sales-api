package ec.com.newsolutions.service.dto;

public class ParishDTO extends AbstractMainDTO{

    private String code;

    private String name;

    private Long cantonId;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getCantonId() {
        return cantonId;
    }

    public void setCantonId(Long cantonId) {
        this.cantonId = cantonId;
    }
}
