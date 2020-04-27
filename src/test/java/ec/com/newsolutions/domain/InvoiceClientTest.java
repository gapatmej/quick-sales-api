package ec.com.newsolutions.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ec.com.newsolutions.web.rest.TestUtil;

public class InvoiceClientTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(InvoiceClient.class);
        InvoiceClient invoiceClient1 = new InvoiceClient();
        invoiceClient1.setId(1L);
        InvoiceClient invoiceClient2 = new InvoiceClient();
        invoiceClient2.setId(invoiceClient1.getId());
        assertThat(invoiceClient1).isEqualTo(invoiceClient2);
        invoiceClient2.setId(2L);
        assertThat(invoiceClient1).isNotEqualTo(invoiceClient2);
        invoiceClient1.setId(null);
        assertThat(invoiceClient1).isNotEqualTo(invoiceClient2);
    }
}
