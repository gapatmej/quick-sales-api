package ec.com.newsolutions.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ec.com.newsolutions.web.rest.TestUtil;

public class PayWayTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(PayWay.class);
        PayWay payWay1 = new PayWay();
        payWay1.setId(1L);
        PayWay payWay2 = new PayWay();
        payWay2.setId(payWay1.getId());
        assertThat(payWay1).isEqualTo(payWay2);
        payWay2.setId(2L);
        assertThat(payWay1).isNotEqualTo(payWay2);
        payWay1.setId(null);
        assertThat(payWay1).isNotEqualTo(payWay2);
    }
}
