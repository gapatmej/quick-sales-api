package ec.com.newsolutions.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ec.com.newsolutions.web.rest.TestUtil;

public class TaxInvoiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaxInvoice.class);
        TaxInvoice taxInvoice1 = new TaxInvoice();
        taxInvoice1.setId(1L);
        TaxInvoice taxInvoice2 = new TaxInvoice();
        taxInvoice2.setId(taxInvoice1.getId());
        assertThat(taxInvoice1).isEqualTo(taxInvoice2);
        taxInvoice2.setId(2L);
        assertThat(taxInvoice1).isNotEqualTo(taxInvoice2);
        taxInvoice1.setId(null);
        assertThat(taxInvoice1).isNotEqualTo(taxInvoice2);
    }
}
