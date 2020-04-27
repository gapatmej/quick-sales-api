package ec.com.newsolutions.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import ec.com.newsolutions.web.rest.TestUtil;

public class BranchOfficeTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(BranchOffice.class);
        BranchOffice branchOffice1 = new BranchOffice();
        branchOffice1.setId(1L);
        BranchOffice branchOffice2 = new BranchOffice();
        branchOffice2.setId(branchOffice1.getId());
        assertThat(branchOffice1).isEqualTo(branchOffice2);
        branchOffice2.setId(2L);
        assertThat(branchOffice1).isNotEqualTo(branchOffice2);
        branchOffice1.setId(null);
        assertThat(branchOffice1).isNotEqualTo(branchOffice2);
    }
}
