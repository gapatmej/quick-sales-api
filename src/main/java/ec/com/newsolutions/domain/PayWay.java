package ec.com.newsolutions.domain;

import ec.com.newsolutions.domain.enumeration.PayWaySRIEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "pay_way")
public class PayWay extends AbstractMainEntity {

    @Column(name = "code", length = 20, unique = true, nullable = false)
    private String code;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "pay_way_sri", length = 40, nullable = false)
    private PayWaySRIEnum payWaySRIEnum;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PayWaySRIEnum getPayWaySRIEnum() {
        return payWaySRIEnum;
    }

    public void setPayWaySRIEnum(PayWaySRIEnum payWaySRIEnum) {
        this.payWaySRIEnum = payWaySRIEnum;
    }
}
