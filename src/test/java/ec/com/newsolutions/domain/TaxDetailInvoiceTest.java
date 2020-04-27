package ec.com.newsolutions.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ec.com.newsolutions.web.rest.TestUtil;

public class TaxDetailInvoiceTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(TaxDetailInvoice.class);
        TaxDetailInvoice taxDetailInvoice1 = new TaxDetailInvoice();
        taxDetailInvoice1.setId(1L);
        TaxDetailInvoice taxDetailInvoice2 = new TaxDetailInvoice();
        taxDetailInvoice2.setId(taxDetailInvoice1.getId());
        assertThat(taxDetailInvoice1).isEqualTo(taxDetailInvoice2);
        taxDetailInvoice2.setId(2L);
        assertThat(taxDetailInvoice1).isNotEqualTo(taxDetailInvoice2);
        taxDetailInvoice1.setId(null);
        assertThat(taxDetailInvoice1).isNotEqualTo(taxDetailInvoice2);
    }
}
