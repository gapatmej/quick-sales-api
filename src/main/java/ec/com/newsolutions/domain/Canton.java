package ec.com.newsolutions.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "canton")
public class Canton extends AbstractMainEntity {

    @Column(name = "code", length = 20, unique = true, nullable = false)
    private String code;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="province_id", nullable = false)
    private Province province;

    @OneToMany(mappedBy = "canton", fetch = FetchType.LAZY)
    private Set<Parish> parishes = new HashSet<>();

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

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Set<Parish> getParishes() {
        return parishes;
    }

    public void setParishes(Set<Parish> parishes) {
        this.parishes = parishes;
    }
}
